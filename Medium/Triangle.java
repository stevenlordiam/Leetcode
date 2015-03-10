/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

public class Trianglex {	// Bottom-Up
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int n = triangle.size(); 		// 层数
        int[][] sum = new int[n][n];

        for (int i = 0; i < n; i++) {
            sum[n - 1][i] = triangle.get(n - 1).get(i);		// 最后一层各个元素的路径和
        }

        for (int i = n - 2; i >= 0; i--) { 
            for (int j = 0; j <= i; j++) {
                sum[i][j] = Math.min(sum[i + 1][j], sum[i + 1][j + 1]) + triangle.get(i).get(j); 	// bottom-up
            }
        }

        return sum[0][0];	// finally at the top with the smallest sum
    }
}

/*
The idea is simple (https://leetcode.com/discuss/23544/my-8-line-dp-java-code-4-meaningful-lines-with-o-1-space)
1) Go from bottom to top
2) We start form the row above the bottom row [size()-2]
3) Each number add the smaller number of two numbers that below it
4) And finally we get to the top we the smallest sum

这是一道动态规划的题目，求一个三角形二维数组从顶到低端的最小路径和。思路是维护到某一个元素的最小路径和，那么在某一个元素i，j的最小路径和就是它上层对应的相邻两个元素的最小路径和加上自己的值，递推式是sum[i][j]=min(sum[i-1][j-1],sum[i-1][j])+triangle[i][j]
最后扫描一遍最后一层的路径和，取出最小的即可。每个元素需要维护一次，总共有1+2+...+n=n*(n+1)/2个元素，所以时间复杂度是O(n^2)。而空间上每次只需维护一层即可（因为当前层只用到上一层的元素），所以空间复杂度是O(n)
上述代码实现时要注意每层第一个和最后一个元素特殊处理一下。换个角度考虑一下，如果这道题不自顶向下进行动态规划，而是放过来自底向上来规划，递归式只是变成下一层对应的相邻两个元素的最小路径和加上自己的值，原理和上面的方法是相同的，这样考虑到优势在于不需要最后对于所有路径找最小的，而且第一个元素和最后元素也不需要单独处理了，所以代码简洁了很多
这道题是不错的动态规划题目，模型简单，比较适合在电面中出现

Reference:
https://leetcodenotes.wordpress.com/2013/11/18/leetcode-triangle/
http://www.ninechapter.com/solutions/triangle/
http://blog.csdn.net/linhuanmars/article/details/23230657
https://yusun2015.wordpress.com/2015/01/12/triangle/
http://fisherlei.blogspot.com/2013/01/leetcode-triangle.html
http://www.bubuko.com/infodetail-509949.html
http://www.faceye.net/search/120975.html
https://leetcode.com/discuss/23544/my-8-line-dp-java-code-4-meaningful-lines-with-o-1-space
https://leetcode.com/discuss/20232/solution-satisfy-detailed-explanation-update-simplicity
*/