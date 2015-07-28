/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

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
public class BinaryTreeInorderTraversal {   // 中序：左中右
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while(root!=null || !stack.isEmpty()) {
            if(root!=null) {            // push root左支到头
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}

/*
Analysis:
Use the proper of stack and queue. Stack: last in first out, queue: first in first out

中序 in order （左中右）：
- push root左支到头
- pop， 若pop的有right，则把right当root对待push它的左支到头
- 这样继续一边pop一边push。直到stack为空
public void inOrder(TreeNode root) {
  if (root == null)
    return;
  Stack<TreeNode> s = new Stack<TreeNode>();
  pushAllTheyWayAtLeft(s, root);
  while (!s.isEmpty()) {
    TreeNode top = s.pop();
    System.out.print(top.val + ", ");
    if (top.right != null)
      pushAllTheyWayAtLeft(s, top.right);
  }
}
private void pushAllTheyWayAtLeft(Stack<TreeNode> s, TreeNode root) {
  while (root != null) {
    s.push(root);
    root = root.left;
  }
}

another way: keep a curr pointer, when curr is null means reached the left most, then start popping
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
        }
        return result;
    }

通常，实现二叉树的遍历有两个常用的方法：一是用递归，二是使用栈实现的迭代方法。下面分别介绍。
递归应该最常用的算法，相信大家都了解，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)

接下来是迭代的做法，其实就是用一个栈来模拟递归的过程。所以算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。
过程中维护一个node表示当前走到的结点（不是中序遍历的那个结点）

最后我们介绍一种比较复杂的方法，这个问题我有个朋友在去google onsite的时候被问到了，
就是如果用常量空间来中序遍历一颗二叉树。这种方法叫 Morris Traversal。
想用O(1)空间进行遍历，因为不能用栈作为辅助空间来保存付节点的信息，重点在于当访问到子节点的时候如何重新回到父节点
（当然这里是指没有父节点指针，如果有其实就比较好办，一直找遍历的后驱结点即可）。
Morris遍历方法用了线索二叉树，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。
算法具体分情况如下：
1. 如果当前结点的左孩子为空，则输出当前结点并将其当前节点赋值为右孩子。
2. 如果当前节点的左孩子不为空，则寻找当前节点在中序遍历下的前驱节点（也就是当前结点左子树的最右孩子）。接下来分两种情况：
 a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点（做线索使得稍后可以重新返回父结点）。然后将当前节点更新为当前节点的左孩子。
 b) 如果前驱节点的右孩子为当前节点，表明左子树已经访问完，可以访问当前节点。将它的右孩子重新设为空（恢复树的结构）。输出当前节点。当前节点更新为当前节点的右孩子。 

接下来我们来分析一下时间复杂度。咋一看可能会觉得时间复杂度是O(nlogn)，因为每次找前驱是需要logn，总共n个结点。
但是如果仔细分析会发现整个过程中每条边最多只走2次，一次是为了定位到某个节点，另一次是为了寻找上面某个节点的前驱节点，
而n个结点的二叉树中有n-1条边，所以时间复杂度是O(2*n)=O(n)，仍然是一个线性算法。
空间复杂度只是两个辅助指针，所以是O(1)

Reference:
(Binary Tree Traversal总结)https://yusun2015.wordpress.com/2015/01/05/binary-tree-traversal/
https://leetcodenotes.wordpress.com/2013/08/04/classic-%E6%A0%91%E7%9A%84%E5%89%8D%E5%BA%8F%E3%80%81%E4%B8%AD%E5%BA%8F%E3%80%81%E5%90%8E%E5%BA%8F%E7%9A%84iteration/
http://blog.csdn.net/linhuanmars/article/details/20187257
*/


/*
三种方法： 递归， 迭代， Morris

// Version 1: Recursive
public ArrayList<Integer> inorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    helper(root, res);
    return res;
}
private void helper(TreeNode root, ArrayList<Integer> res)
{
    if(root == null)
        return;
    helper(root.left,res);
    res.add(root.val);
    helper(root.right,res);
}

// Version 2: Iterative
public ArrayList<Integer> inorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    while(root!=null || !stack.isEmpty())
    {
        if(root!=null)
        {
            stack.push(root);
            root = root.left;
        }
        else
        {
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
    }
    return res;
}

// Version 3: Morris Traversal
public ArrayList<Integer> inorderTraversal(TreeNode root) {
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
                pre.right = cur;
                cur = cur.left;
            }
            else
            {
                pre.right = null;
                res.add(cur.val);
                cur = cur.right;
            }
        }
    }
    return res;
}

*/