/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/

public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length; 		  // row
        int n=matrix[0].length;		// column
        int i=0,j=m*n-1;
        while(i<=j){
            int mid=(i+j)/2;
            if(matrix[mid/n][mid%n]==target) return true;     // 用 matrix[mid/n][mid%n] 表示矩阵元素
            if(matrix[mid/n][mid%n]<target) i=mid+1;
            else j=mid-1;
        }
        return false;
    }
}

/*
Similar to Search in Rotated Sorted Array I/II, Find Minimum in Sorted Rotated Array I/II, Search Insert Position, Search For A Range

Similar to CC150 (11.6) search a 2D matrix (SortingAndSearching_6.java)

这道题是二分查找Search Insert Position的题目，因为矩阵是行有序并且列有序，查找只需要先按行查找，定位出在哪一行之后再进行列查找即可，
所以就是进行两次二分查找。时间复杂度是O(logm+logn)，空间上只需两个辅助变量，因而是O(1)

REMEMBER: 用 matrix[mid/n][mid%n] 表示矩阵元素

两个比较好的解法：
1. O(n)的linear解法：
因为二维矩阵是行列都sort好的，所以最上面一行和最右边一列正好形成了一个sort好的递增数列。取右上角这个A[i][j]，它左边的全<x, 它下边的全>x，所以每次可以删掉一行/列
这样每次往下/往左走一步，最后要是走到左下角了还没找到，那走的最多步数是m + n步。所以时间是O(m + n)
public boolean searchMatrixLinear(int[][] matrix, int target) {
  int i = 0, j = matrix[0].length - 1;  // j是第一行最后一个
  while (i < matrix.length && j >= 0) {
    if (matrix[i][j] == target)
      return true;
    else if (matrix[i][j] > target) // eliminate col, go left
      j--;
    else
      i++; // eliminate row, go down
  }
  return false;
}

2. O(logm * logn)的更快的解法：
每次取中间一列，然后在这一列里面binary search，要是没找到，最后会找到(j1, j2)这样的两个位置，A[i][j1] < x && A[i][j2]
说明(i, j1)左上的全<x，(i, j 2)右下的全>x，说明x肯定不在这两块里面，就可以eliminate掉了。在剩下的两块里面各自找x，然后返回||
这个时间复杂度为什么是O(logm * logn)？每次删掉行数的一半，是logm，在每个check的row里面做一次binary search是logn
public boolean searchMatrixFastest(int[][] matrix, int target) {
  return searchBinary(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, target);
}
private boolean searchBinary(int[][] matrix, int i1, int i2, int j1, int j2, int x) {
  if (i1 > i2 || j1 > j2)
    return false;
  int midRow = (i1 + i2) / 2; 
  int midCol = searchInRow(matrix, midRow, j1, j2, x); 
  if (midCol == -10) //用-10不用-1因为可能没找到但是q=-1
    return true;
  return searchBinary(matrix, i1, midRow - 1, midCol + 1, j2, x) || searchBinary(matrix, midRow + 1, i2, j1, midCol, x);
}
private int searchInRow(int[][] matrix, int i, int p, int q, int x) {
  while (p <= q) {
    int mid = (p + q) / 2;
    if (matrix[i][mid] == x)
      return -10;
    else if (matrix[i][mid] > x) // go up
      q = mid - 1;
    else
      p = mid + 1;
  }
  return q;
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/13/leetcode-search-a-2d-matrix-%E4%BA%8C%E7%BB%B4%E7%9F%A9%E9%98%B5%E6%89%BE%E5%85%B6%E4%B8%AD%E6%9F%90%E4%B8%AAelement%E7%9A%84%E4%BD%8D%E7%BD%AE/
(Binary Search Once and Twice) http://www.ninechapter.com/solutions/search-a-2d-matrix/
https://yusun2015.wordpress.com/2015/01/09/search-a-2d-matrix/
http://blog.csdn.net/linhuanmars/article/details/24216235
*/