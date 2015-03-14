/*
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. 
The number of elements initialized in A and B are m and n respectively.
*/

public class MergeSortedArray {
    public void merge(int A[], int m, int B[], int n) {
        int insertIndex = m+n-1;
        int indexA = m-1,indexB = n-1;
        while(indexB>=0){
            if(indexA<0){       // in case that A is empty
                A[insertIndex--] = B[indexB--];
            }else{
                if(B[indexB]>=A[indexA]){
                    A[insertIndex--] = B[indexB--];
                }else{
                    A[insertIndex--] = A[indexA--];
                }
            }
        }
    }
}

/*
Similar to CC150 (11-1) merge two sorted array - (SortingAndSearching_1.java)
Similar to Median Of Two Sorted Arrays - https://leetcode.com/problems/median-of-two-sorted-arrays/

The key idea is instead starting merging from the front, starting from the end, as the end of A is empty. 
Starting from the end is because that the result is still stored in A, if starting from the front, some unchecked elements may be overlapped.
Time complexity is O(m+n), Space complexity is O(1). Similiar to Merge Two Sorted Lists.

Reference:
http://blog.csdn.net/linhuanmars/article/details/19712333
https://yusun2015.wordpress.com/2015/01/07/merge-sorted-array
*/