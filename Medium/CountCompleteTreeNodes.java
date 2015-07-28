/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia(http://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees):
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class CountCompleteTreeNodes { 	// O(log(n)^2)
    public int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);   // full binary tree只可能在右边缺少node
    }
    public int countNodes(TreeNode root) {
        int nodes = 0, h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) {  // 右边不缺少node
                nodes += 1 << h;        // 1 << h : 2^h
                root = root.right;
            } else {
                nodes += 1 << h-1;      // 右边缺少node
                root = root.left;
            }
            h--;
        }
        return nodes;
    }
}

/*
Explanation:
The height of a tree can be found by just going left. Let a single node tree have height 0. 
Find the height h of the whole tree. If the whole tree is empty, i.e., has height -1, there are 0 nodes.
Otherwise check whether the height of the right subtree is just one less than that of the whole tree, meaning left and right subtree have the same height.
If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. 
So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2. 
So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).

Reference:
https://leetcode.com/discuss/38930/concise-java-solutions-o-log-n-2
https://leetcode.com/discuss/38919/concise-java-iterative-solution-o-logn-2
https://leetcode.com/discuss/38894/java-solution-clean-code-easy-to-understand
https://leetcode.com/discuss/39049/solutions-to-the-problem
https://leetcode.com/discuss/38907/iterative-binary-search-solution-using-binary-numbers-paths
*/