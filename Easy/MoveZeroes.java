/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations
*/

public class MoveZeroes {
	public void moveZeroes(int[] nums) {
		int rightPos = 0, curr = 0;
		while(curr < nums.length) {
			if(nums[curr]!=0) nums[rightPos++] = nums[curr];
			curr++;
		}
		while(rightPos < nums.length) {
			nums[rightPos++] = 0;
		}
	}
}

/*
https://leetcode.com/discuss/59073/java-o-n-solution-in-place-without-basecase
https://leetcode.com/discuss/59089/3-liner-and-few-operations
https://leetcode.com/discuss/59103/simple-in-place-java-solution-o-n-time-complexity
https://leetcode.com/discuss/59015/simple-o-n-java-solution-using-insert-index
https://leetcode.com/discuss/59064/c-accepted-code
*/