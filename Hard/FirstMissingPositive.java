/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

public class FirstMissingPositive {
    public int firstMissingPositive(int[] A) {
        if(A==null || A.length==0){
            return 1;
        }
        for(int i=0;i<A.length;i++){
            if(A[i]<=A.length && A[i]>0 && A[A[i]-1]!=A[i]){
                int temp = A[A[i]-1];
                A[A[i]-1] = A[i];
                A[i] = temp;
                i--;
            }
        }
        for(int i=0;i<A.length;i++){
            if(A[i]!=i+1)
                return i+1;
        }
        return A.length+1;
    }
}

/*
给一个无序int array，有正有负，找第一个missing正整数。比如[3,4,-1,1] return 2.要求O(n)时间，O(1)空间
思路：
- 要求这么高，还不让用空间换时间，说明不是dp，所以基本只让过一两遍数组，一边过一遍直接in place的改动数组（不让生成新数组）
- 既然是大部分不missing，所以可以用index来直接和元素产生关系
- 试图让A[i]这个值x的index是x – 1，即每个index身上的元素都是index本身+1。所以{1 2 3}就是理想中的新数组，{1 5 3}就说明缺2。
算法：
- 如果A[i]不在自己该在的地方，就想办法把他换到该在的地方。如果A[i]是<=0或者A[i]比数组长度还大，说明没有它该在的地方，那就skip他，弄下一个（不用担心当前位置被它占了，如果后面有想在这个位置的正整数，它会被换走的）
- A[i]和A[A[i] – 1]换，然后继续回到i，接着换，直到第一种情况满足。但是如果这俩数一样，换也没用，就别原地打转了
- 最后过一遍shift过的array，第一个不在原位的就是

思路：交换数组元素，使得数组中第i位存放数值(i+1)。最后遍历数组，寻找第一个不符合此要求的元素，返回其下标。整个过程需要遍历两次数组，复杂度为O(n)

这道题要求用线性时间和常量空间，思想借鉴到了Counting sort(http://en.wikipedia.org/wiki/Counting_sort)中的方法。既然不能用额外空间，那就只有利用数组本身，跟Counting sort一样，利用数组的index来作为数字本身的索引，把正数按照递增顺序依次放到数组中
即让A[0]=1, A[1]=2, A[2]=3, ... , 这样一来，最后如果哪个数组元素违反了A[i]=i+1即说明i+1就是我们要求的第一个缺失的正数。对于那些不在范围内的数字，我们可以直接跳过，比如说负数，0，或者超过数组长度的正数，这些都不会是我们的答案
实现中还需要注意一个细节，就是如果当前的数字所对应的下标已经是对应数字了，那么我们也需要跳过，因为那个位置的数字已经满足要求了，否则会出现一直来回交换的死循环。这样一来我们只需要扫描数组两遍，时间复杂度是O(2*n)=O(n)，而且利用数组本身空间，只需要一个额外变量，所以空间复杂度是O(1)

Analysis: 
We need the positive numbers on their right position, so that means we need i=A[i]-1, if not we put A[i]-1 at the the A[i]-1 positions.
- if A[i]==i+1 or A[i]<=0 or A[i]>A.length continue;
- if A[i]!=i+1, swap i with A[i]-1;
- Because  the array may have duplicates so do 2 only when A[i]-1 position do not have the right number A[i].
public class Solution {
    public int firstMissingPositive(int[] A) {
      int i=0;
      while(i<A.length){
          if(A[i]<=0||A[i]>A.length||A[i]==i+1) i++;
          else{
             if(A[A[i]-1]!=A[i]) swap(A,i,A[i]-1);
             else i++;
          }
      }
      for(int j=0;j<A.length;j++){
          if(A[j]!=j+1) return j+1;
      }
      return A.length+1;
    }
    public void swap(int[] A,int i,int j){
        int temp=A[i];
        A[i]=A[j];
        A[j]=temp;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/07/17/first-missing-positive/
http://www.cnblogs.com/AnnieKim/archive/2013/04/21/3034631.html
http://www.ninechapter.com/solutions/first-missing-positive/
http://blog.csdn.net/linhuanmars/article/details/20884585
https://yusun2015.wordpress.com/2015/01/15/first-missing-positive/
http://blog.csdn.net/kenden23/article/details/17099987
*/