/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/
public class ValidParentheses {
	public boolean isValid(String s) {
    	if(s==null || s.length()==0)			// for a string, always check for null and zero-length
        	return true;				
    	LinkedList<Character> stack = new LinkedList<Character>();
    	for(int i=0;i<s.length();i++) {
        	switch(s.charAt(i)) {
            	case '(':
            	case '{':
            	case '[':
                	stack.push(s.charAt(i));
                	break;
            	case ')':
                	if(stack.isEmpty() || stack.pop()!='(')
               	    	return false;
                	break;
            	case '}':
               		if(stack.isEmpty() || stack.pop()!='{')
                    	return false;
                	break;
            	case ']':
                	if(stack.isEmpty() || stack.pop()!='[')
                    	return false;
                	break; 
            	default:
                	break;
        	}
    	}
    	if(stack.isEmpty())
        	return true;
    	return false;
	}
}

/*
Similar to CC150 (9-6) valid parentheses - (RecursionAndDynamicProgramming_6.java)

Using a stack to store is a GOOD solution!
REMEMBER to check whether the stack is empty at last

Other solution:
Using Regular Expression?
*/