/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

You should return [1,2,3,6,9,8,7,4,5].
*/

public class SpiralMatrix {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0) 
            return rst;
        
        int rows = matrix.length;			// num of row
        int cols = matrix[0].length;		// num of column
        int count = 0;                      // num of loop
        while(count * 2 < rows && count * 2 < cols){            // every loop starts with matrix[i][j] in the left-up point
            for(int i = count; i < cols-count; i++)             // 一层一层loop的打印，每打印完一层之后要判断是不是只剩下一行或者一列
                rst.add(matrix[count][i]);
            
            
            for(int i = count+1; i< rows-count; i++)
                rst.add(matrix[i][cols-count-1]);
            
            if(rows - 2 * count == 1 || cols - 2 * count == 1)  // if only one row /col remains(last loop)
                break;
                
            for(int i = cols-count-2; i>=count; i--)
                rst.add(matrix[rows-count-1][i]);
                
            for(int i = rows-count-2; i>= count+1; i--)
                rst.add(matrix[i][count]);
            
            count++;
        }
        return rst;
    }
}

/*
这道题基本思路跟Rotate Image有点类似，就是一层一层的处理，每一层都是按照右下左上的顺序进行读取就可以
实现中要注意两个细节，一个是因为题目中没有说明矩阵是不是方阵，因此要先判断一下行数和列数来确定螺旋的层数
另一个是因为一层会占用两行两列，如果是单数的，最后要将剩余的走完。所以最后还要做一次判断
因为每个元素访问一次，所以时间复杂度是O(m*n)，m，n是分别是矩阵的行数和列数，空间复杂度是O(1)

矩阵总结：(http://blog.csdn.net/linhuanmars/article/details/39248597)
矩阵（一般是二维数组）操作的题目在面试中考察基础coding的时候比较常见，一般来说不带有太多算法思想，纯粹就是二维数组下标的操作。
虽然比较简单，不过还是比较能体现基本的实现能力。LeetCode中关于矩阵操作的题目有以下几个：
Spiral Matrix
Spiral Matrix II
Rotate Image
Valid Sudoku
Set Matrix Zeroes

前面三个题Spiral Matrix，Spiral Matrix II，Rotate Image思路比较类似，都是按照一定的规则在二维数组里面走下标。
Spiral Matrix就是按照螺旋的方式，按照右下左上的顺序，走到头就换方向。小技巧就是把这些螺旋分成层，然后知道区间照着走就可以。
Spiral Matrix II也是一样的，不过是换成1到n^2的整数。而Rotate Image也是比较类似的，主要是想清楚每一个点经过旋转之后的下表位置，然后还是分成层，一层层进行旋转即可。

Valid Sudoku是让我们判断一个Sudoku的盘是否合法，对于行和列的判断应该比较简单，就是1到9不重复出现即可，有点技巧的是对于每个九宫格的合法性判断，
这里需要稍微斟酌一下才可以把代码写得比较通用简洁一些，大家可以看看Valid Sudoku中的实现哈。

最后我们来说说比较有技巧的Set Matrix Zeroes这道题。它是一个非常常见的题目了，也是cc150中的题目。
看似一个非常简单的题目，就是如果矩阵如果有元素为0，就把对应的行和列上面的元素都置为0，但是却有层层的优化方法。
时间复杂度基本都是O(m*n)，而空间复杂度却有很多可以优化的空间。最简单的思路是备份一个矩阵，然后照着原矩阵进行判断，有0则置对应列和行为0，这样空间是O(m*n)。
而如果我们维护一个行和列的布尔数组，然后扫描一遍矩阵记录那一行或者列是不是应该置0，最后在进行一次赋值，这样时间复杂度不变，而空间复杂度只需要O(m+n)。
再进一步优化，如果我们连布尔数组都不要，而用第一行和第一列来代替布尔数组，然后用两个变量来维护第一行和第一列的置0情况，这样就只需要O(1)的额外空间了。
模型非常简单的一个题目，却有非常不同的答案，还是比较值得一考的题目哈。

Reference:
http://blog.csdn.net/linhuanmars/article/details/21667181
(矩阵总结)http://blog.csdn.net/linhuanmars/article/details/39248597
http://www.ninechapter.com/solutions/spiral-matrix/
https://yusun2015.wordpress.com/2015/01/09/spiral-matrix-i-and-ii/
*/