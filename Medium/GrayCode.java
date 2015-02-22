/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

public class GrayCode {  
    public ArrayList<Integer> grayCode(int n) {  
        if(n==0) {  
            ArrayList<Integer> result = new ArrayList<Integer>();  
            result.add(0);  
            return result;  
        }  
          
        ArrayList<Integer> tmp = grayCode(n-1);         // recursion
        int addNumber = 1 << (n-1);                     // add 1 to the beginning of (n-1) gray code 
        ArrayList<Integer> result = new ArrayList<Integer>(tmp);  
        for(int i=tmp.size()-1;i>=0;i--) {              // get (n-1) gray code in reverse order
            result.add(addNumber + tmp.get(i));  
        }  
        return result;  
    }  
}  

/*
假设有n-1的答案为：G0, G1, …, Gn，想得到n的答案，只需要在G0…Gn左边加上一个0，再把G0…Gn颠倒顺序，在左边加上一个1即可。比如n=3（在分界线上倒映）：
000
001
011
010
---
110
111
101
100
用dfs可以每次求出左边是0，然后求出左边是1的，合一起就是了。

我们来看看有了n-1位的格雷码如何得到n位的格雷码呢？其实方法比较简单，首先在n-1位的格雷码前面都添加0作为前2^(n-1)个格雷码，
它们一定是合法的因为除了第一位（都是0）其余位都跟n-1的格雷码一致，所以两两之间只相差一位，满足要求。
接下来看看如何接上剩下的2^(n-1)个，我们把n-1位的格雷码倒序排列，然后在每个前面添加1，然后接到上述的前2^(n-1)个就可以了。
首先因为是倒序过来，所以中间两个元素除去第一位其他都是一样的（因为原来最后一个，现在倒序过来就是第一个），而他们第一位分别是0和1，满足格雷码的要求。
而剩下的元素因为我们是n-1位的格雷码倒序排列，所以两两都是满足要求的，加上前面都一样的位1，仍然满足条件。
最后看看这些数字是不是都不一样，前半部分和后半部分肯定不会一样，而因为前半部分都是0开头，后半部分都是1打头，所以互相之间也不会有重复，可以看出覆盖了所有数字，而且依次下来均满足条件。
算法复杂度是O(2+2^2+...+2^n-1)=O(2^n)，所以是指数量级的，因为是结果数量无法避免。空间复杂度则是结果的大小，也是O(2^n)。

Reference:
https://leetcodenotes.wordpress.com/2013/10/19/leetcode-gray-code-%E7%94%9F%E6%88%90%E7%9B%B8%E9%82%BB%E4%B8%A4%E4%B8%AA%E6%95%B0%E5%8F%AA%E6%9C%891%E4%BD%8D%E4%B8%8D%E5%90%8C%E7%9A%84%E5%BA%8F%E5%88%97%EF%BC%8C%E6%AF%8F%E4%B8%AA%E6%95%B0/
http://blog.csdn.net/fightforyourdream/article/details/14517973
http://www.cnblogs.com/lihaozy/archive/2012/12/31/2840437.html
http://blog.csdn.net/linhuanmars/article/details/24511221
http://www.cnblogs.com/yuzhangcmu/p/4121804.html
*/