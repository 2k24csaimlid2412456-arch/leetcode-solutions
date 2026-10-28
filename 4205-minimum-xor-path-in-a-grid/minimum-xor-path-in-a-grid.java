import java.util.Arrays;

public class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][][] dp = new boolean[m][n][1024];
        
        dp[0][0][grid[0][0]] = true;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (dp[i][j][k]) {
                        if (j + 1 < n) {
                            int nextXor = k ^ grid[i][j + 1];
                            dp[i][j + 1][nextXor] = true;
                        }
                        if (i + 1 < m) {
                            int nextXor = k ^ grid[i + 1][j];
                            dp[i + 1][j][nextXor] = true;
                        }
                    }
                }
            }
        }
        
        for (int k = 0; k < 1024; k++) {
            if (dp[m - 1][n - 1][k]) {
                return k;
            }
        }
        
        return -1;
    }
}
