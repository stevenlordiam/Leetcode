/*
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

public class Subsets {
    public ArrayList<ArrayList<Integer>> subsets(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(num);  
        subsetsHelper(result, list, num, 0);
        return result;
    }

    private void subsetsHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int[] num, int pos) {     // pos - position
        result.add(new ArrayList<Integer>(list));
        for (int i = pos; i < num.length; i++) {        // 回溯法：add -> recursion -> remove last element
            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

/*
Similar to CC150 (9-4) subsets (RecursionAndDynamicProgramming_4.java)
Similar to Subsets II - https://leetcode.com/problems/subsets-ii/

subset 1:  array本身都是distinct number，所以往里一个一个插不用担心重复。{}, 放第一个，放第二个。。

求子集问题是经典的NP问题。一般来说这个问题有两种解法：递归和非递归。
我们先来说说递归解法，主要递推关系就是假设函数返回递归集合，现在加入一个新的数字，我们如何得到包含新数字的所有子集
其实就是在原有的集合中对每集合中的每个元素都加入新元素得到子集，然后放入原有集合中（原来的集合中的元素不用删除，因为他们也是合法子集）
而结束条件就是如果没有元素就返回空集（注意空集不是null，而是没有元素的数组）就可以了。时间和空间都是取决于结果的数量，也就是O(2^n)
非递归解法的思路和递归是一样的，只是没有回溯过程，也就是自无到有的一个个元素加进来，然后构造新的子集加入结果集中
这种NP问题基本上就是考察对于递归和非递归解法的理解，都是和N-Queens一个套路

The total number of the subset is 2^(S.length).  Each number n between 0 and 2^(S.length)-1 corresponds to a subset. If and only If the ith bit of n(binary representation) is 1,  S[i] should be involved in the subset.
一共2^L个子集，可以用一共二进制数来表示，1表示元素在子集中，0表示不在子集中
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        List<List<Integer>> list=new LinkedList<>();
        int len=1<<S.length;        // total number of subsets is 2^(S.length)
        for(int i=0;i<len;i++){
            List<Integer> temp=new LinkedList<>();
            int d=i;
            for(int j=0;j<S.length;j++){
                if(d==0) break;
                if(d%2==1) temp.add(S[j]);
                d=d/2;  // 右移一位
            }
            list.add(temp);
        }
        return list;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/08/leetcode-subsets-2-%E7%BB%99%E4%B8%80%E4%B8%AA%E6%95%B0%E7%BB%84%EF%BC%8C%E8%BE%93%E5%87%BA%E6%89%80%E6%9C%89%E5%8D%87%E5%BA%8F%E7%9A%84%E4%B8%8D%E9%87%8D%E5%A4%8D%E7%9A%84subsets/
http://www.ninechapter.com/solutions/subsets/
http://blog.csdn.net/linhuanmars/article/details/24286377
https://yusun2015.wordpress.com/2015/01/10/subsets/
*/