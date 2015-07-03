/*
Design and implement a TwoSum class. It should support the following operations: add and find.
add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.
For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/

import java.util.HashMap;
import java.util.Map;
 
// case: add(0) -> find(0) -> should be false
public class TwoSumIII {
	Map<Integer, Integer> dict = new HashMap<Integer, Integer>();
	
	public void add(int number) {
		if (dict.containsKey(number)) {
			dict.put(number, dict.get(number) + 1);
		} else {
			dict.put(number, 1);
		}
	}
 
	public boolean find(int value) {
		for (Integer key : dict.keySet()) {
			if (value - key == key) {		// duplicate numbers in the key set, like {1,1,2,3,5}
				if (dict.get(key) >= 2) {
					return true;
				}
			} else if (dict.containsKey(value - key)) {
				return true;
			}
		}
	
		return false;
	}
		
}

/*
关键点在于对重复数字的处理，用hashmap存储值，然后判断value-num == num的情况下，count是否>=2.

Reference:
http://www.cnblogs.com/EdwardLiu/p/4252598.html
http://shanjiaxin.blogspot.com/2015/01/two-sum-iii-data-structure-design.html
*/