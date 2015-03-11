/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int[][] sum = new int[M][N];

        sum[0][0] = grid[0][0];

        for (int i = 1; i < M; i++) {
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < N; i++) {
            sum[0][i] = sum[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }

        return sum[M - 1][N - 1]; 	// at the bottom right, the sum is minimum sum
    }
}

/*
Dynamic Programming

Similar to Unique Paths I / II

Analysis:
This is a standard DP problem, assume D[i][j] is the minimum sum from (0,0) to (i,j) then
D[i][j]=Min{D[i][j-1],D[i-1][j]}+grid[i][j]

这道题跟Unique Paths，Unique Paths II是相同类型的。事实上，这道题是上面两道题的general版本，是寻找带权重的path
在Unique Paths中，我们假设每个格子权重都是1，而在Unique Paths II中我们假设障碍格子的权重是无穷大，所以永远不会选择
剩下的区别就是这道题寻找这些路径中权重最小的，而不是总路径数。其实方法是一样的，还是使用动态规划，只是现在维护的不是路径数，而是到达某一个格子的最短路径（也就是权重之和最小）
而这个当前最短路径可以取前面一格和上面一格中小的最短路径长度得到，因此递推式是res[i][j]=min(res[i-1][j], res[i][j-1])+grid[i][j]。总共进行两层循环，时间复杂度是O(m*n)。而空间复杂度仍是使用Unique Paths中的方法来省去一维，是O(m)

Reference:
http://www.ninechapter.com/solutions/minimum-path-sum/
http://blog.csdn.net/linhuanmars/article/details/22257673
https://yusun2015.wordpress.com/2015/01/09/minimum-path-sum/
*/