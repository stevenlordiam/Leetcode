/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

public class MaximumProductSubarray { 			// Dynamic Programming
    public int maxProduct(int[] A) {
        if(A==null || A.length==0)
            return 0;
        if(A.length == 1)
            return A[0];
        int max_local = A[0];
        int min_local = A[0];
        int global = A[0];
        for(int i=1;i<A.length;i++){
            int max_copy = max_local;
            max_local = Math.max(Math.max(A[i]*max_local, A[i]), A[i]*min_local);
            min_local = Math.min(Math.min(A[i]*max_copy, A[i]), A[i]*min_local);
            global = Math.max(global, max_local);
        }
        return global;
    }
}

/*
Dynamic Programming

Similar to Maximum Subarray - https://leetcode.com/problems/maximum-subarray/

乘法和加法不同，可能出现负数或者0，所以除了维护一个最大乘积，还要有一个最小乘积（可能乘以一个负数变成最大）

This problem is very similar to Question [Maximum Sum Subarray]. There is a slight twist though.
Besides keeping track of the largest product, we also need to keep track of the smallest product. 
Why? The smallest product, which is the largest in the negative sense could become the maximum when being multiplied by a negative number.
Let us denote that:
f(k) = Largest product subarray, from index 0 up to k.
Similarly,
g(k) = Smallest product subarray, from index 0 up to k.
Then,
f(k) = max( f(k-1) * A[k], A[k], g(k-1) * A[k] )
g(k) = min( g(k-1) * A[k], A[k], f(k-1) * A[k] )
There we have a dynamic programming formula. Using two arrays of size n, we could deduce the final answer as f(n-1). 
Since we only need to access its previous elements at each step, two variables are sufficient.

public int maxProduct(int[] A) {
	assert A.length > 0;		// confirm a boolean statement is true
	int max = A[0], min = A[0], maxAns = A[0]; 
	for (int i = 1; i < A.length; i++) {
    	int mx = max, mn = min;
    	max = Math.max(Math.max(A[i], mx * A[i]), mn * A[i]);
    	min = Math.min(Math.min(A[i], mx * A[i]), mn * A[i]);
    	maxAns = Math.max(max, maxAns);
	}
    return maxAns;
}

这道题跟Maximum Subarray模型上和思路上都比较类似，还是用一维动态规划中的“局部最优和全局最优法”
这里的区别是维护一个局部最优不足以求得后面的全局最优，这是由于乘法的性质不像加法那样，累加结果只要是正的一定是递增，乘法中有可能现在看起来小的一个负数，后面跟另一个负数相乘就会得到最大的乘积
不过事实上也没有麻烦很多，我们只需要在维护一个局部最大的同时，在维护一个局部最小，这样如果下一个元素遇到负数时，就有可能与这个最小相乘得到当前最大的乘积和，这也是利用乘法的性质得到的
这道题是一道很不错的面试题目，因为Maximum Subarray这道题过于常见了，所以可能大部分人都做过，这道题模型类似，但是又有一些新的考点，而且总体还是比较简单，无论是思路上还是实现上，又能考察动态规划

Reference:
https://leetcode.com/problems/maximum-product-subarray/solution/
http://blog.csdn.net/linhuanmars/article/details/39537283
https://yusun2015.wordpress.com/2015/01/27/maximum-product-subarray/
*/