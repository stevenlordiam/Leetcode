/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class PathSumII {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> solution = new ArrayList<Integer>();				// one way of solution, all solutions form the two-dimension result arraylist

        findSum(rst, solution, root, sum);
        return rst;
    }

    private void findSum(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> solution, TreeNode root, int sum){
        if (root == null) {
            return;
        }

        sum -= root.val;		// subtract sum value level by level

        if (root.left == null && root.right == null) {
            if (sum == 0){					// until sum is subtracted to zero
                solution.add(root.val);
                result.add(new ArrayList<Integer>(solution));		// add up solutions to result
                solution.remove(solution.size()-1);
            }
            return;
        }

        solution.add(root.val);
        findSum(result, solution, root.left, sum); 			// 继续递归
        findSum(result, solution, root.right, sum);
        // 注意，递归和回溯的特点就是 递归不可以改变path的值。也就是说，你返回时，这个path不能被改变，所以在这里要执行remove操作。
        // 在递归中添加path.add完后，必须要加入path.remove 操作，也就是说我们必须要在递归程序回到上一级之前，使path恢复它的原本的状态，这就是回溯咯。
   		  // 举一个简单的例子： 5 4 11 2 找完后，你希望退回到根节点5，再找5，8，4，5所以递归退回时，要把2，11，4依次删除掉。所以每次添加完并且递归后，再删除是必须的
        solution.remove(solution.size()-1);					// REMEMBER to remove the last path (THIS IS IMPORTANT!!!)
    }
}

/*
Similar to Path Sum - https://oj.leetcode.com/problems/path-sum/   
Similar to CC150 (4-9) path sum (TreesAndGraphs_9)

这道题是树的题目，跟Path Sum的要求很接近，都是寻找从根到叶子的路径。这道题目的要求是求所有满足条件的路径，所以我们需要数据结构来维护中间路径结果以及保存满足条件的所有路径。
这里的时间复杂度仍然只是一次遍历O(n)，而空间复杂度则取决于满足条件的路径和的数量（假设是k条），则空间是O(klogn)
这类求解树的路径的题目是一种常见题型，类似的有Binary Tree Maximum Path Sum，那道题更加复杂一些，路径可以起始和结束于任何结点（把二叉树看成无向图）

树的求和总结：(http://blog.csdn.net/linhuanmars/article/details/38976917)

树的求和属于树的题目中比较常见的，因为可以有几种变体，灵活度比较高，也可以考察到对于树的数据结构和递归的理解。
一般来说这些题目就不用考虑非递归的解法了（虽然其实道理是跟LeetCode总结 -- 树的遍历篇一样的，只要掌握了应该没问题哈）。 
LeetCode中关于树的求和有以下题目：
Path Sum
Path Sum II
Sum Root to Leaf Numbers
Binary Tree Maximum Path Sum

我们先来看看最常见的题目Path Sum。这道题是判断是否存在从根到叶子的路径和跟给定sum相同。树的题目基本都是用递归来解决，主要考虑两个问题：
1）如何把问题分治成子问题给左子树和右子树。这里就是看看左子树和右子树有没有存在和是sum减去当前结点值得路径，只要有一个存在，那么当前结点就存在路径。
2）考虑结束条件是什么。这里的结束条件一个是如果当前节点是空的，则返回false。另一个如果是叶子，那么如果剩余的sum等于当前叶子的值，则找到满足条件的路径，返回true。
想清楚上面两个问题，那么实现起来就是一次树的遍历，按照刚才的分析用参数或者返回值传递需要维护的值，然后按照递归条件和结束条件进行返回即可。算法的时间复杂度是一次遍历O(n)，空间复杂度是栈的大小O(logn)

对于Path Sum II，其实思路和Path Sum是完全一样的，只是需要输出所有路径，所以需要数据结构来维护路径，添加两个参数，一个用来维护走到当前结点的路径，一个用来保存满足条件的所有路径，
思路上递归条件和结束条件是完全一致的，空间上这里会依赖于结果的数量了。
Sum Root to Leaf Numbers这道题多了两个变化，一个是每一个结点相当于位上的值，而不是本身有权重，不过其实没有太大变化，每一层乘以10加上自己的值就可以了。
另一个变化就是要把所有路径累加起来，这个其实就是递归条件要进行调整，Path Sum中是判断左右子树有一个找到满足要求的路径即可，而这里则是把左右子树的结果相加返回作为当前节点的累加结果即可。
变化比较大而且有点难度的是Binary Tree Maximum Path Sum，这道题目的路径要求不再是从根到叶子的路径，这个题目是把树完全看成一个无向图，然后寻找其中的路径。
想起来就觉得比上面那种麻烦许多，不过仔细考虑会发现还是有章可循的，找到一个根节点最大路径，无非就是找到左子树最大路径，加上自己的值，再加上右子树的最大路径（这里左右子树的路径有可能不取，如果小于0的话）。
我们要做的事情就是对于每个结点都做一次上面说的这个累加。而左子树最大路径和右子树最大路径跟Path Sum II思路是比较类似的，虽然不一定要到叶子节点，不过标准也很简单，有大于0的就取，如果走下去路径和小于0那么就不取。
从分治的角度来看，左右子树的最大路径就是取自己的值加上Max(0，左子树最大路径，右子树最大路径)。这么一想也就不用考虑那么多细节了。而通过当前节点的最长路径则是自己的值+Max(0，左子树最大路径)+Max(0，右子树最大路径)。
所以整个算法就是维护这两个量，一个是自己加上左或者右子树最大路径作为它的父节点考虑的中间量，另一个就是自己加上左再加上右作为自己最大路径。具体的实现可以参见Binary Tree Maximum Path Sum
总体来说，求和路径有以下三种：（1）根到叶子结点的路径；（2）父结点沿着子结点往下的路径；（3）任意结点到任意结点（也就是看成无向图）。
这几种路径方式在面试中经常灵活变化，不同的路径方式处理题目的方法也会略有不同，不过最复杂也就是Binary Tree Maximum Path Sum这种路径方式，只要考虑清楚仍然是一次递归遍历的问题

Reference:
http://www.ninechapter.com/solutions/path-sum-ii/
http://blog.sina.com.cn/s/blog_eb52001d0102v264.html
https://yusun2015.wordpress.com/2015/01/10/path-sum-ii/
http://blog.csdn.net/linhuanmars/article/details/23655413
(树的求和总结)http://blog.csdn.net/linhuanmars/article/details/38976917
*/