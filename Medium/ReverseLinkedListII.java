/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevM = dummy;
        ListNode prev = null;
        ListNode curr = null;
        for (int i = 1; i <= n; i++) {  // only care for n, not care for the length of linked list
            if (i < m) {                // find the node prevM (node: 1-m)
                prevM = prevM.next;
            } else if (i == m) {        // prevM -> prev -> curr (node: m)
                prev = prevM.next;
                curr = prev.next;
            }else {                     // for node m+1 to n
                prev.next = curr.next;  // ???
                curr.next = prevM.next;
                prevM.next = curr;      // insert the curr node after prevM
                curr = prev.next;       // go on to find the next node in list 
            }
        }
        return dummy.next;              // remember to return dummy.next
    }
}

/*
Reverse Linked List - http://www.lintcode.com/zh-cn/problem/reverse-linked-list/
public class Solution {
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
}

这道题是比较常见的链表反转操作，不过不是反转整个链表，而是从m到n的一部分。
分为两个步骤，第一步是找到m结点所在位置，第二步就是进行反转直到n结点。
反转的方法就是每读到一个结点，把它插入到m结点前面位置，然后m结点接到读到结点的下一个。
总共只需要一次扫描，所以时间是O(n)，只需要几个辅助指针，空间是O(1)

比如反转b->c->d：a->b->c->d->e => a->d->c->b->e
三个variable
- prevM：指向反转部分的前一个元素a
- prev：指向已经反转成功的最后一个元素，永远是prevM.next，不需要改动。
- curr：想往prevM后面塞的那个元素。
算法：每次拿出右边的新元素，往prevM后面插，最后curr==不需要反转的section的第一个节点时退出

Reference:
https://leetcodenotes.wordpress.com/2013/10/11/leetcode-reverse-linked-list-2-%E6%8A%8A%E5%8D%95%E9%93%BE%E8%A1%A8%E7%AC%ACm%EF%BD%9En%E4%B8%AA%E7%BB%99inplace%E5%8F%8D%E8%BD%AC%E5%85%B6%E4%BB%96%E4%B8%8D%E5%8F%98/
https://yusun2015.wordpress.com/2015/01/14/reverse-linked-list-ii/
https://github.com/leetcoders/LeetCode-Java/blob/master/ReverseLinkedListII.java
http://blog.csdn.net/linhuanmars/article/details/24613781
http://www.ninechapter.com/solutions/reverse-linked-list-ii/
*/