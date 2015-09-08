/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int length = s.length();    
        int max = 0;
        String result = "";
        for(int i = 1; i <= 2 * length - 1; i++){       // there are 2*n-1 centres in total for the substring
            int count = 1;      // number of palindrome characters in one substring
            while(i - count >= 0 && i + count <= 2 * length  && get(s, i - count) == get(s, i + count)){    
                count++;    // 回文以中心对称，应该分别向两边扫描
            }
            count--;        // there will be one extra count for the outbound #
            if(count > max) {
                result = s.substring((i - count) / 2, (i + count) / 2);     // i is the index of the centre point and count is the number of palindrome characters to the centre
                max = count;
            }
        }
        return result;
    }
    
    private char get(String s, int i) {     // i is the index
        if(i % 2 == 0)      // jump over the even number index (???)
            return '#';
        else 
            return s.charAt(i / 2);
    }
}

/*
这道题是比较常考的题目，求回文子串，一般有两种方法。 
第一种方法比较直接，实现起来比较容易理解。基本思路是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙）往两边同时进行扫描，直到不是回文串为止。
假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。对于每个中心往两边扫描的复杂度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)

第二种方法是用动态规划，思路比较复杂一些，但是实现代码会比较简短。基本思路是外层循环i从后往前扫，内层循环j从i当前字符扫到结尾处。
过程中使用的历史信息是从i+1到n之间的任意子串是否是回文已经被记录下来，所以不用重新判断，只需要比较一下头尾字符即可。
这种方法使用两层循环，时间复杂度是O(n^2)。而空间上因为需要记录任意子串是否为回文，需要O(n^2)的空间

两种方法的时间复杂度都是O(n^2)。而空间上来看第一种方法是常量的，比第二种方法优。
这个题目中假设最长回文子串只有一个，实际面试中一般不做这种假设，如果要返回所有最长回文串，只需要稍做变化就可以，维护一个集合，如果等于当前最大的，即加入集合，否则，如果更长，则清空集合，加入当前这个。

二维dp:
- d[i][j]是个boolean，表示(i, j)这个substring是不是中心对称
- d[i][j]
  - 正常情况下，d[i][j] = true when 1.A[i]==A[j] 2. d[i + 1][j – 1] == true (中间那部分已经中心对称了)
  - 处理i+1, j-1出界和左边反而大于右边的情况：
    - 当i == j时，d[i][j] = 1（一个字母肯定是中心对称）
    - 当i == j – 1时，说明两个字符是连着的，看A[i]是否==A[j]就行
- 这个题的坑爹之处在于正常i从0~last, j从0~last那么循环不行。因为d[i][j]用到了d[i + 1][j – 1]，说明i要从后往前，j则要从前往后。导致二层循环是这种顺序：
for (int i = last; i >= 0; i–)
for(int j = i; j  <= last; j++)

不用二维数组的算法（直接按中心对称的定义来，而不是每个pair算一遍，可以省一维空间）：
- 以每个元素以及间隔为中心（共2 * n – 1个），以相同的速度往两侧扩张，直到两边儿的值不相等了，说明palindrome在此打破，于是输出。这样每次输出的肯定是以当前这个点为中心最长的palindrome
- 这样过一遍数组，最长的就出来了

Reference:
https://leetcodenotes.wordpress.com/2013/07/22/longest-palindromic-substring/
http://www.ninechapter.com/solutions/longest-palindromic-substring/
http://blog.csdn.net/linhuanmars/article/details/20888595
https://yusun2015.wordpress.com/2015/01/17/longest-palindromic-substring/
http://www.cnblogs.com/yuzhangcmu/p/4189068.html
*/