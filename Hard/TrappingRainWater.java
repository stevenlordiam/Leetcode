/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/

public class TrappingRainWater {
	public int trap(int[] A) {
    	int[] left = new int[A.length];
    	int[] right = new int[A.length];
    	for (int i = 0; i < A.length; i++) {
        	left[i] = i > 0 ? Math.max(left[i - 1], A[i]) : A[i]; // the first is A[i], other is max(remember to check the first one, cannot only use max, it will be outofbound -1)
    	}
    	for (int i = A.length - 1; i >= 0; i--) {
        	right[i] = i < A.length - 1 ? Math.max(right[i + 1], A[i]) : A[i]; // the last is A[i], other is max
    	}
    	int res = 0;
    	for (int i = 1; i < A.length - 1; i++) { //two edges can't trap nothin'
        	int lowBar = Math.min(left[i - 1], right[i + 1]);
        	if (lowBar > A[i])
            	res += lowBar - A[i];
    	}
    	return res;
	}
}

/*
Similar to Largest Rectangle in Histogram - https://leetcode.com/problems/largest-rectangle-in-histogram/
Container With Most Water - https://leetcode.com/problems/container-with-most-water/

中心思路是：每个bar头顶能接多少水，取决于它两边有木有比自己高的两个木板，有的话自己上方就被trap住了，trap的volumn是（两边比自己高的那两个模板中的短板-自己的高度）×1
然后就要考虑怎么求在每个木板i，他的左边最高板和右边最高板的值呢？用dp一试就出来了

这道题比较直接的做法类似Longest Palindromic Substring中的第一种方法，对于每一个bar往两边扫描，找到它能承受的最大水量，然后累加起来即可
每次往两边扫的复杂度是O(n)，对于每个bar进行处理，所以复杂度是O(n^2)，空间复杂度是O(1)
下面我们说说优化的算法。这种方法是基于动态规划的，基本思路就是维护一个长度为n的数组，进行两次扫描，一次从左往右，一次从右往左
第一次扫描的时候维护对于每一个bar左边最大的高度是多少，存入数组对应元素中，第二次扫描的时候维护右边最大的高度，并且比较将左边和右边小的最大高度（我们成为瓶颈）存入数组对应元素中
这样两遍扫描之后就可以得到每一个bar能承受的最大水量，从而累加得出结果。这个方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。空间上需要一个长度为n的数组，复杂度是O(n)
public int trap(int[] A) {
    if(A==null || A.length==0)
        return 0;
    int max = 0;
    int res = 0;
    int[] container = new int[A.length];
    for(int i=0;i<A.length;i++)
    {
        container[i]=max;
        max = Math.max(max,A[i]);
    }
    max = 0;
    for(int i=A.length-1;i>=0;i--)
    {
        container[i] = Math.min(max,container[i]);
        max = Math.max(max,A[i]);
        res += container[i]-A[i]>0?container[i]-A[i]:0;
    }    
    return res;
}
这个方法算是一种常见的技巧，从两边各扫描一次得到我们需要维护的变量，通常适用于当前元素需要两边元素来决定的问题，非常类似的题目是Candy
上面的方法非常容易理解，实现思路也很清晰，不过要进行两次扫描，复杂度前面的常数得是2，接下来我们要介绍另一种方法，相对不是那么好理解，但是只需要一次扫描就能完成
基本思路是这样的，用两个指针从两端往中间扫，在当前窗口下，如果哪一侧的高度是小的，那么从这里开始继续扫，如果比它还小的，肯定装水的瓶颈就是它了，可以把装水量加入结果，如果遇到比它大的，立即停止，重新判断左右窗口的大小情况，重复上面的步骤
这里能作为停下来判断的窗口，说明肯定比前面的大了，所以目前肯定装不了水（不然前面会直接扫过去）。这样当左右窗口相遇时，就可以结束了，因为每个元素的装水量都已经记录过了
public int trap(int[] A) {
    if(A==null || A.length ==0)
        return 0;
    int l = 0;
    int r = A.length-1;
    int res = 0;
    while(l<r)
    {
        int min = Math.min(A[l],A[r]);
        if(A[l] == min)
        {
            l++;
            while(l<r && A[l]<min)
            {
                res += min-A[l];
                l++;
            }
        }
        else
        {
            r--;
            while(l<r && A[r]<min)
            {
                res += min-A[r];
                r--;
            }
        }
    }
    return res;
}
这个算法每个元素只被访问一次，所以时间复杂度是O(n)，并且常数是1，比前面的方法更优一些，不过理解起来需要想得比较清楚
这种两边往中间夹逼的方法也挺常用的，它的核心思路就是向中间夹逼时能确定接下来移动一侧窗口不可能使结果变得更好，所以每次能确定移动一侧指针，直到相遇为止
这种方法带有一些贪心，用到的有Two Sum，Container With Most Water

Analysis:
One important fact need to notice is: for any position i of the container, there are two bars which are the maximums of the two sides of A[i] and the water trapped at i equals the height of the smaller bar subtract A[i]

Reference:
https://leetcodenotes.wordpress.com/2013/10/03/leetcode-trapping-rain-water-%E4%BF%84%E7%BD%97%E6%96%AF%E6%96%B9%E5%9D%97%E4%B8%80%E6%A0%B7%E7%9A%84%E4%B8%80%E6%8E%92%E8%83%BD%E6%8E%A5%E5%A4%9A%E5%B0%91%E9%9B%A8%E6%B0%B4/
http://www.ninechapter.com/solutions/trapping-rain-water/
http://blog.csdn.net/linhuanmars/article/details/20888505
https://yusun2015.wordpress.com/2015/01/09/trapping-rain-water/
*/