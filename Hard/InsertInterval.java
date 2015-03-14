/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
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
public class InsertInterval {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if (newInterval == null || intervals == null) {
            return intervals;
        }

        ArrayList<Interval> results = new ArrayList<Interval>();
        int insertPos = 0;        // insertion position

        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                results.add(interval);
                insertPos++;
            } else if (interval.start > newInterval.end) {
                results.add(interval);
            } else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }
        results.add(insertPos, newInterval);
        return results;
    }
}

/*
Similar to Merge Intervals - https://leetcode.com/problems/merge-intervals/

这个题是非常常见的面试题，自己试着implement了两种方法，一个O(logn)，一个O(n)，但是写起来都不是很简洁
O(n)的方法：binary search by start，找到一个要insert的位置(start1<start<=start2)，取那个大的start2（因为它肯定会被start给replace掉，start1则不一定）
插入这个新interval，然后从头到尾检查overlap（两个参数无论谁前谁后都能用这个helper查出来），有就merge，不断吞噬overlap的。代码写起来有点长，不过思路非常清晰，不用考虑任何边界条件（什么a.start <= b.end…那些都烦死人了）
interview的时候可以写这种，因为binary search那段可能都不让你写。还有一个trick是用iterator可以直接修改正在iterate的list，不会报modification exception，第一次用，很方便
另一种方法是binary search，找出要start属于的位置，end属于的位置。但是很不好写，需要判断那些this.start >=that.end之类的，就更长了

这道题跟Merge Intervals很类似，都是关于数据结构interval的操作。事实上，Merge Intervals是这道题的子操作，就是插入一个interval，如果出现冲突了，就进行merge
跟Merge Intervals不一样的是，这道题不需要排序，因为插入之前已经默认这些intervals排好序了。简单一些的是这里最多只有一个连续串出现冲突，因为就插入那么一个
基本思路就是先扫描走到新的interval应该插入的位置，接下来就是插入新的interval并检查后面是否冲突，一直到新的interval的end小于下一个interval的start，然后取新interval和当前interval中end大的即可
因为要进行一次线性扫描，所以时间复杂度是O(n)。而空间上如果我们重新创建一个ArrayList返回，那么就是O(n)。有朋友可能会说为什么不in-place的进行操作，这样就不需要额外空间，但是如果使用ArrayList这个数据结构，那么删除操作是线性的，如此时间就不是O(n)的
如果这道题是用LinkedList那么是可以做到in-place的，并且时间是线性的。这道题有一个变体，就是如果插入的时候发现冲突，那就返回失败，不插入了
看起来好像比上面这道题还要简单，但是要注意的是，如此我们就不需要进行线性扫描了，而是进行二分查找，如果不冲突，则进行插入，否则直接返回失败。这样时间复杂度可以降低到O(logn)。当然这里需要用二分查找树去维护这些intervals
同时，这种题目还可以问一些关于OO设计的东西，比如就直接问你要实现一个intervals的类，要维护哪些变量，实现哪些功能，用什么数据结构，等等。这些你可以跟面试官讨论，然后根据他的功能要求用相应的数据结构。所以扩展性还是很强的

Analysis:
- create a list;
- put the new interval into the list;
- insert the original intervals into the list;
- if current interval has no overlap with the last interval in the list, insert it to the last position( current start > last start)  or in front of the last position(current start<last start)
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res=new ArrayList<>();
        res.add(newInterval);
        if(intervals.size()<1) return res;
        for(Interval in:intervals){
           int a=res.get(res.size()-1).start;
           int b=res.get(res.size()-1).end;
           if(in.end<a) res.add(res.size()-1,in);
           else if(b<in.start) res.add(in);
           else{
               res.get(res.size()-1).start=Math.min(a,in.start);
               res.get(res.size()-1).end=Math.max(b,in.end);
           }
        }
        return res;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/19/insert-interval/
http://www.ninechapter.com/solutions/insert-interval/
http://blog.csdn.net/linhuanmars/article/details/22238433
https://yusun2015.wordpress.com/2015/01/16/insert-interval/
*/