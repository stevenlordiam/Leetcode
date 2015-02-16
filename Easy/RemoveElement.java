/*
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/

public class RemoveElement {
    public int removeElement(int[] A, int elem) {
        if(A==null){            // always check for null
            return 0;
        }
        int len = 0;
        for(int i=0; i<A.length; i++){
            if(A[i]!=elem){     // when not equal, set the original number to the new array, otherwise jump over the number equivalent to elem
                A[len]=A[i];
                len++;
            }
        }
        return len;
    }
}

/*
Remember in place replacement -- not check for equality, but check for inequality

Reference:
https://leetcodenotes.wordpress.com/2013/11/03/leetcode-remove-duplicates-from-sorted-array-1-2-%E4%BB%8E%E6%95%B0%E7%BB%84%E9%87%8Cinplace%E5%88%A0%E9%99%A4%E9%87%8D%E5%A4%8D/
*/