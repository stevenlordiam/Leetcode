/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
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
public class BinaryTreePreorderTraversal {  // 前序：中左右
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> preorder = new ArrayList<Integer>();
        
        if (root == null) {
            return preorder;
        }
        
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null) {  		// because stack is FILO, so push right first!!!
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return preorder;
    }
}

/*
Analysis:
Use the proper of stack and queue. Stack: last in first out, queue: first in first out

前序 pre order （中左右）：
- push root
- pop，若有right, push right，若有left push left
- 这样继续一边pop一边push。直到stack为空。
public void preOrder(TreeNode root) {
  if (root == null)
    return;
  Stack<TreeNode> s = new Stack<TreeNode>();
  s.push(root);
  while (!s.isEmpty()) {
    TreeNode top = s.pop();
    System.out.print(top.val + ", ");
    if (top.right != null)
      s.push(top.right);
    if (top.left != null)
      s.push(top.left);
  }
}

跟Binary Tree Inorder Traversal一样，二叉树的先序遍历我们仍然介绍三种方法，
第一种是递归，第二种是迭代方法，第三种是用线索二叉树。
递归是最简单的方法，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)
迭代的做法，其实就是用一个栈来模拟递归的过程。所以算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)

Reference:
(Binary Tree Traversal总结)https://yusun2015.wordpress.com/2015/01/05/binary-tree-traversal/
http://www.ninechapter.com/solutions/binary-tree-preorder-traversal/
https://leetcodenotes.wordpress.com/2013/08/04/classic-%E6%A0%91%E7%9A%84%E5%89%8D%E5%BA%8F%E3%80%81%E4%B8%AD%E5%BA%8F%E3%80%81%E5%90%8E%E5%BA%8F%E7%9A%84iteration/
http://blog.csdn.net/linhuanmars/article/details/21428647
*/

/*

// Version 1: Non-Recursion (Recommend) - iterative

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> preorder = new ArrayList<Integer>();
        
        if (root == null) {
            return preorder;
        }
        
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return preorder;
    }
}

// Version 2: Traverse - recursive
public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result);
    }
}

// Version 3: Divide & Conquer
public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        // null or leaf
        if (root == null) {
            return result;
        }

        // Divide
        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);

        // Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
}

// Version 4: Morris Traversal
Morris遍历方法，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。
具体的分析可以参见Binary Tree Inorder Traversal，中序和先序方法上是完全一样的，只是访问结点的时机不同而已。
这种方法的缺陷在于会暂时性的改变结点的内容，从编程健壮性和封装性来说不是特别好（比如说传进来的树结点很可能是const的变量，不希望对它做任何改变）。
时间复杂度和空间复杂度如同Binary Tree Inorder Traversal中分析的，分别是O(n)和O(1)

public ArrayList<Integer> preorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    TreeNode cur = root;
    TreeNode pre = null;
    while(cur != null)
    {
        if(cur.left == null)
        {
            res.add(cur.val);
            cur = cur.right;
        }
        else
        {
            pre = cur.left;
            while(pre.right!=null && pre.right!=cur)
                pre = pre.right;
            if(pre.right==null)
            {
                res.add(cur.val);
                pre.right = cur;
                cur = cur.left;
            }
            else
            {
                pre.right = null;
                cur = cur.right;
            }
        }
    }
    return res;
}

*/