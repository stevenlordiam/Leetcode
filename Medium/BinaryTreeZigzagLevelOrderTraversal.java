/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
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
public class BinaryTreeZigzagLevelOrderTraversal {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> currLevel = new Stack<TreeNode>();
        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
        Stack<TreeNode> tmp;
        
        currLevel.push(root);
        boolean normalOrder = true;

        while (!currLevel.isEmpty()) {
            ArrayList<Integer> currLevelResult = new ArrayList<Integer>();

            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                currLevelResult.add(node.val);

                if (normalOrder) {
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                }
            }

            result.add(currLevelResult);
            tmp = currLevel;                // swap stack of currLevel and nextLevel
            currLevel = nextLevel;
            nextLevel = tmp;
            normalOrder = !normalOrder;
        }

        return result;

    }
}

/*
Similar to Binary Tree Level Order Traversal I/II:
https://oj.leetcode.com/problems/binary-tree-level-order-traversal/
https://oj.leetcode.com/problems/binary-tree-level-order-traversal-ii/

Analysis:
Use the proper of stack and queue. Stack: last in first out, queue: first in first out

树的dfs变形，还是两个list来回倒。最开始的boolean reverse应该是true，因为他代表了“parent下面一层要什么顺序”，而不是parent本身是什么顺序

这道题其实还是树的层序遍历Binary Tree Level Order Traversal，这里稍微做了一点变体，就是在遍历的时候偶数层自左向右，而奇数层自右向左。
在Binary Tree Level Order Traversal中我们是维护了一个队列来完成遍历，而在这里为了使每次都倒序出来，我们很容易想到用栈的结构来完成这个操作。
有一个区别是这里我们需要一层一层的来处理（原来可以按队列插入就可以，因为后进来的元素不会先处理），所以会同时维护新旧两个栈，一个来读取，一个存储下一层结点。
总体来说还是一次遍历完成，所以时间复杂度是O(n)，空间复杂度最坏是两层的结点，所以数量级还是O(n)（满二叉树最后一层的结点是n/2个）
上面的算法其实还是一次广度优先搜索，只是在读取每一层结点交替的交换顺序

Reference:
(Binary Tree Traversal总结)https://yusun2015.wordpress.com/2015/01/05/binary-tree-traversal/
http://www.ninechapter.com/solutions/binary-tree-zigzag-level-order-traversal/
http://blog.csdn.net/linhuanmars/article/details/24509105
https://leetcodenotes.wordpress.com/2013/10/19/leetcode-binary-tree-zigzag-level-order-traversal-zigzag%E5%BD%A2%E7%8A%B6traverse%E6%A0%91/
*/