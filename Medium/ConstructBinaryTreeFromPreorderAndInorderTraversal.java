/*
Given preorder and inorder traversal of a tree, construct the binary tree.

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
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
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
            int[] preorder, int prestart, int preend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);
        int position = findPosition(inorder, instart, inend, preorder[prestart]);

        root.left = myBuildTree(inorder, instart, position - 1,
                preorder, prestart + 1, prestart + position - instart);
        root.right = myBuildTree(inorder, position + 1, inend,
                preorder, position - inend + preend + 1, preend);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length != preorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
}


/*
思路：
pre的第一个肯定是root
在in里找root出现的位置m，那in的current subarray的左边就是左子树，右边就是右子树，左子树left size大小是m – a
那么pre里面root之后的left size个就是pre里面的左子树，可以将它作为一个subarray开始下一层递归了
pre里面root + leftsize之后的那一半（到q为止）就是pre里面的右子树，可以将它作为一个subarray开始下一层递归，返回作为root的右子树。
所以一共三个变量:
- p the beginning of PRE’s current subarray (subtree)
- q the end of PRE’s current subarray (subtree)
- a the beginning of IN’s current subarray (subtree)
private TreeNode constructFromInAndPre(int[] PRE, int[] IN, int p, int q, int a) {
  if (p > q)
    return null;
  TreeNode root = new TreeNode(PRE[p]);
  int m = findIndex(PRE[p], IN); //the index of the current root in the IN order
  if (m < 0)
    System.out.println("not exist");
  int leftSize = m - a; //the left subtree of root's size
  root.left = constructFromInAndPre(PRE, IN, p + 1, p + leftSize, a);
  root.right = constructFromInAndPre(PRE, IN, p + leftSize + 1, q, m + 1);
  return root;
}


这道题是树中比较有难度的题目，需要根据先序遍历和中序遍历来构造出树来。这道题看似毫无头绪，其实梳理一下还是有章可循的。
下面我们就用一个例子来解释如何构造出树。假设树的先序遍历是12453687，中序遍历是42516837。这里最重要的一点就是先序遍历可以提供根的所在，
而根据中序遍历的性质知道根的所在就可以将序列分为左右子树。比如上述例子，我们知道1是根，所以根据中序遍历的结果425是左子树，而6837就是右子树。
接下来根据切出来的左右子树的长度又可以在先序便利中确定左右子树对应的子序列（先序遍历也是先左子树后右子树）。
根据这个流程，左子树的先序遍历和中序遍历分别是245和425，右子树的先序遍历和中序遍历则是3687和6837，我们重复以上方法，可以继续找到根和左右子树，直到剩下一个元素。
可以看出这是一个比较明显的递归过程，对于寻找根所对应的下标，我们可以先建立一个HashMap，以免后面需要进行线行搜索，这样每次递归中就只需要常量操作就可以完成对根的确定和左右子树的分割。
算法最终相当于一次树的遍历，每个结点只会被访问一次，所以时间复杂度是O(n)。而空间我们需要建立一个map来存储元素到下标的映射，所以是O(n)
public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder==null || inorder==null)
        return null;
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for(int i=0;i<inorder.length;i++)
    {
        map.put(inorder[i],i);
    }
    return helper(preorder,0,preorder.length-1,inorder,0,inorder.length-1, map);
}
private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, HashMap<Integer, Integer> map)
{
    if(preL>preR || inL>inR)
        return null;
    TreeNode root = new TreeNode(preorder[preL]);
    int index = map.get(root.val);
    root.left = helper(preorder, preL+1, index-inL+preL, inorder, inL, index-1, map);
    root.right = helper(preorder, preL+index-inL+1, preR, inorder, index+1, inR,map);
    return root;
}
可以看出上面实现结果还是非常接近于一次树的遍历的，只是我们是以一个构造树的形式，在遍历中把树创建出来


public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length<1) return null;
        HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
       return  buildTreeHelper(preorder, 0,inorder.length-1, inorder,0,inorder.length-1,map);
    }
    public TreeNode buildTreeHelper(int[] preorder, int s,int e, int[] inorder,int s1,int e1,HashMap<Integer,Integer> map) {
        if(s>e) return null;
        TreeNode node=new TreeNode(preorder[s]);
        int mid=map.get(preorder[s]);
        node.left=buildTreeHelper(preorder, s+1,s+mid-s1, inorder,s1,mid-1,map);
        node.right=buildTreeHelper(preorder, s+mid-s1+1,e, inorder,mid+1,e1,map);
        return node;
    }
}


Reference:
https://leetcodenotes.wordpress.com/2013/07/14/construct-binary-tree-from-preorder-and-inorder-traversal/
http://www.ninechapter.com/solutions/construct-binary-tree-from-preorder-and-inorder-traversal/
http://blog.csdn.net/linhuanmars/article/details/24389549
https://yusun2015.wordpress.com/2015/01/12/construct-binary-tree-from-preorder-and-inorder-traversal/
*/