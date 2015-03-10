/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

public class SubsetsII {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(num == null || num.length ==0) {
            return result;
        }
        Arrays.sort(num);
        subsetsHelper(result, list, num, 0);
        return result;
    }

    private void subsetsHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int[] num, int pos) {
        result.add(new ArrayList<Integer>(list));
        for (int i = pos; i < num.length; i++) {
            if ( i != pos && num[i] == num[i - 1]) {    // the only difference with subsets is that here you have to check for dups
                continue;
            }    
            list.add(num[i]);
            subsetsHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

/*
Similar to CC150 (9-4) subsets (RecursionAndDynamicProgramming_4.java)
Similar to Subsets - https://leetcode.com/problems/subsets/

subset 2:  先sort，然后往后加。重点是怎么保证不重复？比如array[] = {1, 2, 2}

这道题跟Subsets一样是经典的NP问题--求子集。比Subsets稍微复杂一些的是这里的集合中可能出现重复元素，因此我们在求子集的时候要避免出现重复的子集
在Subsets中我们每次加进一个元素就会把原来的子集都加上这个元素，然后再加入到结果集中，但是这样重复的元素就会产生重复的子集。为了避免这样的重复，需要用个小技巧
其实比较简单，就是每当遇到重复元素的时候我们就只把当前结果集的后半部分加上当前元素加入到结果集中，因为后半部分就是上一步中加入这个元素的所有子集，上一步这个元素已经加入过了，前半部分如果再加就会出现重复所以算法上复杂度上没有提高，反而少了一些操作，就是遇到重复时少做一半，不过这里要对元素集合先排序，否则不好判断重复元素
同样的还是可以用递归和非递归来解，不过对于重复元素的处理是一样的，递归/非递归的代码如下：http://blog.csdn.net/linhuanmars/article/details/24613193
这种NP问题的重复处理在LeetCode有一定出现频率，比如还有Permutations II也是这样的，其实本质就是当一个重复元素进来时忽略上一个元素已经有的结果，只考虑由重复元素所产生的新结果

To avoid duplicates, have to use a tree map to record the elements and their counts in num[]
If the count of the element “e” is C, then just need to add {e}(1),{e,e}(2),…{e,e,e,…,e}(C) to all the original lists and add these new lists to  the result
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> list=new ArrayList<>();
        list.add(new ArrayList<Integer>());
        Map<Integer,Integer> map=new TreeMap<>();
        for(int i=0;i<num.length;i++){
            if(map.keySet().contains(num[i])) map.put(num[i],map.get(num[i])+1);
            else map.put(num[i],1);
        }
        for(int k:map.keySet()){
            int L=list.size();
            for(int i=0;i<L;i++){
                List<Integer> temp=list.get(i);
                for(int j=0;j<map.get(k);j++){
                    temp=new ArrayList<>(temp);
                    temp.add(k);
                    list.add(temp);
                }
            }
        }
        return list;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/08/leetcode-subsets-2-%E7%BB%99%E4%B8%80%E4%B8%AA%E6%95%B0%E7%BB%84%EF%BC%8C%E8%BE%93%E5%87%BA%E6%89%80%E6%9C%89%E5%8D%87%E5%BA%8F%E7%9A%84%E4%B8%8D%E9%87%8D%E5%A4%8D%E7%9A%84subsets/
http://www.ninechapter.com/solutions/subsets-ii/
http://blog.csdn.net/linhuanmars/article/details/24613193
https://yusun2015.wordpress.com/2015/01/11/subsets-ii/
*/