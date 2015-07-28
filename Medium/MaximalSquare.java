/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4
*/

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length ==0|| matrix[0].length == 0) {
            return  0;
        }

        int[] dp = new int[matrix[0].length +1];
        int max = 0;
        int tmp1 = 0;
        int tmp2 = 0;

        for(int i=0; i < matrix.length; i++) {
            tmp1 = 0;
            for(int j=0; j < matrix[0].length; j++) {
                tmp2 = dp[j+1];
                if(matrix[i][j] == '1') {
                    dp[j+1] = Math.min(tmp1, Math.min(dp[j], dp[j+1])) + 1;
                    max = Math.max(max, dp[j+1]);
                } else {
                    dp[j+1] = 0;
                }
                tmp1 = tmp2;
            }
        }
        return max*max;
    }
}

/*
We assume here that columns are less than rows, otherwise we can put the row loop inside of column loop to archive the O(min(m,n)), tmp is to be used for remember the left-up conner value.
Note: we have to set the tmp1 to 0 before inside loop, otherwise the left-up value might not be zero for calculating the first point of each row, because the tmp1 has the carry-over value from previous row.

Reference:
https://leetcode.com/discuss/38622/my-accepted-java-bottom-up-dp-solution
https://leetcode.com/discuss/38607/2-java-solutions-both-dp-308ms-and-348ms
https://leetcode.com/discuss/38751/java-solution-simple-and-easy-understand-with-explanation
https://leetcode.com/discuss/39389/my-accepted-java-solution-time-o-mn-space-o-min-m-n
https://leetcode.com/discuss/38489/easy-solution-with-detailed-explanations-8ms-time-and-space
*/