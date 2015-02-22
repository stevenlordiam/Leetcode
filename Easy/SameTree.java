/*
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
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
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;
        if(p==null || q==null)
            return false;
        if(p.val!=q.val)
            return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right); 		// recursion
    }
}

/*
这道题是树的题目，属于最基本的树遍历的问题。问题要求就是判断两个树是不是一样，基于先序，中序或者后序遍历都可以做完成，
因为对遍历顺序没有要求。这里我们主要考虑一下结束条件，如果两个结点都是null，也就是到头了，那么返回true。
如果其中一个是null，说明在一棵树上结点到头，另一棵树结点还没结束，即树不相同，或者两个结点都非空，并且结点值不相同，返回false。
最后递归处理两个结点的左右子树，返回左右子树递归的与结果即可。这里使用的是先序遍历，算法的复杂度跟遍历是一致的，如果使用递归，时间复杂度是O(n)，空间复杂度是O(logn)

树的题目大多都是用递归来完成比较简练，当然也可以如同Binary Tree Inorder Traversal中介绍的那样，用非递归方法甚至线索二叉树来做，
只需要根据要求做相应改变即可。其实这些方法道理都相同，所以以后就不列举多种方法，对树的题目还是关注除遍历之外的要求，一般来说还是需要注意一些细节。

Reference:
http://blog.csdn.net/linhuanmars/article/details/22839819

Tree解法总结：(THIS IS IMPORTANT!!!)
https://leetcodenotes.wordpress.com/2013/10/11/summery-tree%E7%9A%84%E8%A7%A3%E6%B3%95%E6%80%BB%E7%BB%93/
http://blog.csdn.net/linhuanmars/article/details/39024195
*/