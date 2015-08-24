/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

// version 1, add up all numbers, O(N) time, O(1) space
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int num: nums)
            sum += num;
        return (nums.length * (nums.length + 1) )/ 2 - sum;
    }
}

// version 2, XOR, O(1) space
// n^n = 0 and n^0 = n
public class MissingNumber {
	public int missingNumber(int[] nums) {
	    int result = 0;
	    for (int i = 1; i <= nums.length; i++) {
	        result ^= nums[i - 1];
	        result ^= i;
	    }
	    return result;
	}
}

/*
Reference:
https://leetcode.com/discuss/53778/java-solution-o-1-space-and-o-n-in-time
https://leetcode.com/discuss/53794/simple-java-solution-with-explanation-time-o-n-space-o-1
https://leetcode.com/discuss/53871/java-simplest-solution-o-1-space
*/