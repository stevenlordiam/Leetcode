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
        int[] res = new int[n];
        res[0] = num[0];
        res[1] = num[0] > num[1] ? num[0] : num[1];
        for (int i = 2; i < n; i++) {
            res[i] = res[i - 2] + num[i];
            res[i] = res[i] > res[i-1]? res[i] : res[i-1];
        }
        return res[n - 1];
    }
}

/*
Dynamic Programming

//方法一：
public long houseRobber(int[] A) {
    // write your code here
    int n = A.length;
    long []res = new long[A.length];
    long ans = 0;
    if(n==0)
        return 0;
    if(n >= 1) 
        res[0] = A[0];
    if(n >= 2)
        res[1] = Math.max(A[0], A[1]);
    if(n >= 3)
        res[2] = Math.max(A[0]+A[2], A[1]);
    if(n > 2){
        for(int i = 3; i < n; i++) {
            res[i] = Math.max(res[i-3], res[i-2])+ A[i];
        }
    }
    for(int i =0 ; i < n; i++){
        ans = Math.max(ans,res[i]);
    }
    return ans;
}

Reference:
http://www.jiuzhang.com/solutions/house-robber/
https://leetcode.com/discuss/30277/java-o-n-dp-solution-with-13-lines-clean-code
https://leetcode.com/discuss/30020/java-o-n-solution-space-o-1
*/