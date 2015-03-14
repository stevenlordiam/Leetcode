/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

public class BestTimeToBuyAndSellStockIII {                     // 从左右扫两次(???)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right, first transaction's max profit(maxProfit from the first day to day i)
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            left[i] = Math.max(left[i - 1], prices[i] - min);       // left[i-1] here, not i
        }

        //DP from right to left, second transaction's max profit(maxProfit from day i to the last day)
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            right[i] = Math.max(right[i + 1], max - prices[i]);     // right[i+1] here, not i
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            profit = Math.max(left[i] + right[i], profit);  // 用max函数的时候这样用: maxnum = Math.max(something, maxnum) 
        }

        return profit;
    }
}


/*
用max函数的时候这样用: maxnum = Math.max(something, maxnum)

Dynamic Programming

Similar to Maximum Subarray, Best Time to Buy and Sell Stock I / II / IV

正确方法是一维dp从左到右扫一遍，再从右到左扫一遍，然后两次加和
两个一维数组的定义一定要搞清，onsite的时候就栽在这上面了！
d[i]: [0…i]这段时间的买卖一次的最大利润。所以i应该从1开始。
f[i]: [i…n – 1]这段时间的买卖一次的最大利润。所以i应该最大n – 2。
然后就看出问题了——两个i不能同步，否则就当天买卖了。最后加和的时候要错开。
因为“最多买卖两次”， 所以还要包括买卖一次的情况。用d[n-1]就是[0…n-1]可以表达这种情况。

Analysis:
This is a dynamical programming problem. Assume {D[i][j]} is the maximum profit for the subarray {prices[i,...,j-1]} when only one transaction is allowed. 
Then D[i][j]=max{D[0][i]+D[i][length]}. So we have the following algorithm, first round form {0} to {length} find the {D[0][i]}, second round from {length} to {0} find {D[i][length]}, at last find {D[i][j]=max{{D[0][i]+D[i][length]}}.
Need to be careful when dealing with the boundary of indices.
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length<2) return 0;
        int[] first=new int[prices.length+1];
        int res=0,min=prices[0];
        for(int i=1;i<=prices.length;i++){
            first[i]=Math.max(first[i-1],prices[i-1]-min);
            min=Math.min(min,prices[i-1]);
        }
        int max=prices[prices.length-1],second=0;
        for(int i=prices.length;i>0;i--){
            res=Math.max(first[i]+second,res);
            second=Math.max(second,max-prices[i-1]);
            max=Math.max(max,prices[i-1]);
        }
        return res;
    }
}

这道是买股票的最佳时间系列问题中最难最复杂的一道，前面两道Best Time to Buy and Sell Stock 买卖股票的最佳时间和Best Time to Buy and Sell Stock II 买股票的最佳时间之二的思路都非常的简洁明了，算法也很简单
而这道是要求最多交易两次，找到最大利润，还是需要用动态规划Dynamic Programming来解，而这里我们需要两个递推公式来分别更新两个变量local和global，参见网友Code Ganker的博客，我们其实可以求至少k次交易的最大利润，找到通解后可以设定 k = 2，即为本题的解答
我们定义local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。然后我们定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。它们的递推式为：
local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)
global[i][j]=max(local[i][j],global[i-1][j])，
其中局部最优值是比较前一天并少交易一次的全局最优加上大于0的差值，和前一天的局部最优加上差值中取较大值，而全局最优比较局部最优和前一天的全局最优

这道题是Best Time to Buy and Sell Stock的扩展，现在我们最多可以进行两次交易。我们仍然使用动态规划来完成，事实上可以解决非常通用的情况，也就是最多进行k次交易的情况
这里我们先解释最多可以进行k次交易的算法，然后最多进行两次我们只需要把k取成2即可。我们还是使用“局部最优和全局最优解法”。我们维护两种量，一个是当前到达第i天可以最多进行j次交易，最好的利润是多少（global[i][j]），另一个是当前到达第i天，最多可进行j次交易，并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）
下面我们来看递推式，全局的比较简单，global[i][j]=max(local[i][j],global[i-1][j])，也就是去当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）
对于局部变量的维护，递推式是local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，并不会增加交易次数，而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件了）
上面的算法中对于天数需要一次扫描，而每次要对交易次数进行递推式求解，所以时间复杂度是O(n*k)，如果是最多进行两次交易，那么复杂度还是O(n)。空间上只需要维护当天数据皆可以，所以是O(k)，当k=2，则是O(1)。
可以看到，这里的模型是比较复杂的，主要是在递推式中，local和global是交替求解的
public int maxProfit(int[] prices) {
    if(prices==null || prices.length==0)
        return 0;
    int[] local = new int[3];
    int[] global = new int[3];
    for(int i=0;i<prices.length-1;i++)
    {
        int diff = prices[i+1]-prices[i];
        for(int j=2;j>=1;j--)
        {
            local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
            global[j] = Math.max(local[j],global[j]);
        }
    }
    return global[2];
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/19/leetcode-best-time-to-buy-and-sell-stocks-3-%E8%82%A1%E7%A5%A8%E9%97%AE%E9%A2%98%EF%BC%8C%E6%9C%80%E5%A4%9A%E4%B8%A4%E6%AC%A1%E4%B9%B0%E5%8D%96%EF%BC%8C%E6%B1%82%E6%9C%80%E5%A4%A7%E5%88%A9/
http://www.ninechapter.com/solutions/best-time-to-buy-and-sell-stock-iii/
http://blog.csdn.net/linhuanmars/article/details/23236995
http://www.cnblogs.com/grandyang/p/4281975.html
https://yusun2015.wordpress.com/2015/01/03/best-time-to-buy-and-sell-stock-iii/
https://leetcode.com/discuss/19759/o-n-solution-in-java-with-good-readability-i-think
https://leetcode.com/discuss/17142/a-simple-solution-in-java-o-n-time-and-o-1-space
https://leetcode.com/discuss/18330/is-it-best-solution-with-o-n-o-1
*/