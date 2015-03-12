/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

public class Combinations {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> solution = new ArrayList<Integer>();
        helper(rst, solution, n, k, 1);
        return rst;
    }
    
    private void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> solution, int n, int k, int start) {
        if (solution.size() == k){
            rst.add(new ArrayList(solution));
            return;
        }
        for(int i = start; i<= n; i++){
            solution.add(i); 		// the new start should be after the next number after i
            helper(rst, solution, n, k, i+1); 
            solution.remove(solution.size() - 1); 	// backtracking, remove last element
        }
    }
}

/*
Backtracking

Similar to Combination Sum I / II:
https://leetcode.com/problems/combination-sum/
https://leetcode.com/problems/combination-sum-ii/

这道题是NP问题的题目，时间复杂度没办法提高，用到的还是N-Queens中的方法：用一个循环递归处理子问题
实现的代码跟Combination Sum非常类似而且更加简练，因为不用考虑重复元素的情况，而且元素只要到了k个就可以返回一个结果
NP问题在LeetCode中出现的频率很高，例如N-Queens，Sudoku Solver，Combination Sum，Permutations等等
不过这类题目都是用一种方法而且也没有办法有时间上的提高，所以还是比较好掌握的

Analysis:
Use breath first search, and  I get a short iterative solution:
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list=new LinkedList<>();
        List<Integer> list1=new ArrayList<>();
        list.add(list1);
        if(n==0) return list;
        while(list.get(0).size()<k){
            list1=list.remove(0);
            int m=list1.isEmpty()?0:list1.get(list1.size()-1);
            for(int i=m+1;i<=n&&i<=n-k+list1.size()+1;i++){
                List<Integer> temp=new ArrayList<>(list1);
                temp.add(i);
                list.add(temp);
            }
        }
        return list;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/09/leetcode-combinations-%E7%BB%99%E6%95%B0%E5%AD%97n%E5%92%8Ck%EF%BC%8C%E6%B1%82%E6%89%80%E6%9C%89%E7%94%B11%EF%BD%9En%E7%BB%84%E6%88%90%E7%9A%84%E9%95%BF%E5%BA%A6%E4%B8%BAk%E7%9A%84combination/
http://www.ninechapter.com/solutions/combinations/
http://blog.csdn.net/linhuanmars/article/details/21260217
https://yusun2015.wordpress.com/2015/01/08/combinations/
*/