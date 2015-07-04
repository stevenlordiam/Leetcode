/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
*/

public class ValidNumber {
    public boolean isNumber(String s) {
        int len = s.length();
        int i = 0, e = len - 1;
        while (i <= e && Character.isWhitespace(s.charAt(i))) i++; 	// char.isWhitespace()
        if (i > len - 1) return false;
        while (e >= i && Character.isWhitespace(s.charAt(e))) e--;
        // skip leading +/-
        if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
        boolean num = false; // is a digit
        boolean dot = false; // is a '.'
        boolean exp = false; // is a 'e'
        while (i <= e) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = true;
            }
            else if (c == '.') {
                if(exp || dot) return false;
                dot = true;
            }
            else if (c == 'e') {
                if(exp || num == false) return false;
                exp = true;
                num = false;
            }
            else if (c == '+' || c == '-') {
                if (s.charAt(i - 1) != 'e') return false;
            }
            else {
                return false;
            }
            i++;
        }
        return num;
    }
}

/*
Character.isWhitespace(c)
Character.isDigit(c)
string.charAt(i)

规则是这样的：
AeB代表A * 10 ^ B
A可以是小数也可以是整数，可以带正负号
.35, 00.神马的都算valid小数；就”.”单独一个不算
B必须是整数，可以带正负号
有e的话，A,B就必须同时存在
算法就是按e把字符串split了，前面按A的法则做，后面按B做

这是一道检查字符串输入是否为合法的题目。基本规则是按照科学计数法，所以会出现的特殊字符有以下几个：符号位‘+’，‘-’，小数点‘.’，还有‘e’和‘E’，剩下的就只有数字0-9了，其他字符如果出现就是非法字符，返回false
数字字符在哪里出现都是ok的，我们主要考虑几个特殊字符的情况
对于小数点出现的时候，我们要满足一下这些条件：（1）前面不能有小数点或者‘e’和‘E’；（2）前一位是数字（不能是第一位）或者后一位要是数字（不能是最后一位）
对于正负号出现的情况，要满足条件：（1）必须是第一位或者在‘e’和‘E’后一位；（2）后一位要是数字
对于‘e’和‘E’的情况，要满足：（1）前面不能有‘e’和‘E’出现过；（2）不能是第一位（前面没数字科学计数没有意义）或者最后一位（后面没数字就不用写指数了）
根据上面列举的情况，我们用两个标签和做前后位的判断来实现，算法复杂度比较明显是O(n)的，只需要O(1)的额外空间

Analysis:
The idea is: trim the string and find ‘e’ and divide the string into two parts and determine each part is valid or not. The corner cases:
- if the length of the string is zero, return false;
- check if the string has sign at the first  position or not, and if ‘+’ or ‘-‘ appears after the first position  return false;
- check ‘.’ already appears or not, if it appears more than once return false(use hasdot to check);
- check if numbers appears in the string, if number never appears return false( use num to check).

Solution 2: regular expression

Reference:
https://leetcodenotes.wordpress.com/2013/11/23/leetcode-valid-number/
http://www.ninechapter.com/solutions/valid-number/
http://blog.csdn.net/linhuanmars/article/details/23809661
https://yusun2015.wordpress.com/2015/01/27/valid-number/
*/