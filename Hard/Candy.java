/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/

public class Candy {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0) {
            return 0;
        }

        int[] count = new int[ratings.length];
        Arrays.fill(count, 1);
        int sum = 0;
        for(int i = 1; i < ratings.length; i++) {   // 从左往右
            if(ratings[i] > ratings[i - 1]) {       // 如果右大于左，右加一
                count[i] = count[i - 1] + 1;
            }
        }

        for(int i = ratings.length - 1; i >= 1; i--) {  // 从右往左
            sum += count[i];
            if(ratings[i - 1] > ratings[i] && count[i - 1] <= count[i]) {  // second round has two conditions
                count[i-1] = count[i] + 1;              // 如果左大于右 而且 左数小于右数，左加一
            }
        }
        sum += count[0];
        return sum;
    }
}

/*
一维DP
d[i] 是给第i个小孩最少几块糖
- rank[i] > rank[i – 1]，必须比前一个多给一块，d[i] = d[i – 1] + 1
- rank[i] == rank[i – 1]，两个排名一样，第二个就给一块就行了, d[i] = 1
- rank[i] < rank[i – 1]，比上一个排名低，应该少给一块，但是若上一个已经只给一块了，就得往前推一个一个多给。推到什么时候为止呢？若排名比下一个高，糖还一样多，就得再给；直到这个关系打破（排名一样或比下一个还低，或是糖已经满足关系）就不用再往前推了

这道题用到的思路和Trapping Rain Water是一样的，用动态规划。基本思路就是进行两次扫描，一次从左往右，一次从右往左。第一次扫描的时候维护对于每一个小孩左边所需要最少的糖果数量，存入数组对应元素中，第二次扫描的时候维护右边所需的最少糖果数，并且比较将左边和右边大的糖果数量存入结果数组对应元素中
这样两遍扫描之后就可以得到每一个所需要的最最少糖果量，从而累加得出结果。方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。空间上需要一个长度为n的数组，复杂度是O(n)
这种两边扫描的方法是一种比较常用的技巧，LeetCode中Trapping Rain Water和这道题都用到了，可以把这种方法作为自己思路的一部分，通常是要求的变量跟左右元素有关系的题目会用到

Analysis:
- use an array candy to store the number of candies needed to put at i;
- in the first scan, if ratings[i]>ratings[i-1], then put candy[i]=candy[i-1]+1, else put 1 candy at i;
- in the second scan, if ratings[i]>ratings[i+1], then put candy[i]=max(candy[i],candy[i+1]+1);
- scan candy and find total number of candies.
public class Solution {
    public int candy(int[] ratings) {
        if(ratings.length<2) return ratings.length;
        int[] candy=new int[ratings.length];
        candy[0]=1;
        for(int i=1;i<ratings.length;i++){
            if(ratings[i]>ratings[i-1]) candy[i]=candy[i-1]+1;
            else candy[i]=1;
        }
        for(int i=ratings.length-2;i>-1;i--){
            if(ratings[i]>ratings[i+1]) 
            candy[i]=Math.max(candy[i+1]+1,candy[i]);
        }
        int res=0;
        for(int i=0;i<candy.length;i++) res=res+candy[i];
        return res;
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/08/leetcode-candy-%E7%BB%99%E5%B0%8F%E5%AD%A9%E5%AD%90%E5%88%86%E7%B3%96/
http://www.ninechapter.com/solutions/candy/
http://blog.csdn.net/linhuanmars/article/details/21424783
https://yusun2015.wordpress.com/2015/01/25/candy/
*/