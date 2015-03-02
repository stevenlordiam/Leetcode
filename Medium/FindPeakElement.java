/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

Note:
Your solution should be in logarithmic complexity.
*/

public class FindPeakElement {
    public int findPeakElement(int[] num) {
        int i=0;
        int j=num.length-1;
        while(i<=j){			// binary search, 夹逼方法
            int mid=(i+j)/2;
            if((mid==0||num[mid]>num[mid-1])&&(mid==num.length-1||num[mid]>num[mid+1]))
            return mid;			// consider if mid may be the solution
            if(num[mid]<num[mid+1]) i=mid+1;
            else j=mid-1;
        }
        return i;
    }
}

/*
思路分析：这题O（N）的思路很容易想到，直接遍历找local max即可。但是还可以用二分的思路得到O（logN）的算法，
每次更新l或者r的标准是要保证剩下的半边一定要包含至少一个peak element。要做到这一点，我们只需要比较num[mid]和num[mid+1], 
如果中间元素大于其相邻后续元素，则中间元素左侧(包含该中间元素）必包含一个局部最大值。如果中间元素小于其相邻后续元素，
则中间元素右侧（不含该中间元素）必包含一个局部最大值。对于更新l和r的时候是否要包括中间元素要想清楚，考虑中间元素是否可能是解

Reference:
https://yusun2015.wordpress.com/2015/01/06/find-peak-element/
http://blog.csdn.net/yangliuy/article/details/42417277
*/