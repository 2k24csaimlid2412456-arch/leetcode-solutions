class Solution {
    public boolean checkString(String s) {
        boolean seenB = false;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                seenB = true;
            } else if (seenB) {
                // Found an 'a' after already seeing a 'b'
                return false;
            }
        }
        
        return true;
    }
}
