/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {          // keep a sliding window
        int front=0;
        int end=0;
        int sum=0;
        int minSub = Integer.MAX_VALUE;
        while(end < nums.length) {
            while(sum<s && end<nums.length)
                sum+=nums[end++];
            while(sum>=s && front<nums.length) {
                minSub = Math.min(minSub, end-front);
                sum-=nums[front++];
            }
        }
        return minSub==Integer.MAX_VALUE? 0: minSub;
    }
}

/*
Since the given array contains only positive integers, the subarray sum can only increase by including more elements. 
Therefore, you don't have to include more elements once the current subarray already has a sum large enough. 
This gives the linear time complexity solution by maintaining a minimum window with a two indices.
As to NLogN solution, logN immediately reminds you of binary search. In this case, you cannot sort as the current order actually matters. 
How does one get an ordered array then? Since all elements are positive, the cumulative sum must be strictly increasing. 
Then, a subarray sum can expressed as the difference between two cumulative sum. Hence, given a start index for the cumulative sum array, the other end index can be searched using binary search.

Reference:
https://leetcode.com/discuss/35378/solutions-java-with-time-complexity-nlogn-with-explanation
https://leetcode.com/discuss/35288/java-ac-solution-using-two-pointers
https://leetcode.com/discuss/35626/java-solution-o-n-time-sliding-window
https://leetcode.com/discuss/35335/o-nlgn-is-not-that-easy-here-is-my-java-code
https://leetcode.com/discuss/35417/c-solution-both-o-n-%26-o-nlogn
*/