/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Clarification:
- What constitutes a word?
  A sequence of non-space characters constitutes a word.
- Could the input string contain leading or trailing spaces?
  Yes. However, your reversed string should not contain leading or trailing spaces.
- How about multiple spaces between two words?
  Reduce them to a single space in the reversed string.
*/

public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        String[] strs = s.trim().split(" "); 		// store words in array
        StringBuilder res = new StringBuilder();    // 不用写reverse string[] 函数，直接用StringBuilder反向append
        for (int i = strs.length - 1; i>=0; i--){
            if (!strs[i].equals("")) {
                res.append(strs[i]).append(" "); 	// word + whitespaces between words 
            }
        }
        return res.toString().trim(); 				// need to trim the last whitespace because line 27 appends whitespace after every word
    }
}

/*
string.trim():
Removes all leading and trailing whitespaces

One simple approach is a two-pass solution: 
First pass to split the string by spaces into an array of words, then second pass to extract the words in reversed order.

We can do better in one-pass:
While iterating the string in reverse order, we keep track of a word’s begin and end position. When we are at the beginning of a word, we append it.

Reference:
https://yusun2015.wordpress.com/2015/01/27/reverse-words-in-a-string/
http://www.ninechapter.com/solutions/reverse-words-in-a-string/
http://blog.csdn.net/linhuanmars/article/details/20982463
*/