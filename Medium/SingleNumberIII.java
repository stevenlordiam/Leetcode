/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:
Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

public class SingleNumberIII {
	/*
	先将所有的数异或，得到的将是x和y以后之后的值n。 因为这两个数一定是不同的，所以最终异或的值至少有一个位为1
    找到这个数n的为1的某一位（为了方便就取最右边为1的一位， n & ~(n-1))，然后根据该位的值是否为1，将数组中的每一个数，分成两个部分
    这样每个部分，就可以采用Sing number I中的方法得到只出现一次的数
	*/
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int n = 0;
        for (int elem : nums) {
            n ^= elem;
        }
        n = n & (~(n-1));
        for (int elem : nums) {
            if ((elem & n) != 0) {
                res[0] = res[0]^elem;
            }
            else res[1] = res[1]^elem;
        }
        return res;
    }
}

/*
https://leetcode.com/discuss/52351/accepted-java-space-easy-solution-with-detail-explanations
http://www.cnblogs.com/EdwardLiu/p/4391455.html
https://richdalgo.wordpress.com/2015/02/01/lintcode-single-number-iii/
https://github.com/shawnfan/LintCode/blob/master/Java/Single%20Number%20III.java
http://www.wengweitao.com/lintcode-single-number-i-ii-iii-luo-dan-de-shu.html
*/