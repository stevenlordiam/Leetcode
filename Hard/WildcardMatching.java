/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

// For detailed explanation, see https://leetcode.com/discuss/questions/oj/wildcard-matching
// Time: O(|s||p|*log|s|), Space: O(|s|)
// Time can also optimize to O(|s||p|)
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        // without this optimization, it will fail for large data set
        int plenNoStar = 0;
        for (char c : p.toCharArray())
            if (c != '*') plenNoStar++;
        if (plenNoStar > s.length()) return false;

        s = " " + s;
        p = " " + p;
        int slen = s.length();
        int plen = p.length();

        boolean[] dp = new boolean[slen];   // 记录0的状态，一共len+1个
        TreeSet<Integer> firstTrueSet = new TreeSet<Integer>();
        firstTrueSet.add(0);
        dp[0] = true;

        boolean allStar = true;
        for (int pi = 1; pi < plen; pi++) {
            if (p.charAt(pi) != '*')
                allStar = false;
            for (int si = slen - 1; si >= 0; si--) {
                if (si == 0) {
                    dp[si] = allStar ? true : false;
                } else if (p.charAt(pi) != '*') {
                    if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?') dp[si] = dp[si-1];
                    else dp[si] = false;
                } else {
                    int firstTruePos = firstTrueSet.isEmpty() ? Integer.MAX_VALUE : firstTrueSet.first();
                    if (si >= firstTruePos) dp[si] = true;
                    else dp[si] = false;
                }
                if (dp[si]) firstTrueSet.add(si);
                else firstTrueSet.remove(si);
            }
        }
        return dp[slen - 1];
    }
}

/*
这个和regex的 .*不一样。这个是平时cd src*这种；?可以match任意一个字符，*可以match任意一个字符串，可以是空
算法：每次两个字符串的第一个字母比较，若p的第一个字母是*，则一点点的chop s和p除了*剩下的recursive比较
p的那个*可以占用s的0个字符，1个字符，2个字符。。
一点优化：出现连续的*，取最右边那个就行

这道题目其实是Regular Expression Matching的简化版，在这里'?'相当于那边的'.'，而'*'相当于那边的'.*'，因为这里'*'就可以代替任何字符串，不需要看前面的字符，所以处理起来更加简单
brute force的方法就不重新列举代码了，有兴趣实现的朋友可以参考一下Regular Expression Matching，代码结构一样，只是处理情况变一下就可以，不过leetcode过不了（因为超时）
我们主要还是说一下动态规划的方法。跟Regular Expression Matching一样，还是维护一个假设我们维护一个布尔数组res[i],代表s的前i个字符和p的前j个字符是否匹配(这里因为每次i的结果只依赖于j-1的结果，所以不需要二维数组，只需要一个一维数组来保存上一行结果即可）
递推公式分两种情况：
(1)p[j]不是'*'。情况比较简单，只要判断如果当前s的i和p的j上的字符一样（如果有p在j上的字符是'?'，也是相同），并且res[i]==true，则更新res[i+1]为true，否则res[i+1]=false;  
(2)p[j]是'*'。因为'*'可以匹配任意字符串，所以在前面的res[i]只要有true，那么剩下的res[i+1], res[i+2],...,res[s.length()]就都是true了
算法的时间复杂度因为是两层循环，所以是O(m*n), 而空间复杂度只用一个一维数组，所以是O(n)，假设s的长度是n，p的长度是m
这种模糊匹配的题目是面试中的一类题目，一般在onsite的时候会遇到

Reference:
https://leetcodenotes.wordpress.com/2013/09/30/leetcode-wildcard-matching-unix%E9%82%A3%E7%A7%8Dwildcard-matching/
http://www.ninechapter.com/solutions/wildcard-matching/
http://blog.csdn.net/linhuanmars/article/details/21198049
http://www.cnblogs.com/yuzhangcmu/p/4116153.html
http://m4tiku.duapp.com/report?pid=123
http://blog.csdn.net/kenden23/article/details/17123497
*/