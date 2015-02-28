/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

public class RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int length = matrix.length;

        for (int i = 0; i < length / 2; i++) {
            for (int j = 0; j < (length + 1) / 2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[length - j - 1][i];
                matrix[length -j - 1][i] = matrix[length - i - 1][length - j - 1];
                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
                matrix[j][length - i - 1] = tmp;
            }
        }   
    }
}

/*
Similar to CC150 (1-6) rotate image (ArraysAndStrings_6.java)

The original matrix is: 
1  	2   3   4  
5   6   7   8  
9   10  11  12  
13  14  15  16

After first rotation, the matrix is: (swap elements symmetrical to the diagonal)
1  5  9   13  
2  6  10  14  
3  7  11  15  
4  8  12  16  

After second rotation, the matrix is: (swap row[i](vertical) and row[n-1-i])
13  9   5  1  
14  10  6  2  
15  11  7  3  
16  12  8  4 

Analysis:
reflect the matrix about the up-right-down-left diagonal(swap (i,j) with (n-1-j,n-1-i))
reflect the matrix about the horizontal central line(swap (i,j) with (n-1-i,j))
By making the two reflection, we can rotate the image by 90

基本思路是把图片分为行数/2层，然后一层层进行旋转，每一层有上下左右四个列，然后目标就是把上列放到右列，右列放到下列，下列放到左列，左列放回上列，中间保存一个临时变量即可。
因为每个元素搬运的次数不会超过一次，时间复杂度是O(矩阵的大小)，空间复杂度是O(1)

Reference:
http://www.ninechapter.com/solutions/rotate-image/
https://yusun2015.wordpress.com/2015/01/09/rotate-image/
http://blog.csdn.net/linhuanmars/article/details/21503683
(矩阵总结)http://blog.csdn.net/linhuanmars/article/details/21503683
*/