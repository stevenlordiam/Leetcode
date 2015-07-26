/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

public class Solution {
    public String convert(String s, int nRows) {
        if(s == null || s.length()==0 || nRows <=0)
            return "";
        if(nRows == 1)
            return s;
        StringBuilder res = new StringBuilder();
        int size = 2*nRows-2;
        for(int i=0;i<nRows;i++) {
            for(int j=i;j<s.length();j+=size) {
                res.append(s.charAt(j));
                if(i!=0 && i!=nRows-1 && j+size-2*i<s.length())
                    res.append(s.charAt(j+size-2*i));
            }                
        }
        return res.toString();
    }
}

/*
这道题就是看坐标的变化。并且需要分块处理。(http://www.cnblogs.com/springfor/p/3889414.html)

n=2时，字符串坐标变成zigzag的走法就是：
0 2 4 6

1 3 5 7

n=3时的走法是：
0     4     8

1  3  5  7  9

2     6    10 

n=4时的走法是：
0      6      12

1   5  7   11 13

2 4    8 10   14

3      9      15 

可以发现规律，zig(0-5)的长度永远是 2n-2. 利用这个规律，可以按行填字，第一行和最后一行，就是按照2n-2的顺序一点点加的
其他行除了上面那个填字规则，就是还要处理斜着那条线的字，可以发现那条线的字的位置永远是当前列j+(2n-2)-2i(i是行的index） 

Reference:
https://leetcodenotes.wordpress.com/2013/09/30/leetcode-zigzag-conversion-%E6%8A%8Azigzag%E7%9A%84string%E7%94%A8%E6%A8%AA%E7%9D%80%E6%89%AB%E7%9A%84%E6%96%B9%E5%BC%8F%E8%BE%93%E5%87%BA/
http://blog.csdn.net/linhuanmars/article/details/21145039
http://www.cnblogs.com/springfor/p/3889414.html
http://fisherlei.blogspot.com/2013/01/leetcode-zigzag-conversion.html
http://blog.unieagle.net/2012/11/08/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Azigzag-conversion/
*/