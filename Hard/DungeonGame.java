/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. 
The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2(K)   -3    3
-5	    -10   1
10	    30	 -5(P)

Notes:
The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/

public class DungeonGame  {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length == 0)
            return 0;
        int m = dungeon.length, n = dungeon[0].length;
        
        // the minimum health that guarantees the survival of the knight for the rest of his quest BEFORE entering room(i, j)
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] >= 0 ? 1 : -dungeon[m - 1][n - 1] + 1;

        // fill in DP
        for(int i = m - 2; i >= 0; i--){    // right
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for(int j = n - 2; j >= 0; j--){    // down
            dp[m - 1][j] = Math.max(dp[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }
        
        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}

/*
Solution：
At first glance, this problem bears a large resemblance to the "Maximum/Minimum Path Sum" problem. 

However, a path with maximum overall health gain does not guarantee the minimum initial health, 
since it is essential in the current problem that the health never drops to zero or below. 
For instance, consider the following two paths: 0 -> -300 -> 310 -> 0 and 0 -> -1 -> 2 -> 0. The net health gain along these paths are -300 + 310 = 10 and -1 + 2 = 1, respectively. 
The first path has the greater net gain, but it requires the initial health to be at least 301 in order to balance out the -300 loss in the second room, whereas the second path only requires an initial health of 2.

Fortunately, this problem can be solved through a table-filling Dynamic Programming technique, similar to other "grid walking" problems:

- To begin with, we should maintain a 2D array D of the same size as the dungeon, where D[i][j] represents the minimum health that guarantees the survival of the knight for the rest of his quest BEFORE entering room(i, j). 
Obviously D[0][0] is the final answer we are after. Hence, for this problem, we need to fill the table from the bottom right corner to left top.

- Then, let us decide what the health should be at least when leaving room (i, j). There are only two paths to choose from at this point: (i+1, j) and (i, j+1). 
Of course we will choose the room that has the smaller D value, or in other words, the knight can finish the rest of his journey with a smaller initial health. Therefore we have:
  min_HP_on_exit = min(D[i+1][j], D[i][j+1])

- Now D[i][j] can be computed from dungeon[i][j] and min_HP_on_exit based on one of the following situations:
	- If dungeon[i][j] == 0, then nothing happens in this room; the knight can leave the room with the same health he enters the room with, i.e. D[i][j] = min_HP_on_exit.
	- If dungeon[i][j] < 0, then the knight must have a health greater than min_HP_on_exit before entering (i, j) in order to compensate for the health lost in this room. The minimum amount of compensation is "-dungeon[i][j]", so we have D[i][j] = min_HP_on_exit - dungeon[i][j].
	- If dungeon[i][j] > 0, then the knight could enter (i, j) with a health as little as min_HP_on_exit - dungeon[i][j], since he could gain "dungeon[i][j]" health in this room. However, the value of min_HP_on_exit - dungeon[i][j] might drop to 0 or below in this situation. 
      When this happens, we must clip the value to 1 in order to make sure D[i][j] stays positive: D[i][j] = max(min_HP_on_exit - dungeon[i][j], 1).
Notice that the equation for dungeon[i][j] > 0 actually covers the other two situations. We can thus describe all three situations with one common equation, i.e.:
    D[i][j] = max(min_HP_on_exit - dungeon[i][j], 1)
for any value of dungeon[i][j].

- Take D[0][0] and we are good to go. Also, like many other "table-filling" problems, the 2D array D can be replaced with a 1D "rolling" array here.


解题思路：
乍一看，这个问题和"Maximum/Minimum Path Sum"问题很相似。然而，具有全局最大HP（生命值）收益的路径并不一定可以保证最小的初始HP，因为题目中具有限制条件：HP不能≤0
例如，考虑下面的两条路径：0 -> -300 -> 310 -> 0 和 0 -> -1 -> 2 -> 0。这两条路径的净HP收益分别是-300 + 310 = 10 与 -1 + 2 = 1
第一条路径的净收益更高，但是它所需的初始HP至少是301，才能抵消第二个房间的-300HP损失，而第二条路径只需要初始HP为2就可以了
幸运的是，这个问题可以通过“table-filling”（表格填充）动态规划算法解决，与其他"grid walking"（格子行走）问题类似：
首先，我们应该维护一个2维数组D，与地牢数组的大小相同，其中D[i][j]代表可以保证骑士在进入房间(i,j)之前，探索其余地牢时能够存活下来的最小HP。显然D[0][0]就是我们随后需要的最终答案。因此，对于这个问题，我们需要从右下角到左上角填充表格
然后，我们来计算离开房间(i,j)时的最小HP。从这一点出发只有两条路径可选：(i + 1, j)和(i, j + 1)。当然我们会选择拥有更小D值的那个房间，换言之，骑士完成剩下的旅途所需的较小HP。因此我们有：
  min_HP_on_exit = min(D[i+1][j], D[i][j+1])
现在D[i][j]可以通过dungeon[i][j]和min_HP_on_exit，根据下面的情景得出：
如果dungeon[i][j] == 0，那么在这个房间里很安全。 骑士离开这个房间时的HP和他进入房间时的HP保持一致， 也就是说 D[i][j] = min_HP_on_exit
如果dungeon[i][j] < 0，那么骑士在进入该房间之前的HP > 离开房间时的HP，min_HP_on_exit才能抵消他在该房间中的HP损失。 最小HP花费就是 "-dungeon[i][j]"， 因此我们有公式 D[i][j] = min_HP_on_exit - dungeon[i][j]
如果dungeon[i][j] > 0, 那么骑士在进入房间(i, j) 时的HP只需为min_HP_on_exit - dungeon[i][j]，因为他可以在该房间内获得数值为"dungeon[i][j]"的HP收益。 不过，这种情况下min_HP_on_exit - dungeon[i][j]的数值可能≤0
此时，我们需要把值置为1以确保D[i][j]为正整数: D[i][j] = max(min_HP_on_exit - dungeon[i][j], 1)
注意 dungeon[i][j] > 0 条件下的等式实际上可以覆盖其他两种情况。 因此我们可以把三种情况归纳为同一个等式： 亦即:
D[i][j] = max(min_HP_on_exit - dungeon[i][j], 1)
dungeon[i][j]可以为任意值，D[0][0]就是最终答案。此外，像许多其他"table-filling"问题一样，二维数组D可以用一维滚动数组替代


用动态规划Dynamic Programming来做，建立一个和迷宫大小相同的二维数组用来表示当前位置出发的起始血量，最先初始化的是公主所在的房间的起始生命值，然后慢慢向第一个房间扩散，不断的得到各个位置的最优的起始生命值
递归方程为: 递归方程是dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]). 
Start from the destination, we can calculate the minimum HP required then store it in the cell since we won't be needing it later. This way we can achieve constant space.
If we care not allowed to change the matrix, then we need at least a 1-D array.
I use another 2-D array to store intermediate result. If you don't care modification of original array, you can replace all "result" as "dungeon". So O(1) space can be achieved.
Then use bottom-up strategy to compute the minimum initial health for each cell from bottom-right direction.
I use the current cell minus previous path's health requirement to compute reversed health requirement for each cell.
I use this line to update required health for each cell:
        result[i][j] = result[i][j] > 0 ? 0 : -result[i][j];


挺有意思的一道题，基本上可以一眼看出要用DP。问题就是DP的递归方程怎么写。dp[i][j]表示进入这个格子后保证knight不会死所需要的最小HP
如果一个格子的值为负，那么进入这个格子之前knight需要有的最小HP是-dungeon[i][j] + 1.如果格子的值非负，那么最小HP需求就是1
这里可以看出DP的方向是从最右下角开始一直到左上角。首先dp[m-1][n-1] = Math.max(1, -dungeon[m-1][n-1] + 1)
递归方程是dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])


思路分析：这题容易想到的思路是用search DFS或者BFS解，给定起点和终点，我们可以搜索所有从起点到终点的路径，然后贪心保存下来最小的路径权值之和，同时要保证每次扩展分支时当前的生命值状态始终大于0
但是这并不是好的解法，时间复杂度太高。实际上，这题和Unique Path很相似，都是从左上角走到右下角，并且每个格子只能向下或者向右，也就是每个格子只有右边或者下边两个相邻格子。我们可以考虑用动态规划解
关键是如何定义dp数组，这题和Unique Path中dp数组的定义相比有点不同。假设给定的网格是m*n维，我们需要定义dp[][]表示从(i,j)到终点(m-1,n-1)需要的最小生命值，
那么递推方程为dp[i][j] = max(min(dp[i][j+1], dp[i+1][j]) - dungeon[i][j], 0)，如果dungeon[i][j]为正，则减去一个正数，初始需要的生命值变小。同理可以理解dungeon[i][j]为负数的情况。和0取最大保证了初始生命值为非负
代码实现很简单，就是初始化后自下而上自由向左填表，典型的二维DP问题的做法。关于这类网格图论问题可以用DFS，BFS，DP以及经典的图算法比如dijkstra 算法，flod算法等等来做，需要多做做这类题目，根据不同情况选择不同算法，增加做这类题目的经验


Analysis:
Use DP from bottom to top:
D[i][j]=min{max{1,D[i][j+1]-Dungeon[i][j]},max{1,D[i+1][j]-Dungeon[i][j]}}.
In this question the order of DP is important, and top to bottom does not work.
//
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m=dungeon.length,n=dungeon[0].length;
        int[][] res=new int[m][n];
        for(int i=m-1;i>-1;i--){
            for(int j=n-1;j>-1;j--){
                if(i==m-1&&j==n-1) res[i][j]=Math.max(1,1-dungeon[i][j]);
                else if(i==m-1) res[i][j]=Math.max(1,res[i][j+1]-dungeon[i][j]);
                else if(j==n-1) res[i][j]=Math.max(1,res[i+1][j]-dungeon[i][j]);
                else res[i][j]=Math.min(Math.max(1,res[i][j+1]-dungeon[i][j]),Math.max(1,res[i+1][j]-dungeon[i][j]));
            }
        }
        return res[0][0];
    }
}

Reference:
https://leetcode.com/problems/dungeon-game/solution/
https://yusun2015.wordpress.com/2015/01/06/dungeon-game/
http://blog.csdn.net/yangliuy/article/details/42643265
http://blog.csdn.net/likecool21/article/details/42516979
https://leetcode.com/discuss/20744/my-java-ac-solution-with-dp-and-o-1-space
https://leetcode.com/discuss/20749/my-bottom-up-o-n-2-java-solution-with-detailed-explanation
https://leetcode.com/discuss/26242/my-java-solution-with-explanation-in-detail
http://bookshadow.com/weblog/2015/01/07/leetcode-dungeon-game/
http://www.cnblogs.com/grandyang/p/4233035.html
http://www.cnblogs.com/easonliu/p/4237644.html
*/