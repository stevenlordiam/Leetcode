/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

Hint:

How many majority elements could it possibly have?
Do you have a better hint? Suggest it!
*/

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) { 	// 方法一, sort之后查找，O(NlogN)
        List<Integer> list = new ArrayList<Integer>();
        if (nums.length == 0 ) return list;
        int index = 0, count = 1;
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] == nums[i]) {
                count++;
            } else {
                if (count > nums.length / 3) {
                    list.add(nums[index]);
                }
                count = 1;
                index = i;
            }
        }
        if (count > nums.length / 3) {
        	list.add(nums[index]);
        }
        return list;
    }
}

/*
方法一: sort之后查找，O(NlogN): https://leetcode.com/discuss/42757/my-easy-c-solution
方法二: 这种数最多有两个。其实我们每次扔掉3个不同的数，结果是不变的。如何扔掉3个不同的数？用两个“槽”保存两个不同的数，遍历所有的数，如果和槽里的数相等，则计数器增加1
否则把两个槽里的数和这个数一起扔掉，正好是3个不同的数。注意槽可能为空（计数器为0），最后两个槽里的数（如果有）就是可能的候选。我们再数一下分别出现了多少次，看看是否能达到要求
因为只有两个候选，数一下时间也是O(n)的。总体时间复杂度是O(n)的，空间复杂度是O(1)的
http://www.meetqun.com/thread-10186-1-1.html
*/