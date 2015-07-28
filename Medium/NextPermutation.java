/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

public class NextPermutation {
    public void nextPermutation(int[] num){
        if(num.length<2) return;
        int p=num.length-1;
        while(p>0&&num[p]<=num[p-1]) p--;				    // find the first p which is not in ascending order from the end
        if(p>0){
            int i=num.length-1;
            while(num[i]<=num[p-1]) i--;				    // find the first one greater than p
            swap(num,p-1,i);							          // remember it's p-1 here because it's the index
        }
        reverse(num,p,num.length-1);					      // reverse elements after p
    }
    private void swap(int[] num,int i,int j){ 			// use the private method to make the code cleaner
              int temp=num[i];
                num[i]=num[j];
                num[j]=temp;
    }
     
    private void reverse(int[] num,int i,int j){ 		// 夹逼方法reverse (Remember this!!!)	
        int start=i;
        int end=j;
               while(start<end){
               swap(num,start,end);
               start++;
               end--;
              }
    }
}

/*
Analysis:
- find the first element p from the end which breaks the descending order;
- swap p with the first element i which is greater than it from the end;
- reverse the array from p+1 to the end.

这道题是给定一个数组和一个排列，求下一个排列。算法上其实没有什么特别的地方，主要的问题是经常不是一见到这个题就能马上理清思路。
下面我们用一个例子来说明，比如排列是(2,3,6,5,4,1)，求下一个排列的基本步骤是这样：
1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
2) 接下来分两种情况：
    (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
    (2) 否则，如果p存在，从p开始往后找，找到下一个数就比p对应的数大的数字，然后两个调换位置，比如例子中的4。调换位置后得到(2,4,6,5,3,1)。最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。
以上方法中，最坏情况需要扫描数组三次，所以时间复杂度是O(3*n)=O(n)，空间复杂度是O(1)。

Reference:
https://yusun2015.wordpress.com/2015/01/25/next-permutation/
http://blog.csdn.net/linhuanmars/article/details/20434115
https://leetcodenotes.wordpress.com/2013/08/03/leetcode-next-permutation-%E7%94%A8%E5%90%8C%E6%A0%B7%E7%9A%84digit-set%E7%BB%84%E6%88%90%E7%9A%84immediate-next%E7%9A%84%E6%95%B0/
*/