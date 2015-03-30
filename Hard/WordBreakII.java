/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

public class WordBreakII {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        return wordBreakHelper(s,dict,map);
    }

    public ArrayList<String> wordBreakHelper(String s, Set<String> dict, Map<String, ArrayList<String>> memo){
        if(memo.containsKey(s)) return memo.get(s);
        ArrayList<String> result = new ArrayList<String>();
        int n = s.length();
        if(n <= 0) return result;
        for(int len = 1; len <= n; ++len){
            String subfix = s.substring(0,len);
            if(dict.contains(subfix)){
                if(len == n){     // if len==n, s.substring(len) will return null, so deal with this case seperately  
                    result.add(subfix);
                }else{
                    String prefix = s.substring(len);   // len is start index of substring
                    ArrayList<String> tmp = wordBreakHelper(prefix, dict, memo);
                    for(String item:tmp){
                        item = subfix + " " + item;
                        result.add(item);
                    }
                }
            }
        }
        memo.put(s, result);
        return result;
    }
}

/*
"catsand   dog"
 <-sub->|<-pre->
 
这题是cache里面存数组: Map<Integer, List<String>> cache，和上题思路一样，不过边界条件不好想：
找不到的时候是返回空list还是null?
找到结尾正好成功了(i == string.len)的时候，返回空还是null?
这两种情况必须区分。因为要在每个list entry里生成新的substring，所以还是找不到的时候返回空list比较好（空的正好不用进loop了）；而找到头的时候，刚好check null，然后把当前词尾返回，catch了最后一个词不用加” “的情况

这道题目要求跟Word Break比较类似，不过返回的结果不仅要知道能不能break，如果可以还要返回所有合法结果。一般来说这种要求会让动态规划的效果减弱很多，因为我们要在过程中记录下所有的合法结果，中间的操作会使得算法的复杂度不再是动态规划的两层循环，
因为每次迭代中还需要不是constant的操作，最终复杂度会主要取决于结果的数量，而且还会占用大量的空间，因为不仅要保存最终结果，包括中间的合法结果也要一一保存，否则后面需要历史信息会取不到
所以这道题目我们介绍两种方法，一种是直接brute force用递归解，另一种是跟Word Break思路类似的动态规划
对于brute force解法，代码比较简单，每次维护一个当前结果集，然后遍历剩下的所有子串，如果子串在字典中出现，则保存一下结果，并放入下一层递归剩下的字符。思路接近于我们在N-Queens这些NP问题中经常用到的套路
接下来我们列出动态规划的解法，递推式跟Word Break是一样的，只是现在不只要保存true或者false，还需要保存true的时候所有合法的组合，以便在未来需要的时候可以得到
不过为了实现这点，代码量就增大很多，需要一个数据结构来进行存储，同时空间复杂度变得非常高，因为所有中间组合都要一直保存
时间上还是有提高的，就是当我们需要前面到某一个元素前的所有合法组合时，我们不需要重新计算了不过最终复杂度还是主要取决于结果的数量
可以看出，用动态规划的代码复杂度要远远高于brute force的解法，而且本质来说并没有很大的提高，甚至空间上还是一个暴涨的情况。所以这道题来说并不是一定要用动态规划，有一个朋友在面Amazon时遇到这道题，面试官并没有要求动态规划，用brute force即可，不过两种方法时间上和空间上的优劣还是想清楚比较好，面试官可能想听听理解。实现的话可能主要是递归解法
还有一点需要指出的是，上面的两个代码放到LeetCode中都会超时，原因是LeetCode中有一个非常tricky的测试case，其实是不能break的，但是又很长，出现大量的记录和回溯，因此一般通过这个的解法是把Word Break先跑一遍，判断是不是能break，如果可以再跑上面的代码

Reference:
https://leetcodenotes.wordpress.com/2013/10/06/leetcode-word-break-2-%E6%8A%8Astring%E6%89%80%E6%9C%89%E8%83%BD%E7%94%A8dict%E7%BB%84%E6%88%90%E7%9A%84%E7%BB%84%E6%B3%95print%E5%87%BA%E6%9D%A5/
http://www.ninechapter.com/solutions/word-break-ii/
http://blog.csdn.net/linhuanmars/article/details/22452163
https://yusun2015.wordpress.com/2015/03/17/word-ladder-ii/
http://www.cnblogs.com/yuzhangcmu/p/4037299.html
*/