/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
*/

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }  
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curt = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);         // 记住这种用法, max=Math.max(max, num)
            }
            stack.push(i);
        }
        return max;
    }
}

/*
Similar to Trapping Rain Water - https://leetcode.com/problems/trapping-rain-water/
Container With Most Water - https://leetcode.com/problems/container-with-most-water/

这个感觉很像那道二维直方图盛水的题，可惜那道是短板固定在两边，这题则是短板可以随意在中间任何一个位置。总之，这种题一定能找到一个状态，就是在i之前，i怎么变都是越来越大（小），但是在i处就不一定了，所以对i才进行运算，之前的就不用算了反正都是比算i的小（大）
算法：
穷举两个边界，但不是完全穷举，这里可以省掉一部分右右边界。对于i，如果A[i] <= A[i + 1]，即当前递增，则无论对于哪个左边界，选i+1作为右边界都比选i作为右边界合适（宽本来就多一个，然后高还更高，所以肯定选右边的面积大）。所以就算i+1作为右边界的所有左边界配对就行，没必要算i作为右边界的了
所以递推下去，只有A[i] > A[i + 1]的时候，在递减，说明拿A[i]和拿A[i + 1]不一定谁大了，这时候算一下A[i]作为右边界的左边界所有可能，就是一个local peak

这道题brute force的方法很直接，就是对于每一个窗口，找到其中最低的高度，然后求面积，去其中最大的矩形面积。总共有n^2个窗口，找最低高度是O(n)的操作，所以复杂度是O(n^3)
接下来我们讨论一种比较容易理解的思路，就是从每一个bar往两边走，以自己的高度为标准，直到两边低于自己的高度为止，然后用自己的高度乘以两边走的宽度得到矩阵面积
因为我们对于任意一个bar都计算了以自己为目标高度的最大矩阵，所以最好的结果一定会被取到。每次往两边走的复杂度是O(n)，总共有n个bar，所以时间复杂度是O(n^2)
最后我们谈谈最优的解法，思路跟Longest Valid Parentheses比较类似，我们要维护一个栈，这个栈从低向上的高度是依次递增的，如果遇到当前bar高度比栈顶元素低，那么就出栈直到满足条件，过程中检测前面满足条件的矩阵
关键问题就在于在出栈时如何确定当前出栈元素的所对应的高度的最大范围是多少。答案跟Longest Valid Parentheses中括号的范围相似，就是如果栈已经为空，说明到目前为止所有元素（当前下标元素除外）都比出栈元素高度要大（否则栈中肯定还有元素），所以矩阵面积就是高度乘以当前下标i
如果栈不为空，那么就是从当前栈顶元素的下一个到当前下标的元素之前都比出栈元素高度大（因为栈顶元素第一个比当前出栈元素小的）
实现中注意最后还要对剩下的栈做清空并且判断，因为算法中每次是对于前面的元素面积进行判断，所以循环结束中如果栈中仍有元素，还是要继续维护面积直到栈为空
这道题思路还是比较绕的，大家可能要仔细想想才能理清。不过解法确实很简练，总共只需要扫描一遍，所以时间复杂度是O(n)，空间复杂度是栈的大小，最坏情况是O(n)
这道题的扩展题目是Maximal Rectangle，用到了这道题作为subroutine，是一道比较复杂的题目

Analysis:
This is a difficult problem. Use a stack,
- if stack is empty or height[i] is greater than the height of the top position in stack, push i into the stack;
- if height[i] is less than the height[top of stack], pop the stack;
- calculate the area equal to the height of the popped position multiplying by the distance between i and previous element in the stack(if stack is empty, the distance is i)

Reference:
https://leetcodenotes.wordpress.com/2013/07/21/largest-rectangle-in-histogram/
http://www.ninechapter.com/solutions/largest-rectangle-in-histogram/
http://blog.csdn.net/linhuanmars/article/details/20524507
https://yusun2015.wordpress.com/2015/01/15/largest-rectangle-in-histogram/
*/