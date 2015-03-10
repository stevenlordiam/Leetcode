/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

public class PalindromePartitioning {
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (s == null) {
            return result;
        }
        ArrayList<String> path = new ArrayList<String>();
        helper(s, path, 0, result);
        return result;
    }

    private boolean isPalindrome(String s) {		// validate if it's palindrome
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    private void helper(String s, ArrayList<String> path, int pos, ArrayList<ArrayList<String>> result) {
        if (pos == s.length()) {
            result.add(new ArrayList<String>(path));
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {
            String prefix = s.substring(pos, i);
            if (!isPalindrome(prefix)) {
                continue;
            }
            path.add(prefix);               // 回溯法模板： add -> recursion -> remove last element
            helper(s, path, i, result);
            path.remove(path.size() - 1);
        }
    }
}

/*
和word break一样做，abaa先分成a, baa，然后再分成ab, aa，左边是palindrome才recurse on右边

这道题是求一个字符串中回文子串的切割，并且输出切割结果，其实是Word Break II和Longest Palindromic Substring结合，该做的我们都做过了
首先我们根据Longest Palindromic Substring中的方法建立一个字典，得到字符串中的任意子串是不是回文串的字典
接下来就跟Word Break II一样，根据字典的结果进行切割，然后按照循环处理递归子问题的方法，如果当前的子串满足回文条件，就递归处理字符串剩下的子串。如果到达终点就返回当前结果
算法的复杂度跟Word Break II一样，取决于结果的数量，最坏情况是指数量级的

First use a matrix[i][j] to record whether string(i,j+1) is a palindrome or not, then use dfs find all the combinations of palindromes which form the strings

Reference:
https://leetcodenotes.wordpress.com/2013/08/03/leetcode-palindrome-partitioning-%E5%88%86%E8%A7%A3string%E4%BD%BF%E6%AF%8F%E4%B8%AAsubstring%E9%83%BD%E4%B8%AD%E5%BF%83%E5%AF%B9%E7%A7%B0%EF%BC%8C%E8%BF%94%E5%9B%9E%E6%89%80%E6%9C%89%E5%88%86/
http://blog.csdn.net/linhuanmars/article/details/22777711
http://www.ninechapter.com/solutions/palindrome-partitioning/
https://yusun2015.wordpress.com/2015/01/13/palindrome-partitioning/
*/