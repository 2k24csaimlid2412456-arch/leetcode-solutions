import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long totalSubarrays = 0;
        long currentCost = 0;
        
        // Monotonic decreasing deque storing pairs of {maxValue, blockLength}
        Deque<long[]> deque = new ArrayDeque<>();
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            long val = nums[l];
            long count = 1;

            // Merge smaller or equal blocks to the right into the new prefix max block
            while (!deque.isEmpty() && deque.peekLast()[0] <= val) {
                long[] block = deque.pollLast();
                currentCost += (val - block[0]) * block[1];
                count += block[1];
            }
            deque.offerLast(new long[]{val, count});

            // Shrink the window from the right until the cost is within k
            while (currentCost > k && r >= l) {
                long[] frontBlock = deque.peekFirst();
                currentCost -= (frontBlock[0] - nums[r]);
                frontBlock[1]--;
                if (frontBlock[1] == 0) {
                    deque.pollFirst();
                }
                r--;
            }

            // All subarrays starting at l and ending at any index in [l, r] are valid
            totalSubarrays += (r - l + 1);
        }

        return totalSubarrays;
    }
}
