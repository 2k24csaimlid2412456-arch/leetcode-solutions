class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1; // Any single element (or pair) satisfies basic properties, min length is at least 2 per constraints
        
        // Maximum possible value of lcm(1..10) * max_element(10) = 2520 * 10
        int MAX_LIMIT = 25200;

        for (int i = 0; i < n; i++) {
            int prod = 1;
            int currentGcd = 0;
            int currentLcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                
                // Early exit to prevent integer overflow and unnecessary checks
                if (prod > MAX_LIMIT) {
                    break;
                }

                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);

                if (prod == currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
