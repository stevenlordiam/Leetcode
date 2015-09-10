/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9
*/

public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        int sqrt = (int)Math.sqrt(n + 0.5);  // ceiling
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= sqrt; j++) {
                int next = i + j * j;
                if (next > n) break;
                dp[next] = Math.min(dp[next], dp[i] + 1);
            }
        }
        return dp[n];
    }
}

/*
Reference:
https://leetcode.com/discuss/56958/o-n-sqrt-n-sifting-method
http://www.cnblogs.com/jcliBlogger/p/4796240.html
https://leetcode.com/discuss/56978/concise-understand-java-solution-with-detailed-explanation
*/