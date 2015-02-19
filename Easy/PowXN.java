/*
Implement pow(x, n)
*/

public class PowXN {
    public double pow(double x, int n) {
        if (x < 0) return (n % 2 == 0) ? pow(-x, n) : -pow(-x, n); 		// negative integer
        if (x == 0 || x == 1) return x; 		// 0 and 1
        if (n < 0) return 1.0 / pow(x,-n); 		// fraction
        if (n == 0) return 1.0; 				
        if (n == 1) return x;
        double half = pow(x,n/2);				// recursion
        if (n % 2 == 0) return half * half; 	// even number
        else return x * half * half; 			// odd number
    }
}

/*
Need to consider all the corner cases
Need to be careful for the corner case when n=Integer.MIN_VALUE

Reference:
https://github.com/leetcoders/LeetCode-Java/blob/master/Pow(x%2Cn).java
https://yusun2015.wordpress.com/2015/01/10/powx-n/
https://leetcodenotes.wordpress.com/2013/11/04/leetcode-powx-y-%E6%8C%87%E6%95%B0%E8%BF%90%E7%AE%97/
http://blog.csdn.net/linhuanmars/article/details/20092829
*/