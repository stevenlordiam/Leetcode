/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
*/

public class BestTimeToBuyAndSellStock {        // Dynamic Programming
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length==0)
            return 0;
        int local = 0;
        int global = 0;
        for(int i=0;i<prices.length-1;i++) {
            local = Math.max(local+prices[i+1]-prices[i],0);
            global = Math.max(local, global);
        }
        return global;
    }
}

/*
Dynamic Programming

Similar to Maximum Subarray, Best Time to Buy and Sell Stock II / III / IV

这道题求进行一次交易能得到的最大利润。如果用brute force的解法就是对每组交易都看一下利润，取其中最大的，总用有n*(n-1)/2个可能交易，所以复杂度是O(n^2)
很容易感觉出来这是动态规划的题目，其实跟Maximum Subarray非常类似，用“局部最优和全局最优解法”。思路是维护两个变量，一个是到目前为止最好的交易，另一个是在当前一天卖出的最佳交易（也就是局部最优）
递推式是local[i+1]=max(local[i]+prices[i+1]-price[i],0), global[i+1]=max(local[i+1],global[i])。这样一次扫描就可以得到结果，时间复杂度是O(n)。而空间只需要两个变量，即O(1)
这种题目的解法非常经典，不过是比较简单的动态规划。这道题目还有两个变体，Best Time to Buy and Sell Stock II和Best Time to Buy and Sell Stock III，虽然题目要求比较像，但是思路却变化比较大，Best Time to Buy and Sell Stock II可以交易无穷多次，思路还是比较不一样，而Best Time to Buy and Sell Stock III则限定这道题交易两次，其实还可以general到限定交易k次，会在这道题目中仔细讲解

This a a simple but interesting dynamical programming problem. Assume D[i] is the maximum profit for prices[0:i-1], then
D[i]=Max(D[j-1],D[i]-Min{prices[0:i-1]})
public class Solution {
    public int maxProfit(int[] prices) {
      if(prices.length<2) return 0;
      int res=0,min=prices[0];
      for(int i=1;i<prices.length;i++){
          res=Math.max(res,prices[i]-min);
          min=Math.min(min,prices[i]);
      }
      return res;
    }
}

Reference:
http://www.ninechapter.com/solutions/best-time-to-buy-and-sell-stock/
http://blog.csdn.net/linhuanmars/article/details/23162793
https://yusun2015.wordpress.com/2015/01/03/best-time-to-buy-and-sell-stock/
*/