public class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        // Find the index of element 0
        int idxZero = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idxZero = i;
                break;
            }
        }
        
        // Check if the array is a standard cyclic shift
        boolean isCyclic = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (i - idxZero + n) % n) {
                isCyclic = false;
                break;
            }
        }
        
        // Check if the array is a reversed cyclic shift
        boolean isRevCyclic = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (idxZero - i + n) % n) {
                isRevCyclic = false;
                break;
            }
        }
        
        // If it matches neither configuration, it's impossible to sort
        if (!isCyclic && !isRevCyclic) {
            return -1;
        }
        
        int minOps = Integer.MAX_VALUE;
        
        // Calculate min operations for standard cyclic shift
        if (isCyclic) {
            int ops = Math.min(idxZero, 2 + n - idxZero);
            minOps = Math.min(minOps, ops);
        }
        
        // Calculate min operations for reversed cyclic shift
        if (isRevCyclic) {
            int ops = Math.min(idxZero < n - 1 ? idxZero + 2 : 1, n - idxZero);
            minOps = Math.min(minOps, ops);
        }
        
        return minOps;
    }
}
