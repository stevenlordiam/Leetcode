/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
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
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy, right = rightDummy;
        
        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = head;				// asign value to left
            } else {
                right.next = head;
                right = head;
            }
            head = head.next;				// move ahead
        }
        
        right.next = null;					// concatenate two list
        left.next = rightDummy.next;
        return leftDummy.next;				// return head of the new list
    }
}

/*
Similar to CC150 (2-4) partition list (LinkedLists_4.java)

这题CC150上做过，属于简单题。但是做的过程中发现两个问题，关于generic linked list的：
最好别用while (curr != null && curr.next != null)这种循环，改成带prev指针的比较好，清晰易懂。
然后出while循环的时候，每次用的prev都判断一下是否是null，是的话就什么也不用做。
注意连接两个list的时候，小心list的最后一个next是不是指回来了，忘了清空。这样有loop的危险。
多拿几个combination试试，做到branch coverage！（这里就是”全<x， 全>=x，先<x后>=x，先>=x后<x“这四种情况）。

这是一道链表操作的题目，要求把小于x的元素按顺序放到链表前面。我们仍然是使用链表最常用的双指针大法，
一个指向当前小于x的最后一个元素，一个进行往前扫描。如果元素大于x，那么继续前进，否则，要把元素移到前面，并更新第一个指针。
这里有一个小细节，就是如果不需要移动（也就是已经是接在小于x的最后元素的后面了），那么只需要继续前进即可。
算法时间复杂度是O(n)，空间只需要几个辅助变量，是O(1)

Reference:
https://leetcodenotes.wordpress.com/2013/08/03/leetcode-partition-list-%E5%9B%B4%E7%BB%95x%E6%8A%8Alinked-list%E5%88%86%E6%88%90x%E7%9A%84%E9%A1%BA%E5%BA%8F/
http://www.ninechapter.com/solutions/partition-list/
https://yusun2015.wordpress.com/2015/01/10/partition-list/
http://blog.csdn.net/linhuanmars/article/details/24446871
*/