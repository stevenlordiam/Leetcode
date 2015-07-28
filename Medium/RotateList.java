/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/

public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || n == 0)
            return head;

        ListNode p = head;
        int len = 1;				// since p is already point to head
        while (p.next != null) {	// get the length of list
            len++;
            p = p.next;
        }

        p.next = head; 				// form a loop
        n = n % len;				// 旋转的结点数可能超过链表长度，所以我们要对这个进行取余(Always do this when there is an argument showing the index of a linked list)

        for (int i = 0; i < len - n; i++) { //len-n一画就出来了
            p = p.next;             // 之前p走到了list的底，然后形成loop，再走len-n步，再断开loop，就实现了rotate n steps
        } 							
        head = p.next;              // now p points to the prev of the new head
        p.next = null;				// cut the loop
        return head;
    }
}

/*
对于array,翻转就是用tmp移动元素，参见Rotate Array - https://oj.leetcode.com/problems/rotate-array/
对于linked list, 要rotate，不如先连起来loop，这样怎么也不怕null pointer exception了，然后再找到该断开的地方断开

Analysis:
k can be any non-negative number, so first find the length of the list
let k=k%L to take care of the case that k is longer than L(需要注意的点就是旋转的结点数可能超过链表长度，所以我们要对这个进行取余)
get to the L-k node
set the next node as new head and set node.next=null

这是一道链表操作的题目，基本思路是用walker-runner定位到要旋转的那个结点，然后将下一个结点设为新表头，并且把当前结点设为表尾
需要注意的点就是旋转的结点数可能超过链表长度，所以我们要对这个进行取余。(Always do this when there is an argument showing the index of a linked list)
定位旋转的尾结点的不超过链表的长度，所以时间复杂度是O(n)，空间复杂度是O(1)
上面的实现中采取的方式是直接走到那个结点，如果没超过长度就直接旋转了，如果超过了，就进行取余，并且重新跑到结尾点。其实也可以先直接求长度，然后取余之后再走

Reference:
https://leetcodenotes.wordpress.com/2013/08/14/leetcode-rotate-list-%E6%8A%8A%E5%90%8Ek%E4%B8%AArotate%E5%88%B0list%E5%89%8D%E9%9D%A2%E5%8E%BB%EF%BC%8Ck%E5%8F%AF%E4%BB%A5%E8%B6%85%E8%BF%87list%E6%9C%AC%E8%BA%AB%E9%95%BF%E5%BA%A6/
http://www.ninechapter.com/solutions/rotate-list/
https://yusun2015.wordpress.com/2015/01/16/rotate-list/
http://blog.csdn.net/linhuanmars/article/details/21903027
https://github.com/leetcoders/LeetCode-Java/blob/master/RotateList.java
*/