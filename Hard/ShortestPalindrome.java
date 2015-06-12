/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:
Given "aacecaaa", return "aaacecaaa".
Given "abcd", return "dcbabcd".
*/

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        String result = "";
        if(s.length() == 0)
            return result;
        int[] prefix = new int[s.length() * 2];
        String mirror = s + new StringBuilder(s).reverse().toString();
        for(int i = 1; i < s.length() * 2; i++) {
            int j = prefix[i-1];
            while(mirror.charAt(j) != mirror.charAt(i) && j > 0)
                j = prefix[j-1];
            if(mirror.charAt(i) == mirror.charAt(j))
                prefix[i] = j + 1;
            else
                prefix[i] = 0;
        }
        int count = s.length() - prefix[s.length() * 2 -1];
        result = new StringBuilder(s.substring(s.length()-count, s.length())).reverse().toString() + s;
        return result;
    }
}

/*
先制作原字符串的对称镜像字符串，如s = "abcd", 镜像a = "abcddcba"
然后对新字符串a，按KMP算法求Prefix的方法，求Prefix, 得【0， 0， 0， 0， 0，  0， 0， 1】，然后s.length() - prefix[2 * s.length()-1] 即为需要复制到s前的s尾部字符串的个数
prefix[i]表示，以i为结尾的当前子串中，从第一个字符开始数起，共有prefix[i]个字符与尾部的prefix[i]个字符相等。
如s = “abcabc”, prefix[] = {0, 0, 0, 1, 2, 3}
具体可参考字符串匹配之KMP算法：
http://billhoo.blog.51cto.com/2337751/411486

Reference:
http://blog.csdn.net/pointbreak1/article/details/45931551
https://leetcode.com/discuss/36807/c-8-ms-kmp-based-o-n-time-%26-o-n-memory-solution
https://leetcode.com/discuss/39561/java-solution-inspired-from-others
https://leetcode.com/discuss/37302/manacher-algorithm-based-java-solution-with-explanations
https://leetcode.com/discuss/36988/java-solution-eliminates-non-palindroming-substrings
https://leetcode.com/discuss/36897/ac-java-solution-not-based-on-manachers-or-kmp-algorithms
https://leetcode.com/discuss/36865/my-java-solution-o-n-2-do-not-need-to-use-manachers-algorithm
*/