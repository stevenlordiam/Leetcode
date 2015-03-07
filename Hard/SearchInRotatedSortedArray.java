/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

public class SearchInRotatedSortedArray {
    public int search(int[] A, int target) {		// binary search
        int i=0,j=A.length-1;
        while(i<=j){
            int mid=(i+j)/2;
            if(A[mid]==target) return mid;
            if(A[mid]<A[j]){
                if(target>A[mid]&&target<=A[j]) i=mid+1;
                else j=mid-1;
            }
            else{
                if(target<A[mid]&&target>=A[i]) j=mid-1;
                else i=mid+1;
            }
        }
        return -1;
    }
}

/*
Simialr to CC150 (11-3) find element in sorted array (SortingAndSearching_3)

Similar to Search in Rotated Sorted Array II and Find Minimum in Sorted Rotated Array I/II

这个题尽管是CC150上的，理论上很简单，但是想明白（而且用好的方法）很不容易。
1. 数组没有重复
既然是断开一次的数组，说明A[p] > A[q]。我们从中间一刀划分，想出找sorted那一半，看是否包含x，包含的话就在sorted那一半找；不包含就在broken那一半找
这个是根据A[mid]和A[p]来初步划分，平时binary search都是根据A[mid]和x来直接划分。所以一开始做复杂了。这个题按上面说的定义做最好，简单易懂
当A[mid] == A[p]的时候，说明mid==p，就左边这一个点，既然已经判断过A[mid] != x了，所以直接找右边就好了

2. 数组有重复数字
还是从中间一刀划分，想找出sorted那一半，看是否包含x
但是当A[p] == A[mid]的时候，不能说明左边就是长度为1的一半了。左边可以是全是repeat，也可以是包含x
这时要和A[q]比较，如果A[mid] == A[q]，总不能整个数组平了吧。这时候x可以是左边也可以是在右边，必须两边找
如果A[mid] != A[q]，说明右半部分有起伏，左边的平的这段就不能有起伏了（自己画一下吧）。所以还是找右边

Analysis:
This is a good question  for binary search:
- first find the position of the pivot
- do binary search for each subarray

这道题是二分查找Search Insert Position的变体，看似有点麻烦，其实理清一下还是比较简单的。因为rotate的缘故，当我们切取一半的时候可能会出现误区，所以我们要做进一步的判断
具体来说，假设数组是A，每次左边缘为l，右边缘为r，还有中间位置是m。在每次迭代中，分三种情况：
（1）如果target==A[m]，那么m就是我们要的结果，直接返回
（2）如果A[m]<A[r]，那么说明从m到r一定是有序的（没有受到rotate的影响），那么我们只需要判断target是不是在m到r之间，如果是则把左边缘移到m+1，否则就target在另一半，即把右边缘移到m-1
（3）如果A[m]>=A[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。
根据以上方法，每次我们都可以切掉一半的数据，所以算法的时间复杂度是O(logn)，空间复杂度是O(1)
注意在这道题中我们假设了这个数组中不会出现重复元素。如果允许出现重复元素，那么我们要对中间和边缘的相等的情况继续处理，进而影响到时间复杂度

Reference:
https://leetcodenotes.wordpress.com/2013/10/13/leetcode-search-in-rotated-sorted-array-12-%E8%A2%AB%E6%97%8B%E8%BD%AC%E4%B8%80%E6%AC%A1%E7%9A%84sorted%E6%95%B0%E7%BB%84%EF%BC%8C%E6%89%BEx%E7%9A%84index/
http://www.ninechapter.com/solutions/search-in-rotated-sorted-array/
https://yusun2015.wordpress.com/2015/01/08/search-in-rotated-sorted-array-iii/
http://blog.csdn.net/linhuanmars/article/details/20525681
*/