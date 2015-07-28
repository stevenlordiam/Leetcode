/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[n];
        k = k - 1;
        int factor = 1; 
        for (int i = 1; i < n; i++) {   // (n-1)阶乘
            factor *= i;
        }
        for (int i = 0; i < n; i++) {
            int index = k / factor;     // k/(n-1)! 可以取得最高位在数列中的index
            k = k % factor;             // 当前的k进行(n-1)!取余，得到的数字就是当前剩余数组的index，如此就可以得到对应的元素
            for (int j = 0; j < n; j++) {
                if (used[j] == false) {
                    if (index == 0) {
                        used[j] = true;
                        sb.append((char) ('0' + j + 1));    // use '0' to convert int to char
                        break;
                    } else {
                        index--;        // ???
                    }
                }
            }
            if (i < n - 1) {
                factor = factor / (n - 1 - i);
            }
        }
        return sb.toString();
    }
}

/*
Backtracking

几个要点：
- 找没用过的里面的第(k – 1) / (n – 1)!个数字，放第一位上
- k = (k – 1) % (n – 1)!，第一个数字确定了，剩下这些里面重新找第k个。
- n每次-1，比如第一位确定后有(n-1)!种后面数字的排法，第二位也确定了后面的数字排法就又少了一位(n – 1 – 1)!

这道题目算法上没有什么特别的，更像是一道找规律的数学题目。我们知道，n个数的permutation总共有n阶乘个，基于这个性质我们可以得到某一位对应的数字是哪一个
思路是这样的，比如当前长度是n，我们知道每个相同的起始元素对应(n-1)!个permutation，也就是(n-1)!个permutation后会换一个起始元素
因此，只要当前的k进行(n-1)!取余，得到的数字就是当前剩余数组的index，如此就可以得到对应的元素。如此递推直到数组中没有元素结束
实现中我们要维护一个数组来记录当前的元素，每次得到一个元素加入结果数组，然后从剩余数组中移除，因此空间复杂度是O(n)
时间上总共需要n个回合，而每次删除元素如果是用数组需要O(n),所以总共是O(n^2)。这里如果不移除元素也需要对元素做标记，所以要判断第一个还是个线性的操作
上面代码还有有个小细节，就是一开始把k--，目的是让下标从0开始，这样下标就是从0到n-1，不用考虑n时去取余，更好地跟数组下标匹配

题解：(http://www.cnblogs.com/springfor/p/3896201.html)
发现数学规律，首先先捋捋这道题要干啥，给了我们n还有k，在数列 1，2，3，... , n构建的全排列中，返回第k个排列
题目告诉我们：对于n个数可以有n!种排列；那么n-1个数就有(n-1)!种排列
那么对于n位数来说，如果除去最高位不看，后面的n-1位就有 (n-1)!种排列
所以，还是对于n位数来说，每一个不同的最高位数，后面可以拼接(n-1)!种排列，所以你就可以看成是按照每组(n-1)!个这样分组。 
利用 k/(n-1)! 可以取得最高位在数列中的index。这样第k个排列的最高位就能从数列中的index位取得，此时还要把这个数从数列中删除
然后，新的k就可以有k%(n-1)!获得。循环n次即可，同时，为了可以跟数组坐标对其，令k先--。

Analysis: 
The set with n elements contains a total of n! unique permutations, so the first digit is the k/(n-1)! elements, do it recursively and find all the digits
public class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder str=new StringBuilder(),res=new StringBuilder();
        int f=1;
        for(int i=1;i<n+1;i++){
            str.append(i);
            f=f*i;
        }
        k=k-1;
        while(str.length()>0){
            f=f/str.length();
            res.append(str.charAt((k/f)));
            str.deleteCharAt(k/f);
            k=k%f;
        }
        return res.toString();
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/20/leetcode-permutation-sequence-%E7%BB%99%E4%B8%AAn%EF%BC%8Ck%EF%BC%8C%E6%89%BE%E7%AC%ACk%E4%B8%AAn%E4%B8%AAdigit%E7%BB%84%E6%88%90%E7%9A%84%E6%95%B0%E5%AD%97%EF%BC%8C%E5%A6%82123132213/
http://www.ninechapter.com/solutions/permutation-sequence/
http://blog.csdn.net/linhuanmars/article/details/22028697
https://yusun2015.wordpress.com/2015/01/13/permutation-sequence/
http://www.cnblogs.com/springfor/p/3896201.html
http://www.lifeincode.net/programming/leetcode-permutation-sequence-java/
http://blog.csdn.net/fightforyourdream/article/details/17483553
http://fisherlei.blogspot.com/2013/04/leetcode-permutation-sequence-solution.html
http://blog.csdn.net/u013027996/article/details/18405735
http://yucoding.blogspot.com/2013/04/leetcode-question-68-permutation.html
http://my.oschina.net/jdflyfly/blog/284496
*/