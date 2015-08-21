/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
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
public class BinaryTreeLevelOrderTraversalII {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int currLevelNodeNum = 1;
        int nextLevelNodeNum = 0;

        while (currLevelNodeNum != 0) {
            ArrayList<Integer> currLevelResult = new ArrayList<Integer>();
            nextLevelNodeNum = 0;

            while (currLevelNodeNum != 0) {
                TreeNode node = queue.poll();

                currLevelNodeNum--;
                currLevelResult.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                    nextLevelNodeNum++;
                }

                if (node.right != null) {
                    queue.offer(node.right);
                    nextLevelNodeNum++;
                }
            }

            result.add(0, currLevelResult);         // result is two-dimension arraylist (dynamically adjust list size)
            currLevelNodeNum = nextLevelNodeNum;
        }
        return result;
    }
}

/*
Similar to Binary Tree Zigzag Level Order Traversal - https://oj.leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Analysis:
Use the proper of stack and queue. Stack: last in first out, queue: first in first out

这道题和Binary Tree Level Order Traversal很类似，都是层序遍历一棵树，只是这道题要求从最底层往最上层遍历。
这道题我没有想到什么好的做法可以一次的自底向上进行层序遍历，能想到的就是进行Binary Tree Level Order Traversal中的遍历，
然后对结果进行一次reverse。时间上和空间上仍是O(n)

Reference:
(Binary Tree Traversal总结)https://yusun2015.wordpress.com/2015/01/05/binary-tree-traversal/
http://www.ninechapter.com/solutions/binary-tree-level-order-traversal-ii/
http://blog.csdn.net/linhuanmars/article/details/23414711
*/