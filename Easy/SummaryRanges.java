/*
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]
*/

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        if(nums == null || nums.length == 0)
            return res;
        int start = 0, end= 0;
        for(int i=0;i<nums.length;i++){
            if(i+1<nums.length&&nums[i+1] == nums[i]+1)
                end++;
            else if(start == end){
                res.add(Integer.toString(nums[start]));
                end++;
                start=end;
            }else{ 
                res.add(Integer.toString(nums[start])+"->"+Integer.toString(nums[end]));
                end++;
                start=end;
            }
        }
        return res;
    }
}

/*
Reference:
https://leetcode.com/discuss/42225/my-easy-java-solution
*/