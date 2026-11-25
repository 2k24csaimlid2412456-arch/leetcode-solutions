import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 131_542_391L;
    private static final long BASE2 = 146_527_931L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 1, right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                right = mid - 1; // Try to find a smaller valid length
            } else {
                left = mid + 1;  // Need a larger length
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        // Precompute BASE^(len - 1) % MOD
        for (int i = 0; i < len - 1; i++) {
            p1 = (p1 * BASE1) % MOD1;
            p2 = (p2 * BASE2) % MOD2;
        }

        Map<Long, Integer> freq = new HashMap<>(n - len + 1);

        for (int i = 0; i < n; i++) {
            // Remove the outgoing leftmost element when window exceeds size `len`
            if (i >= len) {
                long out = nums[i - len];
                h1 = (h1 - (out * p1) % MOD1 + MOD1) % MOD1;
                h2 = (h2 - (out * p2) % MOD2 + MOD2) % MOD2;
            }

            // Add the incoming rightmost element
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;

            // Once we have a full window of size `len`, record its combined 64-bit hash
            if (i >= len - 1) {
                long combinedHash = (h1 << 32) | h2;
                freq.put(combinedHash, freq.getOrDefault(combinedHash, 0) + 1);
            }
        }

        // Check if any subarray hash occurred strictly once
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
