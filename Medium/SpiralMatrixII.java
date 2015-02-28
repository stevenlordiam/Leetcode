/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int k = 1;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (left < right && top < bottom) {			// 夹逼方法
            for (int j = left; j < right; j++) {		// left to right
                res[top][j] = k++;
            }
            for (int i = top; i < bottom; i++) {		// top to bottom
                res[i][right] = k++;
            }
            for (int j = right; j > left; j--) {		// right to left
                res[bottom][j] = k++;
            }
            for (int i = bottom; i > top; i--) {  		// bottom to top
                res[i][left] = k++;
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        if (n % 2 != 0)									// set the middle one if it's odd number, in this case left==right / top==bottom, need to set the one in the middle manually
            res[n / 2][n / 2] = k;
        return res;

    }
}

/*
这道题跟Spiral Matrix很类似，只是这道题是直接给出1到n^2，然后把这些数按照螺旋顺序放入数组中
思路跟Spiral Matrix还是一样的，就是分层，然后按照上右下左的顺序放入数组中。每个元素只访问一次，时间复杂度是O(n^2)

public class Solution {
    public int[][] generateMatrix(int n) {
        if (n < 0) {
            return null;
        }

        int[][] result = new int[n][n];

        int xStart = 0;
        int yStart = 0;
        int num = 1;

        while (n > 0) {
            if (n == 1) {
                result[yStart][xStart] = num++;
                break;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart][xStart + i] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + i][xStart + n - 1] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + n - 1][xStart + n - 1 - i] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + n - 1 - i][xStart] = num++;
            }

            xStart++;
            yStart++;
            n = n - 2;
        }

        return result;

}

Reference:
https://leetcodenotes.wordpress.com/2013/11/23/leetcode-spiral-matrix-%E6%8A%8A%E4%B8%80%E4%B8%AA2d-matrix%E7%94%A8%E8%9E%BA%E6%97%8B%E6%96%B9%E5%BC%8F%E6%89%93%E5%8D%B0/
(矩阵总结)http://blog.csdn.net/linhuanmars/article/details/39248597
http://www.ninechapter.com/solutions/spiral-matrix-ii/
https://yusun2015.wordpress.com/2015/01/09/spiral-matrix-i-and-ii/
*/