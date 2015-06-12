/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k,new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                return a-b;
            }
        });
        for(int i=0;i<nums.length;i++) {
            if(queue.size()<k) queue.add(nums[i]);
            else {
                if(nums[i]>queue.peek()) {
                    queue.remove();
                    queue.add(nums[i]);
                }
            }
        }
        return queue.remove();
    }
}

/*
Reference:
https://leetcode.com/discuss/36966/solution-explained
https://leetcode.com/discuss/36913/solutions-java-having-worst-time-complexity-with-explanation

QuickSelect: O(N) best case / O(N^2) worst case running time + O(1) memory
https://leetcode.com/discuss/37402/4ms-quickselect-algorithm
https://leetcode.com/discuss/36991/java-quick-select

QuickSort: O(N) best case / O(N^2) worst case running time + O(1) memory
https://leetcode.com/discuss/37883/partition-thinking-solution-quick-sort-java
https://leetcode.com/discuss/36955/simple-java-o-n-using-partition

PriorityQueue: O(N lg K) running time + O(K) memory
https://leetcode.com/discuss/36931/java-ac-solution-using-heap-or-priorityqueue
https://leetcode.com/discuss/37959/java-priorityqueue-o-n-log-n

shuffle input / Blum-Floyd-Pratt-Rivest-Tarjan algorithm: O(N) guaranteed running time + O(1) space
https://leetcode.com/discuss/36966/solution-explained
*/