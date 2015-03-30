/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
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
public class RecoverBinarySearchTree {
    private TreeNode firstElement = null;
    private TreeNode secondElement = null;
    private TreeNode lastElement = new TreeNode(Integer.MIN_VALUE); // previous node, set to MIN
    
    private void traverse(TreeNode root) {  // in-order traversal
        if (root == null) {
            return;
        }
        traverse(root.left);
        if (firstElement == null && root.val < lastElement.val) {   // 首次找到反序  
            firstElement = lastElement;
        }
        if (firstElement != null && root.val < lastElement.val) {   // 第二次找到反序
            secondElement = root;
        }
        lastElement = root;
        traverse(root.right);
    }
    
    public void recoverTree(TreeNode root) {
        // traverse and get two elements
        traverse(root);
        // swap 
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }
}

/*
Analysis:
- traverse the BST in order and put the previous and current nodes into a list if current value is greater the previous value;
- find the node in the front and the one in the end of the list, then swap the values of the two nodes.
// iterative
public class Solution {
    public void recoverTree(TreeNode root) {
        List<TreeNode> res=new ArrayList<>(2);
        Stack<TreeNode> s=new Stack<>();
        TreeNode pre=null;
        TreeNode cur=root;
        while(cur!=null){
            while(cur!=null){
                s.push(cur);
                cur=cur.left;
            }
            while(!s.isEmpty()&&cur==null){
                TreeNode temp=s.pop();
                if(pre!=null&&pre.val>temp.val){
                    res.add(pre);
                    res.add(temp);
                }
                pre=temp;
                cur=temp.right;
            }
        }
        int p=res.get(0).val;
        res.get(0).val=res.get(res.size()-1).val;
        res.get(res.size()-1).val=p;
    }
}

// recursive
public class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode[] c=new TreeNode[3];
        recoverTreehelper(root,c);
        int p=c[1].val;
        c[1].val=c[2].val;
        c[2].val=p;
        
    }     
    public void recoverTreehelper(TreeNode root,TreeNode[] c) {
        if(root==null) return;
        recoverTreehelper(root.left,c);
        if(c[0]!=null){
            if(c[0].val>root.val){
                if(c[1]==null){
                    c[1]=c[0];
                    c[2]=root;
                }
                else{
                    c[2]=root;
                }
            }
        }
        c[0]=root;
        recoverTreehelper(root.right,c);
    }
}

这道题是要求恢复一颗有两个元素调换错了的二叉查找树。一开始拿到可能会觉得比较复杂，其实观察出规律了就比较简单
主要还是利用二叉查找树的主要性质，就是中序遍历是有序的性质。那么如果其中有元素被调换了，意味着中序遍历中必然出现违背有序的情况
那么会出现几次呢？有两种情况，如果是中序遍历相邻的两个元素被调换了，很容易想到就只需会出现一次违反情况，只需要把这个两个节点记录下来最后调换值就可以
如果是不相邻的两个元素被调换了，举个例子很容易可以发现，会发生两次逆序的情况，那么这时候需要调换的元素应该是第一次逆序前面的元素，和第二次逆序后面的元素
比如1234567,1和5调换了，会得到5234167，逆序发生在52和41，我们需要把4和1调过来，那么就是52的第一个元素，41的第二个元素调换即可
搞清楚了规律就容易实现了，中序遍历寻找逆序情况，调换的第一个元素，永远是第一个逆序的第一个元素，而调换的第二个元素如果只有一次逆序，则是那一次的后一个，如果有两次逆序则是第二次的后一个
算法只需要一次中序遍历，所以时间复杂度是O(n)，空间是栈大小O(logn)
可以看到实现中pre用了一个ArrayList来存，这样做的原因是因为java都是值传递，所以我们要全局变化pre的值（而不是在当前函数里）
只能传一个数组，才能改变结点的地址，这一点非常重要，也是java和C++一个比较大的区别

判断是不是BST还是中序遍历最方便，但是题中说道O(n)复杂度太easy，要求开常量空间
具体的思路，还是通过中序遍历，只不过，不需要存储每个节点，只需要存一个前驱即可
例如1,4,3,2,5,6
1.当我们读到4的时候，发现是正序的，不做处理
2.但是遇到3时，发现逆序，将4存为第一个错误节点，3存为第二个错误节点
3.继续往后，发现3，2又是逆序了，那么将第2个错误节点更新为2
如果是这样的序列：1,4,3,5,6同上，得到逆序的两个节点为4和3
同理对于边界情况也是可以处理的，例如2,1

Find the place which the order is wrong.
Wrong order:         1 3 8 6 7 4 10 13 14     
FIND:                    8 6
Then we find:                7 4
8, 6 是错误的序列, 但是，7，4也是错误的序列，因为8，6前面的序列是正确的，所以8，6一定是后面的序列交换来的
而后面的是比较大的数字，也就是说8一定是被交换过来的。而7，4中也应该是小的数字4是前面交换过来的

用反证法来证明：
假设：6是后面交换过来的
推论: 那么8比6还大，那么8应该也是后面交换来的，这样起码有3个错误的数字了，而题目是2个错误的数字，得证，只应该是8是交换过来的
结论就是：我们需要交换的是：8，4

Reference:
https://leetcodenotes.wordpress.com/2013/08/10/leetcode-recover-binary-search-tree-bst%E6%9C%89%E4%B8%A4%E4%B8%AA%E5%85%83%E7%B4%A0%E6%8D%A2%E4%BD%8D%E7%BD%AE%E4%BA%86%EF%BC%8C%E4%B8%8D%E7%94%A8%E9%A2%9D%E5%A4%96%E7%A9%BA%E9%97%B4%E7%9A%84/
http://www.ninechapter.com/solutions/recover-binary-search-tree/
http://blog.csdn.net/linhuanmars/article/details/24566995
https://yusun2015.wordpress.com/2015/01/15/recover-binary-search-tree/
http://www.cnblogs.com/yuzhangcmu/p/4208319.html
http://yucoding.blogspot.com/2013/03/leetcode-question-75-recover-binary.html
http://huntfor.iteye.com/blog/2077665
(O(1) solution) http://fisherlei.blogspot.com/2012/12/leetcode-recover-binary-search-tree.html
*/