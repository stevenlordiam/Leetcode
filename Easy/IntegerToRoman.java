/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

public class Solution {
    public String intToRoman(int num) {
        String[] s={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[]  A={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        StringBuilder str=new StringBuilder();
        for(int i=0;i<A.length;i++){
            while(num>=A[i]){
                num=num-A[i];
                str.append(s[i]);
            }
        }
        return str.toString();
    }
}

/*
Roman numeral: I=1, V=5, X=10, L=50, C=100, D=500, M=1000.
Integer to Roman: List all these numbers: I=1, IV=4 V=5, IX=9, X=10,XL=40, L=50,XC=90, C=100, CD=400 D=500,CM=900, M=1000. 
Start at candidate=1000, if candidate<=num, append the corresponding string and set num=num-candidate. Keep doing this until num=0.

Similar:
Roman to Integer

Reference:
https://yusun2015.wordpress.com/2015/01/05/roman-to-integer-integer-to-roman/

Other solution:
http://www.ninechapter.com/solutions/integer-to-roman/
https://leetcodenotes.wordpress.com/2013/08/14/integer-to-roman/
http://blog.csdn.net/linhuanmars/article/details/21145639
*/