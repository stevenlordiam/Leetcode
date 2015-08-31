/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
- Given target value is a floating point
- You are guaranteed to have only one unique value in the BST that is closest to the target
*/

// Recursive
public int closestValue(TreeNode root, double target) {
    int a = root.val;
    TreeNode kid = target < a ? root.left : root.right;
    if (kid == null) return a;
    int b = closestValue(kid, target);
    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
}

// Iterative
public int closestValue(TreeNode root, double target) {
    int closest = root.val;
    while (root != null) {
        if (Math.abs(closest - target) > Math.abs(root.val - target))
            closest = root.val;
        root = target < root.val ? root.left : root.right;
    }
    return closest;
}

/*
Reference:
http://codercareer.blogspot.com/2013/03/no-45-closest-node-in-binary-search-tree_2.html
https://leetcode.com/discuss/54436/java-iterative-solution
https://leetcode.com/discuss/54438/4-7-lines-recursive-iterative-ruby-c-java-python
https://leetcode.com/discuss/54447/my-java-recursive-solution
https://leetcode.com/discuss/oj/closest-binary-search-tree-value
http://algorithmsgeek.blogspot.com/2013/06/algo7-closest-value-search-in-binary.html
http://www.cs.uml.edu/~jlu1/doc/source/report/BinarySearchTree2.html
http://stackoverflow.com/questions/6209325/how-to-find-the-closest-element-to-a-given-key-value-in-a-binary-search-tree
http://likemyblogger.blogspot.com/2015/08/leetcode-270-closest-binary-search-tree.html
http://www.fgdsb.com/2015/01/18/closest-value-in-binary-search-tree/
*/