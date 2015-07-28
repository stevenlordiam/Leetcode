/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class WordSearch {				// recursion
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0)
            return false;
        if(word.length() == 0)
            return true;   
        for(int i = 0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(board[i][j] == word.charAt(0)){           
                    boolean rst = find(board, i, j, word, 0);
                    if(rst)
                        return true;
                }
            }
        }
        return false;
    }
    
    private boolean find(char[][] board, int i, int j, String word, int start){
        if(start == word.length())
            return true;
        if (i < 0 || i>= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)){
            return false;
	 	    }   
        board[i][j] = '#'; 					// should remember to mark it
        boolean rst = find(board, i-1, j, word, start+1) 		// up down left right
				   || find(board, i, j-1, word, start+1) 
				   || find(board, i+1, j, word, start+1) 
				   || find(board, i, j+1, word, start+1);
        board[i][j] = word.charAt(start); 	// 这样就能保证每次visited的能给重置回来 (注意要重置！！！！！！)
        return rst;
    }
}

/*
Backtracking

没啥可说的，对于每个cell，如过和word的开头字母一致，就生成一个新的全是false的visited[][], 进去原matrix开始前后左右的找。直到word的每个字母都找到为止
要点：之前做的是直接
  // left, right, up, down
  return find(board, i, j - 1, word, index + 1, visited) || find(board, i, j + 1, word, index + 1, visited)
  || find(board, i - 1, j, word, index + 1, visited) || find(board, i + 1, j, word, index + 1, visited);
这样就挂了！因为往左走的一路已经Update visited[][]了，然后没人去undo它，往右走的一支就直接拿来用了，不就乱套了么！所以在mark true的时候，函数结尾给undo一下，就可以保证一对儿出现，路径在反上来的被清理
public boolean exist(char[][] board, String word) {
  for (int i = 0; i < board.length; i++) {
    for (int j = 0; j < board[0].length; j++) {
      if (board[i][j] == word.charAt(0)) {
        boolean found = find(board, i, j, word, 0, new boolean[board.length][board[0].length]);
        if (found)
          return true;
      }
    }
  }
  return false;
}
private boolean find(char[][] board, int i, int j, String word, int index, boolean[][] visited) {
  if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
    return false;
  if (board[i][j] != word.charAt(index) || visited[i][j])
    return false;
  visited[i][j] = true;
  if (index == word.length() - 1)
    return true;
  // left, right, up, down
  boolean found = find(board, i, j - 1, word, index + 1, visited) || find(board, i, j + 1, word, index + 1, visited)
                || find(board, i - 1, j, word, index + 1, visited) || find(board, i + 1, j, word, index + 1, visited);
  visited[i][j] = false;//这样就能保证每次true的能给false回来
  return found;
}

这道题很容易感觉出来是图的题目，其实本质上还是做深度优先搜索。基本思路就是从某一个元素出发，往上下左右深度搜索是否有相等于word的字符串
这里注意每次从一个元素出发时要重置访问标记（也就是说虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符还是重新可以用的）
深度优先搜索的算法就不再重复解释了，不了解的朋友可以看看wiki - 深度优先搜索。我们知道一次搜索的复杂度是O(E+V)，E是边的数量，V是顶点数量，在这个问题中他们都是O(m*n)量级的（因为一个顶点有固定上下左右四条边）
加上我们对每个顶点都要做一次搜索，所以总的时间复杂度最坏是O(m^2*n^2)，空间上就是要用一个数组来记录访问情况，所以是O(m*n)

Reference:
https://leetcodenotes.wordpress.com/2013/08/24/leetcode-word-search-%E4%BA%8C%E7%BB%B4%E7%9F%A9%E9%98%B5%E6%89%BE%E9%87%8C%E9%9D%A2%E7%9A%84%E5%8D%95%E8%AF%8D/
http://www.ninechapter.com/solutions/word-search/
http://blog.csdn.net/linhuanmars/article/details/24336987
https://yusun2015.wordpress.com/2015/01/25/word-search/
*/