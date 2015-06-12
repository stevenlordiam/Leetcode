/*
Given an array of integers, find out whether there are two distinct indices i and j 
in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/

public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }
        final TreeSet<Integer> values = new TreeSet<>();
        for (int ind = 0; ind < nums.length; ind++) {
            Integer floor = values.floor(nums[ind] + t);
            Integer ceil = values.ceiling(nums[ind] - t);
            if ((floor != null && floor >= nums[ind])
                    || (ceil != null && ceil <= nums[ind])) {
                return true;
            }
            values.add(nums[ind]);
            if (ind >= k) {
                values.remove(nums[ind - k]);
            }
        }
        return false;
    }
}

/*
Reference:
https://leetcode.com/discuss/38146/java-solution-with-treeset
https://leetcode.com/discuss/38177/java-o-n-lg-k-solution
https://leetcode.com/discuss/38206/ac-o-n-solution-in-java-using-buckets-with-explanation
*/

/*
Treeset: http://www.programcreek.com/2013/03/hashset-vs-treeset-vs-linkedhashset/
In brief, if you need a fast set, you should use HashSet; if you need a sorted set, 
then TreeSet should be used; if you need a set that can be store the insertion order, LinkedHashSet should be used.
*/