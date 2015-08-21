/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/

public class SqrtX {
    public int sqrt(int x) {
        if(x<0) return -1;
        if(x==0) return 0;
        if(x==1) return 1;
        int left=1,right=x-1; 		
        while(left+1<right){ // 注意是left+1 !!!
            int mid=left+(right-left)/2;
            // 如果是mid*mid可以在之前定义一个midSquare这样可以不用重复计算mid*mid的值
            // 不能是mid*mid，应该是mid==x/mid，否则会overflow
            if(mid==(x/mid)) return mid;
            if(mid>(x/mid)) right=mid;
            else left=mid;
        }
        return left;
    }
}

/*
Bisection Method - way to find the root of f(x):
http://en.wikipedia.org/wiki/Bisection_method

Newton's Method:
http://en.wikipedia.org/wiki/Newton%27s_method

The result has to be an integer and it is not necessary to have sqrt(x)=x/sqrt(x).

Reference:
https://oj.leetcode.com/problems/sqrtx/
http://www.ninechapter.com/solutions/sqrtx/
https://github.com/leetcoders/LeetCode-Java/blob/master/Sqrt(x).java
(Bisection Method) https://leetcodenotes.wordpress.com/2013/10/06/leetcode-sqrtint-%E6%B1%82%E4%B8%80%E4%B8%AA%E6%AD%A3%E6%95%B4%E6%95%B0%E7%9A%84%E5%B9%B3%E6%96%B9%E6%A0%B9/
(Newton's Method) http://blog.csdn.net/linhuanmars/article/details/20089131
*/