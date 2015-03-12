/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class PopulatingNextRightPointersInEachNodeII {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode parent = root;
        TreeLinkNode pre;
        TreeLinkNode next;
        while (parent != null) {
            pre = null;
            next = null;
            while (parent != null) {
                if (next == null){
                    next = (parent.left != null) ? parent.left: parent.right;
                }
                if (parent.left != null){
                    if (pre != null) {
                        pre.next = parent.left;
                        pre = pre.next;
                    } else {
                        pre = parent.left;
                    }
                }
                if (parent.right != null) {
                    if (pre != null) {
                        pre.next = parent.right;
                        pre = pre.next;
                    } else {
                        pre = parent.right;
                    }
                }
                parent = parent.next;
            }
            parent = next;
        }
    }
}

/*
Similar to Populating Next Right Pointers In Each Node - https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

很简单的题，因为熟悉了用两个list做tree的bfs，所以这里一层一层iterate就行了，上一层反正已经做完了，所以能直接横着遍历上一层，把下一层populate了。轻松过

这道题目的要求和Populating Next Right Pointers in Each Node是一样的，只是这里的二叉树没要求是完全二叉树
其实在实现Populating Next Right Pointers in Each Node的时候我们已经兼容了不是完全二叉树的情况，其实也比较好实现，就是在判断队列结点时判断一下他的左右结点是否存在就可以了
时间复杂度和空间复杂度不变，还是O(n)和O(1)，这道题本质是树的层序遍历，只是队列改成用结点自带的链表结点来维护

public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null) return;
        TreeLinkNode nexthead=null;
        TreeLinkNode cur=root;
        TreeLinkNode p=null;
       while(cur!=null){
           if(cur.left!=null) {
               if(nexthead==null) nexthead=cur.left;
               if(p!=null) p.next=cur.left;
               p=cur.left;
           }
           if(cur.right!=null) {
               if(nexthead==null) nexthead=cur.right;
               if(p!=null) p.next=cur.right;
               p=cur.right;
           }
           if(cur.next!=null){
               cur=cur.next;
           }
           else{
               cur=nexthead;
               p=null;
               nexthead=null;
           }
       }
    }
}

Reference:
http://www.ninechapter.com/solutions/populating-next-right-pointers-in-each-node-ii/
http://blog.csdn.net/linhuanmars/article/details/23510601
https://yusun2015.wordpress.com/2015/01/05/populating-next-right-pointers-in-each-node/
*/