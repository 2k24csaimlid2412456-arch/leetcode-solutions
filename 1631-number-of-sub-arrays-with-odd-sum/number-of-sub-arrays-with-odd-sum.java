class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long result = 0;
        
        // evenCount starts at 1 to account for an empty prefix (sum = 0, which is even)
        int evenCount = 1;
        int oddCount = 0;
        int prefixSum = 0;
        
        for (int num : arr) {
            prefixSum += num;
            
            if (prefixSum % 2 == 0) {
                // Even - Odd = Odd
                result = (result + oddCount) % MOD;
                evenCount++;
            } else {
                // Odd - Even = Odd
                result = (result + evenCount) % MOD;
                oddCount++;
            }
        }
        
        return (int) result;
    }
}
