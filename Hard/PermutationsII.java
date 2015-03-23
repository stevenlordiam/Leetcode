/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

public class PermutationsII {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num==null && num.length==0)
            return res;
        Arrays.sort(num);		// sort first to avoid dups
        helper(num, new boolean[num.length], new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(int[] num, boolean[] used, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){
        if(item.size() == num.length){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=0;i<num.length;i++){
            if(i>0 && !used[i-1] && num[i]==num[i-1]) continue;		// 跳过重复元素 保证不会出现重复解（比如[112],[112] -> 第一个1分别出现在第一位和第二位形成的重复解）
            if(!used[i]){
                used[i] = true;
                item.add(num[i]);
                helper(num, used, item, res);
                item.remove(item.size()-1);			// backtracking, remove last element
                used[i] = false;    // 改成false表示现在不在用这个元素
            }
        }
    }
}

/*
Backtracking

Similar to Permutations - https://leetcode.com/problems/permutations/

平时的permutation有一个boolean used[]，如果arr[i]在之前的layer里用过了，就不能再用了。比如{1, 2, 1}，到pos2的时候，看即使arr[2]是“1”，但是这个1不是第一个用过的1，所以还可以放在当前pos当中
但是在pos0的时候，如果想用第二个1，就不行了（当前pos已经有人用过数字1了，即使用的是arr[0]不是当前想用的arr[2]，但是是同一个数字就不行），所以skip掉。因此，算法是在每个position存一个hashset记录在这个position放过那些数字，若是以前放过了，这次就别放了

Analysis: At the some level can not insert the some number more than twice and sorting all the candidates will be helpful to avoid duplicates

这个题跟Permutations非常类似，唯一的区别就是在这个题目中元素集合可以出现重复。这给我们带来一个问题就是如果不对重复元素加以区别，那么类似于{1,1,2}这样的例子我们会有重复结果出现
那么如何避免这种重复呢？方法就是对于重复的元素循环时跳过递归函数的调用，只对第一个未被使用的进行递归，我们那么这一次结果会出现在第一个的递归函数结果中，而后面重复的会被略过
如果第一个重复元素前面的元素还没在当前结果中，那么我们不需要进行递归。想明白了这一点，代码其实很好修改。首先我们要对元素集合排序，从而让重复元素相邻，接下来就是一行代码对于重复元素和前面元素使用情况的判断即可
这样的解法是带有一般性的，把这个代码放到Permutations中也是正确的，所以如果熟悉的话，面试时如果碰到这个题目建议直接实现这个代码，不要假设元素没有重复

Reference:
https://leetcodenotes.wordpress.com/2013/08/08/leetcode-permutations-ii-%E4%B8%80%E4%B8%AAarray%E7%9A%84%E6%89%80%E6%9C%89unique-permutation/
http://www.ninechapter.com/solutions/permutations-ii/
http://blog.csdn.net/linhuanmars/article/details/21570835
https://yusun2015.wordpress.com/2015/01/08/permutations/
*/