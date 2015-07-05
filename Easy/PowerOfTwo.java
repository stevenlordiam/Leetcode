/*
Given an integer, write a function to determine if it is a power of two
*/

public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        } else {
            return (n & (n - 1)) == 0;
        }
    }
}

/*
(x-1) & x == 0 → x是2的某次幂
http://algorithm.yuanbin.me/math_and_bit_manipulation/o1_check_power_of_2.html
*/