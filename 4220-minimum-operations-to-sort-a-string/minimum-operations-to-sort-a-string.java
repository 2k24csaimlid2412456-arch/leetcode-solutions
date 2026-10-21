class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Step 1: Check if the string is already sorted
        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) return 0;
        
        // Step 2: If length is 2 and not sorted, it's impossible
        if (n == 2) return -1;
        
        // Step 3: Find the global minimum and maximum characters
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
        // Step 4: Check if min is at the start OR max is at the end
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }
        
        // Step 5: Check if min or max exists somewhere in the middle
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == minChar || s.charAt(i) == maxChar) {
                return 2;
            }
        }
        
        // Step 6: Worst case (max is at index 0 and min is at index n-1)
        return 3;
    }
}
