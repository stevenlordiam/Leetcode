/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/

public class CombinationSumII {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length==0)
            return res;
        Arrays.sort(num);	// no dup -> sort first!!!
        helper(num,0,target,new ArrayList<Integer>(),res);
        return res;
    }

    private void helper(int[] num, int start, int target, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        if(target<0 || start>=num.length)
            return;
        for(int i=start;i<num.length;i++){
            if(i>start && num[i]==num[i-1]) continue;
            item.add(num[i]);
            helper(num,i+1,target-num[i],item,res); 	// I可以重复元素所以是i迭代，II不能重复元素所以是i+1
            item.remove(item.size()-1);					// 递归的时候传进去的index应该是当前元素的下一个(以避免重复元素)
        }
    }
}

/*
Backtracking

Similar to Combinations / Combination Sum:
https://leetcode.com/problems/combinations/
https://leetcode.com/problems/combination-sum/

I - 元素可用多次
II - 元素只可以用一次

比如 {1, 1, 1, 2, 3}，x = 3, 结果就是{111}{12}{3}。这个题挺难想的，和上一题又不一样
正常stack，把1push进去，然后直接在2上recurse x=2的（因为1只能用一次）。这样一直做到x==0
这样就会有重复：当stack pop空了之后，回到第二个1上，又开始push，那stack的第0个又是1了，刚才已经做过stack第0个是1的一遍了（左边的一遍肯定包括了最多种的情况）
所以现在再把1放里面就会重复。所以要在做“要不要在当前layer试一下下一个数？”的决定的时候，要看“下一个数和当前数是否相等？
因为当前数已经做过了，同一层layer说明是同一个position，再放当前数就会重复）
总结：
- combination/set: 用stack，因为长度不固定
- permutation: 用int[]，keep一个pos pointer
- 主函数里面不要循环，就一个以初始值call副函数的语句就行
- 副函数是循环里面call自己 

这道题跟Combination Sum非常相似，不了解的朋友可以先看看，唯一的区别就是这个题目中单个元素用过就不可以重复使用了
乍一看好像区别比较大，但是其实实现上只需要一点点改动就可以完成了，就是递归的时候传进去的index应该是当前元素的下一个
在这里我们还是需要在每一次for循环前做一次判断，因为虽然一个元素不可以重复使用，但是如果这个元素重复出现是允许的，但是为了避免出现重复的结果集，
我们只对于第一次得到这个数进行递归，接下来就跳过这个元素了，因为接下来的情况会在上一层的递归函数被考虑到，这样就可以避免重复元素的出现

Reference:
https://leetcodenotes.wordpress.com/2013/10/09/leetcode-combination-sum-2-%E7%BB%99%E4%B8%AA%E6%95%B0%E7%BB%84%EF%BC%8C%E6%89%BE%E9%87%8C%E9%9D%A2%E8%83%BD%E6%B1%82%E5%92%8Cx%E7%9A%84%E6%89%80%E6%9C%89%E7%BB%84%E5%90%88%EF%BC%88%E6%95%B0/
http://www.ninechapter.com/solutions/combination-sum-ii/
http://blog.csdn.net/linhuanmars/article/details/20829099
https://yusun2015.wordpress.com/2015/01/12/largest-number/
*/