class Solution {
    public double findMaxAverage(int[] nums, int k) {
    int max_sum=0;
    int sum=0;
    for(int i=0;i<k;i++){
        sum+=nums[i];
    }
    max_sum=sum;
    for(int j=k;j<nums.length;j++){
        sum=sum-nums[j-k]+nums[j];
        max_sum=Math.max(max_sum,sum);;
    }
    return (double)max_sum/k;
    }
}