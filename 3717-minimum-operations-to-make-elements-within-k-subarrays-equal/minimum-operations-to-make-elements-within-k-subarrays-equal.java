import java.util.*;

class Solution {

    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();

    int lowSize = 0;
    int highSize = 0;

    long lowSum = 0;
    long highSum = 0;

    int target;

    void addLow(int val) {
        low.put(val, low.getOrDefault(val, 0) + 1);
        lowSize++;
        lowSum += val;
    }

    void addHigh(int val) {
        high.put(val, high.getOrDefault(val, 0) + 1);
        highSize++;
        highSum += val;
    }

    void removeLow(int val) {
        int count = low.get(val);

        if (count == 1) {
            low.remove(val);
        } else {
            low.put(val, count - 1);
        }

        lowSize--;
        lowSum -= val;
    }

    void removeHigh(int val) {
        int count = high.get(val);

        if (count == 1) {
            high.remove(val);
        } else {
            high.put(val, count - 1);
        }

        highSize--;
        highSum -= val;
    }

    void add(int val) {

        if (low.isEmpty() || val <= low.lastKey()) {
            addLow(val);
        } else {
            addHigh(val);
        }

        balance();
    }

    void remove(int val) {

        if (low.containsKey(val)) {
            removeLow(val);
        } else {
            removeHigh(val);
        }

        balance();
    }
void balance() {

    int target = (lowSize + highSize + 1) / 2;

    while (lowSize > target) {
        int val = low.lastKey();

        removeLow(val);
        addHigh(val);
    }

    while (lowSize < target) {
        int val = high.firstKey();

        removeHigh(val);
        addLow(val);
    }
}

    long getCost() {

        long median = low.lastKey();

        return median * lowSize - lowSum
                + highSum - median * highSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        target = (x + 1) / 2;

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        for (int i = 1; i <= n - x; i++) {

            remove(nums[i - 1]);

            add(nums[i + x - 1]);

            cost[i] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                dp[j][i] = dp[j][i - 1];

                if (i >= x && dp[j - 1][i - x] != INF) {

                    long curr = dp[j - 1][i - x]
                              + cost[i - x];

                    dp[j][i] = Math.min(dp[j][i], curr);
                }
            }
        }

        return dp[k][n];
    }
}