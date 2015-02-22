/*
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

public class SingleNumber {
    public int singleNumber(int[] A) {
        int res=0;
        for(int i=0;i<A.length;i++){
            res^=A[i]; 		// use XOR to delete the elements which appear twice (THIS IS IMPORTANT!!!)
        }
        return res;
    }
}

/*
It is nothing but bit manipulation. Use XOR(^) operation to delete the elements which appear twice.(THIS IS IMPORTANT!!!)
XOR: same-0, diff-1
利用每个元素出现两次，以及位操作异或的性质来解决这个问题。因为两个相同的元素异或结果是0，利用这个特点我们可以对所有数组元素进行异或，
如果出现两次的元素就会自行抵消，而最终剩下的元素则是出现一次的元素。这个方法只需要一次扫描，即O(n)的时间复杂度，而空间上也不需要任何额外变量。

Similar to Single Number II - https://oj.leetcode.com/problems/single-number-ii/

A more general solution is mod, see - http://blog.csdn.net/linhuanmars/article/details/22645599

Reference:
https://yusun2015.wordpress.com/2015/01/03/single-number/
http://blog.csdn.net/linhuanmars/article/details/22648829
http://blog.csdn.net/linhuanmars/article/details/22645599
https://leetcodenotes.wordpress.com/2013/10/07/leetcode-single-number-2-%E4%B8%80%E4%B8%AA%E6%95%B0%E7%BB%84%E9%99%A4%E4%BA%86x%E4%BB%A5%E5%A4%96%E5%85%A8%E9%83%BD%E6%AD%A3%E5%A5%BD%E5%87%BA%E7%8E%B0%E4%B8%89%E6%AC%A1%EF%BC%8C%E6%B1%82x/
*/