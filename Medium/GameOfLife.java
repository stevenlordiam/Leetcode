/*
According to the Wikipedia's article (https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life): "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (https://en.wikipedia.org/wiki/Moore_neighborhood) (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

- Any live cell with fewer than two live neighbors dies, as if caused by under-population.
- Any live cell with two or three live neighbors lives on to the next generation.
- Any live cell with more than three live neighbors dies, as if by over-population..
- Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
- Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
- In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/

public class GameOfLife {
	// O(mn) time, O(1) space
    int[][] dir ={{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
    public void gameOfLife(int[][] board) {
    	// States:
		// 0 : dead to dead
		// 1 : live to live
		// 2 : live to dead
		// 3 : dead to live
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int live=0; // number of live neighbors
                for(int[] d:dir){
                    if(d[0]+i<0 || d[0]+i>=board.length || d[1]+j<0 || d[1]+j>=board[0].length) continue;
                    if(board[d[0]+i][d[1]+j]==1 || board[d[0]+i][d[1]+j]==2) live++;    // 1 and 2 means in the previous state the neighbors are live
                }
                if(board[i][j]==0 && live==3) board[i][j]=3;
                if(board[i][j]==1 && (live<2 || live>3)) board[i][j]=2;
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] %= 2;
            }
        }
    }
}

/*
A solution using additional spaces to solve a copy of the original board is easy. 
To get an in-place solution, we need to record the information of state transitions directly in the board.

Reference:
http://www.cnblogs.com/jcliBlogger/p/4854078.html
https://leetcode.com/discuss/61910/clean-o-1-space-o-mn-time-java-solution
https://leetcode.com/discuss/61912/c-o-1-space-o-mn-time
https://leetcode.com/discuss/61915/java-o-1-space-solution
*/