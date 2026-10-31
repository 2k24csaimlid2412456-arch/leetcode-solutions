
class Solution {

    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // -1 means invalid destination
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(right[i], -1);
            java.util.Arrays.fill(down[i], -1);
        }

        // Precompute mirror destinations
        for (int sum = m + n - 2; sum >= 0; sum--) {

            for (int i = 0; i < m; i++) {

                int j = sum - i;

                if (j < 0 || j >= n) continue;

                if (grid[i][j] == 0) {

                    right[i][j] = i * n + j;
                    down[i][j] = i * n + j;

                } else {

                    // Entered mirror while moving right:
                    // Reflect down
                    if (i + 1 < m) {
                        right[i][j] = down[i + 1][j];
                    }

                    // Entered mirror while moving down:
                    // Reflect right
                    if (j + 1 < n) {
                        down[i][j] = right[i][j + 1];
                    }
                }
            }
        }

        long[][] dp = new long[m][n];

        dp[0][0] = 1;

        // Count paths
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) continue;

                // Try moving right
                if (j + 1 < n) {

                    int dest = right[i][j + 1];

                    if (dest != -1) {

                        int x = dest / n;
                        int y = dest % n;

                        dp[x][y] =
                            (dp[x][y] + dp[i][j]) % MOD;
                    }
                }

                // Try moving down
                if (i + 1 < m) {

                    int dest = down[i + 1][j];

                    if (dest != -1) {

                        int x = dest / n;
                        int y = dest % n;

                        dp[x][y] =
                            (dp[x][y] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
