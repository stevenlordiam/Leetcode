/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/
public class StringToInteger {
    public int atoi(String str) {
        str=str.trim();             // omit the leading and trailing whitespaces
        if(str.length()==0) return 0;
        int sign=1,start=0;         // start index
        long res=0;                 // store the result in long type to prevent overflow
        if(str.charAt(0)=='-'){
            sign=-1;
            start=1;
        }
        if(str.charAt(0)=='+'){
            start=1;
        }
        for(int i=start;i<str.length();i++){
            if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
                res=10*res+sign*(int)(str.charAt(i)-'0');           // read the digit
                if(res>Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if(res<Integer.MIN_VALUE) return Integer.MIN_VALUE;
            }
            else break;
        }
        return (int)res;
    }
}

/*
Two things to consider about integer: 
1) +/- sign	2) overflow

Thought: 
first remove the whitespaces, then read in the sign(+/- or none), then read in the number in oder
Three way of ending: non-digit/overflow/string
Some corner cases:
1) first need to use trim to get rid of all the whitespaces. If the length of the string is zero after trimming, return 0;
2) then check the first position of the string and find the sign of the integer;
3) sum over the digits until the end or some non digit character appears. If the result is out of range ,return.

string.trim():
Returns a copy of the string, with leading and trailing whitespace omitted
"   a b c   " ->  "a b c"

To deal with overflow, store the int to long primitive(THIS IS IMPORTANT!!!)

Solution:
To deal with overflow, inspect the current number before multiplication. If the current number is greater than 214748364, 
we know it is going to overflow. On the other hand, if the current number is equal to 214748364, we know that it will overflow only when the current digit is greater than or equal to 8.

The heart of this problem is dealing with overflow. A direct approach is to store the number as a string so we can evaluate at each step if the number had indeed overflowed. 
There are some other ways to detect overflow that requires knowledge about how a specific programming language or operating system works.
A desirable solution does not require any assumption on how the language works. In each step we are appending a digit to the number by doing a multiplication and addition. 
If the current number is greater than 214748364, we know it is going to overflow. On the other hand, if the current number is equal to 214748364, we know that it will overflow only when the current digit is greater than or equal to 8. 
Remember to also consider edge case for the smallest number, –2147483648 (–2^31).
*/