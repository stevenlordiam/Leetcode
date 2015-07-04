/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if(s==null || s.length()==0){
            return true;
        }
        s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();         // regular expression
        StringBuilder sb = new StringBuilder(s);
        String sReverse = sb.reverse().toString();
        return s.equals(sReverse);
    }
}

/*
Thought:
Create an array only storing alphanumeric characters, then compare with two pointers.

for(int j=0; j<sNew.length()/2; j++){
    for(int k=sNew.length()/2; k>0; k--){
        if(sNew.charAt(k) == sNew.charAt(j)){
            return true;
        }
    }
}

This is not good, because time complexity is considerable.

Solution2:
using regular expression
String.replaceAll(String regex, String replacement)

Solution3:
reverse string, then compare(this is GOOD!)
*/