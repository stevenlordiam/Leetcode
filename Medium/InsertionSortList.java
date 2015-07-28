/*
Sort a linked list using insertion sort.
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
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        while (head != null) {
            ListNode node = dummy;
            while (node.next != null && node.next.val < head.val) {
                node = node.next;			// move until head<node
            }
            ListNode temp = head.next;  	// if head < node, insert it in 
            head.next = node.next;			// node is the new list, make the new list point to the same point as the old list, where is the larger node
            node.next = head;				// make the bigger one(head) as the new node in the new list
            head = temp;					// make the new list move
        }
        return dummy.next;
    }
}

/*
Similar to Sort List - https://oj.leetcode.com/problems/sort-list/

插入排序是一种O(n^2)复杂度的算法，基本想法相信大家都比较了解，就是每次循环找到一个元素在当前排好的结果中相对应的位置，然后插进去，经过n次迭代之后就得到排好序的结果了。
时间复杂度是排序算法的O(n^2)，空间复杂度是O(1)。这道题其实主要考察链表的基本操作，用到的小技巧也就是在Swap Nodes in Pairs中提到的用一个辅助指针来做表头避免处理改变head的时候的边界情况。

Create a dummy head as the head of the new list. Then insert each node from the old list to new list.

Reference:
http://www.ninechapter.com/solutions/insertion-sort-list/
https://yusun2015.wordpress.com/2015/01/14/insertion-sort-list/
http://blog.csdn.net/linhuanmars/article/details/21144553
*/