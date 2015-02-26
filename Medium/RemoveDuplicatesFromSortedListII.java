/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return head;
        ListNode dummy=new ListNode(0);         // use dummy node as helper, always do this when need to modify the ListNode head
        dummy.next=head;
        ListNode pre=dummy,cur=head;

        while(cur!=null){
            ListNode end=cur.next;
            int n=1;

            while(end!=null&&end.val==cur.val){ // count the number of dups
                end=end.next;
                n++;
            }
            if(n==1) {                          // no dups
                pre=cur;
                cur=cur.next;
            }else{
                pre.next=end;                   // delete dups by jump over cur node
                cur=end;                        // pre -> cur -> end
            }
        }
        return dummy.next;                      // remember to return dummy.next
    }
}

/*
Similar to Remove Duplicates from Sorted List - https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list/

For current node, use another pointer end to count the number of nodes with the same value. 
If count is greater than 1, remove these nodes.

这道题跟Remove Duplicates from Sorted List比较类似，只是这里要把出现重复的元素全部删除。
其实道理还是一样，只是现在要把前驱指针指向上一个不重复的元素中，如果找到不重复元素，则把前驱指针指到该元素，否则删除此元素。
我们创建了一个辅助的头指针，是为了修改链表头的方便。前面介绍过，一般会改到链表头的题目就会需要一个辅助指针，是比较常见的技巧。
算法只需要一遍扫描，时间复杂度是O(n)，空间只需要几个辅助指针，是O(1)

Reference:
https://leetcodenotes.wordpress.com/2013/11/03/leetcode-remove-duplicates-from-sorted-array-1-2-%E4%BB%8E%E6%95%B0%E7%BB%84%E9%87%8Cinplace%E5%88%A0%E9%99%A4%E9%87%8D%E5%A4%8D/
https://yusun2015.wordpress.com/2015/01/14/remove-duplicates-from-sorted-list-ii/
http://blog.csdn.net/linhuanmars/article/details/24389429
*/