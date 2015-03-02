/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
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
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null) return null;
        ListNode slow=head, fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast) break;			// they meet at the point where cycle starts
        }
        if(fast==null||fast.next==null) return null;
        fast=head;
        while(fast!=slow){					// same speed after they meet, and they will meet again at the point where cycle starts
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }
}

/*
Similar to CC150 (2-6) Linked List Cycle (LinkedLists_6)

Use fast and slow pointers. If there is a cycle, they will meet
After they meet, set the fast pointer to head
Move the pointers forwards with same speed until they meet again
The node they meet is where cycle begins

这道题是Linked List Cycle的扩展，就是在确定是否有cycle之后还要返回cycle的起始点的位置
从Linked List Cycle中用的方法我们可以得知a=kc-b（不了解的朋友可以先看看Linked List Cycle）
现在假设有两个结点，一个从链表头出发，一个从b点出发，经过a步之后，第一个结点会到达cycle的出发点，
而第二个结点会走过kc-b，加上原来的b刚好也会停在cycle的起始点
如此我们就可以设立两个指针，以相同速度前进知道相遇，而相遇点就是cycle的起始点
算法的时间复杂度是O(n+a)=O(2n)=O(n)，先走一次确定cycle的存在性并且走到b点，然后走a步找到cycle的起始点。空间复杂度仍是O(1)

Reference:
http://www.ninechapter.com/solutions/linked-list-cycle-ii/
https://yusun2015.wordpress.com/2015/01/07/linked-list-cycle-ii/
http://blog.csdn.net/linhuanmars/article/details/21260943
*/