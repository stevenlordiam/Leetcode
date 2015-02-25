/*
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
*/

public class LargestNumber {
    public String largestNumber(int[] num) {
        String[] str=new String[num.length];
        for(int i=0;i<num.length;i++) str[i]=String.valueOf(num[i]); 		// valueOf() - return the string presentation of int
        Arrays.sort(str,new Comparator<String>(){		// override sort
            @Override
            public int compare(String s1,String s2){
                 return -(s1+s2).compareTo(s2+s1);      // s1+s2 < s2+s1, return negative. Remember to use the negative sign here!!!
            }											// s1=3, s2=30, s1+s2=330, s2+s1=303, return -27, so the large number is stored in ascending order
        });
        if(str[0].equals("0")) return "0";
        StringBuilder res=new StringBuilder();
        for(int i=0;i<num.length;i++){
            res.append(str[i]);
        } 
        return res.toString();
    }
}

/*
当一个数是另一个数的前缀时, 要注意顺序. 比较 S1+S2 ? S2+S1 即可
leading zeros要去掉

How to use compareTo() method:
http://blog.csdn.net/yixiaotian1988/article/details/7763806
http://www.cnblogs.com/tonychanleader/archive/2012/10/19/2730509.html

Reference:
http://www.51itong.net/leetcode-179-largest-number-2338.html
http://bookshadow.com/weblog/2015/01/13/leetcode-largest-number/
http://www.cnblogs.com/yuzhangcmu/p/4235285.html
https://oj.leetcode.com/discuss/23422/my-java-solution-to-share
https://oj.leetcode.com/discuss/22806/share-my-accepted-java-code
*/