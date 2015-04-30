/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.
Given "foo", "bar", return false.
Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s==null&&t==null) {
            return true;
        }
        if (s==null||t==null || s.length()!=t.length()) {
            return false;
        }
        Map<Character,Character> hm = new HashMap<Character,Character>();
        for (int i=0;i<s.length();i++) {
            Character sChar = s.charAt(i);
            Character tChar = t.charAt(i);
            if (hm.containsKey(sChar)) {   // if map already contains key make sure value is the same 
                if(hm.get(sChar)!=tChar)
                    return false;
            } else if (hm.containsValue(tChar)) { // if map doesn't contain the key make sure it doesn't already contain the value
                return false;
            } else {   // otherwise insert key, value pair
                hm.put(sChar,tChar);
            }
        }
        return true;
    }
}

/*
Reference:
https://leetcode.com/discuss/33889/short-java-solution-without-maps
https://leetcode.com/discuss/33859/java-solution-with-comments-o-n
https://leetcode.com/discuss/33908/simple-java-solution-with-one-hashmap
http://blog.csdn.net/fightforyourdream/article/details/17311575
http://bookshadow.com/weblog/2015/04/29/leetcode-isomorphic-strings/
http://www.cnblogs.com/easonliu/p/4465650.html
*/