/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        //Java note: s.substring(n) (n is the start index) will be "" if n == s.length(), but if n > s.length(), index oob error
        
        int i = 0, j = 0;
        //you don't have to construct a state machine for this problem
    
        // 注意检查corner case
        if (s.length() == 0) {
            return checkEmpty(p);
        }
        if (p.length() == 0) {
            return false;
        }
 
        char c1 = s.charAt(0);
        char d1 = p.charAt(0), d2 = '0'; //any init value except '*'for d2 will do
 
        if (p.length()>1){
            d2 = p.charAt(1);
        }
 
        if (d2 == '*') {
            if (compare(c1, d1)) {
                //fork here: 1. consume the character, and use the same pattern again.
                //2. keep the character, and skip 'd1*' pattern
                 
                //Here is also an opportunity to use DP, but the idea is the same
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));    
                // 注意check两种情况！！！两种情况有一种满足即为true
                // isMatch("aa", "a*") → true
                // isMatch("aab", "c*a*b") → true 
            }
            else {
                return isMatch(s, p.substring(2));      // isMatch("aab", "c*a*b") → true
            }
        }
        else {
            if (compare(c1, d1)) {
                return isMatch(s.substring(1), p.substring(1));
            }
            else {
                return false;
            }
        }
    }
    
    public boolean compare(char c1, char d1){
        return d1 == '.' || c1 == d1;
    }
 
    public boolean checkEmpty(String p) {
        if (p.length()%2 != 0) {
            return false;  
        }
        for (int i = 1; i < p.length(); i+=2) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}

/*
思路：
两个指针i, j，i指regular expression，j指真的string。
观察p[i+1]，如果它不是*，说明没法skip p[i]，则p[i]和s[j]必须match（相等或者p[i]是’.’ which matches everything）
如果不满足，直接返回false，没法match了。如果它是*，说明当前位置可以是0个p[i], 1个p[i], 2个p[i]..先试试“用0个p[i]“，即把p[i]和他后面的* skip掉
若recurse (i + 2, j)成功了，直接返回true，不成功，接着做第二步。从“用1个p[i]“开始while循环，若p[i]和s[j] match, recurse on (i + 2, j + 1)（把*用掉了所以i+2）
如果返回true就直接成了，否则就while继续循环“用2个p[i]”，即recurse on (i + 2, j + 2), recurse on (i + 2, j + 3)…循环的终止条件是p[i]和s[j]不match了，直接返回false

Analysis: 
Let D[i][j] be true if and only if s(0,i) matches p(0,j), then
- if p(j)=s(i) or ‘.’, D[i][j]=D[i-1][j-1];
- if p(j)=’*’, ‘*’ can match zero preceding element, so D[i][j]=true when D[i][j-2]=true;
- if p(j)=’*’, ‘*’ can match one preceding element, so D[i][j]=true when D[i][j-1]=true;
- if p(j)=’*’, ‘*’ can match more than one preceding element, so D[i][j]=true when s(i)=p(j-1) and D[i-1][j]=true.
// 
public class Solution {
    public boolean isMatch(String s, String p) {
        int m=s.length(),n=p.length();
        boolean[] D=new boolean[n+1];
        for(int i=-1;i<m;i++){
            boolean[] temp=new boolean[n+1];
            if(i==-1) temp[0]=true;
            for(int j=0;j<n;j++){
                if(p.charAt(j)=='.'||(i>=0&&s.charAt(i)==p.charAt(j))) temp[j+1]=D[j];
                else if(p.charAt(j)=='*'){
                    if(temp[j-1]||temp[j]){
                        temp[j+1]=true;
                        continue;
                    }
                   if(p.charAt(j-1)=='.'||(i>=0&&s.charAt(i)==p.charAt(j-1))) {
                       temp[j+1]=D[j+1];
                       continue;
                   }
                }
            }
            D=temp;
        }
       return D[D.length-1];
    }
}

这个题目比较常见，但是难度还是比较大的。我们先来看看brute force怎么解决。基本思路就是先看字符串s和p的从i和j开始的子串是否匹配，用递归的方法直到串的最后，最后回溯回来得到结果
假设现在走到s的i位置，p的j位置，情况分为下列两种： 
(1)p[j+1]不是'*'。情况比较简单，只要判断当前s的i和p的j上的字符是否一样（如果有p在j上的字符是'.',也是相同），如果不同，返回false，否则，递归下一层i+1，j+1; 
(2)p[j+1]是'*'。那么此时看从s[i]开始的子串，假设s[i],s[i+1],...s[i+k]都等于p[j]那么意味着这些都有可能是合适的匹配，那么递归对于剩下的(i,j+2),(i+1,j+2),...,(i+k,j+2)都要尝试（j+2是因为跳过当前和下一个'*'字符） 
接下来我们考虑如何优化brute force算法，熟悉动态规划的朋友可能发现了，其实这个思路已经很接近动态规划了。动态规划基本思想就是把我们计算过的历史信息记录下来，等到要用到的时候就直接使用，不用重新计算
在这个题里面，假设我们维护一个布尔数组res[i][j],代表s的前i个字符和p的前j个字符是否匹配(注意这里res的维度是s.length()+1,p.length()+1)。递推公式跟上面类似，分三种种情况： 
(1)p[j+1]不是'*'。情况比较简单，只要判断如果当前s的i和p的j上的字符一样（如果有p在j上的字符是'.',也是相同），并且res[i][j]==true，则res[i+1][j+1]也为true，res[i+1][j+1]=false; 
(2)p[j+1]是'*'，但是p[j]!='.'。那么只要以下条件有一个满足即可对res[i+1][j+1]赋值为true： 
    1)res[i+1][j]为真（'*'只取前面字符一次）; 
    2)res[i+1][j-1]为真（'*'前面字符一次都不取，也就是忽略这两个字符）; 
    3)res[i][j+1] && s[i]==s[i-1] && s[i-1]==p[j-1](这种情况是相当于i从0到s.length()扫过来，如果p[j+1]对应的字符是‘*’那就意味着接下来的串就可以依次匹配下来，如果下面的字符一直重复，并且就是‘*’前面的那个字符）
(3)p[j+1]是'*'，并且p[j]=='.'。因为".*"可以匹配任意字符串，所以在前面的res[i+1][j-1]或者res[i+1][j]中只要有i+1是true，那么剩下的res[i+1][j+1],res[i+2][j+1],...,res[s.length()][j+1]就都是true了
这道题有个很重要的点，就是实现的时候外层循环应该是p,然后待匹配串s内层循环扫过来
对比以上两种做法，其实思路基本类似，动态规划优势在于对于前面计算过得信息不需要再重复计算，而brute force则每次重新计算
上面两种算法中，动态规划的时间复杂度是O(n^2),空间复杂度也是O(n^2)。而brute force的递归算法最坏情况是指数量级的复杂度
这种题目在面试中算是比较难的题目，因为分情况比较多，如果不熟悉比较难在面试中理清思路并且做对，我不是很喜欢，但是在面经中却经常看到，所以还是得搞熟悉比较好
类似的题目有Wildcard Matching，那个还更简单一些，不过思路是基本一致的，只是少分一点情况

Reference:
https://leetcodenotes.wordpress.com/2013/08/25/leetcode-regular-expression-matching/
http://www.ninechapter.com/solutions/regular-expression-matching/
http://blog.csdn.net/linhuanmars/article/details/21145563
https://yusun2015.wordpress.com/2015/01/25/regular-expression-matching/
*/