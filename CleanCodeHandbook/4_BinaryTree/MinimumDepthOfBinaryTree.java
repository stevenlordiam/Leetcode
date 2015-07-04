/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
public class MinimumDepthOfBinaryTree {             // always try recursion in binary tree 
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    public int getMin(TreeNode root){
        if (root == null) {                         // here RETURN Integer.MAX_VALUE not zero, see explanation as follow
            return Integer.MAX_VALUE;               // THIS IS IMPORTANT!!!
        }
        // 比如a#b这种左子树为空的树，我直接认为左子树的depth为0了，那当然左子树的depth小，然后parent直接取了0+1
        // 问题在于，某个子树是null的时候，它不是叶子节点啊！这个时候就算没形成一个叶子到root的path，则认为depth为正无穷(因为取的是min，所以在计算时正无穷不会被考虑)
        
        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
}

/*
Similar to Maximum Depth of Binary Tree - https://oj.leetcode.com/problems/maximum-depth-of-binary-tree/

REMEMBER: 
return Integer.MAX_VALUE when checking if a node is null (THIS IS IMPORTANT!!!)
比如a#b这种左子树为空的树，我直接认为左子树的depth为0了，那当然左子树的depth小，然后parent直接取了0+1
问题在于，某个子树是null的时候，它不是叶子节点啊！这个时候就算没形成一个叶子到root的path，则认为depth为正无穷(因为取的是min，所以在计算时正无穷不会被考虑)

这道题是树的题目，其实跟Maximum Depth of Binary Tree非常类似，只是这道题因为是判断最小深度，
所以必须增加一个叶子的判断（因为如果一个节点如果只有左子树或者右子树，我们不能取它左右子树中小的作为深度，
因为那样会是0，我们只有在叶子节点才能判断深度，而在求最大深度的时候，因为一定会取大的那个，所以不会有这个问题）
这道题同样是递归和非递归的解法，递归解法比较常规的思路，比Maximum Depth of Binary Tree多加一个左右子树的判断

非递归解法同样采用层序遍历(相当于图的BFS），只是在检测到第一个叶子的时候就可以返回了
public int minDepth(TreeNode root) {
    if(root == null)
        return 0;
    LinkedList queue = new LinkedList();
    int curNum = 0;
    int lastNum = 1;
    int level = 1;
    queue.offer(root);
    while(!queue.isEmpty())
    {
        TreeNode cur = queue.poll();
        if(cur.left==null && cur.right==null)
            return level;
        lastNum--;
        if(cur.left!=null)
        {
            queue.offer(cur.left);
            curNum++;
        }
        if(cur.right!=null)
        {
            queue.offer(cur.right);
            curNum++;
        }
        if(lastNum==0)
        {
            lastNum = curNum;
            curNum = 0;
            level++;
        }
    }
    return 0;
}

Reference:
https://leetcodenotes.wordpress.com/2013/08/01/minimum-depth-of-binary-tree/
http://www.ninechapter.com/solutions/minimum-depth-of-binary-tree/
http://blog.csdn.net/linhuanmars/article/details/19660209
*/