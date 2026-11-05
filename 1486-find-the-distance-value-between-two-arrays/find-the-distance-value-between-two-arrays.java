import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;
        
        for (int val : arr1) {
            if (isValid(arr2, val, d)) {
                distanceValue++;
            }
        }
        
        return distanceValue;
    }
    
    private boolean isValid(int[] arr2, int val, int d) {
        int left = 0;
        int right = arr2.length - 1;
        int lowerBound = val - d;
        int upperBound = val + d;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr2[mid] >= lowerBound && arr2[mid] <= upperBound) {
                return false; 
            } else if (arr2[mid] < lowerBound) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return true;
    }
}
