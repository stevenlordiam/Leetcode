/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/

public class SearchInRotatedSortedArrayII {
     public boolean search(int[] A, int target) {
       return searchHelper(A,0,A.length-1,target);
     }
     public boolean searchHelper(int[] A,int i,int j, int target){
         if(j<i) return false;
         if(A[i]==A[j]){    // there's dup, search for the dups
             if(A[i]==target) return true;
             else return searchHelper(A,i+1,j-1,target);
         }
         else{              // same as no dups
             int mid=(i+j)/2;
             if(A[mid]==target) return true;
             if(A[mid]<=A[j]){
                 if(target>A[mid]&&target<=A[j]) return searchHelper(A,mid+1,j,target);
                 else return searchHelper(A,i,mid-1,target);
             }
             else{
                 if(target<A[mid]&&target>=A[i]) return searchHelper(A,i,mid-1,target);
                 else return searchHelper(A,mid+1,j,target);
             }
         }
     }
}


/*
Simialr to CC150 (11-3) find element in sorted array (SortingAndSearching_3)

Similar to Search in Rotated Sorted Array and Find Minimum in Sorted Rotated Array I/II

这个题尽管是CC150上的，理论上很简单，但是想明白（而且用好的方法）很不容易
1. 数组没有重复
既然是断开一次的数组，说明A[p] > A[q]。我们从中间一刀划分，想出找sorted那一半，看是否包含x，包含的话就在sorted那一半找；不包含就在broken那一半找
这个是根据A[mid]和A[p]来初步划分，平时binary search都是根据A[mid]和x来直接划分。所以一开始做复杂了。这个题按上面说的定义做最好，简单易懂
当A[mid] == A[p]的时候，说明mid==p，就左边这一个点，既然已经判断过A[mid] != x了，所以直接找右边就好了

2. 数组有重复数字
还是从中间一刀划分，想找出sorted那一半，看是否包含x。
但是当A[p] == A[mid]的时候，不能说明左边就是长度为1的一半了。左边可以是全是repeat，也可以是包含x
这时要和A[q]比较，如果A[mid] == A[q]，总不能整个数组平了吧。这时候x可以是左边也可以是在右边，必须两边找
如果A[mid] != A[q]，说明右半部分有起伏，左边的平的这段就不能有起伏了（自己画一下吧）。所以还是找右边

// it ends up the same as sequential search
// We used linear search for this question just to indicate that the 
// time complexity of this question is O(n) regardless of binary search is applied or not
public class Solution {
    public boolean search(int[] A, int target) {
        for (int i = 0; i < A.length; i ++) {
            if (A[i] == target) {
                return true;
            }
        }
        return false;
    }
}

这道题是二分查找Search Insert Position的变体，思路在Search in Rotated Sorted Array中介绍过了，
和Search in Rotated Sorted Array唯一的区别是这道题目中元素会有重复的情况出现。不过正是因为这个条件的出现，出现了比较复杂的case，甚至影响到了算法的时间复杂度。
原来我们是依靠中间和边缘元素的大小关系，来判断哪一半是不受rotate影响，仍然有序的。而现在因为重复的出现，如果我们遇到中间和边缘相等的情况，我们就丢失了哪边有序的信息，因为哪边都有可能是有序的结果。
假设原数组是{1,2,3,3,3,3,3}，那么旋转之后有可能是{3,3,3,3,3,1,2}，或者{3,1,2,3,3,3,3}，这样的我们判断左边缘和中心的时候都是3，如果我们要寻找1或者2，我们并不知道应该跳向哪一半。
解决的办法只能是对边缘移动一步，直到边缘和中间不在相等或者相遇，这就导致了会有不能切去一半的可能。所以最坏情况（比如全部都是一个元素，或者只有一个元素不同于其他元素，而他就在最后一个）就会出现每次移动一步，总共是n步，算法的时间复杂度变成O(n)
以上方法和Search in Rotated Sorted Array是一样的，只是添加了中间和边缘相等时，边缘移动一步，但正是这一步导致算法的复杂度由O(logn)变成了O(n)

Reference:
https://leetcodenotes.wordpress.com/2013/10/13/leetcode-search-in-rotated-sorted-array-12-%E8%A2%AB%E6%97%8B%E8%BD%AC%E4%B8%80%E6%AC%A1%E7%9A%84sorted%E6%95%B0%E7%BB%84%EF%BC%8C%E6%89%BEx%E7%9A%84index/
http://www.ninechapter.com/solutions/search-in-rotated-sorted-array-ii/
https://yusun2015.wordpress.com/2015/01/08/search-in-rotated-sorted-array-iii/
http://blog.csdn.net/linhuanmars/article/details/20588511
*/