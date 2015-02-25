/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
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
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null)
            return;
        ListNode p = head;
        int len = 0;
        while (p != null) {						// get the length of list
            len++;
            p = p.next;
        }

        p = head;
        for (int k = 0; k < len / 2; k++) { 	// p points to mid point
            p = p.next;
        } 						

        ListNode curr = p.next, 
        ListNode next = null;
        p.next = null;

        while (curr != null) { 					// reverse last half part of list
            next = curr.next;					// (THIS IS IMPORTANT!!!)
            curr.next = p;
            p = curr;
            curr = next;
        }										// now p points to the last element
        
        ListNode h = head;
        while (h.next != p && h != p) {			// merge two lists (THIS IS IMPORTANT!!!)
            ListNode tempH = h.next;
            ListNode tempP = p.next;
            h.next = p;
            p.next = tempH;
            h = tempH;
            p = tempP;
        }
    }
}

/*
方法一：brute force
过一遍算出总长度len, 再过一遍找到中点或第二部分的第一个点(a->b->c->d->e和a->b->c->d的都是c）
reverse后半部分，变成a->b->c<-d<-e 或者 a->b->c<-d，最后中点c的next置空
两头穿插合并，直到找到c这点。c肯定是一起找到（奇数）或者右半部分先找到（偶数）

1234 5678 -> 1234 8765 -> 1827 3645 
			 h    p

这是一道比较综合的链表操作的题目，要按照题目要求给链表重新连接成要求的结果。其实理清思路也比较简单，分三步完成：
（1）将链表切成两半，也就是找到中点，然后截成两条链表；（2）将后面一条链表进行reverse操作，就是反转过来；
（3）将两条链表按顺序依次merge起来。第一步找中点就是用Linked List Cycle中的fast/slow pointer方法，一个两倍速跑，一个一倍速跑，
知道快的碰到链表尾部，慢的就正好停在中点了。第二步是比较常见的reverse操作，在Reverse Nodes in k-Group也有用到了，
一般就是一个个的翻转过来即可。第三步是一个merge操作，做法类似于Sort List中的merge，只是这里不需要比较元素打小了，
只要按顺序左边取一个，右边取一个就可以了。接下来看看时间复杂度，第一步扫描链表一遍，是O(n)，第二步对半条链表做一次反转，
也是O(n)，第三部对两条半链表进行合并，也是一遍O(n)。所以总的时间复杂度还是O(n)，由于过程中没有用到额外空间，所以空间复杂度O(1)。

// not recursive linked list reverse    (???)
private ListNode reverse(ListNode head)
{
    ListNode pre = null;
    ListNode cur = head;
    while(cur!=null)
    {
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
    }
    return pre;
}

// recursive linked list reverse    (???)
public ListNode recursive_reverse(ListNode head) {
    if(head == null || head.next==null)
        return head;
    return recursive_reverse(head, head.next);
}
private ListNode recursive_reverse(ListNode current, ListNode next) 
{
    if (next == null) return current;
    ListNode newHead = recursive_reverse(current.next, next.next);
    next.next = current;
    current.next = null;
    return newHead;
}

方法二：fast/slow pointer
when use slow and fast pointer, initialize slow=head and fast=head.next,  
slow stop at the middle of the list and fast stop at the end(could be null);
Then reverse the second half list and combine it with the first half list

Reference:
https://leetcodenotes.wordpress.com/2013/11/03/leetcode-reorder-list-%E5%8D%95%E9%93%BE%E8%A1%A8%E5%89%8D%E5%90%8E%E4%BA%A4%E5%8F%89/
http://www.ninechapter.com/solutions/reorder-list/
(recursive/not recursive linked list reverse)http://blog.csdn.net/linhuanmars/article/details/21503215
(方法二)https://yusun2015.wordpress.com/2015/01/24/reorder-list/
*/