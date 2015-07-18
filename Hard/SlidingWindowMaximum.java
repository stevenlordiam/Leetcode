/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered
*/

public class SlidingWindowMaximum {
    public  int[] maxSlidingWindow(int[] input, int windowSize){
        if(input.length == 0) return input;
        PriorityQueue queue = new PriorityQueue(windowSize, Collections.reverseOrder()); 
        // put it in reverse order to implement max heap
        int [] maxArray = new int[input.length - windowSize + 1]; // result
        for (int i = 0; i < windowSize; i++) {
            queue.add(input[i]);
        }
        maxArray[0] = (int) queue.peek();
        for (int i = windowSize ; i < input.length; i++) {
            queue.remove(input[i-windowSize]); // remove left
            queue.add(input[i]); // add right
            maxArray[i-windowSize+1] = (int) queue.peek(); // put the max to the result array
            // System.out.println("Min : " + queue.peek());
        }
        return maxArray;
    }
}

/*
see the full solution in ~/Dropbox/面经/已面/Pocket Gems/untitled folder/SlideWindow.java
*/