/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2]
*/

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] A) {
        if(A.length<2){             // null and one element 
            return A.length;
        }
        int len = 1;
        for(int i=1; i<A.length; i++){
            if(A[i]!=A[i-1]){
                A[len]=A[i];
                len++;
            }
        }
        return len;
    }
}

/*
Similar to Remove Element, just need to know:
A[i]!=A[i-1], and int i = 1

Reference:
https://leetcodenotes.wordpress.com/2013/11/03/leetcode-remove-duplicates-from-sorted-array-1-2-%E4%BB%8E%E6%95%B0%E7%BB%84%E9%87%8Cinplace%E5%88%A0%E9%99%A4%E9%87%8D%E5%A4%8D/
http://blog.csdn.net/linhuanmars/article/details/20023993
*/