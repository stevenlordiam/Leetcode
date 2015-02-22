/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
*/

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        long a=numerator,b=denominator;                 // store as long to avoid overflow
        if(a%b==0) return String.valueOf(a/b);          // can be divide
        Map<Long,Integer> map=new HashMap<>();
        StringBuilder res=new StringBuilder();
        if((a>0&&b<0)||(a<0&&b>0)) res.append("-");     // negative value
        a=Math.abs(a);
        b=Math.abs(b);
        res.append(a/b+".");    
        a=(a%b)*10;                                     // MULTIPLY BY TEN to let the result always be integer 
        while(!map.containsKey(a)){
            map.put(a,res.length());                    // key is the remainder a, value is the length index res.length()
            res.append(String.valueOf(a/b));
            a=(a%b)*10;
            if(a==0) return res.toString();             // not recurring decimal
        }
        return res.insert(map.get(a),"(").append(")").toString();   // append '()' to the decimal part if it's recurring decimal. 
        // for map, put(k,v) and get(k). map.get(a) returns the length index as the offset place to insert the parentheses
        // for StringBuider, insert(int offset, String str) - Insert the string into the character sequence        
        // Remember to use toString() after getting the result as StringBuilder
    }   
}       

/*
trick: 
1. +/- sign, overflow when get the absolute value of Integer.MIN_VALUE
1. use long to avoid overflow
2. use StringBuffer to build String
3. use class pair to keep track of numerator and denominator,if I find the same pair in the map,it means the fractional part is repeating
4. using res=numeratorl/denominatorl; numeratorl=(numeratorl%denominatorl)*10; to simulate division, res is the value to append to buffer

basic idea: (https://oj.leetcode.com/discuss/20515/my-java-solution)
- for the input integer: positive and negative number matters? => yes, so check the sign first
- can get integer part directly by a/b, then deal with decimal part
- get remainder by a%b. the problem is: how to check the decimal part is repeated (or not)?
- for each loop, we can get the digit by remainder10/b and update new remainder=remainder10%b, if we get the same remainder again, previous result is repeated
- so, use a Hash Table to store the remainder(s) that already appeared
- check remainder for each loop to know whether it's belong to "recurring" result or not

0.16  
6 ) 1.00
    0 
    1 0       <-- Remainder=1, mark 1 as seen at position=0.
    - 6 
      40      <-- Remainder=4, mark 4 as seen at position=1.
    - 36 
       4      <-- Remainder=4 was seen before at position=1, so the fractional part which is 16 starts repeating at position=1 => 1(6).

The key insight here is to notice that once the remainder starts repeating, so does the divided result.
You will need a Hash Table that maps from the remainder to its position of the fractional part. 
Once you found a repeating remainder, you may enclose the reoccurring fractional part with parentheses by consulting the position from the table.
The remainder could be zero while doing the division. That means there is no repeating fractional part and you should stop right away.
Just like the question Divide Two Integers, be wary of edge case such as negative fractions and nasty extreme case such as -2147483648 / -1.

【中文解析】
难点：如何识别循环体？
解决方法：用一个HashMap记录每一个余数，当出现重复的余数时，那么将会进入循环，两个重复余数之间的部分就是循环体
示例：1/13=0.076923076923076923...，当小数部分第二次出现0时，就意味着开始了循环，那么需要把076923用括号括起来，结果为0.(076923)
涉及技巧：
1）在不断相除的过程中，把余数乘以10再进行下一次相除，保证一直是整数相除
2）HashMap的key和value分别是<当前余数, 对应结果下标>，这样获取076923时就可根据value值来找
注意点1：考虑正负数，先判断符号，然后都转化为正数；
注意点2：考虑溢出，如果输入为Integer.MIN_VALUE，取绝对值后会溢出。
注意点3：一定要先把 int 转为 long，然后再取绝对值。如果写成 long num = Math.abs(numerator) 就会有问题，因为这句代码在 numerator=Integer.MIN_VALUE 时相当于 long num = Math.abs(-2147483648)，这样得到的 num还是 -2147483648

Reference:
https://yusun2015.wordpress.com/2015/01/29/fraction-to-recurring-decimal/
http://blog.csdn.net/ljiabin/article/details/42025037
https://oj.leetcode.com/discuss/20515/my-java-solution
*/