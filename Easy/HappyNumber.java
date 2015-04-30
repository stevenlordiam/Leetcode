/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

public class HappyNumber {
    public boolean isHappy(int n) {
        int slow = digitSquareSum(n);
        int fast = digitSquareSum(digitSquareSum(n));
        while(slow != 1 && slow != fast) {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        }
        if (slow == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n > 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

}

/*
I see the majority of those posts use hashset to record values
Actually, we can simply adapt the Floyd Cycle detection algorithm
I believe that many people have seen this in the Linked List Cycle detection problem

public boolean isHappy(int n) {
    HashSet<Integer> hashSet = new HashSet<Integer>();
    while (!hashSet.contains(n)) { // use hashset to avoid duplicate result which is not a happy number
        hashSet.add(n);
        int temp = 0;
        while (n > 0) {
            temp += Math.pow(n % 10, 2);
            n /= 10;
        }
        n = temp;
    }
    return n == 1;
}

Reference:
https://leetcode.com/discuss/33055/my-solution-in-c-o-1-space-and-no-magic-math-property-involved
https://leetcode.com/discuss/32842/use-the-same-way-as-checking-cycles-in-a-linked-list
https://leetcode.com/discuss/32924/short-java-code
*/