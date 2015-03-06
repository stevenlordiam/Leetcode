/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

public class ClimbStairs {
    public int climbStairs(int n) {
        int stepOne = 1;
        int stepTwo = 2;
        if(n<=1){
            return n;
        }
        if(n==2){
            return stepTwo;
        }
        for(int i=3;i<=n;i++){
            int stepThree = stepOne+stepTwo;
            stepOne = stepTwo;              // if n-1 step took i ways, then n if stepOne, i+1 way([1]); if stepTwo, i+2 way([1,1] or [2])
            stepTwo = stepThree;
        }
        return stepTwo;
    }
}

/*
Similar to CC150 (9-1) Climbing Stairs (RecursionAndDynamicProgramming_1.java)

Notice:
This is Fibonacci number, time complexity is O(n)

Similiar solution:
https://oj.leetcode.com/discuss/24794/short-java-solution

A better solution is O(logn), see at: 
http://blog.csdn.net/linhuanmars/article/details/23976963
*/