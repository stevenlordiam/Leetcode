/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

public class SetMatrixZeroes {
    // using O(m+n) is easy, to enable O(1), we have to use the space within the matrix   
    public void setZeroes(int[][] matrix) {

		boolean[] row = new boolean[matrix.length];
		boolean[] column = new boolean[matrix[0].length];		

		for(int i=0;i<matrix.length;i++){					//flag zero
        	for(int j=0;j<matrix[0].length;j++){
            	if(matrix[i][j]==0){
            		row[i] = true;
            		column[j] = true;
            	}
            }
        }

        for(int i=0;i<matrix.length;i++){					//set zero
        	for(int j=0;j<matrix[0].length;j++){
            	if(row[i] || column[j]){
            		matrix[i][j]=0;
            	}
            }
        }

	}
}

/*
Similar to CC150 (1-7) set matrix to zeroes (ArraysAndStrings_7.java)
这是一个矩阵操作的题目，目标很明确，就是如果矩阵如果有元素为0，就把对应的行和列上面的元素都置为0。这里最大的问题就是我们遇到0的时候不能直接把矩阵的行列在当前矩阵直接置0，否则后面还没访问到的会被当成原来是0，最后会把很多不该置0的行列都置0了。
一个直接的想法是备份一个矩阵，然后在备份矩阵上判断，在原矩阵上置0，这样当然是可以的，不过空间复杂度是O(m*n)，不是很理想。
上面的方法如何优化呢？我们看到其实判断某一项是不是0只要看它对应的行或者列应不应该置0就可以，所以我们可以维护一个行和列的布尔数组，然后扫描一遍矩阵记录那一行或者列是不是应该置0即可，后面赋值是一个常量时间的判断。这种方法的空间复杂度是O(m+n)。
其实还可以再优化，我们考虑使用第一行和第一列来记录上面所说的行和列的置0情况，这里问题是那么第一行和第一列自己怎么办？想要记录它们自己是否要置0，只需要两个变量（一个是第一行，一个是第一列）就可以了。然后就是第一行和第一列，如果要置0，就把它的值赋成0（反正它最终也该是0，无论第一行或者第一列有没有0），否则保留原值。然后根据第一行和第一列的记录对其他元素进行置0。最后再根据前面的两个标记来确定是不是要把第一行和第一列置0就可以了。这样的做法只需要两个额外变量，所以空间复杂度是O(1)。
时间上来说上面三种方法都是一样的，需要进行两次扫描，一次确定行列置0情况，一次对矩阵进行实际的置0操作，所以总的时间复杂度是O(m*n)

这道题也是cc150里面比较经典的题目，看似比较简单，却可以重重优化，最终达到常量空间。其实面试中面试官看重的是对于算法时间空间复杂度的理解，对优化的概念，这些常常比题目本身的难度更加重要，平常做题还是要对这些算法分析多考虑哈。

Analysis: 

Use tow variable s to record whether the first row and column have zero or not;
Use first row and column to record whether the remaining rows and columns have zero or not;
Set (i,j) zeros if the ith row or jth column have zeros;
Set the first row or column zeros if they originally have.

Reference:
http://www.ninechapter.com/solutions/set-matrix-zeroes/
http://blog.csdn.net/linhuanmars/article/details/24066199
https://yusun2015.wordpress.com/2015/01/09/set-matrix-zeroes/
*/