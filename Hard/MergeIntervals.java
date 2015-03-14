/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class MergeIntervals {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        Collections.sort(intervals, new IntervalComparator());       
  
        ArrayList<Interval> result = new ArrayList<Interval>();
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end ){
                last.end = Math.max(last.end, curt.end);
            }else{
                result.add(last);
                last = curt;
            }
        }
        result.add(last);
        return result;
    }
    
    private class IntervalComparator implements Comparator<Interval> { 		// 自定义comparator
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }
}

/*
Similar to Insert Interval - https://leetcode.com/problems/insert-interval/

思路：
先sort by start，然后不断判断当前的interval能不能吞噬下一个(curr.end >= next.start，说明这两个能连起来）。做的过程中发现几个问题：
- 本来是想直接循环list，然后不断吞噬，删掉被吞噬的。但是这样就一边iterate一边modify array了，会挂。所以还得用另一个list，然后决定是不断删list2里的东西，还是不断往list2里加东西。这个要拿例子试试哪种好使
- 一开始写的是if (curr.end >= next.start) then curr.end = next.end。然后挂了几个test case，才注意到忘记了curr本身包括了next的情况。应该是curr.next = Math.max(curr.end, next.end)
public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
  Collections.sort(intervals, new Comparator<Interval>() {
    public int compare(Interval a, Interval b) {
      return a.start - b.start;
    }
  });
  ArrayList<Interval> result = new ArrayList<Interval>();
  for (int i = 0; i < intervals.size(); i++) {
    Interval curr = intervals.get(i);
    while (i < intervals.size() - 1 && curr.end >= intervals.get(i + 1).start) {
      curr.end = Math.max(curr.end, intervals.get(i + 1).end);
      i++;
    }//while出来说明curr已经吃饱了，可以加入result了。
    result.add(curr);
  }
  return result;
}

这是一道关于interval数组结构的操作，在面试中也是一种比较常见的数据结构。假设这些interval是有序的（也就是说先按起始点排序，然后如果起始点相同就按结束点排序）
那么要把它们合并就只需要按顺序读过来，如果当前一个和结果集中最后一个有重叠，那么就把结果集中最后一个元素设为当前元素的结束点（不用改变起始点因为起始点有序，因为结果集中最后一个元素起始点已经比当前元素小了）
那么剩下的问题就是如何给interval排序，在java实现中就是要给interval自定义一个Comparator，规则是按起始点排序，然后如果起始点相同就按结束点排序
整个算法是先排序，然后再做一次线性遍历，时间复杂度是O(nlogn+n)=O(nlogn)，空间复杂度是O(1)，因为不需要额外空间，只有结果集的空间
自定义Comparator有时候在面试中也会要求实现，LeetCode中关于interval的题目还有Insert Interval

Analysis:
- put all the intervals to the tree map to sort them;
- create a new list;
- put the intervals in the tree  map to the list and if current interval has no intersection with the last one in the list, put the current one in the list, otherwise merge the two intervals
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res=new LinkedList<>();
        if(intervals.size()<2) return intervals;
        Map<Integer,Integer> map=new TreeMap<>();
        for(Interval temp:intervals){
            if(map.keySet().contains(temp.start)){
                map.put(temp.start,Math.max(map.get(temp.start),temp.end));
            }
            else{
                map.put(temp.start,temp.end);
            }
        }
        for(Integer i:map.keySet()){
            if(res.size()==0){
                res.add(new Interval(i,map.get(i)));
            }
            else{
                int b=res.get(res.size()-1).end;
                if(b<i) res.add(new Interval(i,map.get(i)));
                else{
                    res.get(res.size()-1).end=Math.max(b,map.get(i));
                }
            }
        }
        return res;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/08/01/merge-intervals/
http://www.ninechapter.com/solutions/merge-intervals/
http://blog.csdn.net/linhuanmars/article/details/21857617
https://yusun2015.wordpress.com/2015/01/16/merge-intervals/
*/