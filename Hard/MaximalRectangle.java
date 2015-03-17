/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*/

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length < 1) return 0;
        int n = matrix.length;
        if (n == 0) return 0;
        int m = matrix[0].length;
        if (m == 0) return 0;
        int[][] height = new int[n][m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i == 0)
                    height[i][j] = ((matrix[i][j] == '1') ? 1 : 0);		// start from 1
                else
                    height[i][j] += ((matrix[i][j] == '1') ? height[i-1][j] + 1 : 0);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; ++i) {
            Stack<Integer> S = new Stack<Integer>();  
            for (int j = 0; j < m; j++) {
                while (!S.empty() && height[i][j] < height[i][S.peek()]) {
                    int pos = S.peek();
                    S.pop();
                    answer = Math.max(answer, height[i][pos]*(S.empty()? j : j-S.peek()-1));  // ???
                }
                S.push(j); // 0 to m-1
            }
            while (!S.empty()) {
                int pos = S.peek();
                S.pop();
                answer = Math.max(answer, height[i][pos]*(S.empty()? m : m-S.peek()-1));
            }   // check for last element m
        } 
        return answer;
    }
}

/*
Similar to CC150 (18-11) Maximal Rectangle

Similar to Largest Rectangle in Histogram - https://leetcode.com/problems/largest-rectangle-in-histogram/

算法：
先用dp求一个新矩阵，d[i][j]表示以(i, j)结尾有几个连续1（在当前row)
然后遍历这个新矩阵，在每个cell，都看看“宽度是d[i][j]的矩阵最多能有多高？“，也就是往上expand到宽度变窄为止，
往下expand到宽度变窄为止，然后总高度×当前宽度就是d[i][j]所属于的矩阵的最大面积。这就是个O(M * N) * O(M)
把二维降成一维怎么做，不也是找以当前结束的1有多长吗
public int maximalRectangle(char[][] matrix) {
    if (matrix.length == 0)
        return 0;
    int res = 0;
    int m = matrix.length, n = matrix[0].length;
    int[][] d = new int[m][n];
    for (int i = 0; i < m; i++) {
        d[i][0] = matrix[i][0] - '0';
        for (int j = 1; j < n; j++) {
            d[i][j] = matrix[i][j] == '1' ? d[i][j - 1] + 1 : 0;
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            res = Math.max(res, expand(d, i, j));
        }
    }
    return res;
}
private int expand(int[][] d, int I, int J) {
    int height = 0;
    int width = d[I][J];
    //go up
    for (int i = I - 1; i >= 0; i--) {
        if (d[i][J] >= width) {
            height++;
        } else {
            break;
        }
    }
    //go down
    for (int i = I; i < d.length; i++) {
        if (d[i][J] >= width) {
            height++;
        } else {
            break;
        }
    }
    return width * height;
}

这是一道非常综合的题目，要求在0-1矩阵中找出面积最大的全1矩阵。刚看到这道题会比较无从下手，brute force就是对于每个矩阵都看一下，
总共有m(m+1)/2*n(n+1)/2个子矩阵（原理跟字符串子串类似，字符串的子串数有n(n+1)/2，只是这里是二维情形，所以是两个相乘），复杂度相当高，肯定不是面试官想要的答案，就不继续想下去了
这道题的解法灵感来自于Largest Rectangle in Histogram这道题，假设我们把矩阵沿着某一行切下来，然后把切的行作为底面，将自底面往上的矩阵看成一个直方图（histogram）。直方图的中每个项的高度就是从底面行开始往上1的数量
根据Largest Rectangle in Histogram我们就可以求出当前行作为矩阵下边缘的一个最大矩阵，接下来如果对每一行都做一次Largest Rectangle in Histogram，从其中选出最大的矩阵，那么它就是整个矩阵中面积最大的子矩阵
算法的基本思路已经出来了，剩下的就是一些节省时间空间的问题了。我们如何计算某一行为底面时直方图的高度呢？ 如果重新计算，那么每次需要的计算数量就是当前行数乘以列数
然而在这里我们会发现一些动态规划的踪迹，如果我们知道上一行直方图的高度，我们只需要看新加进来的行（底面）上对应的列元素是不是0，如果是，则高度是0，否则则是上一行直方图的高度加1
利用历史信息，我们就可以在线行时间内完成对高度的更新。我们知道，Largest Rectangle in Histogram的算法复杂度是O(n)。所以完成对一行为底边的矩阵求解复杂度是O(n+n)=O(n)
接下来对每一行都做一次，那么算法总时间复杂度是O(m*n)。空间上，我们只需要保存上一行直方图的高度O(n)，加上Largest Rectangle in Histogram中所使用的空间O(n)，所以总空间复杂度还是O(n)
这道题最后的复杂度是非常令人满意的，居然在O(m*n)时间内就可以完成对最大矩阵的搜索，可以看出这已经是下界（因为每个元素总要访问一下才知道是不是1）了。难度还是比较大的，相信要在面试当场想到这种方法是很不容易的。这道题，既用到了别的题目，又有动态规划的思想

Analysis:
Have to use the method in LARGEST RECTANGLE IN HISTOGRAM to calculate the area of the rectangle, as for any row we can think about all the 1s above form a histogram. So for any row we have to calculate the heights at each position.
public class Solution {
    public int maximalRectangle(char[][] matrix) {
       int m=matrix.length;
       if(m==0) return 0;
       int n=matrix[0].length,res=0;
       int[] h=new int[n];
       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               if(matrix[i][j]=='1') h[j]++;
               else h[j]=0;
           }
           res=Math.max(res,maximalH(h));
       }
       return res;
    }
    public int maximalH(int[] A){
        int r=A[0];
        Stack<Integer> stack=new Stack<>();
        int i=0;
        while(i<=A.length){
            if(!stack.isEmpty()&&(i==A.length||A[i]<A[stack.peek()])){
                r=Math.max(r,A[stack.pop()]*(stack.isEmpty()?i:i-stack.peek()-1));
            }
            else{
                stack.push(i);
                i++;
            }
        }
        return r;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/19/leetcode-maximal-rectangle-0101%E7%BB%84%E6%88%90%E7%9A%84%E7%9F%A9%E9%98%B5%EF%BC%8C%E6%B1%82%E9%87%8C%E9%9D%A2%E5%85%A8%E6%98%AF1%E7%9A%84%E7%9F%A9%E5%BD%A2%E7%9A%84%E6%9C%80%E5%A4%A7%E9%9D%A2/
http://www.ninechapter.com/solutions/maximal-rectangle/
http://blog.csdn.net/linhuanmars/article/details/24444491
https://yusun2015.wordpress.com/2015/01/16/maximal-rectangle/
*/