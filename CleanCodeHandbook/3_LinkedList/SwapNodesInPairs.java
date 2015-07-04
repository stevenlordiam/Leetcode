/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {					// always check this when it's linked list
            return head;
        }
        ListNode point = new ListNode(0);
        point.next = head;
        head = point;
        while(point.next != null && point.next.next != null) {
            ListNode tmp = point.next.next.next;				// tmp = 4	
            point.next.next.next = point.next;					// 4 = 2
            point.next = point.next.next;						// 2 = 1
            point.next.next.next = tmp;							// 4 = tmp
            point = point.next.next;							// 1 = 3
        }
        return head.next;
    }
}

/*
Similar to Reverse Nodes in k-Group - https://leetcode.com/problems/reverse-nodes-in-k-group/

这道题属于链表操作的题目，思路比较清晰，就是每次跳两个节点，后一个接到前面，前一个接到后一个的后面，最后现在的后一个（也就是原来的前一个）接到下下个结点（如果没有则接到下一个）。
这道题中用了一个辅助指针作为表头，这是链表中比较常用的小技巧，因为这样可以避免处理head的边界情况，一般来说要求的结果表头会有变化的会经常用这个技巧，大家以后会经常遇到。
时间复杂度是O(n)，空间复杂度是O(1)。

Code(Iterative):
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        ListNode cur=head,pre=dummy;
        while(cur!=null&&cur.next!=null){
            ListNode temp=cur.next;
            cur.next=temp.next;
            pre.next=temp;
            temp.next=cur;
            pre=cur;
            cur=cur.next;
        }
        return dummy.next;
    }
}

Code(Recursive):
public class Solution {
    public ListNode swapPairs(ListNode head) {
    	if(head==null||head.next==null) return head;
        ListNode temp=head.next;
        head.next=swapPairs(temp.next);
        temp.next=head;
        return temp;
    }
}

Reference:
http://www.ninechapter.com/solutions/swap-nodes-in-pairs/
https://yusun2015.wordpress.com/2015/01/06/swap-nodes-in-pairs/
http://blog.csdn.net/linhuanmars/article/details/19948569
*/