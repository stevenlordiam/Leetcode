/*
Note: This is an extension of House Robber.
After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/

public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }
    
    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
}

/*
Since this question is a follow-up to House Robber, we can assume we already have a way to solve the simpler question, i.e. 
given a 1 row of house, we know how to rob them. So we already have such a helper function. We modify it a bit to rob a given range of houses.

private int rob(int[] num, int lo, int hi) {
    int include = 0, exclude = 0;
    for (int j = lo; j <= hi; j++) {
        int i = include, e = exclude;
        include = e + num[j];
        exclude = Math.max(e, i);
    }
    return Math.max(include, exclude);
}

Now the question is how to rob a circular row of houses. It is a bit complicated to solve like the simpler question. 
It is because in the simpler question whether to rob num[lo] is entirely our choice. 
But, it is now constrained by whether num[hi] is robbed. However, since we already have a nice solution to the simpler problem. 
We do not want to throw it away. Then, it becomes how can we reduce this problem to the simpler one. 
Actually, extending from the logic that if house i is not robbed, then you are free to choose whether to rob house i + 1, 
you can break the circle by assuming a house is not robbed. For example, 1 -> 2 -> 3 -> 1 becomes 2 -> 3 if 1 is not robbed. 
Since every house is either robbed or not robbed and at least half of the houses are not robbed, 
the solution is simply the larger of two cases with consecutive houses, i.e. house i not robbed, break the circle, 
solve it, or house i + 1 not robbed. Hence, the following solution. I chose i = n and i + 1 = 0 for simpler coding. 
But, you can choose whichever two consecutive ones.

public int rob(int[] nums) {
    if (nums.length == 1) return nums[0];
    return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
}

Reference:
https://leetcode.com/discuss/36544/simple-ac-solution-in-java-in-o-n-with-explanation
https://leetcode.com/discuss/36676/java-ac-code-may-help-u
*/