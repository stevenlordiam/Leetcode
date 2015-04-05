/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
*/

public class HouseRobber {
    public int rob(int[] num) {
        int n = num.length;
        if (n < 2) {
            return n == 0 ? 0 : num[0];
        }
        int[] cache = new int[n];
        cache[0] = num[0];
        cache[1] = num[0] > num[1] ? num[0] : num[1];
        for (int i = 2; i < n; i++) {
            cache[i] = cache[i - 2] + num[i];
            cache[i] = cache[i] > cache[i-1]? cache[i] : cache[i-1];
        }
        return cache[n - 1];
    }
}

/*
Dynamic Programming

Reference:
https://leetcode.com/discuss/30277/java-o-n-dp-solution-with-13-lines-clean-code
https://leetcode.com/discuss/30020/java-o-n-solution-space-o-1
*/