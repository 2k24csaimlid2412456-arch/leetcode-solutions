class Solution {
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int divisibleIslandCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long[] islandSum = new long[1]; 
                    dfs(grid, i, j, m, n, islandSum);
                    
                    if (islandSum[0] % k == 0) {
                        divisibleIslandCount++;
                    }
                }
            }
        }
        
        return divisibleIslandCount;
    }
    
    private void dfs(int[][] grid, int r, int c, int m, int n, long[] islandSum) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] <= 0) {
            return;
        }
        
        islandSum[0] += grid[r][c];
        grid[r][c] = 0; 
        
        dfs(grid, r + 1, c, m, n, islandSum); 
        dfs(grid, r - 1, c, m, n, islandSum); 
        dfs(grid, r, c + 1, m, n, islandSum); 
        dfs(grid, r, c - 1, m, n, islandSum); 
    }
}
