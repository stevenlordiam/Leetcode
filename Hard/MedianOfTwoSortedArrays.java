/*
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0 ;
        } else {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }
    
    // find kth number of two sorted array
    public static int findKth(int[] A, int A_start, int[] B, int B_start, int k){		
		if(A_start >= A.length) 
			return B[B_start + k - 1];	// start from B (in case array out of bound)
		if(B_start >= B.length)
			return A[A_start + k - 1];	// start from A (in case array out of bound)

		if (k == 1)
			return Math.min(A[A_start], B[B_start]);
		
		int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
		int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE; 
		
		if (A_key < B_key) {
			return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
		} else {
			return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
		}
	}
}

/*
Similar to Merge Sorted Array - https://leetcode.com/problems/merge-sorted-array/

这道题比较直接的想法就是用Merge Sorted Array这个题的方法把两个有序数组合并，当合并到第(m+n)/2个元素的时候返回那个数即可，而且不用把结果数组存起来
算法时间复杂度是O(m+n)，空间复杂度是O(1)。因为代码比较简单，就不写出来了，跟Merge Sorted Array比较类似，大家可以参照这个题目的解法
接下来我们考虑有没有优化的算法。优化的思想来源于order statistics，在算法导论10.3节中提到。问题等价于求两个array的第k=(m+n)/2（假设m和n分别是两个数组的元素个数）大的数是多少
基本思路是每次通过查看两个数组的第k/2大的数(假设是A[k/2],B[k/2])，如果两个A[k/2]=B[k/2]，说明当前这个数即为两个数组剩余元素的第k大的数
如果A[k/2]>B[k/2], 那么说明B的前k/2个元素都不是我们要的第k大的数，反之则排除A的前k/2个，如此每次可以排除k/2个元素，最终k=1时即为结果
总的时间复杂度为O(logk),空间复杂度也是O(logk)，即为递归栈大小。在这个题目中因为k=(m+n)/2,所以复杂度是O(log(m+n))。比起第一种解法有明显的提高
public double findMedianSortedArrays(int A[], int B[]) {
    if((A.length+B.length)%2==1)
        return helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2+1);
    else
        return (helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2)  
               +helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2+1))/2.0;
}
private int helper(int A[], int B[], int i, int i2, int j, int j2, int k)
{
    int m = i2-i+1;
    int n = j2-j+1;
    if(m>n)
        return helper(B,A,j,j2,i,i2,k);
    if(m==0)
        return B[j+k-1];
    if(k==1)
        return Math.min(A[i],B[j]);
    int posA = Math.min(k/2,m);
    int posB = k-posA;
    if(A[i+posA-1]==B[j+posB-1])
        return A[i+posA-1];
    else if(A[i+posA-1]<B[j+posB-1])
        return helper(A,B,i+posA,i2,j,j+posB-1,k-posA);
    else
        return helper(A,B,i,i+posA-1,j+posB,j2,k-posB);
}
实现中还是有些细节要注意的，比如有时候剩下的数不足k/2个，那么就得剩下的，而另一个数组则需要多取一些数
但是由于这种情况发生的时候，不是把一个数组全部读完，就是可以切除k/2个数，所以不会影响算法的复杂度。
这道题的优化算法主要是由order statistics派生而来，原型应该是求topK的算法，这个问题是非常经典的问题，
一般有两种解法，一种是用quick select(快速排序的subroutine),另一种是用heap。topK问题在海量数据处理中也是一个非常经典的问题

Reference:
http://www.ninechapter.com/solutions/median-of-two-sorted-arrays/
http://blog.csdn.net/linhuanmars/article/details/19905515
*/