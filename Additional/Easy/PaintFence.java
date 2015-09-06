/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Write an algorithm that counts the number of ways you can paint a fence with N posts using K colors such that no more than 2 adjacent fence posts are painted with the same color.
*/

public class PaintFence {
	public int numWays(int n, int k) {
	    if(n <= 0 || k <= 0) return 0;   
	    int prev_prev = k, prev = k * k;
	    for (int i = 0; i < n - 1; ++i) {
	        int old_dif = prev;
	        prev = (k - 1) * (prev_prev + prev);
	        prev_prev = old_dif;
	    }
	    return prev_prev;
	}	
}

/*
因为题目要求是不超过两个相邻的栅栏有同样颜色，所以可以把题目分解一下：
设T(n)为符合要求的染色可能总数，S(n)为最后两个相邻元素为相同颜色的染色可能数，D(n)为最后两个相邻元素为不同颜色的染色可能数。
显然D(n) = (k - 1) * (S(n-1) + D(n-1))
S(n) = D(n-1)
T(n) = S(n) + D(n)
带入化简一下得出：
T(n) = (k - 1) * (T(n-1) + T(n-2)), n > 2

Reference:
http://www.cnblogs.com/easonliu/p/4784886.html
http://www.fgdsb.com/2015/01/04/fence-painter/
http://skyzjkang.blogspot.com/2015/04/fence-painter.html
http://www.cnblogs.com/jcliBlogger/p/4783051.html
http://likemyblogger.blogspot.com/2015/09/leetcode-276-paint-fence.html
https://leetcode.com/discuss/56146/dynamic-programming-c-o-n-time-o-1-space-0ms
*/