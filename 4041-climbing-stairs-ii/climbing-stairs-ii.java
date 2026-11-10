public class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        int[] dp = new int[n + 1];
        
     
        dp[0] = 0;
        
        
        for (int j = 1; j <= n; j++) {
            int minCost = Integer.MAX_VALUE;
            
           
            for (int k = 1; k <= 3; k++) {
                int i = j - k;
                if (i >= 0) {
                    // Cost formula: dp[i] + costs[j-1] + (j - i)^2
                    int currentCost = dp[i] + costs[j - 1] + (k * k);
                    minCost = Math.min(minCost, currentCost);
                }
            }
            dp[j] = minCost;
        }
        
        return dp[n];
    }
}
