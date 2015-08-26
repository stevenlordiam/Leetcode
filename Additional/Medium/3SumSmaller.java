/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.
Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n^2) runtime?
*/

public class 3SumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        if(nums == null || nums.length <= 2)  return 0;
        Arrays.sort(nums);
        int i, j, count = 0;
        for(int k = 2; k <= nums.length - 1; k++){
            i = 0;
            j = k - 1;
            while(i < j){
                int sum = nums[i] + nums[j] + nums[k];

                if(sum < target){
                    count += j - i;
                    i++;
                }
                else
                    j--;
            }
        }
        return count;
    }
}

/*
Reference:
https://leetcode.com/discuss/52362/11-lines-o-n-2-python
https://leetcode.com/discuss/52424/my-solutions-in-java-and-python
https://leetcode.com/discuss/53683/accepted-java-code-with-explanation
http://www.cnblogs.com/jcliBlogger/p/4736809.html
http://www.meetqun.com/thread-10672-1-1.html
*/