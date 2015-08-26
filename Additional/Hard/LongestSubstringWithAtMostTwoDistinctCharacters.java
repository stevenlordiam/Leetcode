/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
For example,Given s = “eceba”, T is "ece" which its length is 3.
*/

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int i = 0, j = -1, maxLen = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(k - 1)) continue;
            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
                maxLen = Math.max(k - i, maxLen);
                i = j + 1; 
            }
            j = k - 1;  
        }
        return Math.max(s.length() - i, maxLen);
    }
}

/*
Hashtable - O(n) space
two pointers - O(1) space

[分析]
brute force的解法就是构造出来所有substring然后线性扫描一遍，复杂度为O(n^3)。可以使用Set来判断是不是有重复的字符。如果假定是在26个英文字母之间的话，更容易了，直接上个array就好。但这个假定一般都不会成立的

最优的解法应该是维护一个sliding window，指针变量i指向sliding window的起始位置，j指向另个一个字符在sliding window的最后一个，用于定位i的下一个跳转位置。内部逻辑就是
1）如果当前字符跟前一个字符是一样的，直接继续
2）如果不一样，则需要判断当前字符跟j是不是一样的
a）一样的话sliding window左边不变，右边继续增加，但是j的位置需要调整到k-1
b）不一样的话，sliding window的左侧变为j的下一个字符（也就是去掉包含j指向的字符的区间），j的位置也需要调整到k-1
在对i进行调整的时候（1.a），需要更新maxLen

[注意事项]
1）在最后返回的时候，注意考虑s.length()-i这种情况，也就是字符串读取到最后而没有触发（1.a）
2）讲解清楚sliding window的更新
3）该题目有个follow-up，就是如果是k个distinct characters怎么办。这样的话就只能对所有可能的字符用一个数组去做counting，而且只能假设ASIC字符集256。Unicode太大了

Referece:
http://www.danielbit.com/blog/puzzle/leetcode/leetcode-longest-substring-with-at-most-two-distinct-characters
http://blog.csdn.net/whuwangyi/article/details/42451289
http://www.51itong.net/leetcode-159-longest-substring-with-at-most-two-distinct-characters-4101.html
https://changhaz.wordpress.com/2014/11/26/leetcode-longest-substring-with-at-most-two-distinct-characters/
https://yusun2015.wordpress.com/2015/01/28/longest-substring-with-at-most-two-distinct-characters/
https://leetcode.com/discuss/19261/my-java-ac-solution
https://leetcode.com/discuss/22980/java-solution-for-k-unique-characters
https://leetcode.com/discuss/24815/share-my-dp-solution-with-constant-space
https://leetcode.com/discuss/28214/ac-o-n-java-solution
https://leetcode.com/discuss/30366/o-n-time-and-o-1-space-solution-without-using-hashmap
*/