/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
       if(divisor==0) return Integer.MAX_VALUE;
       if(divisor==1) return dividend;
       if(dividend==0) return 0;          // remember these special cases
       long a=dividend,b=divisor;         // use long to avoid overflow
       long res=divideHelper(Math.abs(a),Math.abs(b));
       if((a>0&&b<0)||(a<0&&b>0)) res=-res;
       if(res>Integer.MAX_VALUE||res<Integer.MIN_VALUE) return  Integer.MAX_VALUE;
       else return (int)res;              // cast long to integer
        
    }
    public long divideHelper(long a, long b){
       if(a==b) return 1;
       if(a<b) return 0;    // because it's integer division, if a<b, return 0
       long c=b;
       long n=1;
       while(a>=c){         // 让除数左移直到大于被除数之前得到一个最大的数
           c=c<<1;          // 左移一位相当于乘以2
           n=n<<1;          // n is count
       }
       return (n>>1)+divideHelper(a-(c>>1),b);  // a减去c(b)小于a的最大一个数，然后迭代，要加上（n>>1）表示左移了n次，即乘了2的几次方
    }  // a= 100, b=3, while循环后c=3*2^6=192, n=2*6=64, return的时候要除以2（右移一位）得到小于a的最大的数32，然后再迭代
}

/*
不让用*, /, %号做整数除法。基本只能bit了。但是bit操作都是跟2有关的，所以到最后还得不断缩小范围，好能贴近结果
算法：a / b
a本来是想一直-b，然后减到<0了，算counter。但是这样慢，所以类似c++ vector的思路，每次发现还没减没，那减数就翻倍（b <<= 1）
然后到了一定程度，b实在太大了，大于a剩余的部分了，那么就停止。然后剩下的a再一点点开始减，b回归成最开始的值，重做这两步
重点是一旦超过，b应该不要一下回到原始值，而是应该一点一点/2这样做。最刁钻的地方是test case全是各种Integer.MINVALUE之类的，搞得各种溢出，
然后才发现Math.abs(MIN_VALUE)其实还是-2147483648（坑爹呢吧abs还是负数），而不是2147483648
测试数据出现了-2147483648，还不能把它转成正的(2147483648就溢出了），所以直接全转换成long（64位），跑一遍就过了

这道题属于数值处理的题目，对于整数处理的问题，在Reverse Integer中我有提到过，比较重要的注意点在于符号和处理越界的问题
对于这道题目，因为不能用乘除法和取余运算，我们只能使用位运算和加减法。比较直接的方法是用被除数一直减去除数，直到为0
这种方法的迭代次数是结果的大小，即比如结果为n，算法复杂度是O(n)
那么有没有办法优化呢？ 这个我们就得使用位运算。我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n
基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基
然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止
因为这个方法的迭代次数是按2的幂直到超过结果，所以时间复杂度为O(logn)

这种数值处理的题目在面试中还是比较常见的，类似的题目有Sqrt(x)，Pow(x, n)等，上述方法在其他整数处理的题目中也可以用到

SOLUTION 1
1. 基本思想是不断地减掉除数，直到为0为止。但是这样会太慢
2. 我们可以使用2分法来加速这个过程。不断对除数*2（左移1位），直到它比被除数还大为止。加倍的同时，也记录下cnt，将被除数减掉加倍后的值，并且结果+cnt
因为是2倍地加大，所以速度会很快，指数级的速度
3. 另外要注意的是：最小值的越界问题。对最小的正数取abs，得到的还是它。。。 因为最小的正数的绝对值大于最大的正数（INT）。所以，我们使用Long来接住这个集合就可以了

Reference:
https://leetcodenotes.wordpress.com/2013/10/19/divide-two-integers/
http://www.cnblogs.com/yuzhangcmu/p/4049170.html
http://blog.csdn.net/linhuanmars/article/details/20024907
https://yusun2015.wordpress.com/2015/01/27/divide-two-integers/
*/