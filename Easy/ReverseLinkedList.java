/*
Reverse a singly linked list.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {	// iterativve
        ListNode newHead = null;
    	while(head != null) {
        	ListNode next = head.next;
        	head.next = newHead;
        	newHead = head;
        	head = next;
    	}
    	return newHead;
    }
}

/*
// recursive
public ListNode reverseList(ListNode head) {
    return reverseListInt(head, null);
}

public ListNode reverseListInt(ListNode head, ListNode newHead) {
    if(head == null)
        return newHead;
    ListNode next = head.next;
    head.next = newHead;
    return reverseListInt(next, head);
}

Reference:
https://leetcode.com/discuss/34474/in-place-iterative-and-recursive-java-solution
https://leetcode.com/discuss/37804/iteratively-and-recursively-java-solution
https://leetcode.com/discuss/34712/recursive-java-solution
http://www.programmerinterview.com/index.php/data-structures/reverse-a-linked-list/
http://www.java2blog.com/2014/07/how-to-reverse-linked-list-in-java.html
*/