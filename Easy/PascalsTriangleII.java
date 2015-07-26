/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/
public class PascalsTriangleII {
    public ArrayList<Integer> getRow(int rowIndex) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(rowIndex<0)
            return res;
        res.add(1);
        for(int i=1;i<=rowIndex;i++) {          // 依次算出各层的值，返回最后一层的值
            for(int j=res.size()-2;j>=0;j--) {
                res.set(j+1,res.get(j)+res.get(j+1));           // arraylist.set(i, int)
            }
            res.add(1);             // add the final '1' of the row
        }
        return res;
    }
}

/*
Similar to Pascal's Triangle - https://oj.leetcode.com/problems/pascals-triangle/

Remember to use without extra space
这道题跟Pascal's Triangle很类似，只是这里只需要求出某一行的结果。Pascal's Triangle中因为是求出全部结果，所以我们需要上一行的数据就很自然的可以去取。
而这里我们只需要一行数据，就得考虑一下是不是能只用一行的空间来存储结果而不需要额外的来存储上一行呢？这里确实是可以实现的。对于每一行我们知道如果从前往后扫，
第i个元素的值等于上一行的res[i]+res[i+1]，可以看到数据是往前看的，如果我们只用一行空间，那么需要的数据就会被覆盖掉。所以这里采取的方法是从后往前扫，
这样每次需要的数据就是res[i]+res[i-1]，我们需要的数据不会被覆盖，因为需要的res[i]只在当前步用，下一步就不需要了。这个技巧在动态规划省空间时也经常使用，
主要就是看我们需要的数据是原来的数据还是新的数据来决定我们遍历的方向。时间复杂度还是O(n^2)，而空间这里是O(k)来存储结果，仍然不需要额外空间。
这道题相比于Pascal's Triangle其实更有意思一些，因为有一个考点就是能否省去额外空间，在面试中出现的可能性大一些，不过总体比较简单，电面中比较合适。

Another solution:
public class Solution {
    public List<Integer> getRow(int rowIndex) {
          List<Integer> list=new LinkedList<>();
          list.add(1);
          for(int i=1;i<=rowIndex;i++){
              List<Integer> temp=new LinkedList<>();
              int pre=0;
              for(int p:list){
                  temp.add(pre+p);
                  pre=p;
              }
              temp.add(1);
              list=temp;
          }
          return list;
    }
}

Reference:
http://blog.csdn.net/linhuanmars/article/details/23311629
https://yusun2015.wordpress.com/2015/01/09/pascals-triangle-i-and-ii/
*/