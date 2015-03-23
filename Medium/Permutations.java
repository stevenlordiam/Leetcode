/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/

public class Permutations {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
         ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
         if (num == null || num.length == 0) {
             return rst; 
         }

         ArrayList<Integer> list = new ArrayList<Integer>();
         helper(rst, list, num);
         return rst;
    }
    
    public void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list, int[] num){
        if(list.size() == num.length) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = 0; i<num.length; i++){      // backtracking
            if(list.contains(num[i])){
                continue;		// 跳过当前循环，进入下一个循环(i+1)
            }
            list.add(num[i]);
            helper(rst, list, num);
            list.remove(list.size() - 1);   // 递归之后要移除 
        }   // 回溯法这种题目都是使用这个套路，就是用一个循环去枚举当前所有情况，然后把元素加入，递归，再把元素移除
            // http://blog.csdn.net/u010500263/article/details/18178243
    }
}

/*
Similar to CC150 (9-5) Permutations (RecursionAndDynamicProgramming_5)

这个思路本来很简单，如果用recursion来enumeration的话，即用set，每次第i个位置用了set的第j个元素，然后set remove j, recurse on i + 1。非常straight forward的做法，但是用java这么做有好几个问题：
- 如果input有重复，如aabc，set没法处理同时存在2个a的情况。
- 在recurse on set的每个元素的时候，因为for循环是interate thru set itself，但里面还有set.add(), set.remove这些改变set本身的语句，所以会有modify iterator exception。还得弄个copy先。
所以还是CC150上的另一种思路：每次取一个元素，对于之前（上一层循环的list结果）的每个permutation, 在能插的地方就插这个新元素，然后生成新的permutation，放入这一层的结果。这样代码相对简单，而且不用recursion。有几个要点：
- 最开始要传一个空的permutation进去，否则没处可insert第一个元素
- 这种做法不能防止duplication（1212可能有好几种insert成它的方法）

这道题跟N-Queens，Sudoku Solver，Combination Sum，Combinations等一样，也是一个NP问题。方法还是原来那个套路，还是用一个循环递归处理子问题
区别是这里并不是一直往后推进的，前面的数有可能放到后面，所以我们需要维护一个used数组来表示该元素是否已经在当前结果中，因为每次我们取一个元素放入结果，然后递归剩下的元素，所以不会出现重复
注意在实现中有一个细节，就是在递归函数的前面，我们分别设置了used[i]的标记，表明改元素被使用，并且把元素加入到当前结果中，而在递归函数之后，我们把该元素从结果中移除，并把标记置为false，这个我们可以称为“保护现场”，递归函数必须保证在进入和离开函数的时候，变量的状态是一样的，这样才能保证正确性
当然也可以克隆一份结果放入递归中，但是那样会占用大量空间。所以个人认为这种做法是最高效的，而且这种方法在很多题目（几乎所有NP问题）中一直用到，对于NP问题我们一直都是用递归方法，也是一个很成熟的套路，可以举一反三
迭代一般就是要理清每次加入一个新的元素，我们应该做什么，这里其实还是比较清晰的，假设有了当前前i个元素的所有permutation，当第i+1个元素加进来时，我们要做的就是将这个元素带入每一个之前的结果，并且放到每一个结果的各个位置中。因为之前的结果没有重复，所以带入新元素之后也不会有重复（当然这里假设数字集合本身是没有重复的元素的）
总的时间复杂度还是会是指数量级以上的，这种NP问题的求解在LeetCode中非常常见，类似的有N-Queens，Sudoku Solver，Combination Sum，Combinations，思路差不多。这道题还有一个扩展就是如果元素集合中会出现重复，那么意味着我们需要跳过一些重复元素，具体的细节可以参见Permutations II

Why list.remove(list.size() - 1) ?
1. Create a new array "visited[num.size()]" to keep the which element of the original array has been visited, so as to ensure only the remaining elements will be processed.  
For example, in case of array[0] = 1, only 2,3,4 can be process for array[1].
2. Remove the last element from the array, and resume the visit flag in order to process next possible permutation. 
For example, after having [1, 2, 3, 4], remove 4 from array (array will be fallen back to [1, 2, 3]), and reset visit flag of the 3rd element to un-visited.  Then go into the next iteration: put 4 into the array.  
New array this time would be [1, 2, 4].



Reference:
https://leetcodenotes.wordpress.com/2013/08/07/leetcode-permutations-%E4%B8%80%E4%B8%AAarray%E7%9A%84%E6%89%80%E6%9C%89permutation/
http://www.ninechapter.com/solutions/permutations/
http://blog.csdn.net/linhuanmars/article/details/21569031
https://yusun2015.wordpress.com/2015/01/08/permutations/
http://blog.csdn.net/u010500263/article/details/18178243
*/