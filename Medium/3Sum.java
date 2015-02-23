/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/

public class 3Sum {
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
		if(num == null || num.length < 3) {			
			return rst;
		}
		Arrays.sort(num); 		// sort first
		for (int i = 0; i < num.length - 2; i++) {
			if (i != 0 && num[i] == num[i - 1]) {
				continue; // to skip duplicate numbers; e.g [0,0,0,0] 	(REMEMBER the use of continue here)
			}
			int left = i + 1;	// 夹逼方法
			int right = num.length - 1;
			while (left < right) {
				int sum = num[left] + num[right] + num[i];
				if (sum == 0) {
					ArrayList<Integer> tmp = new ArrayList<Integer>();
					tmp.add(num[i]);
					tmp.add(num[left]);
					tmp.add(num[right]);
					rst.add(tmp);
					left++;
					right--;
					while (left < right && num[left] == num[left - 1]) { // to skip duplicates
						left++;
					}
					while (left < right && num[right] == num[right + 1]) { // to skip duplicates
						right--;
					}
				} else if (sum < 0) { 		// 夹逼方法从左右分别接近，因为先排序了所以小于0时应该增大，left应该右移
					left++;
				} else {
					right--;
				}
			}
		}
		return rst;
	}
}

/*
Similar to 2Sum
1. 用continue跳过duplicate
2. 用夹逼的方法分别从左右接近
3. 先排序再相加(THIS IS IMPORTANT!!!)
4. 记得要去重，还有找到sum=0后要移动两个指针 left 和 right

continue:
break的作用是跳出当前循环块（for、while、do while）或程序块（switch）。在循环块中的作用是跳出当前正在循环的循环体。在程序块中的作用是中断和下一个case条件的比较。
continue用于结束循环体中其后语句的执行，并跳回循环程序块的开头执行下一次循环，而不是立刻循环体。中断本次循环开始下一次。
http://lavasoft.blog.51cto.com/62575/52685/
http://blog.csdn.net/luoweifu/article/details/10756017

如何处理“和上一个一样就不要做了”这种duplicate情况？
if (i > 0 或类似condition && num[i] == num[i – 1]) continue;
这样可以保证永远笼罩在while (some condition)的保证之下，用continue就不会出现越界等情况。
如果是递归，外层没有while，则用while (boundary &&  num[i] == num[i – 1[) {i++;}

这道题是Two Sum的扩展，brute force时间复杂度为O(n^3), 对每三个数进行比较。这道题和Two Sum有所不同，
使用哈希表的解法并不是很方便，因为结果数组中元素可能重复，如果不排序对于重复的处理将会比较麻烦，因此这道题一般使用排序之后夹逼的方法，
总的时间复杂度为O(n^2+nlogn)=(n^2),空间复杂度是O(n),注意，在这里为了避免重复结果，对于已经判断过的数会skip掉，这也是排序带来的方便。 
这道题考察的点其实和Two Sum差不多，Two Sum是3Sum的一个subroutine, 不过更加综合一些，实现上更加全面，需要注意细节，此题更加复杂的扩展是4Sum。

Reference:
http://www.ninechapter.com/solutions/3sum/
https://leetcodenotes.wordpress.com/2013/10/18/leetcode-3sum-%E4%B8%89%E4%B8%AA%E6%95%B0%E5%8A%A0%E5%92%8C%E7%AD%89%E4%BA%8E0%EF%BC%8C%E8%A6%81%E6%B1%82%E6%97%A0%E9%87%8D%E5%A4%8D/
http://blog.csdn.net/linhuanmars/article/details/19711651
https://yusun2015.wordpress.com/2015/02/05/3-sum/
*/