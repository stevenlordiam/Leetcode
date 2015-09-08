/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
*/

public class ReverseInteger {
    public int reverse(int x) {
        int result = 0;
        while(x!=0){
            if (Math.abs(result) > 214748364) {         // handle overflow/underflow, should first divide the Integer.MAX_VALUE by 10, otherwise cannot compare
                return 0;
            }
            result = result*10+x%10;
            x /= 10;                                    // reverse an integer
        }
        return result;
    }
}

/*
Notice:
1) +/- sign; 2) integer overflow; 3) remember to check for the case 10, 100
4)abs(Integer.MIN_VALUE) == abs(Integer.MAX_VALUE) + 1

Thought:
maybe put all the digit in an array and reverse them? (Not good, it's space complexity)
*/