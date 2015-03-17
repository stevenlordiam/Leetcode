/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

public class TwoSum {
	    public int[] twoSum(int[] numbers, int target) {
	    	if(numbers == null || numbers.length < 2) {
	    		return null;
	    	}               //checking for null and one-element array;
	        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();        //use hashmap to store the data;
	        for(int i=0; i<numbers.length; i++){
	            hashmap.put(numbers[i], i+1);   
	        }       
	        
	        int[] index = new int[2];
	        
	        for(int i=0; i<numbers.length ; i++){
	            if ( hashmap.containsKey( target - numbers[i] )){
	                int index1 = i+1;
	                int index2 = hashmap.get(target - numbers[i]);
	                if (index1 == index2){
	                    continue;
	                }
	                index[0] = index1;
	                index[1] = index2;
	                return index;
	            }
	        }
	        return index;
	    }
}

/* 
Similar to CC150 (17-12) -> 先排序后查找！！！

Note:
Pay attention to the time complexity!
Cannot use direct adding like: if(numbers[i]+numbers[j]==target), 
because the time complexity is O(n^2)

Solution:
O(n^2) runtime, O(1) space – Brute force:
The brute force approach is simple. Loop through each element x and find if there is another value that equals to target – x. 
As finding another value requires looping through the rest of array, its runtime complexity is O(n^2).

O(n) runtime, O(n) space – Hash table:
We could reduce the runtime complexity of looking up a value to O(1) using a hash map that maps a value to its index.

*/