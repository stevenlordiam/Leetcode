/*
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 

*/

public class ExcelSheetColumnNumber {
    public static int titleToNumber(String s) {
        if(s == null || s.length() == 0){
        	return 0;
        }
        int count = 0;
        for(int i = 0; i<s.length(); i++){
        	char c = s.charAt(i);		// 'A' to 'Z' is 65 to 90, 'a' to 'z' is 97 to 122 in ASCII code;
       		if(c>'Z' || c<'A'){			// check for input;
        		return 0;
        	}   
        	int tmp = c - 64;
        	count = 26*count +tmp;
        }
        return count;
    }
}

/*
Remember:
char and int can be used together, 'A' equals 65.
string.charAt(i) returns the ith character in a string.
*/