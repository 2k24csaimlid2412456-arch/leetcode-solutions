import java.util.Arrays;

class Solution {
    private static final int MOD = 1000000007;
    
    private long[] sumOfSquares;
    private long[] sumOfElements;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumOfSquares = new long[4 * n];
        sumOfElements = new long[4 * n];
        lazy = new long[4 * n];

        int[] lastSeen = new int[100001];
        Arrays.fill(lastSeen, -1);

        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int prevIdx = lastSeen[nums[j]];
            int startRange = prevIdx + 1;
            int endRange = j;

            update(1, 0, n - 1, startRange, endRange, 1);
            
            totalSum = (totalSum + sumOfSquares[1]) % MOD;
            
            lastSeen[nums[j]] = j;
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0) return;

        long val = lazy[node];
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        apply(leftChild, start, mid, val);
        apply(rightChild, mid + 1, end, val);

        lazy[node] = 0;
    }

    private void apply(int node, int start, int end, long val) {
        long numElements = end - start + 1;
        
        sumOfSquares[node] = (sumOfSquares[node] 
            + 2 * val * sumOfElements[node] % MOD 
            + val * val % MOD * numElements % MOD) % MOD;
            
        sumOfElements[node] = (sumOfElements[node] + val * numElements % MOD) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (r < start || end < l) {
            return;
        }
        if (l <= start && end <= r) {
            apply(node, start, end, val);
            return;
        }

        pushDown(node, start, end);

        int mid = start + (end - start) / 2;
        update(2 * node, start, mid, l, r, val);
        update(2 * node + 1, mid + 1, end, l, r, val);

        sumOfSquares[node] = (sumOfSquares[2 * node] + sumOfSquares[2 * node + 1]) % MOD;
        sumOfElements[node] = (sumOfElements[2 * node] + sumOfElements[2 * node + 1]) % MOD;
    }
}
