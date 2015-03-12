/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/

public class CombinationSum {
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(candidates == null || candidates.length==0)
            return res;
        Arrays.sort(candidates); 	// no dup -> sort first!!!
        helper(candidates,0,target,new ArrayList<Integer>(),res);
        return res;
    }
    
    private void helper(int[] candidates, int start, int target, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){
        if(target<0)
            return;
        if(target==0){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=start;i<candidates.length;i++){
            if(i>0 && candidates[i]==candidates[i-1])
                continue; 						// 跳出此次循环继续下次循环(i+1) 
            // for循环中第一步有一个判断，那个是为了去除重复元素产生重复结果的影响，因为每个数可以重复使用，所以重复的元素也就没有作用了，所以应该跳过重复元素
            item.add(candidates[i]);
            helper(candidates,i,target-candidates[i],item,res);
            // 基本思路是先排好序，然后每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题
            item.remove(item.size()-1); 		// backtracking, remove last element
        }
    }
}

/*
Backtracking

Similar to Combinations / Combination Sum II:
https://leetcode.com/problems/combinations/
https://leetcode.com/problems/combination-sum-ii/

比如{2, 3, 6, 7}，x = 7，组合就是{2, 2, 3}和{7}。不能有重复，且必须是sorted order
这个是标准DFS，一定要记住。先sort array，然后试图用2，用了2之后x相当于只剩5，然后下一层递归再用2，
用到没法再用为止（当前值比x还大），失败后stack pop出来，再试着在前面用了2, 2, 2的条件下用3， 挂，pop出2，
在22的条件下用3，正好，输出，然后再pop一个2，再用3， {2, 3}，再用下一个3

这个题是一个NP问题，方法仍然是N-Queens中介绍的套路。基本思路是先排好序，然后每次递归中把剩下的元素一一加到结果集合中，
并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题
算法复杂度因为是NP问题，所以自然是指数量级的。注意在实现中for循环中第一步有一个判断，那个是为了去除重复元素产生重复结果的影响，
因为在这里每个数可以重复使用，所以重复的元素也就没有作用了，所以应该跳过那层递归

Reference:
https://leetcodenotes.wordpress.com/2013/10/09/leetcode-combination-sum-%E7%BB%99%E4%B8%AA%E6%95%B0%E7%BB%84%EF%BC%8C%E6%89%BE%E9%87%8C%E9%9D%A2%E8%83%BD%E6%B1%82%E5%92%8Cx%E7%9A%84%E6%89%80%E6%9C%89%E7%BB%84%E5%90%88%EF%BC%88%E6%95%B0/
http://www.ninechapter.com/solutions/combination-sum/
http://blog.csdn.net/linhuanmars/article/details/20828631
https://yusun2015.wordpress.com/2015/01/12/largest-number/
*/