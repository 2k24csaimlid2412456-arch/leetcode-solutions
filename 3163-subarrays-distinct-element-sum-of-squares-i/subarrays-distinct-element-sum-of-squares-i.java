class Solution {
    public int sumCounts(List<Integer> nums) {
        int res = 0;
        int n = nums.size(); 
        
        for (int i = 0; i < n; i++) {
            Set<Integer> uniqueElements = new HashSet<>();
            for (int j = i; j < n; j++) {
                uniqueElements.add(nums.get(j)); 
                res += uniqueElements.size() * uniqueElements.size();
            }
        }
        return res;
    }
}
