/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
public class ValidateBinarySearchTree {
    private int lastVal = Integer.MIN_VALUE; // infinite minimum as the initial left value of root
    private boolean firstNode = true;		// means it's the root node
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
}

/*
Similar to CC150 (4-5) Validate Binary Search Tree (TreesAndGraphs_5.java)

这道题是检查一颗二分查找树是否合法，二分查找树是非常常见的一种数据结构，因为它可以在O(logn)时间内实现搜索。
第一种是利用二分查找树的性质，就是它的中序遍历结果是按顺序递增的。根据这一点我们只需要中序遍历这棵树，
然后保存前驱结点，每次检测是否满足递增关系即可。注意以下代码我么用一个一个变量的数组去保存前驱结点，原因是java没有传引用的概念，
如果传入一个变量，它是按值传递的，所以是一个备份的变量，改变它的值并不能影响它在函数外部的值.
public boolean isValidBST(TreeNode root) {
    ArrayList<Integer> pre = new ArrayList<Integer>();
    pre.add(null);
    return helper(root, pre);
}
private boolean helper(TreeNode root, ArrayList<Integer> pre)
{
    if(root == null)
        return true;
    boolean left = helper(root.left,pre);
    if(pre.get(0)!=null && root.val<=pre.get(0))
        return false;
    pre.set(0,root.val);
    return left && helper(root.right,pre);
}

第二种方法是根据题目中的定义来实现，其实就是对于每个结点保存左右界，也就是保证结点满足它的左子树的每个结点比当前结点值小，
右子树的每个结点比当前结点值大。对于根节点不用定位界，所以是无穷小到无穷大，接下来当我们往左边走时，上界就变成当前结点的值，
下界不变，而往右边走时，下界则变成当前结点值，上界不变。如果在递归中遇到结点值超越了自己的上下界，则返回false，否则返回左右子树的结果
public boolean isValidBST(TreeNode root) {
    return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE); 
}
boolean helper(TreeNode root, int min, int max)   
{  
    if(root == null)  
       return true;  
    if(root.val <= min || root.val >= max)
         return false;  
     return helper(root.left, min, root.val) && helper(root.right, root.val, max);
}  
上述两种方法本质上都是做一次树的遍历，时间复杂度是O(n)，空间复杂度是O(logn)。

Reference:
http://www.ninechapter.com/solutions/validate-binary-search-tree/
https://yusun2015.wordpress.com/2015/01/31/validate-binary-search-tree/
http://blog.csdn.net/linhuanmars/article/details/23810735
*/