/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

public class BestTimeToBuyAndSellStockIV {
     public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);    
        // 直接采用动态规划会返回Time Limit Exceeded，可以针对题目部分样例做出下面的优化：
        // 当k > size / 2时，问题可以转化为：Best Time to Buy and Sell Stock II (unlimited transaction)

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }

    private int quickSolve(int[] prices) {      // unlimited transaction
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}

/*
动态规划（Dynamic Programming）
问题的实质是从长度为n的prices数组中挑选出至多2 * k个元素，组成一个交易（买卖）序列，交易序列中的首次交易为买入，其后卖出和买入操作交替进行。
总收益为交易序列中的偶数项之和 - 奇数项之和，dp[j]表示完成j次交易时的最大收益，转移方程如下：
dp[j] = max(dp[j], dp[j - 1] + prices[i] * [1, -1][j % 2])
当j为奇数时，交易类型为买入；当j为偶数时，交易类型为卖出。由于直接采用动态规划会返回Time Limit Exceeded，可以针对题目部分样例做出下面的优化：
令最大交易次数为k，数组长度为size；
则当k > size / 2时，问题可以转化为：Best Time to Buy and Sell Stock II

这道题是Best Time to Buy and Sell Stock的扩展，现在我们最多可以进行两次交易。我们仍然使用动态规划来完成，事实上可以解决非常通用的情况，也就是最多进行k次交易的情况
这里我们先解释最多可以进行k次交易的算法，然后最多进行两次我们只需要把k取成2即可。我们还是使用“局部最优和全局最优解法”
我们维护两种量，一个是当前到达第i天可以最多进行j次交易，最好的利润是多少（global[i][j]），另一个是当前到达第i天，最多可进行j次交易，并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）
下面我们来看递推式，全局的比较简单，global[i][j]=max(local[i][j],global[i-1][j])，也就是去当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）
全局（到达第i天进行j次交易的最大收益） = max{局部（在第i天交易后，恰好满足j次交易），全局（到达第j-1天时已经满足j次交易）}
对于局部变量的维护，递推式是 local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，
如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），第二个量则是取local第i-1天j次交易，然后加上今天的差值，（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，并不会增加交易次数，而且这里无论diff是不是大于0都一定要加上，
因为否则就不满足local[i][j]必须在最后一天卖出的条件了）。局部（在第i天交易后，总共交易了j次） =  max{情况2，情况1}
情况1：在第i-1天时，恰好已经交易了j次（local[i-1][j]），那么如果i-1天到i天再交易一次：即在第i-1天买入，第i天卖出（diff），则这不并不会增加交易次数！
【例如我在第一天买入，第二天卖出；然后第二天又买入，第三天再卖出的行为和第一天买入，第三天卖出的效果是一样的，其实只进行了一次交易！因为有连续性】
情况2：第i-1天后，共交易了j-1次（global[i-1][j-1]），因此为了满足“第i天过后共进行了j次交易，且第i天必须进行交易”的条件：我们可以选择1：在第i-1天买入，然后再第i天卖出（diff），或者选择在第i天买入，然后同样在第i天卖出（收益为0）
上面的算法中对于天数需要一次扫描，而每次要对交易次数进行递推式求解，所以时间复杂度是O(n*k)，如果是最多进行两次交易，那么复杂度还是O(n)。空间上只需要维护当天数据皆可以，所以是O(k)，当k=2，则是O(1)

Reference:
https://leetcode.com/discuss/25603/a-concise-dp-solution-in-java
http://www.cnblogs.com/grandyang/p/4295761.html
https://gist.github.com/ElninoFong/d08051d98e634991cd93
http://bookshadow.com/weblog/2015/02/18/leetcode-best-time-to-buy-and-sell-stock-iv/
http://www.cnblogs.com/EdwardLiu/p/4306941.html
http://blog.csdn.net/fightforyourdream/article/details/14503469
*/