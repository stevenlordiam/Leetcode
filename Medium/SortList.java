/*
Sort a linked list in O(n log n) time using constant space complexity.
*/

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
public class SortList {            
    private ListNode findMiddle(ListNode head) { 				// use fast/slow pointers approach to find the middle
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }    

    private ListNode merge(ListNode head1, ListNode head2) { 	// merge sort
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }
        return dummy.next;							// this statement does nothing
    }

    public ListNode sortList(ListNode head) {		// merge sort, sort two half parts
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMiddle(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }
}

/*
Analysis:
- here I use recursion, so the space complexity is O(long(n))
- use slow and fast pointer to find the mid of the list. The trick is set slow=head and fast=head.next, so that slow finally will be the previous node of mid
- set slow.next as the head of second half and slow.next=null.

使用Merge Sort, 空间复杂度是 O(logN) 因为使用了栈空间。
SOLUTION 1:
使用Merge Sort来解决问题。为什么不用QuickSort? 因为随机访问对于链表而言太耗时，而heap sort不可行。
注意，Find Mid用了2种解法。或者是让Fast提前结束，或是让Fast先走一步，目的就是要取得中间节点的前一个。这样做的目的，主要是为了解决：
1->2->null 这一种情况。如果不这么做，slow会返回2.这样我们没办法切割2个Node的这种情况。
SOLUTION 2:
使用快排也可以解决。但是注意，要加一个优化才可以过大数据，就是判断一下是不是整个链条是相同的节点，比如2 2 2 2 2 2 2 ，这样的就直接扫一次不用执行快排，否则它会是N平方的复杂度。
参考资料：https://oj.leetcode.com/discuss/3577/i-use-quick-sort-to-sort-the-list-but-why-i-get-time-limited

Reference:
https://yusun2015.wordpress.com/2015/01/16/sort-list/
http://www.ninechapter.com/solutions/sort-list/
http://www.cnblogs.com/yuzhangcmu/p/4131885.html
*/