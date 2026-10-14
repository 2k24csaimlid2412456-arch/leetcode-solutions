public class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the required sum is greater than the maximum possible sum (all 9s), it's impossible.
        if (sum > 9L * num) { 
            return "";
        }
        
        int count9 = sum / 9;
        int remainder = sum % 9;
        
        StringBuilder sb = new StringBuilder();
        
        // Append all the 9s first to maximize both the square sum and the number value
        for (int i = 0; i < count9; i++) {
            sb.append('9');
        }
        
        // Append the remainder if it's greater than 0
        if (remainder > 0) {
            sb.append((char) ('0' + remainder));
        }
        
        // Fill the remaining digit positions with 0s
        int zeroes = num - count9 - (remainder > 0 ? 1 : 0);
        for (int i = 0; i < zeroes; i++) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}
