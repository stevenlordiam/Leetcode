/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
*/

public class BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        if(m == 0) {
            return 0;
        }
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }
}

/*
基本思路就是从高位往低位找如果找到不一样就跳出
比如7和5
0111
0101
从左往右第二位是一样， 第三位不一样就跳出

这道题的意思是，计算从m到n的非负整数的按位与值
其实就是m和n公共头部。例子中5的二进制为101,7的二进制位111，其公共头部为100
再如，若计算3到5的按位或，3的二进制位11，5的二进制位101，没有公共头部，返回0

Solution 1:
The idea is very simple:
- last bit of (odd number & even number) is 0.
- when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
- Move m and n rigth a position.
- Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time.

Solution 2:
The idea is to use a mask to find the leftmost common digits of m and n. 
Example: m=1110001, n=1110111, and you just need to find 1110000 and it will be the answer.
public class Solution {
	public int rangeBitwiseAnd(int m, int n) {
    	int r=Integer.MAX_VALUE;
    	while((m&r)!=(n&r)) {
    		r=r<<1;
    	}
    	return n&r;
    }
}

Reference:
http://blog.csdn.net/kangrydotnet/article/details/45099051
http://www.cnblogs.com/grandyang/p/4431646.html
http://www.meetqun.com/thread-8773-1-1.html
http://www.meetqun.com/thread-8769-1-1.html
https://leetcode.com/discuss/32115/bit-operation-solution-java
https://leetcode.com/discuss/32053/accepted-c-solution-with-simple-explanation
https://leetcode.com/discuss/32017/my-simple-java-solution-3-lines
*/