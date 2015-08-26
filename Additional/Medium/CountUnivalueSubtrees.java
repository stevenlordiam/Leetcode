/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,

    5
   / \
  1   5
 / \   \
5   5   5

return 4
*/

public class CountUnivalueSubtrees {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int[] result = new int[]{0};
        helper(root, result);
        return result[0];
    }

    private boolean helper(TreeNode root, int[] result) {
        if (root.right == null && root.left == null) {
            result[0]++;
            return true;
        } else if (root.right != null && root.left == null) {
            if (helper(root.right, result) && root.val == root.right.val) {
                result[0]++;
                return true;
            } else {
                return false;
            }
        } else if (root.right == null) {
            if (helper(root.left, result) && root.val == root.left.val) {
                result[0]++;
                return true;
            } else {
                return false;
            }
        } else {
            boolean l = helper(root.right, result);
            boolean r = helper(root.left, result);
            if (l && r && root.val == root.left.val && root.val == root.right.val) {
                result[0]++;
                return true;
            } else {
                return false;
            }
        }
    }
}

/*
Reference:
http://sbzhouhao.net/LeetCode/LeetCode-Count-Univalue-Subtrees.html
*/