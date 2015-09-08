/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation (http://en.wikipedia.org/wiki/Reverse_Polish_notation).

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<Integer>();
        String operators = "+-*/";
        for(String token : tokens){
            if(!operators.contains(token)){
                s.push(Integer.valueOf(token));     // string to Integer
                continue;
            }       

            int a = s.pop();                        // pop two values when meet operators
            int b = s.pop();
            if(token.equals("+")) {
                s.push(b + a);
            } else if(token.equals("-")) {
                s.push(b - a);
            } else if(token.equals("*")) {
                s.push(b * a);
            } else {
                s.push(b / a);
            }
        }
        return s.pop();                             // pop the last value on stack as the result
    }
}

/*
逆波兰式有个优点就是他不需要括号来表示优先级，直接根据式子本身就可以求解。思路是维护一个运算数栈，读到运算数的时候直接进栈，
而每得到一个运算符，就从栈顶取出两个运算数，运算之后将结果做为一个运算数放回栈中，直到式子结束，此时栈中唯一一个元素便是结果
对逆波兰式错误的情况进行出错处理就是每次pop操作检查栈空情况，如果栈空，则说明出错。还有就是最后检查一下栈的size，如果不是1也说明运算数多了，返回错误
和这道题类似的，有波兰式求解，中缀表达式求解，这几个其实是表达式的不同表达方式。既然这里出现了逆波兰式，大家还是看看其他两种的求解方法，原理其实近似，都是通过维护栈来实现

We evaluate the expression left-to-right and push operands onto the stack until we encounter an operator, which we pop the top two values from the stack
We then evaluate the operator, with the values as arguments and push the result back onto the stack

Reference:
https://oj.leetcode.com/problems/evaluate-reverse-polish-notation/solution/
http://www.ninechapter.com/solutions/evaluate-reverse-polish-notation/
http://blog.csdn.net/linhuanmars/article/details/21058857
https://yusun2015.wordpress.com/2015/01/25/evaluate-reverse-polish-notation/
*/