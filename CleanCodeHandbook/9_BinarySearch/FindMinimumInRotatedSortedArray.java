/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

public class FindMinimumInRotatedSortedArray {      // binary search
    public int findMin(int[] num) {
        int start = 0, end = num.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (num[mid] >= num[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (num[start] < num[end]) {
            return num[start];
        } else {
            return num[end];
        }
    }
}

/*
Simialr to CC150 (11-3) find element in sorted array (SortingAndSearching_3)

Similar to Search in Rotated Sorted Array - https://oj.leetcode.com/problems/search-in-rotated-sorted-array/
Find Minimum in Rotated Sorted Array II - https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

The minimum is at Ai where Ai-1 > Ai. Notice that if we subdivide the array into two, one will always be sorted, while the other contains the minimum.

Imagine we have an array [1,2,3,4,5,6,7] (See graph 1) which was being rotated 3 steps to the right [5,6,7,1,2,3,4] (See graph 2). Let’s say we subdivide the array at point k to two subarrays [AL, AL+1, …, Ak], [Ak+1, …, AR].

If the sorted array is not rotated, then AL < AR then we could return AL as the minimum immediately.

Otherwise for a sorted array that was rotated at least one step, AL must always be greater than AR.

Let’s assume we choose M1 as the dividing point. Since AM1 > AR, we know that each element in [AL … AM1] is greater than AR (Remember that AL > AR?). Therefore, the minimum value must locate in [AM1+1 … AR].

On the other hand, let’s assume we choose M2 as the dividing point. Since AM2 ¬≤ AR, we know that each element in [AM2+1 … AR] is greater than AM2. Therefore, the minimum point must locate in [AL … AM2].

As we are discarding half of the elements at each step, the runtime complexity is O(log n).

To understand the correct terminating condition, we look at two elements. Let us choose the lower median as M = (L + R) / 2. Therefore, if there are two elements, it will choose AL as the first element.

There are two cases for two elements:

A = [1,2]
B = [2,1]
For A, 1 < 2 => AM < AR, and therefore it will set R = M => R = 0.

For B, 2 > 1 => AM > AR, and therefore it will set L = M + 1 => L = 1.

Therefore, it is clear that when L == R, we have found the minimum element.

public int findMin(int[] A) {
   int L = 0, R = A.length - 1;
   while (L < R && A[L] >= A[R]) {
      int M = (L + R) / 2;
      if (A[M] > A[R]) {
         L = M + 1;
      } else {
		     R = M; }
	}
    return A[L];
}


For binary search the termination condition is import. Assume i is the start and j is the end.

- Use (i<j) if i=mid+1, the result is at i;
- and i<=j if i=mid+1 and j=mid-1, the result is at i;
- i+i<j if i=mid and j=mid and the result is at j;

otherwise may get infinite loops.

When array has duplicates,

- if A[i]=A[j], i–;
- then we must have A[i]>A[j] or A[i]<A[j];
- if A[mid]<=A[j], let j=mid;
- otherwise let i=mid+1;
- stop until i=j;
- return A[i].

public class Solution {
    public int findMin(int[] num) {
        int i=0,j=num.length-1;
        while(i<j){
            int mid=(j+i)/2;
            if(mid>0&&num[mid]<num[mid-1]) return num[mid];
            if(num[mid]<num[j]) j=mid;
            else i=mid+1;
        }
        return num[i];
    }
}


这道题是Search in Rotated Sorted Array的扩展，区别就是现在不是找一个目标值了，而是在bst中找最小的元素。
主要思路还是跟Search in Rotated Sorted Array差不多，还是通过左边界和中间的大小关系来得到左边或者右边有序的信息，
如果左半边有序，那么左半边最小就是左边第一个元素，可以和当前最小相比取小的，然后走向右半边。否则，那么就是右半半边第一个元素，然后走向左半边。
这样子每次可以截掉一半元素，所以最后复杂度等价于一个二分查找，是O(logn)，空间上只有四个变量维护二分和结果，所以是O(1)
上面的实现还有第三种情况，就是左边界和中间是相等的情况，这道题目其实是不用发生的，因为元素没有重复，而Find Minimum in Rotated Sorted Array II这道题这考虑了元素重复的情况

Reference:
https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/solution/
http://www.ninechapter.com/solutions/find-minimum-in-rotated-sorted-array/
https://yusun2015.wordpress.com/2015/01/07/find-minimum-in-rotated-sorted-array-i-and-ii/
http://blog.csdn.net/linhuanmars/article/details/40449295
*/