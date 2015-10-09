/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses. 

Note: 
All costs are positive integers. 

Follow up: 
Could you solve it in O(nk) runtime? 
*/

public class PaintHouseII { 
	// A Time O(n*K) Solution: Use two variables min1 and min2, where min1 is the minimum value, whereas min2 is next to the minimum value. 
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
         
        int n = costs.length;
        int k = costs[0].length;
         
        // dp[j] means the min cost for color j
        int[] dp = new int[k];
        int min1 = 0;
        int min2 = 0;
 
        for (int i = 0; i < n; i++) {
            int oldMin1 = min1;
            int oldMin2 = min2;
             
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
             
            for (int j = 0; j < k; j++) {
                if (dp[j] != oldMin1 || oldMin1 == oldMin2) {
                    dp[j] = oldMin1 + costs[i][j];
                } else {
                    dp[j] = oldMin2 + costs[i][j];
                }
                 
                if (min1 <= dp[j]) {
                    min2 = Math.min(min2, dp[j]);
                } else {
                    min2 = min1;
                    min1 = dp[j];
                }
            }
             
        }
         
        return min1;
    }
}

/*
Referene:
http://buttercola.blogspot.com/2015/09/leetcode-paint-house-ii.html
https://aquahillcf.wordpress.com/2015/09/07/leetcode-paint-house-ii/
https://leetcode.com/discuss/54415/ac-java-solution-without-extra-space
https://leetcode.com/discuss/52964/my-accept-java-o-nk-solution
https://leetcode.com/discuss/52969/java-o-nkk-and-improved-o-nk-solution
http://www.meetqun.com/thread-10660-1-1.html
http://www.cnblogs.com/easonliu/p/4784858.html
*/