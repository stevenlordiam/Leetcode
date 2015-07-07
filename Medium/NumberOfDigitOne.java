/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13

Hint:
Beware of overflow
*/

public class NumberOfDigitOne {
    public int countDigitOne(int n) {
        long result = 0;
        long base = 1;
        while (n/base > 0) {
            long cur = (n/base)%10;
            long low = n - (n/base) * base;;
            long high = n/(base * 10);
            if (cur == 1) {
                result += high * base + low + 1;
            } else if (cur < 1) {
                result += high * base;
            } else {
                result += (high + 1) * base;
            }
            base *= 10;
        }
        return (int)result;
    }
}

/*
https://leetcode.com/discuss/44281/4-lines-o-log-n-c-java-python
http://blog.csdn.net/nicaishibiantai/article/details/43369937
http://www.jiuzhang.com/problem/52/
http://www.cnblogs.com/lishiblog/p/4194962.html
https://github.com/shogunsea/lintcode/blob/master/digit-counts.java
http://blog.xiaohuahua.org/2015/01/25/lintcode-digit-counts/
http://www.myext.cn/c/a_7543.html
*/