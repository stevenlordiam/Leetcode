/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/

public class NQueensII {    // 九章算法6 - Graph & Search
    public static int sum;
    public int totalNQueens(int n) {
        sum = 0;
        int[] usedColumns = new int[n];
        placeQueen(usedColumns, 0);
        return sum;
    }
    public void placeQueen(int[] usedColumns, int row) {
        int n = usedColumns.length;
        
        if (row == n) {
            sum ++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (isValid(usedColumns, row, i)) {
                usedColumns[row] = i;
                placeQueen(usedColumns, row + 1);
            }
        }
    }
    public boolean isValid(int[] usedColumns, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (usedColumns[i] == col) {
                return false;
            }
            if ((row - i) == Math.abs(col-usedColumns[i])) {        // ???
                return false;
            }
        }
        return true;
    }
}

/*
Analysis:
Use a vector b[] to resprent the state of the board. B[i] means there is a queen at position (i,B[i]).  Then we use dfs to fill vector b to find all distinct solutions.
For the position (i,j), if b[k]=j or |i-k|=|b[k]-j|  for some k<i, then it implies (i,j) conflicts with (k,b[k]), and can not put a queen at (i,j).
Do bfs, start with the first row, and find all the valid positions and go on to fill next row.
Return 1 if the row number is the length of b.
//
public class Solution {
    public int totalNQueens(int n) {
        int[] b=new int[n];
        return dfs(b,0);
    }
    public int dfs(int[] b,int len){
        if(len==b.length) return 1;
        int res=0;
        for(int i=0;i<b.length;i++){
            boolean valid=true;
            b[len]=i;
            for(int j=0;j<len;j++){
                if(b[j]==i||Math.abs(len-j)==Math.abs(b[j]-i)){
                    valid=false;
                    break;
                }
            }
            if(valid) res=res+dfs(b,len+1);
        }
        b[len]=0;
        return res;
    }
}

这道题跟N-Queens算法是完全一样的，只是把输出从原来的结果集变为返回结果数量而已。思路我们就不在赘述了，大家可以参见N-Queens，算法的时间复杂度仍然是指数量级的，空间复杂度是O(n)

Reference:
http://www.ninechapter.com/solutions/n-queens-ii/
http://blog.csdn.net/linhuanmars/article/details/20668017
https://yusun2015.wordpress.com/2015/01/05/n-queens-ii/
*/