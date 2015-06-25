/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/

public class BasicCalculatorII {
    public int calculate(String s) {
        int total = 0;
        for (String t : s.replace(" ", "").split("(?=[+-])")) {
            int term = 1;
            for (String u : ('*' + t).split("(?=[*/])")) {
                int n = Integer.parseInt(u.substring(1));
                term = u.startsWith("*") ? term * n : term / n;
            }
            total += term;
        }
        return total;
    }
}

/*
Split the whole expression into terms on + and -. Split each term on * and /. 
Split with look-ahead, so the operators remain in the strings as the first character. Otherwise I'd lose them. 
I don't have to deal with +/- myself since parseInt will get the number including the +/- prefix and do it for me.

Reference:
https://leetcode.com/discuss/41790/10-16-lines-java-easy
*/