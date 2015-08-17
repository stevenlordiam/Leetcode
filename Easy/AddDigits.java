/*
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:
Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Hint:
A naive implementation of the above process is trivial. Could you come up with other methods?
What are all the possible results?
How do they occur, periodically or randomly?
You may find this Wikipedia article(https://en.wikipedia.org/wiki/Digital_root) useful
*/

public class AddDigits {
    public int addDigits(int num) {
        String str = String.valueOf(num);
        while(str.length()>1){
            num =0;
            for(int i =0;i<str.length();i++){
                num += str.charAt(i)-'0';
            }
            str = String.valueOf(num);
        }
        return num;
    }
}

/*
Reference:
https://leetcode.com/discuss/52140/share-my-simple-to-understand-no-tricky-java-solution
https://leetcode.com/discuss/52092/256ms-java-one-line-solution
*/