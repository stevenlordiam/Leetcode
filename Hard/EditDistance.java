/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[][] dp = new int[n+1][m+1];
        for(int i=0; i< m+1; i++){
            dp[0][i] = i; 
        }
        for(int i=0; i<n+1; i++){
            dp[i][0] = i;
        }
        
        for(int i = 1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1])); 	// dp, one step
                }
            }
        }
        return dp[n][m];
    }
}

/*
将一个字符串变成另外一个字符串所用的最少操作数，每次只能增加、删除或者替换一个字符。如lamb -> slave
这是经典二维DP题。希望做完这个能掌握二维dp要领，因为实在是不够直观
思路
- 状态：d[i][j]表示将A的0~i这个substring转化成b的0~j这个substring要的最少次数。
- 所以在二维递归的时候，我们一直有三个已经算好的值：（假设 i = m, j = v，即想算lam->slav)
  - d[i-1][j-1]: A, B不算当前char，把A转成B最少要几步 (la -> sla)
  - d[i-1][j]: A不算当前char，转成B最少要几步 (la -> slav)
  - d[i][j – 1]: A转成B比当前char少一个，最少要几步 (lam -> sla)
- 这样的话，怎么由介三个推d[i][j]呢？
  - (la -> sla)已经算好了，所以看m和v是不是同一个字符，是的话，直接d[i][j]就=d[i-1][j-1]；不是的话，相当于替换第m成v，lam就能变成slav了，即步数+1。
  - (la -> slav)已经算好，所以现在处理lam怎么办呢？删一个m成la就行了，因为已经知道怎么把la变成slav了。即步数+1。
  - (lam -> sla)已经算好，所以后面加一个v就行了，lamv就能变成slav了，即步数+1。
- 二维dp一定要先写出来已经有什么了，并且有的这三个东西的物理意义。这种求最小的，肯定是dp的，就别花时间自我怀疑了
int minDistance(String word1, String word2) {
	int[][] d = new int[word1.length()][word2.length()];
	boolean hadSameInA = false;
	boolean hadSameInB = false;
	if (word1.charAt(0) == word2.charAt(0)) {
		d[0][0] = 0;
 		hadSameInA = true;
 		hadSameInB = true;
 	} else {
 		d[0][0] = 1;
 	}	//do the same for string B. 但是别人做的都是直接assign i,不考虑有相同的情况。那是怎么回事？
	for (int i = 1; i < word1.length(); i++) {
  		for (int j = 1; j < word2.length(); j++) {
  			if (word1.charAt(i) == word2.charAt(j))
   				d[i][j] = d[i - 1][j - 1];
  			else {
   				d[i][j] = Math.min(d[i - 1][j - 1] + 1, Math.min(d[i][j - 1] + 1, d[i - 1][j] + 1));
 			}
 	}
	return d[word1.length - 1][word2.length - 1];
}

这道题求一个字符串编辑成为另一个字符串的最少操作数，操作包括添加，删除或者替换一个字符。这道题难度是比较大的，用常规思路出来的方法一般都是brute force，而且还不一定正确
这其实是一道二维动态规划的题目，模型上确实不容易看出来，下面我们来说说递推式
我们维护的变量res[i][j]表示的是word1的前i个字符和word2的前j个字符编辑的最少操作数是多少。假设我们拥有res[i][j]前的所有历史信息，看看如何在常量时间内得到当前的res[i][j]，我们讨论两种情况：
1）如果word1[i-1]=word2[j-1]，也就是当前两个字符相同，也就是不需要编辑，那么很容易得到res[i][j]=res[i-1][j-1]，因为新加入的字符不用编辑；
2）如果word1[i-1]!=word2[j-1]，那么我们就考虑三种操作，如果是插入word1，那么res[i][j]=res[i-1][j]+1，也就是取word1前i-1个字符和word2前j个字符的最好结果，然后添加一个插入操作；
如果是插入word2，那么res[i][j]=res[i][j-1]+1，道理同上面一种操作；如果是替换操作，那么类似于上面第一种情况，但是要加一个替换操作（因为word1[i-1]和word2[j-1]不相等），所以递推式是res[i][j]=res[i-1][j-1]+1
上面列举的情况包含了所有可能性，有朋友可能会说为什么没有删除操作，其实这里添加一个插入操作永远能得到与一个删除操作相同的效果，所以删除不会使最少操作数变得更好，因此如果我们是正向考虑，则不需要删除操作。取上面几种情况最小的操作数，即为第二种情况的结果，即res[i][j] = min(res[i-1][j], res[i][j-1], res[i-1][j-1])+1
接下来就是分析复杂度，算法时间上就是两层循环，所以时间复杂度是O(m*n)，空间上每一行只需要上一行信息，所以可以只用一维数组即可，我们取m, n中小的放入内层循环，则复杂度是O(min(m,n))
public int minDistance(String word1, String word2) {
    if(word1.length()==0)
        return word2.length();
    if(word2.length()==0)
        return word1.length();
    String minWord = word1.length()>word2.length()?word2:word1;
    String maxWord = word1.length()>word2.length()?word1:word2;
    int[] res = new int[minWord.length()+1];
    for(int i=0;i<=minWord.length();i++)
    {
        res[i] = i;
    }
    for(int i=0;i<maxWord.length();i++)
    {
        int[] newRes = new int[minWord.length()+1];
        newRes[0] = i+1;
        for(int j=0;j<minWord.length();j++)
        {
            if(minWord.charAt(j)==maxWord.charAt(i))
            {
                newRes[j+1]=res[j];
            }
            else
            {
                newRes[j+1] = Math.min(res[j],Math.min(res[j+1],newRes[j]))+1;
            }
        }
        res = newRes;
    }
    return res[minWord.length()];
}
上面代码用了minWord, maxWord是为了使得空间是min(m,n)，细节做得比较细，面试的时候可能不用刻意这么做，提一下就好
这道题目算是难度比较大的题目，所以在短时间的面试可能时间太紧了，所以也有变体。我自己在面试Google的时候，问的是如何判断edit distance是不是在1以内，返回true或false就可以了
这样一改其实就没有必要动态规划了，只需要利用距离只有1这一点进行判断就可以，大概思路就是只要有一个不同，接下来就不能再有不同了

Analysis:
Let D[i][j] be the minimum number of steps converting word(0,i) to word(0,j), then
- if word1[i]==word1[j], D[i][j]=D[i-1][j-1];
- otherwise D[i][j]=min{D[i-1][j-1]+1,D[i-1][j]+1,D[i][j-1]+1};
public class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        if(word1.length()==0||word2.length()==0) return Math.max(word1.length(),word2.length());
        int m=word1.length(),n=word2.length();
        int[] D=new int[n+1];
        for(int i=0;i<=n;i++) D[i]=i;
        for(int i=1;i<=m;i++){
            int[] temp=new int[n+1];
            temp[0]=i;
            for(int j=1;j<=n;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    temp[j]=D[j-1];
                }
                else{
                    temp[j]=Math.min(D[j-1]+1,Math.min(D[j],temp[j-1])+1);
                }
            }
            D=temp;
        }
       return D[n];
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/07/16/edit-distance/
http://www.ninechapter.com/solutions/edit-distance/
http://blog.csdn.net/linhuanmars/article/details/24213795
https://yusun2015.wordpress.com/2015/01/16/edit-distance/
*/