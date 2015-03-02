/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
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
public class FlattenBinaryTreeToLinkedList {
    private TreeNode lastNode = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {           // pre order traversal
            lastNode.left = null;         // 维护先序遍历的前一个结点pre，然后每次把pre的左结点置空，右结点设为当前结点
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;      // 保存右节点数据以免被覆盖
        flatten(root.left);
        flatten(right);
    }
}

/*
Analysis:
Use pre order traversal and need to record the most recent visited node for any subtree
- Flatten the left tree of root
- Get the most recent visited node of left tree and set root.right as its right child
- Set root.right=root.left
- Set root.left=null
- Flatten the right tree of node from 2

这是树的题目，要求把一颗二叉树按照先序遍历顺序展成一个链表，不过这个链表还是用树的结果，就是一直往右走（没有左孩子）来模拟链表。
老套路还是用递归来解决，维护先序遍历的前一个结点pre，然后每次把pre的左结点置空，右结点设为当前结点。这里需要注意的一个问题就是我们要先把右子结点保存一下，
以便等会可以进行递归，否则有可能当前结点的右结点会被覆盖，后面就取不到了。算法的复杂度时间上还是一次遍历，O(n)。空间上是栈的大小，O(logn) 
树的题目主要思路就是递归，考虑好递归条件和结束条件，有时候如果递归过程会对树结构进行修改的话，要先保存一下结点

Reference:
https://leetcodenotes.wordpress.com/2013/07/17/flatten-binary-tree-to-linked-list/
http://www.ninechapter.com/solutions/flatten-binary-tree-to-linked-list/
https://yusun2015.wordpress.com/2015/01/10/flatten-binary-tree-to-linked-list/
http://blog.csdn.net/linhuanmars/article/details/23717703
*/