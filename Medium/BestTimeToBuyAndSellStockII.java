/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

public class BestTimeToBuyAndSellStockII { 			// Greedy
    public int maxProfit(int[] prices) {
        if(prices.length<2) return 0;
        int sum=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1])				// in this case, diff>0, can profit
            sum=sum+prices[i]-prices[i-1];
        }
        return sum;
    }
}

/*
I: one transaction -> DP
II: many transactions -> Greedy

Similar to Maximum Subarray, Best Time to Buy and Sell Stock I / III / IV

As you can complete as many transactions as you want, one can buy one whenever the next price is higher. So it is greedy algorithm

这道题跟Best Time to Buy and Sell Stock类似，求最大利润。区别是这里可以交易无限多次（当然我们知道交易不会超过n-1次，也就是每天都进行先卖然后买）
既然交易次数没有限定，可以看出我们只要对于每次两天差价大于0的都进行交易，就可以得到最大利润。因此算法其实就是累加所有大于0的差价既可以了，非常简单
如此只需要一次扫描就可以了，时间复杂度是O(n)，空间上只需要O(1)存一个累加结果即可

Reference:
http://www.ninechapter.com/solutions/best-time-to-buy-and-sell-stock-ii/
http://blog.csdn.net/linhuanmars/article/details/23164149
https://yusun2015.wordpress.com/2015/01/03/best-time-to-buy-and-sell-stock-ii/
*/