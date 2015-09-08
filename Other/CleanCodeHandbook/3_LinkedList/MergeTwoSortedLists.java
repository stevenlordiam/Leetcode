/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
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
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;          // for linked list, a dummy node is really helpful
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }
        
        if (l1 != null) {
            lastNode.next = l1;
        } else {
            lastNode.next = l2;
        }
        
        return dummy.next;
        
    }
}

/*
Because the two lists are already sorted, we can just compare their values node by node
For linked list, a dummy node is really helpful

Reference:
https://yusun2015.wordpress.com/2015/01/05/merge-two-sorted-lists/
http://blog.csdn.net/linhuanmars/article/details/19712593
https://leetcodenotes.wordpress.com/2013/08/01/merge-two-sorted-lists/
*/