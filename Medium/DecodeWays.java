/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] nums = new int[s.length() + 1];
        nums[0] = 1;
        nums[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {   // one digit
                nums[i] = nums[i - 1];
            }
            int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            if (twoDigits >= 10 && twoDigits <= 26) {
                nums[i] += nums[i - 2];
            }
        }
        return nums[s.length()];
    }
}

/*
Dynamic Programming

其实一维数组，问“几种ways”，肯定是要dp的。这题类似于小孩上楼梯，求d[i]有两种情况：
- 当前这个single digit arr[i]是在1~9之内，说明能组成一个字母，则当前的组合方式(d[i – 1], arr[i])成功，则d[i] = d[i – 1]。不成功，则以i结尾的string没有组合方式，d[i] = 0
- 当前这个arr[i]是能组成一个10~26的两位数的第二个digit。这样的话，当前的组合(d[i -2], “arr[i – 1]arr[i]”）成功，则d[i] += d[i – 2]。不成功也不归零，因为可能之前single digit的方式成了，所以不动这个d[i]就行了
public int numDecodings(String s) {
	if (s.length() == 0)
		return 0;
	int[] d = new int[s.length()];
	if (s.charAt(0) - '0' != 0)
		d[0] = 1;
	for (int i = 1; i < s.length(); i++) {
		if (s.charAt(i) - '0' != 0)
			d[i] = d[i - 1];
		else
			d[i] = 0;
		if (canFormTwo(s, i - 1, i + 1))
   			d[i] += i > 1 ? d[i - 2] : 1;
 	}
 	return d[s.length() - 1];
}
boolean canFormTwo(String s, int i, int j) {
	if (j > s.length())
		return false;
	Integer firstTwo = Integer.valueOf(s.substring(i, j));
	return firstTwo > 9 && firstTwo <= 26;
}

这道题要求解一个数字串按照字符串编码方式可解析方式的数量。看到这种求数量的，我们很容易想到动态规划来存储前面信息，然后迭代得到最后结果
我们维护的量res[i]是表示前i个数字有多少种解析的方式，接下来来想想递归式，有两种方式：第一种新加进来的数字不然就是自己比较表示一个字符，那么解析的方式有res[i-1]种，第二种就是新加进来的数字和前一个数字凑成一个字符，解析的方式有res[i-2]种（因为上一个字符和自己凑成了一个）
当然这里要判断前面说的两种情况能否凑成一个字符，也就是范围的判断，如果可以才有对应的解析方式，如果不行，那么就是0。最终结果就是把这两种情况对应的解析方式相加。这里可以把范围分成几个区间：
（1）00：res[i]=0（无法解析，没有可行解析方式）；
（2）10, 20：res[i]=res[i-2]（只有第二种情况成立）；
（3）11-19, 21-26：res[i]=res[i-1]+res[i-2]（两种情况都可行）；
（4）01-09, 27-99：res[i]=res[i-1]（只有第一种情况可行）；
递推式就是按照上面的规则来得到，接下来我们只要进行一遍扫描，然后依次得到维护量就可以了，算法的时间复杂度是O(n)。空间上可以看出我们每次只需要前两位的历史信息，所以只需要维护三个变量然后迭代赋值就可以了，所以空间复杂度是O(1)

Analysis:
For  each position i, let a and b be the decoding numbers for s(0,i-2) and s(0,i-1), then
- if s(i)=’0′, the decoding number for partition s(0,i-1) with s(i) is 0, so we let b=0;
- if s(i-1,i) is a valid number then the decoding number for i is b+a;
- if not the decoding number is b.

Reference:
https://leetcodenotes.wordpress.com/2013/07/17/decode-ways/
http://www.ninechapter.com/solutions/decode-ways/
http://blog.csdn.net/linhuanmars/article/details/24570759
(一维动态规划总结)http://blog.csdn.net/linhuanmars/article/details/38468361
https://yusun2015.wordpress.com/2015/01/27/decode-ways/
*/