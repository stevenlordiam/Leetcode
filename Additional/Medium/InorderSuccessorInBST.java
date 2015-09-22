/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
Note: If the given node has no in-order successor in the tree, return null
*/

public class InorderSuccessorInBST { 	// O(N)
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p != null && p.right != null) { 	// case 1: if there's right subtree, then find the leftmost node
            TreeNode cur = p.right;
            while(cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        TreeNode next = findNext(root, p);
        return next;
    }

    public TreeNode findNext(TreeNode root, TreeNode p) { // case 2: no right subtree, do a traversal
        if(root == p) {
            return null;
        }
        if(root.val < p.val) {
            return findNext(root.right, p);
        } else {
            TreeNode left = findNext(root.left, p);
            return findNext(root.left, p) == null ? root : left; 
        }
    }
}


/*
There are just two cases:
- The easier one: p has right subtree, then its successor is just the leftmost child of its right subtree;
- The harder one: p has no right subtree, then a traversal is needed to find its successor.

Traversal: we start from the root, each time we see a node with val larger than p -> val, we know this node may be p's successor
So we record it in suc. Then we try to move to the next level of the tree: if p -> val > root -> val, which means p is in the right subtree, 
then its successor is also in the right subtree, so we update root = root -> right; if p -> val < root -> val, we update root = root -> left similarly
once we find p -> val == root -> val, we know we've reached at p and the current sucis just its successor

Reference:
https://leetcode.com/discuss/59686/java-o-lgn-solution
https://leetcode.com/discuss/59728/10-lines-o-h-java-c
http://www.cnblogs.com/jcliBlogger/p/4829200.html
http://www.cnblogs.com/easonliu/p/4828925.html
http://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
http://fisherlei.blogspot.com/2013/02/google-inorder-successor-in-binary.html
*/