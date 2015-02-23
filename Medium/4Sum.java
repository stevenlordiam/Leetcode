/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/

public class 4Sum {
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(num); 		// sort first

		for (int i = 0; i < num.length - 3; i++) {
			if (i != 0 && num[i] == num[i - 1]) {
				continue; 		// skip duplicates
			}

			for (int j = i + 1; j < num.length - 2; j++) {
				if (j != i + 1 && num[j] == num[j - 1])
					continue; 	// skip duplicates

				int left = j + 1;
				int right = num.length - 1;
				while (left < right) {
					int sum = num[i] + num[j] + num[left] + num[right];
					if (sum < target) {
						left++;
					} else if (sum > target) {
						right--;
					} else {	// sum == target
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(num[i]);
						tmp.add(num[j]);
						tmp.add(num[left]);
						tmp.add(num[right]);
						rst.add(tmp);
						left++;
						right--;
						while (left < right && num[left] == num[left - 1]) {
							left++;
						}
						while (left < right && num[right] == num[right + 1]) {
							right--;
						}
					}
				}
			}
		}
		return rst;
	}
}

/*
Similar to 2 sum, 3 sum, 3 sum closest

方法一：
夹逼方法（类似3 sum)
方法二：
二分法，先求一次两两pair的值，再求2 sum。算法复杂度O(n^3) -> O(n^2*logn)

(http://blog.csdn.net/linhuanmars/article/details/24826871)
这道题要求跟3Sum差不多，只是需求扩展到四个的数字的和了。我们还是可以按照3Sum中的解法，只是在外面套一层循环，相当于求n次3Sum。
我们知道3Sum的时间复杂度是O(n^2)，所以如果这样解的总时间复杂度是O(n^3)。上述这种方法比较直接，根据3Sum的结果很容易进行推广。
那么时间复杂度能不能更好呢？其实我们可以考虑用二分法的思路，如果把所有的两两pair都求出来，然后再进行一次Two Sum的匹配，
我们知道Two Sum是一个排序加上一个线性的操作，并且把所有pair的数量是O((n-1)+(n-2)+...+1)=O(n(n-1)/2)=O(n^2)。
所以对O(n^2)的排序如果不用特殊线性排序算法是O(n^2*log(n^2))=O(n^2*2logn)=O(n^2*logn)，算法复杂度比上一个方法的O(n^3)是有提高的。
思路虽然明确，不过细节上会多很多情况要处理。首先，我们要对每一个pair建一个数据结构来存储元素的值和对应的index，
这样做是为了后面当找到合适的两对pair相加能得到target值时看看他们是否有重叠的index，如果有说明它们不是合法的一个结果，因为不是四个不同的元素。
接下来我们还得对这些pair进行排序，所以要给pair定义comparable的函数。最后，当进行Two Sum的匹配时因为pair不再是一个值，
所以不能像Two Sum中那样直接跳过相同的，每一组都得进行查看，这样就会出现重复的情况，所以我们还得给每一个四个元素组成的tuple定义hashcode和相等函数，
以便可以把当前求得的结果放在一个HashSet里面，这样得到新结果如果是重复的就不加入结果集了。第二种方法比第一种方法时间上还是有提高的，
其实这道题可以推广到k-Sum的问题，基本思想就是和第二种方法一样进行二分，然后两两结合。

Reference:
http://www.ninechapter.com/solutions/4sum/
https://leetcodenotes.wordpress.com/2013/10/18/leetcode-4sum-%E5%9B%9B%E4%B8%AA%E6%95%B0%E5%8A%A0%E5%92%8C%E7%AD%89%E4%BA%8Ex%EF%BC%8C%E4%B8%8D%E8%AE%B8%E9%87%8D%E5%A4%8D/
https://yusun2015.wordpress.com/2015/02/05/3-sum/
http://blog.csdn.net/linhuanmars/article/details/24826871
*/