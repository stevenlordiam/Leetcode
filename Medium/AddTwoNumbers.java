/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
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
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int digit = 0;
        ListNode head = null;
        ListNode pre = null;
        while(l1!=null && l2!=null) {		// adding the digit while l1 and l2 have same length
            digit = (l1.val+l2.val+carry)%10; 			// get the digit
            carry = (l1.val+l2.val+carry)/10;			// get the carry
            ListNode newNode = new ListNode(digit);
            if(head==null)
                head = newNode;
            else
                pre.next = newNode;
            pre = newNode;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1!=null) {					// l1 is longer than l2, after the while in line 25, need to adding the rest of l1: (2->2->2)+(1)=(3->2->2)
            digit = (l1.val+carry)%10;
            carry = (l1.val+carry)/10;
            ListNode newNode = new ListNode(digit);
            if(head==null)
                head = newNode;
            else
                pre.next = newNode;
            pre = newNode;
            l1 = l1.next;            
        }
        while(l2!=null)	{					// l2 is longer than l1, after the while in line 25, need to adding the rest of l2: (1)+(2->2->2)=(3->2->2)
            digit = (l2.val+carry)%10;
            carry = (l2.val+carry)/10;
            ListNode newNode = new ListNode(digit);
            if(head==null)
                head = newNode;
            else
                pre.next = newNode;
            pre = newNode;
            l2 = l2.next;            
        }        
        if(carry>0) {						// additional carry at the end, (9->9)+(1)=(0->0->1)
            ListNode newNode = new ListNode(carry);
            pre.next = newNode;
        }
        return head;
    }
}

/*
REMEMBER mod and divide:
digit = (l1.val+l2.val+carry)%10; 		// get the digit
carry = (l1.val+l2.val+carry)/10;		// get the carry

Keep track of the carry using a variable and simulate digits-by-digits sum from the head of list, 
which contains the least-significant digit.
Take extra caution of the following cases:
- When one list is longer than the other.
- The sum could have an extra carry of one at the end, which is easy to forget. (e.g., (9->9) + (1) = (0->0->1))

Reference:
http://blog.csdn.net/linhuanmars/article/details/19957829
http://www.ninechapter.com/solutions/add-two-numbers/
*/