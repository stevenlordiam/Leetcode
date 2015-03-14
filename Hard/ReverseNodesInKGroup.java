/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
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
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 0;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            count ++;
            ListNode next = cur.next;
            if(count == k){
                pre = reverse(pre, next);
                count = 0;   
            }
            cur = next;
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode pre, ListNode end){ 		// ???
        if(pre==null || pre.next==null)
            return pre;
        ListNode head = pre.next;
        ListNode cur = pre.next.next;
        while(cur!=end){
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
        head.next = end;
        return head;
    }
}

/*
Similar to Swap Nodes in Pairs - https://leetcode.com/problems/swap-nodes-in-pairs/

keep 3个pointer：
- prevStart：要reverse的section的前一个节点
- start：section第一个
- end：section最后一个
然后就每次reverse好了section，和prevStart连上就行了，然后prevStart 越过k个，接着做。这是个练习linked list的好题
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prevStart = dummy;
    while (prevStart != null) {
        ListNode start = prevStart.next;
        ListNode end = prevStart;
        for (int i = 0; i < k; i++) {
            end = end.next;
            if (end == null)
                return dummy.next;
        }
        prevStart.next = reverse(start, end);
        for (int i = 0; i < k; i++) {
            prevStart = prevStart.next;
        }
    }
    return dummy.next;
}
private ListNode reverse(ListNode start, ListNode end) {
    ListNode prev = start;
    ListNode curr = start.next;// want to make curr point to prev
    ListNode afterEnd = end.next;
    while (curr != afterEnd) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    start.next = afterEnd;
    return end;
}

这道题是Swap Nodes in Pairs的扩展，Swap Nodes in Pairs其实是这道题k=2的特殊情况。不过实现起来还是比较不一样的，因为要处理比较general的情形
基本思路是这样的，我们统计目前节点数量，如果到达k，就把当前k个结点reverse，这里需要reverse linked list的subroutine
这里我们需要先往前走，到达k的时候才做reverse，所以总体来说每个结点会被访问两次。总时间复杂度是O(2*n)=O(n)，空间复杂度是O(1)
有朋友可能会说为什么不边走边reverse，这样可以省一个pass。但是问题是这个题目的要求中最后如果不足k个不需要reverse，所以没法边走边倒
这道题考查的还是链表的基本操作，其中reverse是一个非常重要的链表操作

Reference:
https://leetcodenotes.wordpress.com/2013/08/13/leetcode-reverse-nodes-in-k-group-%E6%8A%8A%E9%93%BE%E8%A1%A8%E6%8C%89k%E4%B8%AA%E4%B8%80%E7%BB%84%E5%8F%8D%E8%BD%AC/
http://www.ninechapter.com/solutions/reverse-nodes-in-k-group/
http://blog.csdn.net/linhuanmars/article/details/19957455
https://yusun2015.wordpress.com/2015/01/14/reverse-nodes-in-k-group/
*/