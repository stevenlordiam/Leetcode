/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/

public class PlusOne {
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length==0)  
            return digits;                          //check for null array;
        int carry = 1;  
        for(int i=digits.length-1;i>=0;i--) {  
            int digit = (digits[i]+carry)%10;       //if it's 9, then digit is 0, carry over; otherwise, just plus one;
            carry = (digits[i]+carry)/10;           //if it's 9, carry is 1; otherwise carry is 0;
            digits[i] = digit;  
            if(carry==0)                            //process from the last digit, if carry is zero, just return original digits;
                return digits;      
        }  
        int [] res = new int[digits.length+1];      //if carry over, then the highest-digit is 1, and other is 0 by default
        //REMEMBER: after newly initiating an array, the array is all zero by default 
        res[0] = 1;  
        return res;  
    }
}

/*
Notice:
If it is [9,9], output should be [1,0,0]. Should check the carry.
Check: (print out array)
System.out.print(Arrays.toString(plusOne(digits)));
*/