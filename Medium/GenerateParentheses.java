/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/

public class GenerateParentheses {
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> rst = new ArrayList<String>();
        if(n <= 0) {
            return rst;
        }
        getPair(rst, "", n, n);
        return rst;
    }
    
	public void getPair(ArrayList<String> rst , String s, int left, int right) {
		if(left > right || left < 0 || right < 0) {
			return; 	
		}
		if(left == 0 && right == 0) {
			rst.add(s);
			return;
		}

		getPair(rst, s + "(", left - 1, right);
		getPair(rst, s + ")", left, right - 1);
	}
}

/*
Similar to CC150 (9-6) generate parentheses (RecursionAndDynamicProgramming_6.java)

一般来说是用递归的方法，因为可以归结为子问题去操作。在每次递归函数中记录左括号和右括号的剩余数量，然后有两种选择，一个是放一个左括号，另一种是放一个右括号
当然有一些否定条件，比如剩余的右括号不能比左括号少，或者左括号右括号数量都要大于0。正常结束条件是左右括号数量都为0。算法的复杂度是O(结果的数量)

Use depth-first search:
- if the number of “(” is less than n, add “(“;
- if the number of “)” is less than the number of “(“, add “)”
- return when both the number of “(” and “)” are n.

Reference:
http://www.ninechapter.com/solutions/generate-parentheses/
http://blog.csdn.net/linhuanmars/article/details/19873463
https://yusun2015.wordpress.com/2015/01/08/generate-parentheses/
*/