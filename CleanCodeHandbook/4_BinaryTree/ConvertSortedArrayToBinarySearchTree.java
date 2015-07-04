/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null) {
            return null;
        }
        return buildTree(num, 0, num.length - 1);
    }

    private TreeNode buildTree(int[] num, int start, int end) {         // start and end is the index, not actual integer number in the array
        if (start > end) {          // check if the array is actually in ascending order
            return null;
        }

        TreeNode node = new TreeNode(num[(start + end) / 2]);       // binary search, let the middle element to be the root of the tree
        node.left = buildTree(num, start, (start + end) / 2 - 1);
        node.right = buildTree(num, (start + end) / 2 + 1, end);
        return node;
    }

}

/*
Similar to Convert Sorted List To Binary Search Tree - https://oj.leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

Let the middle element be the root of the tree

这道题是二分查找树的题目，要把一个有序数组转换成一颗二分查找树。
其实从本质来看，如果把一个数组看成一棵树（也就是以中点为根，左右为左右子树，依次下去）数组就等价于一个二分查找树
所以如果要构造这棵树，那就是把中间元素转化为根，然后递归构造左右子树。
所以我们还是用二叉树递归的方法来实现，以根作为返回值，每层递归函数取中间元素，作为当前根和赋上结点值，然后左右结点接上左右区间的递归函数返回值。
时间复杂度还是一次树遍历O(n)，总的空间复杂度是栈空间O(logn)加上结果的空间O(n)，额外空间是O(logn)，总体是O(n)

Reference:
http://www.ninechapter.com/solutions/convert-sorted-array-to-binary-search-tree/
https://yusun2015.wordpress.com/2015/01/05/convert-sorted-array-to-binary-search-tree/
http://blog.csdn.net/linhuanmars/article/details/23904883
*/