import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // Precompute proper divisors for every possible substring length from 2 to n
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }
        
        // cost[i][j] stores the min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getSemiPalindromeCost(s, i, j, divisors[j - i + 1]);
            }
        }
        
        // dp[p][i] = min changes to partition prefix of length i into p semi-palindromes
        int INF = 1_000_000;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        
        for (int p = 1; p <= k; p++) {
            // Each of the p substrings needs length at least 2, so i >= 2 * p
            for (int i = 2 * p; i <= n; i++) {
                // m is the starting index of the p-th substring
                for (int m = 2 * (p - 1); m <= i - 2; m++) {
                    if (dp[p - 1][m] != INF) {
                        dp[p][i] = Math.min(dp[p][i], dp[p - 1][m] + cost[m][i - 1]);
                    }
                }
            }
        }
        
        return dp[k][n];
    }
    
    private int getSemiPalindromeCost(String s, int start, int end, List<Integer> divs) {
        int len = end - start + 1;
        int minChanges = Integer.MAX_VALUE;
        
        for (int d : divs) {
            int changes = 0;
            // Check each of the d interleaved groups
            for (int rem = 0; rem < d; rem++) {
                int left = start + rem;
                int right = end - d + 1 + rem;
                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }
        
        return minChanges;
    }
}
