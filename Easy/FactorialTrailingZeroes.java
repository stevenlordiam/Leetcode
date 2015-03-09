/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int sum=0;			// number of '5', the number of '0' == the number of '5'
        int i=n/5;			// say n=126, then i=25 here, in the loop, sum=0+25+5+1=31
        					// say n=26, then i=5 here, in the loop, sum=0+5+1=6
        while(i>0){
            sum=sum+i;
            i=i/5;
        }
        return sum;
    }
}

/*
Similar to CC150 (17-3)

Need to find how many 5s there are as factor of n!

10 is the product of 2 and 5. In n!, we need to know how many 2 and 5, and the number of zeros is the minimum of the number of 2 and the number of 5.
Since multiple of 2 is more than multiple of 5, the number of zeros is dominant by the number of 5. 
Sometimes one number may have several 5 factors, for example, 25 have two 5 factors, 125 have three 5 factors. 

Explanation:
https://oj.leetcode.com/discuss/20691/my-explanation-of-the-log-n-solution
https://oj.leetcode.com/discuss/19855/my-one-line-solutions-in-3-languages
*/