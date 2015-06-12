/*
Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
*/

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if(nums.length==0) return false;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++) {
            if(set.contains(nums[i])) return true;
            else {
                set.add(nums[i]);
            }
        }
        return false;
    }
}

/*
Reference:
https://leetcode.com/discuss/37219/possible-solutions
https://leetcode.com/discuss/37355/java-solution-with-sorting-o-nlog-n-time-o-1-space
https://leetcode.com/discuss/37190/java-o-n-ac-solutions-with-hashset-and-bitset
*/