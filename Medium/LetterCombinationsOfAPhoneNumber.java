/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

public class LetterCombinationsOfAPhoneNumber {
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();

        if (digits == null || digits.length()==0) {		// check for null and ""
            return result;
        }

        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        StringBuilder sb = new StringBuilder();
        helper(map, digits, sb, result);

        return result;
    }

    private void helper(Map<Character, char[]> map, String digits, StringBuilder sb, ArrayList<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(sb.length()))) {	// 标准backtracking模板(REMEMBER IT!!!)
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

/*
Backtracking，记住标准模板（！！！）

这道题目和求组合的思路差不多，比较简单。依次读取数字，然后把数字可以代表的字符依次加到当前的所有结果中，然后进入下一次迭代
假设总共有n个digit，每个digit可以代表k个字符，那么时间复杂度是O(k^n)，就是结果的数量，空间复杂度也是一样
相关的扩展是这道题如何用递归来解，思路其实类似，就是对于当前字符，递归剩下的数字串，然后得到结果后加上自己返回回去

Reference:
http://www.ninechapter.com/solutions/letter-combinations-of-a-phone-number/
http://blog.csdn.net/linhuanmars/article/details/19743197
https://yusun2015.wordpress.com/2015/01/12/letter-combinations-of-a-phone-number/
*/