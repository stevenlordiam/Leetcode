/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate element must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
- You must not modify the array (assume the array is read only).
- You must use only constant, O(1) extra space.
- Your runtime complexity should be less than O(n2)
- There is only one duplicate number in the array, but it could be repeated more than once.
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
	
	// version 2: Cycle Detection - LinkList Cycle Detection II
	// O(N) time, O(1) space
	public static int findDuplicate2(int[] nums) {
		int len = nums.length;
		int slow = 0;
		int fast = 0;
		while(true) {
			slow = nums[slow];
			fast = nums[nums[fast]];
			if(slow == fast) {
				break;
			}
		}
		fast = 0;
		while(true) {
			slow = nums[slow];
			fast = nums[fast];
			if(slow == fast) {
				return slow;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1,4,5,7,2,6,4,3};
		System.out.println(findDuplicate(nums));
		System.out.println(findDuplicate2(nums));
	}
}

/*
Reference:
http://stackoverflow.com/questions/7117388/finding-out-the-duplicate-element-in-an-array
(Binary Search) https://leetcode.com/discuss/60830/solution-explanation-time-space-without-changing-input-array
(Cycle Detection) https://leetcode.com/discuss/60852/ac-c-code-with-o-n-time-and-o-1-space
http://keithschwarz.com/interesting/code/?dir=find-duplicate
*/