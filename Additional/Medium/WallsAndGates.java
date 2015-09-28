/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than2147483647
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
0  -1 INF INF

After running your function, the 2D grid should be:
3  -1   0   1
2   2   1  -1
1  -1   2  -1
0  -1   3   4
*/

import java.util.*;

public class WallsAndGates { 	// BFS
	public static void wallsAndGates(int[][] rooms) {
		if (rooms.length == 0 || rooms[0].length == 0) return;
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				if (rooms[i][j] == 0) queue.offer(new int[]{i, j});
			}
		}
		while (!queue.isEmpty()) {
			int[] top = queue.poll();
			int row = top[0], col = top[1];
			if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE) {
				rooms[row - 1][col] = rooms[row][col] + 1;
				queue.offer(new int[]{row - 1, col});
			}
			if (row < rooms.length - 1 && rooms[row + 1][col] == Integer.MAX_VALUE) {
				rooms[row + 1][col] = rooms[row][col] + 1;
				queue.offer(new int[]{row + 1, col});
			}
			if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE) {
				rooms[row][col - 1] = rooms[row][col] + 1;
				queue.offer(new int[]{row, col - 1});
			}
			if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE) {
				rooms[row][col + 1] = rooms[row][col] + 1;
				queue.offer(new int[]{row, col + 1});
			}
		}
	}
	
	public static void main(String[] args) {
		final int INF = Integer.MAX_VALUE;
		int[][] rooms = {{INF, -1, 0, INF},
						 {INF, INF, INF, -1},
						 {INF, -1, INF, -1},
						 {0, -1, INF, INF}};
		wallsAndGates(rooms);
		for(int i = 0; i < rooms.length; i++) {
			System.out.println(Arrays.toString(rooms[i]));
		}
	}
}

/*
Push all gates into queue first. Then for each gate update its neighbor cells and push them to the queue.
Repeating above steps until there is nothing left in the queue.

Reference:
http://www.cnblogs.com/jcliBlogger/p/4836567.html
https://leetcode.com/discuss/60149/straightforward-python-solution-without-recursion
https://leetcode.com/discuss/60179/java-bfs-solution-o-mn-time
*/