/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

public class UniqueBinarySearchTrees {
/*
The case for 3 elements example
Count[3] = Count[0]*Count[2]  (1 as root)
              + Count[1]*Count[1]  (2 as root)
              + Count[2]*Count[0]  (3 as root)
Therefore, we can get the equation:
Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1  

*/
    public int numTrees(int n) {
        int[] count = new int[n+2];
        count[0] = 1;
        count[1] = 1;
        
        for(int i=2;  i<= n; i++){
            for(int j=0; j<i; j++){
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}

/*
Dynamic Programming

算法：1～n中每个数字都可以是root，定了谁是root之后（比如k），他的左子树只能是1～k-1组成的，右子树只能是k+1～n组成的
左子树的variation数目×右子树的就是k作为root的有几种rotation
有点要注意：终止条件有两种：
- p == q，说明就一个节点了，没啥可转的，就一种return 1
- p > q，说明这一段已经不存在了，这时不应该return 0，因为这正是子树是null的情况！所以还应该return 1
public int numTrees(int n) {
  return getWays(1, n);
}
int getWays(int p, int q) {
  if (p >= q)
    return 1;
  int ways = 0;
  for (int i = p; i <= q; i++) {
    int leftWays = getWays(p, i - 1);
    int rightWays = getWays(i + 1, q);
    ways += leftWays * rightWays;
  }
  return ways;
}


Analysis: 
For any n, to get all the unique BST, we need to consider the set {1,2,…,n}, and we can take any element i to be the root and let the left and right part of i form the left and right BST respectively
This can generate all the BSTs. So this is a dynamical programming problem, the opt function is:
opt[n]=opt[0]*opt[n-1]+opt[1]*opt[n-2]+…+opt[n-1]opt[0].

public class Solution {
    public int numTrees(int n) {
        int[] A=new int[n+1];
        A[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                A[i]=A[i]+A[j]*A[i-1-j];
            }
        }
        return A[n];
    }
}


这道题要求可行的二叉查找树的数量，其实二叉查找树可以任意取根，只要满足中序遍历有序的要求就可以
从处理子问题的角度来看，选取一个结点为根，就把结点切成左右子树，以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，所以总的数量是将以所有结点为根的可行结果累加起来
熟悉卡特兰数的朋友可能已经发现了，这正是卡特兰数的一种定义方式，是一个典型的动态规划的定义方式（根据其实条件和递推式求解结果）。所以思路也很明确了，维护量res[i]表示含有i个结点的二叉查找树的数量
根据上述递推式依次求出1到n的的结果即可。时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，总体要求n次，所以总时间复杂度是O(1+2+...+n)=O(n^2)。空间上需要一个数组来维护，并且需要前i个的所有信息，所以是O(n)
public int numTrees(int n) {
    if(n<=0)
        return 0;
    int[] res = new int[n+1];
    res[0] = 1;
    res[1] = 1;
    for(int i=2;i<=n;i++)
    {
        for(int j=0;j<i;j++)
        {
            res[i] += res[j]*res[i-j-1];
        }
    }
    return res[n];
}
这种求数量的题目一般都容易想到用动态规划的解法，这道题的模型正好是卡特兰数的定义。当然这道题还可以用卡特兰数的通项公式来求解，这样时间复杂度就可以降低到O(n)
如果是求解所有满足要求的二叉树（而不仅仅是数量）那么时间复杂度是就取决于结果的数量了，不再是一个多项式的解法了，参见Unique Binary Search Trees II

Reference:
https://leetcodenotes.wordpress.com/2013/10/01/leetcode-unique-binary-search-trees-%E7%BB%99n%EF%BC%8C1n%E7%9A%84%E6%95%B0%E5%AD%97%E8%83%BD%E7%BB%84%E6%88%90%E5%A4%9A%E5%B0%91%E7%A7%8D%E4%B8%8D%E5%90%8Crotation%E7%9A%84bst/
http://www.ninechapter.com/solutions/unique-binary-search-trees/
http://blog.csdn.net/linhuanmars/article/details/24761459
https://yusun2015.wordpress.com/2015/01/03/unique-binary-search-trees/
*/