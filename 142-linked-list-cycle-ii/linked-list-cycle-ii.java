/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 import java.util.HashSet;
public class Solution {
    public ListNode detectCycle(ListNode head) {
   HashSet<ListNode> set= new HashSet<>();
       ListNode curr= head;
   while(curr!=null){
    if(set.contains(curr)){
        return curr;
    }else{
        set.add(curr);
        curr=curr.next;
    }
  
   }
   return null;
    }
}