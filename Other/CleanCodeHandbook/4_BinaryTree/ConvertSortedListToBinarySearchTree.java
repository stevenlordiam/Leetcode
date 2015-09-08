/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class ConvertSortedListToBinarySearchTree {
    private ListNode current;

    private int getListLength(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public TreeNode sortedListToBST(ListNode head) {
        int size;
        current = head;
        size = getListLength(head);
        return sortedListToBSTHelper(size);
    }

    public TreeNode sortedListToBSTHelper(int size) {	// in order traversal, recursive solution
        if (size <= 0) {
            return null;
        }

        TreeNode left = sortedListToBSTHelper(size / 2);	
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);

        root.left = left;
        root.right = right;

        return root;
    }
}



/*
Similar to Convert Sorted Array To Binary Search Tree - https://oj.leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

Because it's already sorted, we can just use the middle element as the root of the BST
Find the node in the middle as root and find the left and right tree recursively
Should find the length of the list first

思路：
- 因为没有random access，所以用in order的方式，一个一个遍历element，然后assign给parent
- 平时正常array convert bst都是用pre order的方式，root算好，然后left=..., right=...
- 一般你做树的bottom up是post order，left做出来，right做出来，root取决于这两个值
- 这里为什么要in order呢？ 左，中，右的方式，正好是sorted。所以每次做完子树，然后下一个节点就是下一个位置。但是什么时候是个头呢，因为h.next会一直存在的，但是你的子树怎么知道什么时候返回到上一层？
  所以就要用p, q两个指针了，p代表当前sub list的头，q是尾。但是不要真的用他们来random access，就是用来stop就行了			

这个题是二分查找树的题目，要把一个有序链表转换成一棵二分查找树。其实原理还是跟Convert Sorted Array to Binary Search Tree这道题相似，我们需要取中点作为当前函数的根。
这里的问题是对于一个链表我们是不能常量时间访问它的中间元素的。这时候就要利用到树的中序遍历了，按照递归中序遍历的顺序对链表结点一个个进行访问，而我们要构造的二分查找树正是按照链表的顺序来的。
思路就是先对左子树进行递归，然后将当前结点作为根，迭代到下一个链表结点，最后在递归求出右子树即可。整体过程就是一次中序遍历，时间复杂度是O(n)，空间复杂度是栈空间O(logn)。
一般来说我们都是对于存在的树进行遍历，这里是模拟一个中序遍历的过程把树从无到有地构造出来

Reference:
https://leetcodenotes.wordpress.com/2013/11/23/convert-sorted-list-to-binary-search-tree/
http://www.ninechapter.com/solutions/convert-sorted-list-to-binary-search-tree/
http://blog.csdn.net/linhuanmars/article/details/23904937
https://yusun2015.wordpress.com/2015/01/11/convert-sorted-list-to-binary-search-tree/
*/