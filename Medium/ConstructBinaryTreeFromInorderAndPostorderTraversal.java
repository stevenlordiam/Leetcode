/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
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
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private int findPosition(int[] arr, int start, int end, int key) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private TreeNode myBuildTree(int[] inorder, int instart, int inend,
            int[] postorder, int poststart, int postend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postend]);
        int position = findPosition(inorder, instart, inend, postorder[postend]);   // root position in inorder traversal array

        root.left = myBuildTree(inorder, instart, position - 1,
                postorder, poststart, poststart + position - instart - 1);
        root.right = myBuildTree(inorder, position + 1, inend,
                postorder, poststart + position - instart, postend - 1);    // poststart + position - instart 是后序遍历左子树长度
        return root;                                                        // poststart = 0, position - instart 是后序遍历左子树长度
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
}


/*
这道题和Construct Binary Tree from Preorder and Inorder Traversal思路完全一样
这里的区别是要从中序遍历和后序遍历中构造出树，算法还是一样，只是现在取根是从后面取（因为后序遍历根是遍历的最后一个元素）
思想和代码基本都是差不多的，自然时间复杂度和空间复杂度也还是O(n)
public TreeNode buildTree(int[] inorder, int[] postorder) {
    if(inorder==null || postorder==null || inorder.length==0 || postorder.length==0)
    {
        return null;
    }
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for(int i=0;i<inorder.length;i++)
    {
        map.put(inorder[i],i);
    }
    return helper(inorder,postorder,0,inorder.length-1, 0, postorder.length-1,map);
}
private TreeNode helper(int[] inorder, int[] postorder, int inL, int inR, int postL, int postR, HashMap<Integer, Integer> map)
{
    if(inL>inR || postL>postR)
        return null;
    TreeNode root = new TreeNode(postorder[postR]);
    int index = map.get(root.val);
    root.left = helper(inorder,postorder,inL,index-1,postL,postL+index-inL-1,map);
    root.right = helper(inorder,postorder,index+1,inR,postR-(inR-index),postR-1,map);
    return root;
}
根据先序遍历和后序遍历能不能重新构造出树来？答案是否定的。只有中序便利可以根据根的位置切开左右子树，其他两种遍历都不能做到，
其实先序遍历和后序遍历是不能唯一确定一棵树的，会有歧义发生，也就是两棵不同的树可以有相同的先序遍历和后序遍历


Reference:
http://www.ninechapter.com/solutions/construct-binary-tree-from-inorder-and-postorder-traversal/
http://blog.csdn.net/linhuanmars/article/details/24390157
*/