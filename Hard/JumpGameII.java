/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*/

public class JumpGameII {
    public int jump(int[] A) {
      int res=0;
      int start=0,end=0,i=0;
      while(start<A.length-1){
          if(A[start]+start>=A.length-1) return res+1;
          while(i<=A[start]+start){         // A[start]+start 第一步的最远距离
              if(A[i]+i>A[end]+end) end=i;  // (如果某一次第一步中的某些步可以跳到最后)end表示第i步可以跳到最后
              i++;
          }
          if(end==start) return -1;
          start=end; 
          res++;
      }
      return res;
    }
}

/*
Similar to Jump Game - https://leetcode.com/problems/jump-game/

这个是要算到达最后一步最少要跳几步。不用非的跳到last index上，越过去也算。还是，用dp的话有重复，不需要算d[i]跳到每一步用的最小步数，只要算最后一步能不能跳到，最少用几步就行了 
算法：在每一步i，都已知到当前这一点用的最小step数k（但是不是存在数组里的，而是local variable），在每层小循环里，都是“走一步能到的这些点中”， 最远能到哪一点，记载为p，那么到p就能最少用k + 1步就能到了
如果p出了数组边界，直接返回k + 1，就做出来了。要是没出界，则小循环之后，继续下一层循环，但是下一层循环和当前循环不重合，因为下一层说明多走了一步，则k++， 那循环的起始部分就是上一个小循环的尾
（每层小循环的意义是：用k步能走到的这些点，都试探一下，看看从他们身上走一步最远能走到哪）

// version 1: Dynamic Programming
public class Solution {
    public int jump(int[] A) {
        int[] steps = new int[A.length];
        steps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }
        return steps[A.length - 1];
    }
}

// version 2: Greedy
public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0, end = 0, jumps = 0;
        while (end < A.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (A[i] + i > farthest) {
                    farthest = A[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }
}

这道题是Jump Game的扩展，区别是这道题不仅要看能不能到达终点，而且要求到达终点的最少步数
其实思路和Jump Game还是类似的，只是原来的全局最优现在要分成step步最优和step-1步最优（假设当前步数是step）
当走到超过step-1步最远的位置时，说明step-1不能到达当前一步，我们就可以更新步数，将step+1
时间复杂度仍然是O(n)，空间复杂度也是O(1)，动态规划是面试中特别是onsite中非常重要的类型，一般面试中模型不会过于复杂，
所以可以熟悉一下比较经典的几个题，比如Jump Game，Maximum Subarray等

Analysis:
Every time choose the one which has potential to allow your to jump farther (A[i]+i)

SOLUTION 1:
参考：http://blog.csdn.net/fightforyourdream/article/details/14517453
我们可以使用贪心法来解决这个问题，从后往前思考问题。以 des = len - 1反向思考。思考能到达它的最远的距离是多少
例子：
2 3 1 1 4
   i = 1
上例子中index i = 1是达到4的最远的距离。这时index i = 1 到4是最后一步的最优解，因为其它的解，如果跳到index = 2, 3 到达4为最后一步，那么倒数第二步既然可以到达 index = 2, 3 也可以到达index = 1，所以跳到index = 1步数会是一样的
所以其它的解最好的情况也就是与从index = 1跳过去一样而已，而前面的点因为距离限制，有可能只能跳到index = 1,而不可以跳到index = 2, 3. 所以 将倒数第二步设置在index = 1可以得到最多的解

SOLUTION 2:
Leetcode增强test case之后，前面的算法不能通过，感谢http://fisherlei.blogspot.com/2012/12/leetcode-jump-ii.html的灵感：
[解题思路]
二指针问题，最大覆盖区间。
从左往右扫描，维护一个覆盖区间，每扫过一个元素，就重新计算覆盖区间的边界。比如，开始时区间[start, end], 遍历A数组的过程中，不断计算A[i]+i最大值（即从i坐标开始最大的覆盖坐标），并设置这个最大覆盖坐标为新的end边界
而新的start边界则为原end+1。不断循环，直到end> n. 当n=1的时候，需要特殊处理一下

Reference:
https://leetcodenotes.wordpress.com/2013/07/21/jump-game-2/
http://www.ninechapter.com/solutions/jump-game-ii/
http://www.cnblogs.com/yuzhangcmu/p/4148858.html
http://blog.csdn.net/linhuanmars/article/details/21356187
https://yusun2015.wordpress.com/2015/01/11/jump-game/
*/