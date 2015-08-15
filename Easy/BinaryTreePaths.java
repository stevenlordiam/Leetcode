/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:
["1->2->5", "1->3"]
*/

public class BinaryTreePaths {  // recursion
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root==null) return res;
        String path = ""+root.val;
        getPath(root,res,path);
        return res;
    }
    private void getPath(TreeNode root, List<String> res, String path) {
        if(root.left==null&&root.right==null) {
            res.add(path);
        } else {
            if(root.left!=null) getPath(root.left, res, path+"->" + root.left.val);
            if(root.right!=null) getPath(root.right, res, path+"->" + root.right.val);
        }
    }
}

/*
Reference:
https://leetcode.com/discuss/52019/java-recursion-solution
*/