/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

public class MaximumGap {
    public int maximumGap(int[] num) {
        if(num.length<2) return 0;
        int max=num[0];
        int min=num[0];
        for(int i=1;i<num.length;i++){
            if(num[i]<min) min=num[i];
            if(num[i]>max) max=num[i];
        }
        Integer[] left=new Integer[num.length];
        Integer[] right=new Integer[num.length];
         for(int i=0;i<num.length;i++){
             int ind=(int)Math.floor((double) (num[i]-min)*(num.length-1)/(max-min));   // index, num of bucket
             if(left[ind]!=null){
                 left[ind]=Math.min(left[ind],num[i]);
                 right[ind]=Math.max(right[ind],num[i]);
             } 
             else{
                left[ind]=num[i]; 
                right[ind]=num[i];
             } 
         }
         int pre=min,res=0;
         for(int i=0;i<left.length;i++){
             if(left[i]!=null){
                 res=Math.max(left[i]-pre,res);
                 pre=right[i];
             }
         }
         return res;
    }
}

/*
Radix sort / bucket sort -> O(N)
ordinary sorting cannot do better than O(NlogN)

ceiling向上取整，floor向下取整

Bucket Sort Solution:
- Suppose there are N elements and they range from A to B.
- Then the maximum gap will be no smaller than ceiling[(B - A) / (N - 1)]
- Let the length of a bucket to be len = ceiling[(B - A) / (N - 1)], then we will have at most num = (B - A) / len + 1 of bucket
- For any number K in the array, we can easily find out which bucket it belongs by calculating loc = (K - A) / len and therefore maintain the maximum and minimum elements in each bucket.
- Since the maximum difference between elements in the same buckets will be at most len - 1, so the final answer will not be taken from two elements in the same buckets.
- For each non-empty buckets p, find the next non-empty buckets q, then q.min - p.max could be the potential answer to the question. Return the maximum of all those values.

Analysis:
- Assume Max and Min are the maximum and minimum of the array,  then divide the array into n-1 buckets with length of (Max-Min)/(n-1);
- Find the most left and right point for each bucket;
- The maximum distance between different buckets is the the maximum difference.

Analysis:
We all know that, merge, heap or quick sorting algorithm can achieve no better than O(n Log n) time complexity. But for linear time requirement, bucket sort, counting sort and radix sort are often good options.
For this specific problem, bucket sort is not a good option because it usually works well on uniform distributions. Otherwise, in each bucket, the insertion sort may cause heavy burden on time complexity. 
Counting sort, has time complexity O(n + k), where k is the range of the elements. But when k > n^2, it is worse than the common sorting algorithms. So we use the radix sort for solving this problem.

To sort array according to each digit, counting sort is used. Note that, we only need to have a array of size 10 to store the frequencies of elements. This is because we only consider and sort a single digit in each iteration of Radix sort. 
The general form of counting sort is:
(1) Count the frequencies of each elements.  (count[a[i]] ++, this also considers the duplicates)
(2) Get the index of each number in the output array. (count[i]+= count[i-1])
(3) Put numbers in order. (output[count[a[i]] = a[i], count[a[i]]-- to handle the duplicates)

Reference:
https://yusun2015.wordpress.com/2015/01/14/maximum-gap/
http://yucoding.blogspot.com/2014/12/leetcode-question-maximum-gap.html
http://blog.csdn.net/u012162613/article/details/41936569
http://blog.csdn.net/u011345136/article/details/41963051
*/