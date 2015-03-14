/*
You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

public class SubstringWithConcatenationOfAllWords {         // ???
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashMap<String, Integer> toFind = new HashMap<String, Integer>();
        HashMap<String, Integer> found = new HashMap<String, Integer>();
        int m = L.length;           // num of words 
        int n = L[0].length();      // length of each word
        for (int i = 0; i < m; i ++){
            if (!toFind.containsKey(L[i])){
                toFind.put(L[i], 1);
            }
            else{
                toFind.put(L[i], toFind.get(L[i]) + 1);
            }
        }
        for (int i = 0; i <= S.length() - n * m; i++){      // scan
            found.clear();      // clear the hashmap
            for (int j = 0; j < m; j ++){
                int k = i + j * n;
                String stub = S.substring(k, k + n);        // substring
                if (!toFind.containsKey(stub)) break;
                if(!found.containsKey(stub)){
                    found.put(stub, 1);
                }
                else{
                    found.put(stub, found.get(stub) + 1);
                }
                if (found.get(stub) > toFind.get(stub)) break;  // match
            }
            if (j == m) result.add(i);  // if still can go to last element(without break in loop meaning that all previous characters are matching), add the last element to result
        }   // remember to check for the last element!!!
        return result;
    }
}

/*
Similar to Minimum Window Substring - https://leetcode.com/problems/minimum-window-substring/
Longest Substring Without Repeating Characters - https://leetcode.com/problems/longest-substring-without-repeating-characters/

给一个String[] L，里面可以有重复，在S里找能包括所有L且都只出现一次的window的start
这道题看似比较复杂，其实思路和Longest Substring Without Repeating Characters差不多。因为那些单词是定长的，所以本质上和单一个字符一样
和Longest Substring Without Repeating Characters的区别只在于我们需要维护一个字典，然后保证目前的串包含字典里面的单词有且仅有一次
思路仍然是维护一个窗口，如果当前单词在字典中，则继续移动窗口右端，否则窗口左端可以跳到字符串下一个单词了。假设源字符串的长度为n，字典中单词的长度为l。因为不是一个字符，所以我们需要对源字符串所有长度为l的子串进行判断
做法是i从0到l-1个字符开始，得到开始index分别为i, i+l, i+2*l, ...的长度为l的单词。这样就可以保证判断到所有的满足条件的串
因为每次扫描的时间复杂度是O(2*n/l)(每个单词不会被访问多于两次，一次是窗口右端，一次是窗口左端)，总共扫描l次（i=0, ..., l-1)，所以总复杂度是O(2*n/l*l)=O(n)，是一个线性算法
空间复杂度是字典的大小，即O(m*l)，其中m是字典的单词数量。这种移动窗口的方法在字符串处理的问题中非常常见，是一种可以把时间复杂度降低到线性的有效算法。还有非常类似的题目Minimum Window Substring，思路完全一样，只是移动窗口的规则稍微不同而已

Reference:
https://leetcodenotes.wordpress.com/2013/11/10/leetcode-substring-with-concatenation-of-all-words-foobar%E9%A2%98/
http://www.ninechapter.com/solutions/substring-with-concatenation-of-all-words/
http://blog.csdn.net/linhuanmars/article/details/20342851
https://yusun2015.wordpress.com/2015/01/26/substring-with-concatenation-of-all-words/
http://www.cnblogs.com/TenosDoIt/p/3807055.html
http://blog.unieagle.net/2012/10/28/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Asubstring-with-concatenation-of-all-words/
https://linchicoding.wordpress.com/2014/08/25/leetcode-substring-with-concatenation-of-all-words/
*/