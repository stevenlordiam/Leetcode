/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*/

public class WordBreak {
    public boolean wordBreak(String s, Set<String> dict) {
       int n=s.length();
       boolean[] res = new boolean[n+1];
       res[0] = true;
       for(int i=0; i<n; i++) {
           for(int j=0; j<=i; j++) {
               if(res[j]&&dict.contains(s.substring(j,i+1))) {
                   res[i+1] = true;
               }
           }
       }
       return res[res.length-1];
    }
}

/*
Dynamic Programming

算法是：
- 拿字典建prefix tree：O(字典大小*最长词的长度）
- i=0开始recurse, j=i开始一点点增长当前substring长度，若curr substring在字典里，就看看j + 1开头的剩下一半能不能成；能成就直接返回true，不能的话还得继续i++
- curr substring不在字典里，那应该j++的，但这时候先看一看curr是不是至少是个prefix，要是连prefix都不是，这个i直接作废，i++
- 每次做好了cache一下，boolean cache[i]表示以i开头到词尾的这个substring能不能用字典组成

这道题仍然是动态规划的题目，我们总结一下动态规划题目的基本思路: 首先我们要决定要存储什么历史信息以及用什么数据结构来存储信息
然后是最重要的递推式，就是如从存储的历史信息中得到当前步的结果。最后我们需要考虑的就是起始条件的值, 接下来我们套用上面的思路来解这道题
首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，我们需要一个长度为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，我们来获得res[i]的表达式
思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true
假设总共有n个字符串，并且字典是用HashSet来维护，那么总共需要n次迭代，每次迭代需要一个取子串的O(i)操作，然后检测i个子串，而检测是constant操作
所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），而空间复杂度则是字符串的数量，即O(n)

Analysis: 
assume res[] is a boolean vector, and s(0,i) can be segmented if only res[i]=true; then we have
- res[0]=true
- res[i]=true if for some j<i such that res[j]=true and set contains s(j,i)

Reference:
https://leetcodenotes.wordpress.com/2013/10/06/leetcode-word-break-%E4%BA%8C%E5%88%86%E6%B3%95%E7%9C%8B%E4%B8%80%E4%B8%AA%E8%AF%8D%E6%98%AF%E4%B8%8D%E6%98%AF%E8%83%BD%E7%94%A8%E5%AD%97%E5%85%B8%E9%87%8C%E7%9A%84%E8%AF%8D%E7%BB%84%E6%88%90/
http://www.ninechapter.com/solutions/word-break/
http://blog.csdn.net/linhuanmars/article/details/22358863
https://yusun2015.wordpress.com/2015/01/16/word-break/
*/