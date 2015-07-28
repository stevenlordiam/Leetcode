/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
*/

public class KthSmallestElementInABST {
	int res = 0;
	int count = 0;
	public int kthSmallest(TreeNode root, int k) {
		count = k;
		helper(root);
		return res;
	}
	public void helper (TreeNode root) { 	// inorder traversal
		if (root == null) return;
		helper(root.left);
		if (--count == 0) res = root.val;
		helper(root.right);
	}
}

/*
https://leetcode.com/discuss/43263/java-straight-forward-recursion-solution
*/