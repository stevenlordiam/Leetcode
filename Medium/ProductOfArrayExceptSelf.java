/*
Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis)
*/

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        if (nums.length == 0) {
            return result;
        }
        long[] left = new long[nums.length];
        long[] right = new long[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {         // left: [1,1,2,6]
            left[i] = left[i-1] * nums[i-1];
        }
        right[nums.length-1] = 1;
        for (int i = nums.length-2; i >= 0; i--) {      // right: [24,12,4,1]
            right[i] = right[i+1] * nums[i+1];
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = (int)(left[i] * right[i]);
        }
        return result;
    }
}