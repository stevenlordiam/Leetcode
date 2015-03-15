/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/


public class JumpGame {						// greedy
    public boolean canJump(int[] A) { 		// think it as merging n intervals
        if (A == null || A.length == 0) {
            return false;
        }
        int farthest = A[0];                // local optimal solution v.s. global optimal solution
        for (int i = 1; i < A.length; i++) {
            if (i <= farthest && A[i] + i >= farthest) {
                farthest = A[i] + i;
            }
        }
        return farthest >= A.length - 1;
    }
}


/*
Similar to Jump Game II - https://leetcode.com/problems/jump-game-ii/

DP / Greedy Algorithm

没必要把每个i能不能走到都算出来，只要算出最后一跳能不能>=n就行了。
算法：keep一个var，表示目前看来最远能走到哪。比如现在在i上，已知之前算的从0~i-1上最远就能跳到m处，那么如果i <= m，就说明i这个位置是能跳到的。为什么这就说明了呢？因为你的m是在0~i-1中的某个index上算出的，比如i0，那么说明从i0一直到m全能跳到（一步一步跳），而i还>i0，所以i到m之间当然也能一步一步跳到了。
public boolean canJump(int[] A) {
  int farIndex = 0;
  for (int i = 0; i < A.length; i++) {
    if (i <= farIndex){
      farIndex = Math.max(farIndex, A[i] + i);
    }
  }
  return farIndex >= A.length - 1;
}

这道题是动态规划的题目，所用到的方法跟是在Maximum Subarray中介绍的套路，用“局部最优和全局最优解法”，我们维护一个到目前为止能跳到的最远距离，以及从当前一步出发能跳到的最远距离
局部最优local=A[i]+i，而全局最优则是global=Math.max(global, local)。递推式出来了，代码就比较容易实现了。因为只需要一次遍历时间复杂度是O(n)，而空间上是O(1)
这也是一道比较经典的动态规划的题目，不过不同的切入点可能会得到不同复杂度的算法，比如如果维护的历史信息是某一步是否能够到达，那么每一次需要维护当前变量的时候就需要遍历前面的所有元素，那么总的时间复杂度就会是O(n^2)
所以同样是动态规划，有时候也会有不同的角度，不同效率的解法。这道题目还有一个扩展Jump Game II

// version 1: Dynamic Programming
public class Solution {
    public boolean canJump(int[] A) {
        boolean[] can = new boolean[A.length];
        can[0] = true;       
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (can[j] && j + A[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }       
        return can[A.length - 1];
    }
}

// version 2: Greedy
public class Solution {
    public boolean canJump(int[] A) {			// think it as merging n intervals
        if (A == null || A.length == 0) {
            return false;
        }
        int farthest = A[0];
        for (int i = 1; i < A.length; i++) {
            if (i <= farthest && A[i] + i >= farthest) {
                farthest = A[i] + i;
            }
        }
        return farthest >= A.length - 1;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/07/19/jump-game/
http://www.ninechapter.com/solutions/jump-game/
http://blog.csdn.net/linhuanmars/article/details/21354751
https://yusun2015.wordpress.com/2015/01/11/jump-game/
*/