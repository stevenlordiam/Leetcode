/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

public class SurroundedRegions {			// flood fill algorithm
    public void solve(char[][] board) {
        if(board==null || board.length<=1 || board[0].length<=1)
            return;

        // 根据题目要求，边缘上的'O'是不需要填充的，所以我们的办法是对上下左右边缘做Flood fill算法
        // 把所有边缘上的'O'都替换成另一个字符'#'
        for(int i=0;i<board[0].length;i++){ 		// first row, right / down 
            fill(board,0,i);
            fill(board,board.length-1,i);
        }
        for(int i=0;i<board.length;i++){			// first col, down / right
            fill(board,i,0);
            fill(board,i,board[0].length-1);
        }

        // 四个边缘flood fill(mark 'O' to '#'), 然后还原边缘的'O'并且将'O'转换为'X'
        for(int i=0;i<board.length;i++){			
            for(int j=0;j<board[0].length;j++){     
                if(board[i][j]=='O')
                    board[i][j]='X';
                else if(board[i][j]=='#')
                    board[i][j]='O';                
            }
        }
    }

    private void fill(char[][] board, int i, int j){    // mark 'O' to '#'
        if(board[i][j]!='O')
            return;
        board[i][j] = '#';
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int code = i*board[0].length+j;         // the index of element
        queue.offer(code);                      // add()
        while(!queue.isEmpty()){
            code = queue.poll();                // remove()
            int row = code/board[0].length;     // row
            int col = code%board[0].length;     // column
            if(row>0 && board[row-1][col]=='O'){                // four direction flood fill                                                                                         
                queue.offer((row-1)*board[0].length+col);       // index
                board[row-1][col]='#';
            }
            if(row<board.length-1 && board[row+1][col]=='O'){
                queue.offer((row+1)*board[0].length+col);
                board[row+1][col]='#';
            }
            if(col>0 && board[row][col-1]=='O'){
                queue.offer(row*board[0].length+col-1);
                board[row][col-1]='#';
            }
            if(col<board[0].length-1 && board[row][col+1]=='O'){
                queue.offer(row*board[0].length+col+1);
                board[row][col+1]='#';
            }            
        }
    }
}


/*
这个题目用到的方法是图形学中的一个常用方法：Flood fill算法 (http://en.wikipedia.org/wiki/Flood_fill) 
其实就是从一个点出发对周围区域进行目标颜色的填充。背后的思想就是把一个矩阵看成一个图的结构，每个点看成结点，而边则是他上下左右的相邻点，然后进行一次广度或者深度优先搜索
接下来我们看看这个题如何用Flood fill算法来解决。首先根据题目要求，边缘上的'O'是不需要填充的，所以我们的办法是对上下左右边缘做Flood fill算法，把所有边缘上的'O'都替换成另一个字符，比如'#'
接下来我们知道除去被我们换成'#'的那些顶点，剩下的所有'O'都应该被替换成'X'，而'#'那些最终应该是还原成'O'，如此我们可以做最后一次遍历，然后做相应的字符替换就可以了
复杂度分析上，我们先对边缘做Flood fill算法，因为只有是'O'才会进行，而且会被替换成'#'，所以每个结点改变次数不会超过一次，因而是O(m*n)的复杂度，最后一次遍历同样是O(m*n)，所以总的时间复杂度是O(m*n)
空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，同时存在的空间占用不会超过O(m+n)（以广度优先搜索为例，每次队列中的结点虽然会往四个方向拓展，但是事实上这些结点会有很多重复，假设从中点出发，可以想象最大的扩展不会超过一个菱形，也就是n/2*2+m/2*2=m+n，所以算法的空间复杂度是O(m+n)）
可以看到上面代码用的是广度优先搜索，用一个队列来维护，当然也可以用深度优先搜索，但是如果使用递归，会发现LeetCode过不了，这是因为在图形中通常图片（或者说这里的矩阵）一般会很大，递归很容易导致栈溢出，所以即使要用深度优先搜索，也最好使用非递归的实现方式


// version 1:
public class Solution {
    static final int[] directionX = {+1, -1, 0, 0};
    static final int[] directionY = {0, 0, +1, -1}; 
    static final char FREE = 'F';
    static final char TRAVELED = 'T';
    
    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }     
        int row = board.length;
        int col = board[0].length;        
        for (int i = 0; i < row; i++) {
            bfs(board, i, 0);
            bfs(board, i, col - 1);
        }     
        for (int j = 1; j < col - 1; j++) {
            bfs(board, 0, j);
            bfs(board, row - 1, j);
        }        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch(board[i][j]) {
                    case 'O': 
                        board[i][j] = 'X';
                        break;
                    case 'F':
                        board[i][j] = 'O';
                }
            }
        }
    }
    
    public void bfs(char[][] board, int i, int j) {
        if (board[i][j] != 'O') {
            return;
        }       
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(i, j));       
        while (!queue.isEmpty()) {
            Node crt = queue.poll();
            board[crt.x][crt.y] = FREE;           
            for (Node node : expand(board, crt)) {
                queue.offer(node);
            }
        }
    }
    
    private List<Node> expand(char[][] board, Node node) {
        List<Node> expansion = new ArrayList<Node>();      
        for (int i = 0; i < directionX.length; i++) {
            int x = node.x + directionX[i];
            int y = node.y + directionY[i];           
            // check validity
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O') {
                board[x][y] = TRAVELED;
                expansion.add(new Node(x, y));
            }
        }       
        return expansion;
    }
    
    static class Node {
        int x;
        int y;    
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


// version 2:
public class Solution {
    private static Queue<Integer> queue = null;
    private static char[][] board;
    private static int rows = 0;
    private static int cols = 0;

    public void solve(char[][] board) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (board.length == 0 || board[0].length == 0) return;
        queue = new LinkedList<Integer>();
        board = board;
        rows = board.length;
        cols = board[0].length;
        for (int i = 0; i < rows; i++) { // **important**
            enqueue(i, 0);
            enqueue(i, cols - 1);
        }
        for (int j = 1; j < cols - 1; j++) { // **important**
            enqueue(0, j);
            enqueue(rows - 1, j);
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / cols,
                y = cur % cols;
            if (board[x][y] == 'O') {
                board[x][y] = 'D';
            }
            enqueue(x - 1, y);
            enqueue(x + 1, y);
            enqueue(x, y - 1);
            enqueue(x, y + 1);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'D') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
        queue = null;
        board = null;
        rows = 0;
        cols = 0;
    }

    public static void enqueue(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == 'O'){  
            queue.offer(x * cols + y);
        }
    }
}


Reference:
http://www.ninechapter.com/solutions/surrounded-regions/
http://blog.csdn.net/linhuanmars/article/details/22904855
*/