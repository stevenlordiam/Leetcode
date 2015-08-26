/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on… Find the minimum cost to paint all houses.

Note:
All costs are positive integers
*/

public class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int h = costs.length;
        int w = costs[0].length;

        int[][] map = new int[h + 1][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i + 1][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int k = 0; k < w; k++) {
                    if (j != k) {
                        map[i + 1][j] = Math.min(map[i + 1][j], map[i][k] + costs[i][j]);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < w; i++) {
            result = Math.min(result, map[h][i]);
        }
        return result;
    }
}

/*
Reference:
http://sbzhouhao.net/LeetCode/LeetCode-Paint-House.html
*/