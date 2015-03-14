/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

public class MinimumWindowSubstring {
    public String minWindow(String S, String T) {
        if (S == null || S.length() == 0) {
            return S;
        }
        if (T == null || T.length() == 0) {
            return "";
        }
        
        HashMap <Character, Integer> tCounter = new HashMap<Character, Integer>();      // count number of char in String T
        for (int i = 0; i < T.length(); i++) {
            Character c = T.charAt(i);
            if (tCounter.containsKey(c)) {
                tCounter.put(c, tCounter.get(c) + 1);
            } else {
                tCounter.put(c, 1);
            }
        }
        
        HashMap <Character, Integer> minWindowCounter = new HashMap<Character, Integer>();
        String minWindow = null;
        int tCount = 0, leftBound = 0;
        for (int i = 0; i < S.length(); i++) {
            Character c = S.charAt(i);
            if (!tCounter.containsKey(c)) {
                continue;
            }
            if (minWindowCounter.containsKey(c)) {  // count number of char (diff from char in T) in String S
                minWindowCounter.put(c, minWindowCounter.get(c) + 1);
            } else {
                minWindowCounter.put(c, 1);
            }

            if (minWindowCounter.get(c) <= tCounter.get(c)) {   // there is char match between S and T
                tCount ++;
            }
            if (tCount == T.length()) {   // all char match!
                while (leftBound < S.length()) {
                    Character ch = S.charAt(leftBound);
                    if (!tCounter.containsKey(ch)) {
                        leftBound ++;
                        continue;
                    }          
                    if (minWindowCounter.get(ch) > tCounter.get(ch)) {  // not match
                        minWindowCounter.put(ch, minWindowCounter.get(ch) - 1);
                        leftBound ++;
                        continue;
                    }                   
                    break;
                }               
                if (minWindow == null || i - leftBound + 1 < minWindow.length()) {
                    minWindow = S.substring(leftBound, i + 1);
                }
            }
        }
        
        if (minWindow == null) {
            return "";
        }
        return minWindow;
    }
}

/*
Similar to Substring with Concatenation of All Words - https://leetcode.com/problems/substring-with-concatenation-of-all-words/
Longest Substring Without Repeating Characters - https://leetcode.com/problems/longest-substring-without-repeating-characters/

这题说的不清楚，其实T可以有重复字母，比如AAA，那S中必须包括3个A才行。不是包括A就行了
这道题是字符串处理的题目，和Substring with Concatenation of All Words思路非常类似，同样是建立一个字典，然后维护一个窗口
区别是在这道题目中，因为可以跳过没在字典里面的字符（也就是这个串不需要包含且仅仅包含字典里面的字符，有一些不在字典的仍然可以满足要求）
所以遇到没在字典里面的字符可以继续移动窗口右端，而移动窗口左端的条件是当找到满足条件的串之后，一直移动窗口左端直到有字典里的字符不再在窗口里
在实现中就是维护一个HashMap，一开始key包含字典中所有字符，value就是该字符的数量，然后遇到字典中字符时就将对应字符的数量减一
算法的时间复杂度是O(n),其中n是字符串的长度，因为每个字符再维护窗口的过程中不会被访问多于两次。空间复杂度则是O(字典的大小)，也就是代码中T的长度
这个方法在Substring with Concatenation of All Words和Longest Substring Without Repeating Characters中都介绍过，属于一种类型的题目，只要掌握了思路便可以举一反三，都可以将这类问题降低到线性复杂度

双指针，动态维护一个区间。尾指针不断往后扫，当扫到有一个窗口包含了所有T的字符后，然后再收缩头指针，直到不能再收缩为止。最后记录所有可能的情况中窗口最小的

Analysis
We are required to implement a O(n) algorithm. We can achieve it by using two pointers. We will use pointer “start” and pointer “end”, which indicates the substring we are processing.
Firstly, we will move the “end” pointer right, until we find all characters. But how can we know that we have found all the characters? There may be duplicate characters in string T. 
So I use a HashMap to save the number of each characters in string T. Another HashMap is used to save the number of characters we found. When we move pointer “end”, we will check if the number of this character is smaller than we need to found. 
If it is, we will increase one to a total counter and increase one to the number of this character. Otherwise only the number of this character is increased. When the total counter equals to length of T, we know that we have found all characters. 
Now we can move the “start” pointer as right as possible. We can move it when it pointing to a character that is not in T, or even it is in T, but the number of this character is larger than the number we need to find. 
In other words, we move “start” pointer when the substring from “start” to “end” does contains all characters in T. We can compare the value end–start+1, which is the length of a substring that contains all characters in T, to the minimum length. 
If it’s shorter, we can update the minimum length. In fact, we can also use an array to save the number of each characters. It could be faster than using HashMap. You can check the code here: http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html

Reference:
https://leetcodenotes.wordpress.com/2013/11/18/leetcode-minimum-window-substring/
https://leetcodenotes.wordpress.com/2013/08/02/minimum-window-substring/
http://www.ninechapter.com/solutions/minimum-window-substring/
http://blog.csdn.net/linhuanmars/article/details/20343903
http://fisherlei.blogspot.com/2012/12/leetcode-minimum-window-substring.html
http://www.lifeincode.net/programming/leetcode-minimum-window-substring-java/
http://huntfor.iteye.com/blog/2083485
*/