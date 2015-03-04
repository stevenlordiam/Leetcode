/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

public class SearchInsertPosition {
    public int searchInsert(int[] A, int target) {
        int i=0,j=A.length-1;
        while(i<=j){
            int mid=(i+j)/2;
            if(A[mid]==target) return mid;
            if(A[mid]<target) i=mid+1;
            else j=mid-1;
        }
        return i;
    }
}

/*
Similar to Search in Rotated Sorted Array I/II and Find Minimum in Sorted Rotated Array I/II
CC150 - (11.3)

This is a good template for binary search.
public class Solution {			
    public int searchInsert(int[] A, int target) {
        int i=0,j=A.length-1;
        while(i<=j){
            int mid=(i+j)/2;
            if(A[mid]==target) return mid;
            if(A[mid]<target) i=mid+1;
            else j=mid-1;
        }
        return i;
    }
}

这道题比较简单，就是二分查找。思路就是每次取中间，如果等于目标即返回，否则根据大小关系切去一半。因此算法复杂度是O(logn)，空间复杂度O(1)
注意以上实现方式有一个好处，就是当循环结束时，如果没有找到目标元素，那么l一定停在恰好比目标大的index上，r一定停在恰好比目标小的index上，所以个人比较推荐这种实现方式
二分查找是一个非常经典的方法，不过一般在面试中很少直接考二分查找，会考一些变体，例如Search in Rotated Sorted Array，Search for a Range和Search a 2D Matrix，思路其实是类似的

Reference:
http://www.ninechapter.com/solutions/search-insert-position/
https://yusun2015.wordpress.com/2015/01/05/search-insert-position/
http://blog.csdn.net/linhuanmars/article/details/20278967
*/