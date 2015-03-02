/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

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
public class BinaryTreePostorderTraversal {     // 后序：左右中
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null;                   // previously traversed node
        TreeNode curr = root;

        if (root == null) {
            return result;
        }

        stack.push(root);
        while (!stack.empty()) {
            curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) { // traverse down the tree
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {     // traverse up the tree from the left
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else {                            // traverse up the tree from the right
                result.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }

        return result;
    }
}

/*
Analysis:
Use the proper of stack and queue. Stack: last in first out, queue: first in first out

用两个stack O(n)的方法：
- 一个stack s1 push root
- 一边pop s1进s2，一边把pop的left, right push进s1（先左后右，这先进s2的是右边，左边在s2靠前部分，所以最后先print出来）
- 这样最后s2就是一个正好从上到下是post order的traversal，一边pop一边print就行了
public void postOrderTwoStacks(TreeNode root) {
  Stack<TreeNode> s1 = new Stack<TreeNode>();
  Stack<TreeNode> s2 = new Stack<TreeNode>();
  s1.push(root);
  while (!s1.isEmpty()) {
    TreeNode pop = s1.pop();
    s2.push(pop);
    if (pop.left != null)
      s1.push(pop.left);
    if (pop.right != null)
      s1.push(pop.right);
  }
  while (!s2.isEmpty()) {
    System.out.print(s2.pop().val + ", ");
  }
}

- 用一个stack O(h)的方法：
- 一个stack，先push root
- keep一个prev variable表示刚才试过的node（在stack里尝试来着，不管最后是否把它pop出去了），一直在更新。
- stack.peek() == curr，每次用curr和prev比较
- 如果prev是空或者prev是curr的parent，说明在top down的traverse，这时候可不能print prev（那就变成preorder了）；而应该push curr的左子，木有才push右子，全都木有说明curr是leaf，就pop print就行了。
- 如果prev是curr的左子，说明在从左下角往上traverse，这时若curr有右子，则push右子（应该traverse右子）- prev应该是已经pop出来的了。
- 如果prev是curr的右子，说明从右下角往左上traverse，这时直接pop print curr就行了，因为这时root也该出来了。
public void postOrder(TreeNode root) {
  Stack<TreeNode> s = new Stack<TreeNode>();
  s.push(root);
  TreeNode prev = null;
  while (!s.isEmpty()) {
    TreeNode curr = s.peek();
    if (prev == null || prev.left == curr || prev.right == curr) { // top down
      if (curr.left != null)
        s.push(curr.left);
      else if (curr.right != null)
        s.push(curr.right);
      else {// is leaf
        popAndPrint(s, curr);
      }
    } else if (prev == curr.left) { // from left child to parent
      if (curr.right != null)
        s.push(curr.right);
      else {
        popAndPrint(s, curr);
      }
    } else { // prev.right == curr, from right child to parent
      popAndPrint(s, curr);
    }
    prev = curr;
  }
}
private void popAndPrint(Stack<TreeNode> s, TreeNode curr) {
  System.out.print(curr.val + ", ");
  s.pop();
}


跟Binary Tree Inorder Traversal以及Binary Tree Preorder Traversal一样，二叉树的后序遍历我们还是介绍三种方法，
第一种是递归，第二种是迭代方法，第三种是用线索二叉树。 递归还是那么简单，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)
接下来是迭代的做法，本质就是用一个栈来模拟递归的过程，但是相比于Binary Tree Inorder Traversal和Binary Tree Preorder Traversal，后序遍历的情况就复杂多了。
我们需要维护当前遍历的cur指针和前一个遍历的pre指针来追溯当前的情况（注意这里是遍历的指针，并不是真正按后序访问顺序的结点）。具体分为几种情况：
（1）如果pre的左孩子或者右孩子是cur，那么说明遍历在往下走，按访问顺序继续，即如果有左孩子，则是左孩子进栈，否则如果有右孩子，则是右孩子进栈，如果左右孩子都没有，则说明该结点是叶子，可以直接访问并把结点出栈了。
（2）如果反过来，cur的左孩子是pre，则说明已经在回溯往上走了，但是我们知道后序遍历要左右孩子走完才可以访问自己，所以这里如果有右孩子还需要把右孩子进栈，否则说明已经到自己了，可以访问并且出栈了。
（3）如果cur的右孩子是pre，那么说明左右孩子都访问结束了，可以轮到自己了，访问并且出栈即可。
算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)

上面迭代实现的思路虽然清晰，但是实现起来还是分情况太多，不容易记忆。看wiki的时候发现有跟Binary Tree Inorder Traversal和Binary Tree Preorder Traversal非常类似的解法，容易统一进行记忆，思路可以参考其他两种，
区别是最下面在弹栈的时候需要分情况一下：
1）如果当前栈顶元素的右结点存在并且还没访问过（也就是右结点不等于上一个访问结点），那么就把当前结点移到右结点继续循环；
2）如果栈顶元素右结点是空或者已经访问过，那么说明栈顶元素的左右子树都访问完毕，应该访问自己继续回溯了

最后介绍Morris遍历方法，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了，所以优势在于不需要额外空间。
不过同样相比于Binary Tree Inorder Traversal和Binary Tree Preorder Traversal，后序遍历的情况要复杂得多，因为后序遍历中一直要等到孩子结点访问完才能访问自己，需要一些技巧来维护。
在这里，我们需要创建一个临时的根节点dummy，把它的左孩子设为树的根root。同时还需要一个subroutine来倒序输出一条右孩子路径上的结点。
跟迭代法一样我们需要维护cur指针和pre指针来追溯访问的结点。具体步骤是重复以下两步直到结点为空：
1. 如果cur指针的左孩子为空，那么cur设为其右孩子。
2. 否则，在cur的左子树中找到中序遍历下的前驱结点pre（其实就是左子树的最右结点）。接下来分两种子情况：
（1）如果pre没有右孩子，那么将他的右孩子接到cur。将cur更新为它的左孩子。
（2）如果pre的右孩子已经接到cur上了，说明这已经是回溯访问了，可以处理访问右孩子了，倒序输出cur左孩子到pre这条路径上的所有结点，并把pre的右孩子重新设为空（结点已经访问过了，还原现场）。最后将cur更新为cur的右孩子。
空间复杂度同样是O(1)，而时间复杂度也还是O(n)，倒序输出的过程只是加大了常数系数，并没有影响到时间的量级。

Reference:
(Binary Tree Traversal总结)https://yusun2015.wordpress.com/2015/01/05/binary-tree-traversal/
http://www.ninechapter.com/solutions/binary-tree-postorder-traversal/
https://leetcodenotes.wordpress.com/2013/10/08/leetcode-%E6%A0%91%E7%9A%84%E5%90%8E%E5%BA%8F%E7%9A%84iterative-traversal/
http://blog.csdn.net/linhuanmars/article/details/22009351
*/

/*

// Version 1: Recursive
public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<Integer>();

    if (root == null) {
        return result;
    }

    result.addAll(postorderTraversal(root.left));
    result.addAll(postorderTraversal(root.right));
    result.add(root.val);

    return result;   
}

public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    helper(root, res);
    return res;
}
private void helper(TreeNode root, ArrayList<Integer> res)
{
    if(root == null)
        return;
    helper(root.left,res);
    helper(root.right,res);
    res.add(root.val);
}

// Version 2: Iterative
public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode prev = null; // previously traversed node
    TreeNode curr = root;

    if (root == null) {
        return result;
    }

    stack.push(root);
    while (!stack.empty()) {
        curr = stack.peek();
        if (prev == null || prev.left == curr || prev.right == curr) { // traverse down the tree
            if (curr.left != null) {
                stack.push(curr.left);
            } else if (curr.right != null) {
                stack.push(curr.right);
            }
        } else if (curr.left == prev) { // traverse up the tree from the left
            if (curr.right != null) {
                stack.push(curr.right);
            }
        } else { // traverse up the tree from the right
            result.add(curr.val);
            stack.pop();
        }
        prev = curr;
    }

    return result;
}

public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if(root == null)
        return res;
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    stack.push(root);
    TreeNode pre = null;
    while(!stack.isEmpty())
    {
        TreeNode cur = stack.peek();
        if(pre==null || pre.left==cur || pre.right==cur)
        {
            if(cur.left!=null)
            {
                stack.push(cur.left);
            }
            else if(cur.right!=null)
            {
                stack.push(cur.right);
            }
            else
            {
                res.add(cur.val);
                stack.pop();
            }
        }
        else if(cur.left==pre && cur.right!=null)
        {
            stack.push(cur.right);
        }
        else
        {
            res.add(cur.val);
            stack.pop();
        }
        pre = cur;
    }
    return res;
}

// Version 3: same as preorder/inorder
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if(root == null)
    {
        return res;
    }
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    TreeNode pre = null;
    while(root != null || !stack.isEmpty())
    {
        if(root!=null)
        {
            stack.push(root);
            root = root.left;
        }
        else
        {
            TreeNode peekNode = stack.peek();
            if(peekNode.right!=null && pre != peekNode.right)
            {
                root = peekNode.right;
            }
            else
            {
                stack.pop();
                res.add(peekNode.val);
                pre = peekNode;
            }
        }
    }
    return res;
}

// Version 4: Morris Traversal
public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    TreeNode dummy = new TreeNode(0);
    dummy.left = root;
    TreeNode cur = dummy;
    TreeNode pre = null;
    while(cur!=null)
    {
        if (cur.left==null)
        {
            cur = cur.right;
        }
        else
        {
            pre = cur.left;
            while (pre.right!=null && pre.right!=cur)
                pre = pre.right;
            if (pre.right==null)
            {
                pre.right = cur;
                cur = cur.left;
            }
            else
            {
                reverse(cur.left, pre);
                TreeNode temp = pre;
                while (temp != cur.left)
                {
                    res.add(temp.val);
                    temp = temp.right;
                }
                res.add(temp.val);
                reverse(pre, cur.left);
                pre.right = null;
                cur = cur.right;
            }
        }
    } 
    return res;
}
void reverse(TreeNode start, TreeNode end) 
{
    if (start == end)
        return;
    TreeNode pre = start;
    TreeNode cur = start.right;
    TreeNode next;
    while (pre != end)
    {
        next = cur.right;
        cur.right = pre;
        pre = cur;
        cur = next;
    }
}

*/