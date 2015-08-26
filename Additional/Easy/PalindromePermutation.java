/*
Given a string, determine if a permutation of the string could form a palindrome.
For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Understand the problem:
The problem can be easily solved by count the frequency of each character using a hash map. The only thing need to take special care is consider the length of the string to be even or odd. 
  -- If the length is even. Each character should appear exactly times of 2, e.g. 2, 4, 6, etc..
  -- If the length is odd. One and only one character could appear odd times
*/

public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set=new HashSet<Character>();
        for(int i=0; i<s.length(); ++i){
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else 
                set.remove(s.charAt(i));
        }
        return set.size()==0 || set.size()==1;
    }
}

/*
Reference:
https://leetcode.com/discuss/53295/java-solution-w-set-one-pass-without-counters
https://leetcode.com/discuss/53180/1-4-lines-python-ruby-c-c-java
https://leetcode.com/discuss/53187/ac-java-solution
https://leetcode.com/discuss/53201/java-use-map-one-scan
https://leetcode.com/discuss/53234/accepted-java-o-n-solution-using-hashset-only-one-scan
https://leetcode.com/discuss/53744/java-ac-8-lines
http://buttercola.blogspot.com/2015/08/leetcode-palindrome-permutation.html
*/