/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4]
*/

public class WiggleSort {
	// solution1
    public static void wiggleSort1(int[] nums) { 	
        for(int i = 0; i < nums.length; i++) {
            if(i % 2 ==1) {
            	if(nums[i-1] > nums[i]) {
               		swap(nums, i);
            	}
            } else if(i != 0 && nums[i-1] < nums[i]) {
            	swap(nums, i);
            }
        }
    }
    
    public static void swap(int[] nums, int i) {    // swap with previous element
        int tmp=nums[i];
        nums[i]=nums[i-1];
        nums[i-1]=tmp;
    }

    // solution2
	public static void wiggleSort(int[] nums) { 	
	    for (int i=1; i < nums.length; i++) {
	        int a = nums[i-1];
	        if ((i%2 == 1) == (a > nums[i])) {
	            nums[i-1] = nums[i];
	            nums[i] = a;
	        }
	    }
	}
}

/*
Reference:
https://leetcode.com/discuss/57113/java-o-n-solution
http://www.cnblogs.com/jcliBlogger/p/4797531.html
http://www.cnblogs.com/easonliu/p/4798814.html
http://www.fgdsb.com/2015/01/20/special-sorting/
*/