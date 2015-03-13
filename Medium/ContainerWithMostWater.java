/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container. (ASK THE INTERVIEWER!!!)
*/

public class ContainerWithMostWater {
    // for any i, the maxium area will be the farthest j that has a[j] > a[i];
    public int maxArea(int[] height) {
        if(height == null || height.length < 2) {
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = height.length -1;
        while(left < right) {
            max = Math.max( max, (right - left) * Math.min(height[left], height[right]));
            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}

/*
Similar to Largest Rectangle in Histogram - https://leetcode.com/problems/largest-rectangle-in-histogram/
Container With Most Water - https://leetcode.com/problems/container-with-most-water/

首先一般我们都会想到brute force的方法，对每一对pair都计算一次容积，然后去最大的那个，总共有n*(n-1)/2对pair，所以时间复杂度是O(n^2)。 
接下来我们考虑如何优化。思路有点类似于Two Sum中的第二种方法--夹逼。从数组两端走起，每次迭代时判断左pointer和右pointer指向的数字哪个大，
如果左pointer小，意味着向左移动右pointer不可能使结果变得更好，因为瓶颈在左pointer，移动右pointer只会变小，所以这时候我们选择左pointer右移。
反之，则选择右pointer左移。在这个过程中一直维护最大的那个容积。因为左pointer只向右移，右pointer只向左移，
直到相遇为止，所以两者相加只走过整个数组一次，时间复杂度为O(n),空间复杂度是O(1)。该算法利用了移动指针可确定的规律，做了一步贪心，实现了时间复杂度的降低。

Need to use two pointers to scan the array from both ends.

max = Math.max( max, something) -- use like this!!!

Reference:
https://leetcodenotes.wordpress.com/2013/07/14/22/
http://blog.unieagle.net/2012/09/16/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Acontainer-with-most-water/
http://blog.csdn.net/linhuanmars/article/details/21145429
*/