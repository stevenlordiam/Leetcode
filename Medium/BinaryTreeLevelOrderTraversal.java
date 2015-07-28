/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
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
public class BinaryTreeLevelOrderTraversal {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);                          // same as add()

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();       // remove and return the head of queue
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }
        
        return result;
    }
}

/*
Similar to Binary Tree Zigzag Level Order Traversal - https://oj.leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Queue.offer() - same as add(), but not throw exceptions

        | Throws exceptions | Special value
--------+-------------------+----------------
Insert  | add(e)            | offer(e)   
Remove  | remove()          | poll()     
Examine | element()         | peek()

Analysis:
Use the proper of stack and queue. Stack: last in first out, queue: first in first out

这道题要求实现树的层序遍历，其实本质就是把树看成一个有向图，然后进行一次广度优先搜索，这个图遍历算法是非常常见的，
这里同样是维护一个队列，只是对于每个结点我们知道它的邻接点只有可能是左孩子和右孩子。
算法的复杂度是就结点的数量，O(n)，空间复杂度是一层的结点数，也是O(n)
层序遍历也是树的一种遍历方式，在某些题目中会比较实用，不过这样其实更接近于图的问题了，
在有些树的题目中层序遍历提供了更好的方法。这道题还有一个变体Binary Tree Zigzag Level Order Traversal，
其实也是进行广度优先搜索，不过因为要求不同，要换一种数据结构来记录层节点。

Reference:
(Binary Tree Traversal总结)https://yusun2015.wordpress.com/2015/01/05/binary-tree-traversal/
http://www.ninechapter.com/solutions/binary-tree-level-order-traversal/
http://blog.csdn.net/linhuanmars/article/details/23404111
*/