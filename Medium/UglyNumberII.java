/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:
The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5)
*/

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        if (n < 1) return 0;
        Queue<Long> queue2 = new LinkedList<>();
        Queue<Long> queue3 = new LinkedList<>();
        Queue<Long> queue5 = new LinkedList<>();
        queue2.add(1l);     // long
        long val = 0;
        for (int i = 0; i < n; i++) {
            long v2 = queue2.isEmpty() ? Long.MAX_VALUE : queue2.peek();
            long v3 = queue3.isEmpty() ? Long.MAX_VALUE : queue3.peek();
            long v5 = queue5.isEmpty() ? Long.MAX_VALUE : queue5.peek();
            val = Math.min(v2, Math.min(v3, v5));
            if (val == v2) {
                queue2.poll();
                queue2.add(val*2);
                queue3.add(val*3);
            }
            else if (val == v3) {
                queue3.poll();
                queue3.add(val*3);
            }
            else
                queue5.poll();
            queue5.add(val*5);
        }
        return (int)val;
    }
}

/*
https://leetcode.com/discuss/52710/java-solution-with-three-queues
https://leetcode.com/discuss/52716/o-n-java-solution
https://leetcode.com/discuss/52739/share-clear-accepted-solution-using-queues-with-explanation
https://leetcode.com/discuss/52746/java-solution-using-priorityqueue-o-n
*/