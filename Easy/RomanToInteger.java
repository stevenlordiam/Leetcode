/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

public class RomanToInteger {
 	public int romanToInt(String s) {
	    if (s == null || s.length()==0) {			// always check for null and zero-length
 			return 0;
	    }
	    Map<Character, Integer> m = new HashMap<Character, Integer>();			// use HashMap to store the data
	    m.put('I', 1);
	    m.put('V', 5);
	    m.put('X', 10);
	    m.put('L', 50);
	    m.put('C', 100);
	    m.put('D', 500);
	    m.put('M', 1000);

	    int length = s.length();
	    int result = m.get(s.charAt(length - 1));		// 初始赋值为最后一个element
	    for (int i = length - 2; i >= 0; i--) {
	        if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) {
	            result += m.get(s.charAt(i));
	        } else {
	            result -= m.get(s.charAt(i));
	        }
	    }
	    return result;
	}
}

/*
Roman numeral: I=1, V=5, X=10, L=50, C=100, D=500, M=1000.
Roman to Integer: If the smaller number is ahead of the bigger one, need to use the bigger one minus the smaller one, otherwise sum the two numbers up.

Similar:
Integer to Roman

Reference:
https://leetcodenotes.wordpress.com/2013/08/14/leetcode-roman-to-integer-%E7%BD%97%E9%A9%AC%E6%95%B0%E5%AD%97%E8%BD%AC%E6%8D%A2%E6%88%90%E6%95%B4%E6%95%B0/
https://yusun2015.wordpress.com/2015/01/05/roman-to-integer-integer-to-roman/
https://oj.leetcode.com/discuss/24297/my-easy-understanded-python-solution-in-160-ms
*/