/*
Given a linked list, determine if it has a cycle in it.

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
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast) return true;
        }
        return false;
    }
}

/*
Similar to CC150 (2-6) Linked List Cycle (LinkedLists_6)

Use two pointers, the slow one has a velocity of 1 while the fast one has 2. 
If there is a cycle, the two pointer must meet at some position. If not, that implied no cycle

这道题是cc150里面的题目，算是链表里面比较经典的题目。我们先讲一下比较直接容易想到的方法，就是用一个hashset，
然后把扫过的结点放入hashset中，如果出现重复则返回true。下面我们来考虑如何不用额外空间来判断是否有cycle，
用到的方法很典型，就是那种walker-runner的方法，基本想法就是维护两个指针，一个走的快，一个走得慢，当一个走到链表尾或者两个相见的时候，
能得到某个想要的结点，比如相遇点，中点等等。假设两个指针walker和runner，walker一倍速移动，runner两倍速移动。
有一个链表，假设他在cycle开始前有a个结点，cycle长度是c，而我们相遇的点在cycle开始后b个结点。那么想要两个指针相遇，意味着要满足以下条件：
(1) a+b+mc=s; (2) a+b+nc=2s; 其中s是指针走过的步数，m和n是两个常数。这里面还有一个隐含的条件，就是s必须大于等于a，否则还没走到cycle里面，两个指针不可能相遇。
假设k是最小的整数使得a<=kc，也就是说(3) a<=kc<=a+c。接下来我们取m=0, n=k，用(2)-(1)可以得到s=kc以及a+b=kc。由此我们可以知道，只要取b=kc-a(由(3)可以知道b不会超过c)，
那么(1)和(2)便可以同时满足，使得两个指针相遇在离cycle起始点b的结点上。因为s=kc<=a+c=n，其中n是链表的长度，所以走过的步数小于等于n，时间复杂度是O(n)。并且不需要额外空间，空间复杂度O(1)
这道题是链表中比较有意思的题目，基于这个方法，我们不仅可以判断链表中有没有cycle，还可以确定cycle的位置，参见Linked List Cycle II

Reference:
http://www.ninechapter.com/solutions/linked-list-cycle/
https://yusun2015.wordpress.com/2015/01/03/linked-list-cycle/
http://blog.csdn.net/linhuanmars/article/details/21200601
*/