/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
*/

public class SudokuSolver {
    public void solveSudoku(char[][] board){
        solve(board);
    }

    public boolean solve(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    continue;
                }
                // 对于每个格子，带入不同的9个数，然后判合法，如果成立就递归继续，结束后把数字设回空
                for(int k = 1; k <= 9; k++){
                    board[i][j] = (char) (k + '0');
                    if (isValid(board, i, j) && solve(board)){
                        return true;
                    }
                    board[i][j] = '.';
                }
                return false;
            }
        }
        return true;
    }
     
    public boolean isValid(char[][] board, int a, int b){
        Set<Character> contained = new HashSet<Character>();
        for(int j=0;j<9;j++){
            if(contained.contains(board[a][j])) return false;
            if(board[a][j]>'0' && board[a][j]<='9')
                contained.add(board[a][j]);
        }
            
        contained = new HashSet<Character>();
        for(int j=0;j<9;j++){
            if (contained.contains(board[j][b])) {
                return false;
            }
            if (board[j][b]>'0' && board[j][b]<='9') {
                contained.add(board[j][b]);
            }
        }
         
        contained = new HashSet<Character>();
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++){
                int x = a / 3 * 3 + m, y = b / 3 * 3 + n;
                if (contained.contains(board[x][y])) {
                    return false;
                }
                if (board[x][y] > '0' && board[x][y] <= '9') {
                        contained.add(board[x][y]);
                }
            } 
        }
    
        return true;
    }
}

/*
Analysis: 
use a list to record all the unfilled positions, then use dfs to find the solution.
//
public class Solution {
    public void solveSudoku(char[][] board) {
        List<int[]> list=new ArrayList<>(81);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.') list.add(new int[]{i,j});
            }
        }
        solveSudokuHelper(board,list);
    }
    public boolean solveSudokuHelper(char[][] board,List<int[]> list) {
        if(list.isEmpty()) return true;
        int[] can=list.get(0);
        for(char i='1';i<='9';i++){
            if(isValid(board,can[0],can[1],i)){
                board[can[0]][can[1]]=i;
                list.remove(0);
                if(solveSudokuHelper(board,list)) return true;
                else list.add(0,can);
            }
        }
        board[can[0]][can[1]]='.';
        return false;
    }
    public boolean isValid(char[][] board,int i, int j,char x){
        for(int k=0;k<9;k++){
            if(board[k][j]!='.'&&board[k][j]==x) return false;
            if(board[i][k]!='.'&&board[i][k]==x) return false;
            if(board[i/3*3+k/3][j/3*3+k%3]!='.'&&board[i/3*3+k/3][j/3*3+k%3]==x) return false;
        }
        return true;
    }
}

这道题的方法就是用在N-Queens中介绍的常见套路。简单地说思路就是循环处理子问题，对于每个格子，带入不同的9个数，然后判合法，如果成立就递归继续，结束后把数字设回空
大家可以看出代码结构和N-Queens是完全一样的。判合法可以用Valid Sudoku做为subroutine，但是其实在这里因为每次进入时已经保证之前的board不会冲突，
所以不需要判断整个盘，只需要看当前加入的数字和之前是否冲突就可以，这样可以大大提高运行效率，毕竟判合法在程序中被多次调用
public void solveSudoku(char[][] board) {
    if(board == null || board.length != 9 || board[0].length !=9)
        return;
    helper(board,0,0);
}
private boolean helper(char[][] board, int i, int j) {
    if(j>=9)
        return helper(board,i+1,0);
    if(i==9) {
        return true;
    }
    if(board[i][j]=='.') {
        for(int k=1;k<=9;k++) {
            board[i][j] = (char)(k+'0');
            if(isValid(board,i,j)) {
                if(helper(board,i,j+1))
                    return true;
            }
            board[i][j] = '.';
        }
    } else {
        return helper(board,i,j+1);
    }
    return false;
}
private boolean isValid(char[][] board, int i, int j) {
    for(int k=0;k<9;k++) {
        if(k!=j && board[i][k]==board[i][j])
            return false;
    }
    for(int k=0;k<9;k++) {
        if(k!=i && board[k][j]==board[i][j])
            return false;
    }        
    for(int row = i/3*3; row<i/3*3+3; row++) {
        for(int col=j/3*3; col<j/3*3+3; col++) {
            if((row!=i || col!=j) && board[row][col]==board[i][j])
                return false;
        }
    }
    return true;
}
再强调一下，以上方法是一个非常典型的套路，大部分NP问题的都是可以这个方法，比如N-Queens，Combination Sum，Combinations，Permutations等

Reference:
http://www.ninechapter.com/solutions/sudoku-solver/
http://blog.csdn.net/linhuanmars/article/details/20748761
https://yusun2015.wordpress.com/2015/01/12/valid-sudoku/
http://www.cnblogs.com/yuzhangcmu/p/4067733.html
http://blog.csdn.net/fightforyourdream/article/details/16916985
*/