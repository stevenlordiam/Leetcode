/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution.
Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

public class TwoSumII {
	public int[] twoSum(int[] numbers, int target) {
		int[] result = {-1, -1};
		
		if (numbers == null || numbers.length == 0) {
			return result;
		}
		
		int start = 0;
		int end = numbers.length - 1; 
		
		while (start < end) { // we can use two points to scan the array from both sides
			int sum = numbers[start] + numbers[end];
			if (sum == target) {
				result[0] = start + 1;
				result[1] = end + 1;
				break;
			} else if (sum < target) {
				start++;
			} else {
				end--;
			}
		}
		
		return result;        
    }
}

/*
Without HashMap, just use two pointers, start points to index 0, end points to index len - 1, shrink the scope based on the value and target comparison

Reference:
http://shanjiaxin.blogspot.com/2015/01/two-sum-ii-input-array-is-sorted.html
http://www.programcreek.com/2014/03/two-sum-ii-input-array-is-sorted-java/
http://leetcode.tgic.me/two-sum-ii-input-array-is-sorted/
https://jiajiewu.wordpress.com/2014/12/23/leetcode-two-sum-ii/
https://leetcode.com/discuss/19080/share-my-java-ac-solution
*/