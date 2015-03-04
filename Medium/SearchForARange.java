/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

public class SearchForARange {
    public int[] searchRange(int[] A, int target) {
        int start, end, mid;
        int[] bound = new int[2]; 
        
        // search for left bound
        start = 0; 
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                end = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] == target) {
            bound[0] = start;
        } else if (A[end] == target) {
            bound[0] = end;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }
        
        // search for right bound
        start = 0;
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                start = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[end] == target) {
            bound[1] = end;
        } else if (A[start] == target) {
            bound[1] = start;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }
        
        return bound;
    }
}

/*
Similar to Search in Rotated Sorted Array I/II, Find Minimum in Sorted Rotated Array I/II, Search Insert Position
CC150 - (11.3)

先用二分法找左边的7（就算找到了8，就当没看见，因为想找7,8之间的一个数）；再找右边的10
注意：最后如果两个boundry连起来了，说明之间啥也没有，就是没找到8，就全赋成-1

这道题是二分查找Search Insert Position的变体，思路并不复杂，就是先用二分查找找到其中一个target，然后再往左右找到target的边缘
找边缘的方法跟二分查找仍然是一样的，只是切半的条件变成相等，或者不等（往左边找则是小于，往右边找则是大于），这样下来总共进行了三次二分查找，所以算法的时间复杂度仍是O(logn)，空间复杂度是O(1)
实现中用到了在Search Insert Position中提到的方法，可以保证当搜索结束时，l和r所停的位置正好是目标数的后面和前面

Reference:
https://leetcodenotes.wordpress.com/2013/08/19/leetcode-seach-for-a-range-%E4%BA%8C%E5%88%86%E6%B3%95%E6%89%BE%E6%8E%92%E5%A5%BD%E5%BA%8F%E7%9A%84%E6%95%B0%E7%BB%84%E7%9A%84%E6%9F%90%E4%B8%AA%E6%95%B0%E7%9A%84%E8%B5%B7%E5%A7%8B%E4%BD%8D/
http://www.ninechapter.com/solutions/search-for-a-range/
http://blog.csdn.net/linhuanmars/article/details/20593391
https://yusun2015.wordpress.com/2015/01/11/search-for-a-range/
*/