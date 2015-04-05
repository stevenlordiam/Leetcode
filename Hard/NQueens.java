/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
For example, there exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

public class NQueens {      // 九章算法6 - Graph & Search
    private String[] drawChessboard(ArrayList<Integer> cols) {
        String[] chessboard = new String[cols.size()];
        for (int i = 0; i < cols.size(); i++) {
            chessboard[i] = "";
            for (int j = 0; j < cols.size(); j++) {
                if (j == cols.get(i)) {
                    chessboard[i] += "Q";
                } else {
                    chessboard[i] += ".";
                }
            }
        }
        return chessboard;
    }
    
    private boolean isValid(ArrayList<Integer> cols, int col) {
        int row = cols.size();
        for (int i = 0; i < row; i++) {
            // same column
            if (cols.get(i)== col)  {   // Arraylist.get() - returns the element at the specified position in the list
                return false;
            }
            // left-top to right-bottom
            if (i - cols.get(i) == row - col) {     
                return false;
            }
            // right-top to left-bottom
            if (i + cols.get(i) == row + col) {
                return false;
            }
        }
        return true;
    }
    
    private void search(int n, ArrayList<Integer> cols, ArrayList<String[]> result) {
        if (cols.size() == n) {
            result.add(drawChessboard(cols));
            return;
        }   
        for (int col = 0; col < n; col++) {
            if (!isValid(cols, col)) {
                continue;
            }
            cols.add(col);
            search(n, cols, result);
            cols.remove(cols.size() - 1);       // 回溯法
        }
    }
    
    public ArrayList<String[]> solveNQueens(int n) {
        ArrayList<String[]> result = new ArrayList<String[]>();
        if (n <= 0) {
            return result;
        }
        search(n, new ArrayList<Integer>(), result);
        return result;
    }
}

/*
二维数组char[][] board到leetcode上跑才发现超时，问题出现在算“对角能不能放”的时候，因为char[][]要遍历才能知道之前都在哪些位置放了皇后，所以多花了遍历的时间
书上的算法是用int[] queenColAtRow，这样queenColAtRow[i]就表示“在第i行，把queen放在了result位置上“。这样可以直接O(1)的知道每行queen都放在哪列了，少遍历一维，大的就过了

N皇后问题是非常经典的问题了。因为这个问题是典型的NP问题，所以在时间复杂度上就不用纠结了，肯定是指数量级的。下面我们来介绍这个题的基本思路
主要思想就是一句话：用一个循环递归处理子问题。这个问题中，在每一层递归函数中，我们用一个循环把一个皇后填入对应行的某一列中，如果当前棋盘合法，我们就递归处理先一行，找到正确的棋盘我们就存储到结果集里面
这种题目都是使用这个套路，就是用一个循环去枚举当前所有情况，然后把元素加入，递归，再把元素移除，这道题目中不用移除的原因是我们用一个一维数组去存皇后在对应行的哪一列，因为一行只能有一个皇后，如果二维数组，那么就需要把那一行那一列在递归结束后设回没有皇后，所以道理是一样的
这道题最后一个细节就是怎么实现检查当前棋盘合法性的问题，因为除了刚加进来的那个皇后，前面都是合法的，我们只需要检查当前行和前面行是否冲突即可。检查是否同列很简单，检查对角线就是行的差和列的差的绝对值不要相等就可以
这道题实现的方法是一个非常典型的套路，有许多题都会用到，基本上大部分NP问题的求解都是用这个方式，比如Sudoku Solver，Combination Sum，Combinations，Permutations，Word Break II，Palindrome Partitioning等

Analysis:
Use a vector b[] to resprent the state of the board. B[i] means there is a queen at position (i,B[i]).  Then we use dfs to fill vector b to find all distinct solutions
For the position (i,j), if b[k]=j or |i-k|=|b[k]-j|  for some k<i, then it implies (i,j) conflicts with (k,b[k]), and can not put a queen at (i,j)
Do bfs, start with the first row, and find all the valid positions and go on to fill next row
Return 1 if the row number is the length of b
//
public class Solution {
    public List<String[]> solveNQueens(int n) {
        int[] b=new int[n];
        List<String[]> list=new LinkedList<>();
        bfs(b,0,list);
        return list;
    }
    public void bfs(int[] b,int row,List<String[]> list){
        if(row==b.length) {
            printB(b,list);
            return;
        }
        for(int i=0;i<b.length;i++){
            b[row]=i;
            boolean valid=true;
            for(int j=0;j<row;j++){
                if(b[j]==i||Math.abs(row-j)==Math.abs(i-b[j])){
                    valid=false;
                    break;
                }
            }
            if(valid) bfs(b,row+1,list);
        }
        b[row]=0;
    }
    public void printB(int[] b,List<String[]> list){
        char[] c=new char[b.length];
        String[] s=new String[b.length];
        Arrays.fill(c,'.');
        for(int i=0;i<b.length;i++){
            c[b[i]]='Q';
            s[i]=new String(c);
            c[b[i]]='.';
        }
        list.add(s);
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/08/02/leetcode-n-queens-8%E7%9A%87%E5%90%8E%E9%97%AE%E9%A2%98/
http://www.ninechapter.com/solutions/n-queens/
http://blog.csdn.net/linhuanmars/article/details/20667175
https://yusun2015.wordpress.com/2015/01/05/n-queens-ii/
*/