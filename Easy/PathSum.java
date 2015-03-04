/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) { 			// recursion
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum (root.left, sum - root.val)
            || hasPathSum(root.right, sum - root.val);
    }
}

/*
Similar to Path Sum II - https://oj.leetcode.com/problems/path-sum-ii/
Similar to CC150 (4-9) path sum (TreesAndGraphs_9)

这道题是树操作的题目，判断是否从根到叶子的路径和跟给定sum相同的。还是用常规的递归方法来做，(树的遍历一般都用递归解法)
递归条件是看左子树或者右子树有没有满足条件的路径，也就是子树路径和等于当前sum减去当前节点的值。
结束条件是如果当前节点是空的，则返回false，如果是叶子，那么如果剩余的sum等于当前叶子的值，则找到满足条件的路径，返回true。
算法的复杂度是树的遍历，时间复杂度是O(n)，空间复杂度是O(logn)

树的题目在LeetCode中出现的频率很高，不过方法都很接近，掌握了就都差不多。
这类求解树的路径的题目是一种常见题型，类似的还有Binary Tree Maximum Path Sum，那道题更加复杂一些，路径可以起始和结束于任何结点（把二叉树看成无向图）

top down利用leaf的树题一般是这样的pattern:
void topDown(Node node) {
    if (node == null)
        //this means reaching a branch that only one side has something, the other side is this (null)
    if (node.left == null && root.right == null)
        //this is leaf, do something, then return;
    topDown(node.left);
    topDown(node.right);
}
和平时做的preorder（中左右）的区别：preOrder这种遍历下来，的确会在empty node的时候停，但是不能判断什么时候到了叶子节点。
而topDown利用了叶子节点的定义来判断当前节点是否叶子，可以这时候做一些运算。
void preOrder(Node node) {
   if (node == null)
   //this could either mean parent is a leaf, or parent's this branch is empty but has another child
   }
   preOrder(node.left);
   preOrder(node.right);
}

Reference:
https://leetcodenotes.wordpress.com/2013/08/04/leetcode-pathsum-%E6%89%BE%E4%BB%8Eroot%E5%88%B0leaft%E7%9A%84%E5%8A%A0%E5%92%8Cx%E7%9A%84%E8%B7%AF%E5%BE%84%E6%98%AF%E5%90%A6%E5%AD%98%E5%9C%A8/
https://leetcodenotes.wordpress.com/2013/08/04/leetcode-pathsum-%E6%89%BE%E4%BB%8Eroot%E5%88%B0leaft%E7%9A%84%E5%8A%A0%E5%92%8Cx%E7%9A%84%E8%B7%AF%E5%BE%84%E6%98%AF%E5%90%A6%E5%AD%98%E5%9C%A8iteratively/
http://www.ninechapter.com/solutions/path-sum/
http://blog.csdn.net/linhuanmars/article/details/23654413
*/