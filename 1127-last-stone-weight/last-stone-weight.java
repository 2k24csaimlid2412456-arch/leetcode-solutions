import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Solution {
    public int lastStoneWeight(int[] stones) {
        ArrayList<Integer> st=new ArrayList<>();
   for(int i=0;i<stones.length;i++){
     st.add(stones[i]);
    }
  
    while(st.size()>1){
          Collections.sort(st);
       int x= st.remove(st.size()-1);
       int y= st.remove(st.size()-1);
       if(x-y!=0){
        st.add(x-y);
       }
    }
           
       return st.isEmpty()?0:st.get(0);    
}
}