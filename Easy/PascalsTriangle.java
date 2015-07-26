/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

public class PascalsTriangle {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();       // two dimension arraylist
        if(numRows<=0)
            return res;
        ArrayList<Integer> pre = new ArrayList<Integer>();
        pre.add(1);
        res.add(pre);       // first row [1]
        for(int i=2;i<=numRows;i++) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
            cur.add(1);
            for(int j=0;j<pre.size()-1;j++) {
                cur.add(pre.get(j)+pre.get(j+1));       // formula of pascal's triangle
            }
            cur.add(1);         // add the final '1' of each row
            res.add(cur);       // add each finished row to result two dimension arraylist
            pre = cur; 
        }
        return res;
    }
}

/*
Similar to Pascal's Triangle II - https://oj.leetcode.com/problems/pascals-triangle-ii/

基本思路是每层保存前一行的指针，然后当前行数据根据上一行来得到，每个元素就是上一行两个相邻元素相加（第一个和最后一个元素是1）。
算法时间复杂度应该是O(1+2+3+...+n)=O(n^2)，空间上只需要二维数组来存储结果，不需要额外空间。

Reference:
http://blog.csdn.net/linhuanmars/article/details/23311527
https://yusun2015.wordpress.com/2015/01/09/pascals-triangle-i-and-ii/
*/