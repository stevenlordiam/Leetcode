/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets
*/

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        int[] count = new int[26];
        int len = t.length();
        for(int i = 0; i < len; i++) {
            count[t.charAt(i) - 'a']++;
        }
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(count[c - 'a'] > 0) {
                count[c - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }
}

/*
Reference:
https://leetcode.com/discuss/49787/3-solutions-sort-hash-array-and-prime
https://leetcode.com/discuss/50850/o-n-java-solution
https://leetcode.com/discuss/49795/share-my-java-solution
https://leetcode.com/discuss/49764/my-o-n-solution-using-array-as-hash
https://leetcode.com/discuss/49639/java-o-n-solution-for-strings-of-arbitrary-characters
https://leetcode.com/discuss/49399/accepted-java-o-n-solution-in-5-lines
*/