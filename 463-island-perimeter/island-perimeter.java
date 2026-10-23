public class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    return calculatePerimeterDFS(grid, r, c, rows, cols);
                }
            }
        }
        
        return 0;
    }

    private int calculatePerimeterDFS(int[][] grid, int r, int c, int rows, int cols) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return 1;
        }
        
        if (grid[r][c] == 0) {
            return 1;
        }
        
        if (grid[r][c] == 2) {
            return 0;
        }
        
        grid[r][c] = 2;
        
        int perimeter = 0;
        perimeter += calculatePerimeterDFS(grid, r - 1, c, rows, cols);
        perimeter += calculatePerimeterDFS(grid, r + 1, c, rows, cols);
        perimeter += calculatePerimeterDFS(grid, r, c - 1, rows, cols);
        perimeter += calculatePerimeterDFS(grid, r, c + 1, rows, cols);
        
        return perimeter;
    }
}
