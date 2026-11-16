import java.util.*;
class Solution {
    public int findDuplicate(int[] nums) {
    Arrays.sort(nums);
    int i=0;
    int j=1;
    int ans=0;
    while(j!=nums.length){
        if(nums[i]==nums[j]){
            ans = nums[i];
        }
        i++;
        j++;
    }
    return ans ;
    }
}