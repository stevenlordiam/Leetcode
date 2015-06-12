/*
Given an array of integers and an integer k, find out whether there there are two distinct indices i and j 
in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
*/

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i <  nums.length; i++) {
            Integer ord = map.put(nums[i], i);      // map.put() returns the previous value associated with key, or null if there was no mapping for key
            if(ord != null && i - ord <= k) {
                return true;
            }
        }
        return false;
    }
}

/*
Reference:
https://leetcode.com/discuss/38445/simple-java-solution
https://leetcode.com/discuss/38323/java-solution-using-hashmaps-put
https://leetcode.com/discuss/38124/java-solution-using-set-and-sliding-window
https://leetcode.com/discuss/37798/easy-and-clear-solution-by-using-hashmap-java
https://leetcode.com/discuss/37794/java-solution-with-hashset
*/