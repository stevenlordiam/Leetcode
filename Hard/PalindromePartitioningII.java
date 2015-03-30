/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

public class PalindromePartitioningII {
    /*
    private boolean isPalindrome(String s, int start, int end) {    // 用此方法复杂度O(N^3), 如果按照如下预处理的方法是O(N^2)
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    */
    
    private boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;      // 从i到i为palindrome 比如a
        }
        for (int i = 0; i < s.length() - 1; i++) {  // aa也是palindrome, 要与a不同的预处理
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]         // j+1到i是不是palindrome
                    = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] cut = new int[s.length() + 1];    // 要储存0的结果所以是长度+1
        boolean[][] isPalindrome = getIsPalindrome(s);

        cut[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            cut[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                if (isPalindrome[i - j][i - 1] && cut[i - j] != Integer.MAX_VALUE) {    // cut[i - j] != Integer.MAX_VALUE 预处理减少时间复杂度
                    cut[i] = Math.min(cut[i], cut[i - j] + 1);
                }
            }
        }

        return cut[s.length()] - 1;
    }
}

/*
这道题跟Palindrome Partitioning非常类似，区别就是不需要返回所有满足条件的结果，而只是返回最小的切割数量就可以
做过Word Break的朋友可能马上就会想到，其实两个问题非常类似，当我们要返回所有结果（Palindrome Partitioning和Word Break II）的时候，使用动态规划会耗费大量的空间来存储中间结果，所以没有明显的优势
而当题目要求是返回某个简单量（比如Word Break是返回能否切割，而这道题是返回最小切割数）时，那么动态规划比起brute force就会有明显的优势，这道题先用Palindrome Partitioning中的方法建立字典，接下来动态规划的方式和Word Break是完全一样的
因为保存历史信息只需要常量时间就能完成，进行两层循环，时间复杂度是O(n^2)，空间上需要一个二维数组来保存字典和一个线性数组来保存动态规划信息，所以是O(n^2)
这个问题和Word Break可以说是一个题目，这里多了一步求解字典。如果求解所有结果时，他们没有多项式时间的解法，复杂度取决于结果数量，而当求解某一种统计的特殊量时，用动态规划就会很大的优势，可以降低时间复杂度

Reference:
https://leetcodenotes.wordpress.com/2013/10/20/leetcode-palindrome-partitioning-2-%E4%B8%80%E4%B8%AAstring%E6%9C%80%E5%B0%91%E5%88%87%E5%87%A0%E5%88%80%E8%83%BD%E8%AE%A9%E5%88%86%E6%88%90%E7%9A%84substring%E5%85%A8%E6%98%AFpalindrome/
http://www.ninechapter.com/solutions/palindrome-partitioning-ii/
http://blog.csdn.net/linhuanmars/article/details/22837047
https://yusun2015.wordpress.com/2015/01/13/palindrome-partitioning/
*/