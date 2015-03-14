/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

public class LongestConsecutiveSequence {
    // Sort & search: space O(1), time O(n logn)
    // HashMap: space O(n), time O(n)
    public int longestConsecutive(int[] num) {
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
        for(int i: num){
            hs.put(i, 0);
        }
        int maxl = 1;
        for(int i: num){
            if (hs.get(i) == 1) continue;

            int tmp = i;
            int current_max = 1;
            while(hs.containsKey(tmp+1)){       // tmp右边tmp+1
                current_max ++;
                tmp ++;
                hs.put(tmp, 1);
            }

            tmp = i;
            while(hs.containsKey(tmp-1)){       // tmp左边tmp-1
                current_max ++;
                tmp --;
                hs.put(tmp, 1);
            }

            maxl = Math.max(current_max, maxl);
        }

        return maxl;
    }
}

/*
这个算法挺难想到的。让O(n)说明不能sort，那只能用container。但是用container怎么traverse连续的呢？又不能sort key
所以，我们人眼是怎么做这个题的呢，上来是100，我们其实就想找99和101，有的话就接着那个方向，直到找不着了为止
这样算过的数就从set里删掉，既然是别人的连续，那也是自己的连续，所以不用看了。这样过一遍就能把所有连续数列找全，没有元素重复处理的
public int longestConsecutive(int[] num) {
    int res = 0;
    Set<Integer> set = new HashSet<Integer>();
    for (int i : num)
        set.add(i);
    for (int i = 0; i < num.length && !set.isEmpty(); i++) {
        int len = 0;
        //go up
        for (int j = num[i] - 1; set.contains(j); j--) {
            set.remove(j);
            len++;
        }
        //go down
        for (int j = num[i]; set.contains(j); j++) {
            set.remove(j);
            len++;
        }
        res = Math.max(res, len);
    }
    return res;
}

Analysis:
Put all the elements in a hash map. Then traverse the array, if the the i-th element has not been involved in a consecutive subarray, find the longest subarray which contains it, otherwise skip it.

这道题是要求出最长的整数连续串。我们先说说简单直接的思路，就是先排序，然后做一次扫描，记录当前连续串长度，如果连续串中断，则比较是否为当前最长连续串，并且把当前串长度置0
这样时间复杂度是很明确，就是排序的复杂度加上一次线性扫描。如果不用特殊的线性排序算法，复杂度就是O(nlogn)
其实这个题看起来是数字处理，排序的问题，但是如果要达到好的时间复杂度，还得从图的角度来考虑。思路是把这些数字看成图的顶点，而边就是他相邻的数字，然后进行深度优先搜索
通俗一点说就是先把数字放到一个集合中，拿到一个数字，就往其两边搜索，得到包含这个数字的最长串，并且把用过的数字从集合中移除（因为连续的关系，一个数字不会出现在两个串中）
最后比较当前串是不是比当前最大串要长，是则更新。如此继续直到集合为空。如果我们用HashSet来存储数字，则可以认为访问时间是常量的，那么算法需要一次扫描来建立集合，第二次扫描来找出最长串，所以复杂度是O(2*n)=O(n)，空间复杂度是集合的大小，即O(n)
这是一个非常不错的题目，有比较好的算法思想，看起来是一个排序扫描的题目，其实想要优化得借助图的算法，模型也比较简单，很适合在面试中提问

Reference:
https://leetcodenotes.wordpress.com/2013/07/21/longest-consecutive-sequence/
http://www.ninechapter.com/solutions/longest-consecutive-sequence/
http://blog.csdn.net/linhuanmars/article/details/22964467
https://yusun2015.wordpress.com/2015/01/10/longest-consecutive-sequence/
*/