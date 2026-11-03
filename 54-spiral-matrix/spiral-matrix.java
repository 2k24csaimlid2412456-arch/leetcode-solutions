import java.util.*;
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

          List <Integer> res= new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
      int top=0;
      int bottom =matrix.length-1;
      int left =0;
      int right=matrix[0].length-1;
    
      while(top<=bottom && left<=right){
        
        for( int i=left;i<=right;i++){
             res.add(matrix[top][i]);
        }
        top++;
        //top to bottom
        for( int j=top;j<=bottom;j++){
            res.add(matrix[j][right]);
        }
        right--;
        if(top<=bottom  ){
            for(int i=right;i>=left;i--){
                res.add(matrix[bottom][i]);
            }
            bottom--;
        }
      
      if(left<=right){
        for(int j=bottom;j>=top;j--){
            res.add(matrix[j][left]);
        }
        left++;
      } 
    }
       return res; 
}
}