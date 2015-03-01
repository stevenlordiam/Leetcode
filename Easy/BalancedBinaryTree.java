/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
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
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
            return -1;							// return -1 means not balanced
        }
        return Math.max(left, right) + 1;		// remember to plus one (plus the depth of itself)
    }
}

/*
经典题，注意balanced的定义：左右子树都balance，且高度差<=1。所以下面这个也是balanced的，即使a的左右子树都不是完全树。
    a
   / \ 
  b   d
 /     \
c       e
唯一的trick:不用生成新的data structure来保存“boolean isBalanced, int height”，直接用height = -1表示不平衡就行。

这道题是树操作的题目，还是老套路用递归。这道题是求解树是否平衡，还是有一些小技巧的。
要判断树是否平衡，根据题目的定义，深度是必须的信息，所以我们必须维护深度，另一方面我们又要返回是否为平衡树，那么对于左右子树深度差的判断也是必要的。
这里我们用一个整数来做返回值，而0或者正数用来表示树的深度，而-1则用来比较此树已经不平衡了，如果已经不平衡，则递归一直返回-1即可，也没有继续比较的必要了，
否则就利用返回的深度信息看看左右子树是不是违反平衡条件，如果违反返回-1，否则返回左右子树深度大的加一作为自己的深度即可。
可以看出树的题目万变不离其宗，都是递归遍历，只是处理上保存量，递归条件和结束条件会有一些变化。
算法的时间是一次树的遍历O(n)，空间是栈高度O(logn)。

Use a helper function to calculate the height of the tree.

Reference:
https://leetcodenotes.wordpress.com/2013/10/07/leetcode-balanced-binary-tree-%E7%9C%8B%E4%B8%80%E6%A3%B5%E4%BA%8C%E5%8F%89%E6%A0%91%E6%98%AF%E5%90%A6balanced/
http://www.ninechapter.com/solutions/balanced-binary-tree/
http://blog.csdn.net/linhuanmars/article/details/23731355
https://yusun2015.wordpress.com/2015/01/06/balanced-binary-tree/
*/