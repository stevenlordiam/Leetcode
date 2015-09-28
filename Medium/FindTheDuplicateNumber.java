/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate element must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant extra space.
Your runtime complexity should be less than O(n2)
*/

import java.util.*;

public class FindTheDuplicateNumber {
	// version 1: Pigeonhole principle
	// without sorting and changing array - O(NlogN)
	public static int findDuplicate(int[] nums) {
		int N = nums.length - 1;
		int low = 1; // this is the range[1..N]
		int high = N;
		while (low < high) {
			int mid = (low + high) / 2;
			int count = 0;
			for (int index = 0; index < N + 1; index++) {
				if (nums[index] >= low && nums[index] <= mid) {
					count++;
				}
			}
			if (count > mid - low + 1) { // there are more on the left range
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
	
	// version 2: sort and compare, O(NlogN)
	public static int findDuplicate2(int[] nums) {
		Arrays.sort(nums);
		for(int i = 0; i < nums.length-1; i++) {
			if(nums[i] == nums[i+1]) return nums[i];
		}
		return nums[0]; // no need 
	}
	
	// version 3: ariithmetic method, add all the numbers, O(N)
	public static int findDuplicate3(int[] nums) {
		int n = nums.length - 1; 	
		int sum = (1 + n) * n / 2;
		int dupSum = 0;
		for(int i = 0; i < nums.length; i++) {
			dupSum += nums[i];
		}
		return dupSum - sum;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,3,5,7,2,6,4,3};
		System.out.println(findDuplicate(nums));
		System.out.println(findDuplicate2(nums));
		System.out.println(findDuplicate3(nums));
	}
}

/*
Reference:
http://stackoverflow.com/questions/7117388/finding-out-the-duplicate-element-in-an-array
*/