/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

public class UniquePathII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] paths = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] != 1) {
                paths[i][0] = 1;
            } else {
                break;
            }
        }
        
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[0][i] != 1) {
                paths[0][i] = 1; 
            } else {
                break;      // continue跳出本次循环继续下次循环(i+1), break跳出当前for循环(一层)
            }
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] != 1) {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                } else {
                    paths[i][j] = 0;    // meet obstacle
                }
            }
        }

        return paths[n - 1][m - 1];
    }
}

/*
Similar to CC150 (9-2) Unique Paths - (RecursionAndDynamicProgramming_2.java)

d[i][j]表示从(0, 0)到(i, j)有几种走法，因为只能从上面来或从左边来，所以每次计算只需要d[i – 1][j]和d[i, j – 1]，左上角的全都浪费了
f[i]表示从(0,0)到(当前row, j)有几种走法，上面来的就是自己，左边来的已经算好了，所以 f[j] = f[j] + f[j – 1];
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length, n = obstacleGrid[0].length;
    // one way DP, f[i] means from (0, 0) to (currRow, i) has how many ways
    int f[] = new int[n];
    f[0] = obstacleGrid[0][0] > 0 ? 0 : 1;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) { //comes from above or left 
            if (obstacleGrid[i][j] > 0) {
                f[j] = 0;
            } else {
                if (j == 0) //first col only from above
                    f[j] = f[j];
                else 
                    f[j] = f[j] + f[j - 1];
            }
        }
    }
    return f[n - 1];
}

这道题跟Unique Paths非常类似，只是这道题给机器人加了障碍，不是每次都有两个选择（向右，向下）了。因为有了这个条件，所以Unique Paths中最后一个直接求组合的方法就不适用了，这里最好的解法就是用动态规划
递推式还是跟Unique Paths一样，只是每次我们要判断一下是不是障碍，如果是，则res[i][j]=0，否则还是res[i][j]=res[i-1][j]+res[i][j-1]。实现中还是只需要一个一维数组，因为更新的时候所需要的信息足够了
这样空间复杂度是是O(n)（如同Unique Paths中分析的，如果要更加严谨，我们可以去行和列中小的那个，然后把小的放在内层循环，空间复杂度就是O(min(m,n))，时间复杂度还是O(m*n)
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length,n=obstacleGrid[0].length;
        int[] res=new int[n+1];
        res[0]=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(obstacleGrid[i][j]==0){
                   res[j+1]=res[j]+res[j+1];
                }
                else res[j+1]=0;
            }
            res[0]=0;
        }
        return res[res.length-1];   
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/11/02/summery-leetcode-unique-path2-%E6%9C%BA%E5%99%A8%E4%BA%BA%E5%B8%A6%E9%9A%9C%E7%A2%8D%EF%BC%88%E7%AE%80%E5%8D%95%E4%BE%8B%E5%AD%90demonstrate-2waydp%E5%8F%981way%EF%BC%89/
http://www.ninechapter.com/solutions/unique-paths-ii/
http://blog.csdn.net/linhuanmars/article/details/22135231
https://yusun2015.wordpress.com/2015/01/10/unique-paths-ii/
*/