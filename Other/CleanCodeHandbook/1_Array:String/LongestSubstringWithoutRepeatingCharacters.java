/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
*/

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        HashSet<Character> set = new HashSet<Character>();
        
        int leftBound = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {            // found repeat
                while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {       // scan from left for the non repeat char
                    set.remove(s.charAt(leftBound));
                    leftBound ++;
                }
                leftBound ++;
            } else {            // no repeat, add char to set
                set.add(s.charAt(i));
                max = Math.max(max, i - leftBound + 1);
            }
        }
        return max;
    }
}

/*
基本思路是维护一个窗口，每次关注窗口中的字符串，在每次判断中，左窗口和右窗口选择其一向前移动。同样是维护一个HashSet, 正常情况下移动右窗口，如果没有出现重复则继续移动右窗口，
如果发现重复字符，则说明当前窗口中的串已经不满足要求，继续移动有窗口不可能得到更好的结果，此时移动左窗口，直到不再有重复字符为止，中间跳过的这些串中不会有更好的结果，因为他们不是重复就是更短。
因为左窗口和右窗口都只向前，所以两个窗口都对每个元素访问不超过一遍，因此时间复杂度为O(2*n)=O(n),是线性算法。
空间复杂度为HashSet的size,也是O(n). 

A good data structure is helpful, for this problem, brute force is O(N^3)
Keep a HashSet to store the character

max = Math.max( max, something) -- use like this!!!

Reference:
https://leetcodenotes.wordpress.com/2013/11/02/leetcode-longest-substring-without-repeating-characters-%E6%9C%80%E9%95%BF%E7%9A%84unique-char%E7%BB%84%E6%88%90%E7%9A%84substring/
http://www.ninechapter.com/solutions/longest-substring-without-repeating-characters/
http://blog.csdn.net/linhuanmars/article/details/19949159
https://yusun2015.wordpress.com/2015/01/14/longest-substring-without-repeating-characters/
*/