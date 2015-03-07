/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/

public class FindMinimumInRotatedSortedArrayII {      // just search for minimum
    public int findMin(int[] num) {
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] < min)
                min = num[i];
        }
        return min;
    }
}

/*
Simialr to CC150 (11-3) find element in sorted array (SortingAndSearching_3)

Similar to Search in Rotated Sorted Array - https://oj.leetcode.com/problems/search-in-rotated-sorted-array/
Find Minimum in Rotated Sorted Array - https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/

For case where AL == AM == AR, the minimum could be on AM’s left or right side (eg, [1, 1, 1, 0, 1] or [1, 0, 1, 1, 1]). 
In this case, we could not discard either subarrays and therefore such worst case degenerates to the order of O(n).

public int findMin(int[] A) {
   int L = 0, R = A.length - 1;
   while (L < R && A[L] >= A[R]) {
      int M = (L + R) / 2;
      if (A[M] > A[R]) {
         L = M + 1;
      } else if (A[M] < A[L]) {
		     R = M;
 	    } else { // A[L] == A[M] == A[R]
		     L = L + 1;
	    }
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

public class Solution {                 // binary search
    public int findMin(int[] num) {
       int i=0;
       int j=num.length-1;
       while(i<j){
           if(num[i]==num[j]){
               j--;
               continue;
           }
           int mid=(i+j)/2;
           if(num[mid]<=num[j]) j=mid;
           else i=mid+1;
       }
       return num[i];
    }
}

这道题是Search in Rotated Sorted Array的扩展，思路在Find Minimum in Rotated Sorted Array中已经介绍过了，
和Find Minimum in Rotated Sorted Array唯一的区别是这道题目中元素会有重复的情况出现。不过正是因为这个条件的出现，影响到了算法的时间复杂度
原来我们是依靠中间和边缘元素的大小关系，来判断哪一半是不受rotate影响，仍然有序的。而现在因为重复的出现，如果我们遇到中间和边缘相等的情况，
我们就无法判断哪边有序，因为哪边都有可能有序。假设原数组是{1,2,3,3,3,3,3}，那么旋转之后有可能是{3,3,3,3,3,1,2}，或者{3,1,2,3,3,3,3}，
这样的我们判断左边缘和中心的时候都是3，我们并不知道应该截掉哪一半。解决的办法只能是对边缘移动一步，直到边缘和中间不在相等或者相遇，这就导致了会有不能切去一半的可能。
所以最坏情况就会出现每次移动一步，总共移动n此，算法的时间复杂度变成O(n)

Reference:
https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/
http://www.ninechapter.com/solutions/find-minimum-in-rotated-sorted-array-ii/
https://yusun2015.wordpress.com/2015/01/07/find-minimum-in-rotated-sorted-array-i-and-ii/
http://blog.csdn.net/linhuanmars/article/details/40449299
*/