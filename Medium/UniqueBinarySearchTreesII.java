/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class UniqueBinarySearchTreesII {
    public ArrayList<TreeNode> generateTrees(int n) {
        return generate(1, n);
    }
    
    private ArrayList<TreeNode> generate(int start, int end){
        ArrayList<TreeNode> rst = new ArrayList<TreeNode>();   
    
        if(start > end){
            rst.add(null);
            return rst;
        }
     
        for(int i=start; i<=end; i++){
            ArrayList<TreeNode> left = generate(start, i-1);            // recursion
            ArrayList<TreeNode> right = generate(i+1, end);
            for(TreeNode l: left){
                for(TreeNode r: right){
                // should new a root here because it need to be different for each tree
                    TreeNode root = new TreeNode(i);  
                    root.left = l;
                    root.right = r;
                    rst.add(root);
                }
            }
        }
        return rst;
    }
}

/*
Dynamic Programming

Analysis: 
For any n, to get all the unique BST, we need to consider the set {1,2,…,n}, and we can take any element i to be the root and let the left and right part of i form the left and right BST respectively
This can generate all the BSTs. So this is a dynamical programming problem, the opt function is:
opt[n]=opt[0]*opt[n-1]+opt[1]*opt[n-2]+…+opt[n-1]opt[0].

public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generateTreesHelper(1,n);
    }
    public List<TreeNode> generateTreesHelper(int start,int end){
        List<TreeNode> list=new LinkedList<>();
        if(start>end){
            list.add(null);
            return list;
        }
        for(int i=start;i<=end;i++){
             List<TreeNode> left=generateTreesHelper(start,i-1);
             List<TreeNode> right=generateTreesHelper(i+1,end);
             for(TreeNode mynode1:left){
                 for(TreeNode mynode2:right){
                 TreeNode node=new TreeNode(i);
                 node.left=mynode1;
                 node.right=mynode2;
                 list.add(node);
                 }
             }
        }
        return list;
    }
}

这道题是求解所有可行的二叉查找树，从Unique Binary Search Trees中我们已经知道，可行的二叉查找树的数量是相应的卡特兰数，不是一个多项式时间的数量级
算法上还是用求解NP问题的方法来求解，也就是N-Queens中介绍的在循环中调用递归函数求解子问题。思路是每次一次选取一个结点为根，然后递归求解左右子树的所有结果，
根据左右子树的返回的所有子树，依次选取然后接上（每个左边的子树跟所有右边的子树匹配，而每个右边的子树也要跟所有的左边子树匹配，总共有左右子树数量的乘积种情况），构造好之后作为当前树的结果返回
public ArrayList<TreeNode> generateTrees(int n) {
    return helper(1,n);
}
private ArrayList<TreeNode> helper(int left, int right)
{
    ArrayList<TreeNode> res = new ArrayList<TreeNode>();
    if(left>right)
    {
        res.add(null);
        return res;
    }
    for(int i=left;i<=right;i++)
    {
        ArrayList<TreeNode> leftList = helper(left,i-1);
        ArrayList<TreeNode> rightList = helper(i+1,right);
        for(int j=0;j<leftList.size();j++)
        {
            for(int k=0;k<rightList.size();k++)
            {
                TreeNode root = new TreeNode(i);
                root.left = leftList.get(j);
                root.right = rightList.get(k);
                res.add(root);
            }
        }
    }
    return res;
}
实现中还是有一些细节的，因为构造树时两边要遍历所有左右的匹配，然后接到根上面
当然我们也可以像在Unique Binary Search Trees中那样存储所有的子树历史信息，然后进行拼合，但每个结果还是要一次运算，时间复杂度还是非多项式的，并且要耗费大量的空间

Reference:
http://www.ninechapter.com/solutions/unique-binary-search-trees-ii/
http://blog.csdn.net/linhuanmars/article/details/24761437
https://yusun2015.wordpress.com/2015/01/03/unique-binary-search-trees/
*/