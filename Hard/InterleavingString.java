/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        boolean [][] interleaved = new boolean[s1.length() + 1][s2.length() + 1];
        interleaved[0][0] = true;
        
        for (int i = 1; i <= s1.length(); i++) {
            if(s3.charAt(i - 1) == s1.charAt(i - 1) && interleaved[i - 1][0])
                interleaved[i][0] = true;
        }
        
        for (int j = 1; j <= s2.length(); j++) {
            if(s3.charAt(j - 1) == s2.charAt(j - 1) && interleaved[0][j - 1])
                interleaved[0][j] = true;
        }
        
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(((s3.charAt(i + j - 1) == s1.charAt(i - 1) && interleaved[i - 1][j]))
                    || ((s3.charAt(i + j - 1)) == s2.charAt(j - 1) && interleaved[i][j - 1]))
                interleaved[i][j] = true;
            }
        }
        
        return interleaved[s1.length()][s2.length()];
    }
}

/*
Dynamic Programming

感觉是二维DP，尽管没让求什么最大最小个数之类的，只是boolean，但是递推关系还是有的
其实只要把状态敢于定义出来（旁边标注物理意义），状态转移方程就能写出来了。然后就是处理最开始。思路：
- 一开始是这么定义的：d[i][j]: whether s1[0, i]的substring和s2[0, j]的substring能够cross match s3[0, i + j + 1]这个substring
  比如ab和c，即(0, 1), (0, 0)，能否合起来是acb(0, 2)。要点是s3的index要+1，这个一开始就想错了直接i + j的
- d[i][j] = s3[i + j + 1] == s2[j] && d[i][j – 1] == true （用第s2的j个来填s3的i + j + 1个能成功）， OR s3[i + j + 1] == s1[i] && d[i – 1][j] == true （用第s1的i个来填s3的i + j + 1个能成功）
- d[i][j]物理意义一定要搞清楚，因为d[0][0]说明拿了两个元素，那么它对应的是长度为2的s3，这index干脆就乱套了
  这个时候，换一种方式定义：d[0][0]表示从s1中拿0个，s2中拿0个，然后组成s3（长度为0 + 0)能不能成。这个肯定是true
  d[1][0]就能表达只从s1中拿一个，s2中不拿，然后组成长度为1的s3这种make sense的意义了。这样i, j就要一直到=size这么大。所以之前的i + j + 1改成i + j – 1，才能真的往string里charAt
public boolean isInterleave(String s1, String s2, String s3) {
  if (s3.length() != s1.length() + s2.length())
    return false;
  boolean[][] d = new boolean[s1.length() + 1][s2.length() + 1];
  d[0][0] = true;
  for (int i = 0; i <= s1.length(); i++) {
    for (int j = 0; j <= s2.length(); j++) {
     if (j > 0 && s3.charAt(i + j - 1) == s2.charAt(j - 1) && d[i][j - 1])
       d[i][j] = true;
     else if (i > 0 && s3.charAt(i + j - 1) == s1.charAt(i - 1) && d[i - 1][j])
       d[i][j] = true; 
    }
  }
  return d[s1.length()][s2.length()];
}

这是一道关于字符串操作的题目，要求是判断一个字符串能不能由两个字符串按照他们自己的顺序，每次挑取两个串中的一个字符来构造出来
像这种判断能否按照某种规则来完成求是否或者某个量的题目，很容易会想到用动态规划来实现
先说说维护量，res[i][j]表示用s1的前i个字符和s2的前j个字符能不能按照规则表示出s3的前i+j个字符，如此最后结果就是res[s1.length()][s2.length()]，判断是否为真即可
接下来就是递推式了，假设知道res[i][j]之前的所有历史信息，我们怎么得到res[i][j]。可以看出，其实只有两种方式来递推，一种是选取s1的字符作为s3新加进来的字符，另一种是选s2的字符作为新进字符
而要看看能不能选取，就是判断s1(s2)的第i(j)个字符是否与s3的i+j个字符相等。如果可以选取并且对应的res[i-1][j](res[i][j-1])也为真，就说明s3的i+j个字符可以被表示
这两种情况只要有一种成立，就说明res[i][j]为真，是一个或的关系。所以递推式可以表示成res[i][j] = res[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1) || res[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1)
时间上因为是一个二维动态规划，所以复杂度是O(m*n)，m和n分别是s1和s2的长度。最后就是空间花费，可以看出递推式中只需要用到上一行的信息，所以我们只需要一个一维数组就可以完成历史信息的维护，为了更加优化，我们把短的字符串放在内层循环，这样就可以只需要短字符串的长度即可，所以复杂度是O(min(m,n))
动态规划其实还是有套路的，无非就是找到维护量，然后得到递推式，接下来看看历史信息对于空间的需求，尽量优化，动态规划通用总结(http://blog.csdn.net/linhuanmars/article/details/38468361)

Reference:
https://leetcodenotes.wordpress.com/2013/07/19/interleaving-string/
http://www.ninechapter.com/solutions/interleaving-string/
http://blog.csdn.net/linhuanmars/article/details/24683159
(LeetCode总结 - 一维动态规划)http://blog.csdn.net/linhuanmars/article/details/38468361
https://yusun2015.wordpress.com/2015/01/25/interleaving-string/
*/