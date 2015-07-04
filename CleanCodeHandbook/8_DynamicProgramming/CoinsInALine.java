/*
There are n coins in a line. (Assume n is even). Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.

Would you rather go first or second? Does it matter?
Assume that you go first, describe an algorithm to compute the maximum amount of money you can win.
*/

public class CoinsInALine {
    int coins(int[] A) {
        int n=A.length;
        int[][] sum=new int[n][n];
        int[][] dp=new int[n][n];
 
        for(int i=n-1;i>=0;i--)
            for(int j=i;j<n;j++)
                sum[i][j]=A[j]+(i==j?0:sum[i][j-1]);
 
        for(int i=n-1;i>=0;i--)
            for(int j=i;j<n;j++)
            {
                if(i==j)
                    dp[i][j]=A[i];
                else
                    dp[i][j]=sum[i][j]-Math.min(dp[i+1][j], dp[i][j-1]);
            }
        return dp[0][n-1];
    }
}

/*
[分析]
题目要求就是依次拿硬币，但是拿哪个需要参考子问题的结果，典型的DP问题。
dp[i][j]代表从第i个硬币到第j个硬币这个子问题的结果，也就是可以拿到的最大钱数。sum[i][j]代表从第i个硬币到第j个硬币总钱数之和
那么对于每一个子问题，你有两种选择
1）如果你取第一个的话，剩下的子问题就变成了(i+1,j)了，由于轮到对方取了，因此对方可以得到dp[i+1][j]的钱数，而你则得到sum[i+1,j]-dp[i+1][j]的钱数，也就是剩余的钱数。
2）如果你取最后一个的话，同理。
递推公式为：
dp[i][j] = max(sum[i][j]-dp[i+1][j], sum[i][j]-dp[i][j-1]) = sum[i][j]-min(dp[i+1][j],dp[i][j-1])
[注意事项]
1）这道问题看起来想是一维的DP，但需要用二维DP来求解

Reference:
http://articles.leetcode.com/2011/02/coins-in-line.html
http://www.danielbit.com/blog/puzzle/leetcode/leetcode-coins-in-a-line
http://www.shuatiblog.com/blog/2014/07/27/Coins-in-Line/
*/