/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
*/

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] mat=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0||j==0) mat[i][j]=1;
                else mat[i][j]=mat[i-1][j]+mat[i][j-1];
            }
        }
        return mat[m-1][n-1];       // DP, at the bottom right is the total number of unique paths
    }
}

/*
Dynamic Programming

Similar to Minimum Path Sum

Similar to CC150 (9-2) Unique Paths - (RecursionAndDynamicProgramming_2.java)

这道题是比较典型的动态规划的题目。模型简单，但是可以考核动态规划的思想
其实上一步中递推式已经出来了，就是res[i][j]=res[i-1][j]+res[i][j-1]，这样我们就可以用一个数组来保存历史信息，也就是在i行j列的路径数，这样每次就不需要重复计算，从而降低复杂度
用动态规划我们只需要对所有格子进行扫描一次，到了最后一个得到的结果就是总的路径数，所以时间复杂度是O(m*n)。而对于空间可以看出我们每次只需要用到上一行当前列，以及前一列当前行的信息，
我们只需要用一个一维数组存上一行的信息即可，然后扫过来依次更替掉上一行对应列的信息即可（因为所需要用到的信息都还没被更替掉），所以空间复杂度是O(n)（其实如果要更加严谨，我们可以去行和列中小的那个，然后把小的放在内层循环，这样空间复杂度就是O(min(m,n))
上面的方法用动态规划来求解，如果我们仔细的看这个问题背后的数学模型，其实就是机器人总共走m+n-2步，其中m-1步往下走，n-1步往右走，本质上就是一个组合问题，也就是从m+n-2个不同元素中每次取出m-1个元素的组合数
根据组合的计算公式，只需要做一次行或者列的扫描，所以时间复杂度是O(min(m,n))，而空间复杂度是O(1)。比起上面的两种解法更优。不过这里有个弊端，就是如果代码中的dom和dedom如果不是double，而是用int，那么会很容易越界，因为这是一个阶乘，所以讨论这种方法要注意和提及越界的问题
public int uniquePaths(int m, int n) {
    double dom = 1;
    double dedom = 1;
    int small = m<n? m-1:n-1;
    int big = m<n? n-1:m-1;
    for(int i=1;i<=small;i++)
    {
        dedom *= i;
        dom *= small+big+1-i;
    }
    return (int)(dom/dedom);
}

Reference:
http://www.ninechapter.com/solutions/unique-paths/
http://blog.csdn.net/linhuanmars/article/details/22126357
https://yusun2015.wordpress.com/2015/01/07/unique-paths/
*/