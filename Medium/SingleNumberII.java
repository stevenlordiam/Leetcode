/*
Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

public class SingleNumberII {
    public int singleNumberII(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int result=0;
        int[] bits=new int[32];
        for (int i = 0; i < 32; i++) {
            for(int j = 0; j < A.length; j++) {
                bits[i] += (A[j] >> i) & 1;     // get the i-th bit of A[j] (THIS IS IMPORTANT!!!) 
                bits[i] %= 3;                   // if appear 3 times it's 0, if only once it's 1
            }
            result |= (bits[i] << i);           // assemble the bits array to result integer
        }
        return result;
    }
}

/*
Similar to Single Number

Solution 1: HashSet
用一个HashMap对于出现的元素进行统计，key是元素，value是出现个数，如果元素出现三次，则从HashMap中移除，
最后在HashMap剩下来的元素就是我们要求的元素（因为其他元素都出现三次，有且仅有一个元素不是如此）。
这样需要对数组进行一次扫描，所以时间复杂度是O(n)，而需要一个哈希表来统计元素数量，总共有(n+2)/3个元素，所以空间复杂度是O((n+2)/3)=O(n)。

Solution 2: Bit Operation
在LeetCode的题目中要求我们不要用额外空间来实现，也就是O(1)空间复杂度。实现的思路是基于数组的元素是整数，
我们通过统计整数的每一位来得到出现次数。我们知道如果每个元素重复出现三次，那么整数转换为二进制后每一位出现1的次数也会是3的倍数，如果我们统计完对每一位进行取余3，
那么结果中就只剩下那个出现一次的元素。总体只需要对数组进行一次线性扫描，统计完之后每一位进行取余3并且将位数字赋给结果整数，
这是一个常量操作（因为整数的位数是固定32位），所以时间复杂度是O(n)。而空间复杂度需要一个32个元素的数组，也是固定的，因而空间复杂度是O(1)。
这个题目主要是对整数数组的操作，用到的位统计是整数中经常使用的技巧，利用位的固定性来省去一定的时间或者空间复杂度。

*******************************************************************************************************
bit operation: (see CC150 chapter5 for details)
clear the i roghtmost bits of a:    a&(~0<<i)
get i-th bit of a:                  a&(1<<i)
set i-th bit of a to 1:             a|(1<<i)
clear i-th bit of a to 0:           a &= ~(1<<i)
创建一个长度为32的数组a，a[i]表示所有数字在i位出现的次数。假如a[i]是3的整数倍，则忽略；否则就把该位取出来组成答案。空间复杂度O(1).
*******************************************************************************************************

Reference:
http://blog.csdn.net/linhuanmars/article/details/22645599
http://www.ninechapter.com/solutions/single-number-ii/
https://leetcodenotes.wordpress.com/2013/10/07/leetcode-single-number-2-%E4%B8%80%E4%B8%AA%E6%95%B0%E7%BB%84%E9%99%A4%E4%BA%86x%E4%BB%A5%E5%A4%96%E5%85%A8%E9%83%BD%E6%AD%A3%E5%A5%BD%E5%87%BA%E7%8E%B0%E4%B8%89%E6%AC%A1%EF%BC%8C%E6%B1%82x/
https://yusun2015.wordpress.com/2015/01/07/single-number-ii/
*/