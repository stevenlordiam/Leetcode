/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
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
public class BinaryTreeMaximumPathSum {
    private class ResultType {					// wrapper class
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }

    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
}

/*
Depth First Search

算法：
- arch代表穿过当前节点的路径（左边一支儿+自己节点+右边一支儿）
- 注意树的节点可以是负数，所以arch不一定是最长的
- 每次return以root（当前节点）开头最大的单只path sum
- res[]就是一个存result的reference object，java不支持c++那种直接&传reference，所以要么用个长度为一的数组，要么写个wrapper。还是用数组简单
- update res[0]，用arch和以自己开头一支儿的比，谁大就把res[0] update成谁

这道题是求树的路径和的题目，不过和平常不同的是这里的路径不仅可以从根到某一个结点，而且路径可以从左子树某一个结点，然后到达右子树的结点，就像题目中所说的可以起始和终结于任何结点
在这里树没有被看成有向图，而是被当成无向图来寻找路径。因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果
在这里，函数的返回值定义为以自己为根的一条从根到子结点的最长路径（这里路径就不是当成无向图了，必须往单方向走）
这个返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可
这样一来，一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值
而返回值则是自己的值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）
在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新。算法的本质还是一次树的遍历，所以复杂度是O(n)。而空间上仍然是栈大小O(logn)
树的题目大多是用递归方式，但是根据要求的量还是比较灵活多变的，这道题是比较有难度的，他要用返回值去维护一个中间量，而结果值则通过参数来维护，需要一点技巧

Analysis:
- use a helper function to return the maximum sum from the root to its descendants;
- use the sum in 1 to find the maximum path sum
public class Solution {
    public int maxPathSum(TreeNode root) {
        if(root==null) return 0;
        int res[]=new int[1];
        res[0]=Integer.MIN_VALUE;
        helper(root,res);
        return res[0];
    }
    public int helper(TreeNode root,int[] res) {
        if(root==null) return 0;
        int l=helper(root.left,res);
        int r=helper(root.right,res);
        int p=Math.max(Math.max(root.val+l,root.val+r),root.val);
        res[0]=Math.max(res[0],Math.max(p,root.val+l+r));
        if(root.left!=null) res[0]=Math.max(res[0],l);
        if(root.right!=null) res[0]=Math.max(res[0],r);
        return p;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/11/04/leetcode-binary-tree-maximum-path-sum-%E6%A0%91%E4%B8%AD%E4%BB%BB%E6%84%8F%E4%B8%A4%E7%82%B9%E9%97%B4%E6%9C%80%E5%A4%A7path-sum/
http://www.ninechapter.com/solutions/binary-tree-maximum-path-sum/
http://blog.csdn.net/linhuanmars/article/details/22969069
https://yusun2015.wordpress.com/2015/01/24/binary-tree-maximum-path-sum/
*/