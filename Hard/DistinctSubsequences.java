/*
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

public class DistinctSubsequences {
    public int numDistinct(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }

        int[][] nums = new int[S.length() + 1][T.length() + 1];

        for (int i = 0; i < S.length(); i++) {
            nums[i][0] = 1;
        }
        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                nums[i][j] = nums[i - 1][j];
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    nums[i][j] += nums[i - 1][j - 1];
                }
            }
        }
        return nums[S.length()][T.length()];
    }
}

/*
这道题应该很容易感觉到是动态规划的题目。还是老套路，先考虑我们要维护什么量。这里我们维护res[i][j]，对应的值是S的前i个字符和T的前j个字符有多少个可行的序列（注意这道题是序列，不是子串，也就是只要字符按照顺序出现即可，不需要连续出现）
下面来看看递推式，假设我们现在拥有之前的历史信息，我们怎么在常量操作时间内得到res[i][j]。假设S的第i个字符和T的第j个字符不相同，那么就意味着res[i][j]的值跟res[i-1][j]是一样的，前面该是多少还是多少，而第i个字符的加入也不会多出来任何可行结果
如果S的第i个字符和T的第j个字符相同，那么所有res[i-1][j-1]中满足的结果都会成为新的满足的序列，当然res[i-1][j]的也仍是可行结果，所以res[i][j]=res[i-1][j-1]+res[i-1][j]
所以综合上面两种情况，递推式应该是res[i][j]=(S[i]==T[j]?res[i-1][j-1]:0)+res[i-1][j]。算法进行两层循环，时间复杂度是O(m*n)，而空间上只需要维护当前i对应的数据就可以，也就是O(m)
可以看到代码跟上面推导的递推式下标有点不同，因为下标从0开始，这种细节在实现的时候比较能想清楚，这里res[j+1]相当于T的前j个字符对应的串，少看一个
而res[0]表示一个字符都没有时的结果，最后结果返回res[T.length()]，对应于整个字符串的可行序列的数量
public int numDistinct(String S, String T) {
    if(T.length()==0)
    {
        return 1;
    }
    if(S.length()==0)
        return 0;
    int[] res = new int[T.length()+1];
    res[0] = 1;
    for(int i=0;i<S.length();i++)
    {
        for(int j=T.length()-1;j>=0;j--)
        {
            res[j+1] = (S.charAt(i)==T.charAt(j)?res[j]:0)+res[j+1];
        }
    }
    return res[T.length()];
}

Use dynamical programming, assume D[i][j]=the number of distinct subsequences of T(0,i) in S(0,j). Then,
- if T(i)==S(j) D[i][j]=D[i][j-1]+D[i-1][j];
- else D[i][j]=D[i][j-1].
public class Solution {
    public int numDistinct(String S, String T) {
        int[] p=new int[T.length()+1];
        p[0]=1;
        for(int i=0;i<S.length();i++){
            for(int j=T.length();j>0;j--){
                if(S.charAt(i)==T.charAt(j-1)) p[j]=p[j]+p[j-1];
            }
        }
        return p[p.length-1];
    }
}

其余解法：
http://www.cnblogs.com/yuzhangcmu/p/4196373.html
http://blog.csdn.net/fightforyourdream/article/details/17346385?reload#comments
http://blog.csdn.net/abcbc/article/details/8978146
http://leetcodesolution.blogspot.com/2013/09/distinct-subsequences.html

Reference:
http://www.ninechapter.com/solutions/distinct-subsequences/
http://www.cnblogs.com/yuzhangcmu/p/4196373.html
http://blog.csdn.net/linhuanmars/article/details/23589057
https://yusun2015.wordpress.com/2015/01/13/distinct-subsequences/
*/