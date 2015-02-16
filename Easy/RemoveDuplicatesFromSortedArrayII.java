/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
*/

public class Solution {
    public int removeDuplicates(int[] A) {
       if(A.length<2){
           return A.length;
       }
       int counter = 1;
       for(int i=2; i<A.length; i++){
           if(A[i]==A[counter-1]){ 			// compare the current examinated element and the second last element of the result array, but not A[i] and A[i-1]
               continue;
           }
           else{
               counter++;
               A[counter]=A[i];
           }
       }
       return counter+1;
    }
}

/*
Because it's already sorted, so we can use a counter and a "result array"(which is just the original array with replaced elements, no additional space needed)
Compare the current examinated element and the second last element of the result array, but not A[i] and A[i-1] (THIS IS IMPORTANT!)

Another solution: 
Using Java collection like ArrayList and remove the duplicated elements
(http://stackoverflow.com/questions/642897/removing-an-element-from-an-array-java)

Reference:
https://leetcodenotes.wordpress.com/2013/11/03/leetcode-remove-duplicates-from-sorted-array-1-2-%E4%BB%8E%E6%95%B0%E7%BB%84%E9%87%8Cinplace%E5%88%A0%E9%99%A4%E9%87%8D%E5%A4%8D/
http://blog.csdn.net/linhuanmars/article/details/24343525
*/