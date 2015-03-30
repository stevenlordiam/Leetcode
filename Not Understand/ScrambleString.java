/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

public class ScrambleString {
    /*
       Second method: comes up with DP naturally
       f[n][i][j] means isScramble(s1[i: i+n], s2[j: j+n])
       f[n][i][j] = f[k][i][j] && f[n - k][i+k][j+k] || f[k][i][j+n-k] && f[n-k][i+k][j]
  
   */
    
    public boolean isScramble(String s1, String s2) {
        if( s1.length() != s2.length() ){
            return false;
        }
        
        if( s1.length() == 0 || s1.equals(s2)) { 	// same length
            return true;
        }
        
        int n = s1.length();
        boolean[][][] rst = new boolean[n][n][n];
        for(int i=0; i< n; i++){
            for(int j=0;j<n; j++){
                rst[0][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }
        
        for(int len = 2; len <= n; len++){
            for(int i = n - len; i>= 0; i--) {
                for(int j = n - len;  j>=0; j--){
                    boolean r = false;
                    for(int k = 1; k < len && r == false; k++){
                        r = (rst[k-1][i][j] && rst[len-k-1][i+k][j+k]) || (rst[k-1][i][j+len-k] && rst[len-k-1][i+k][j]);
                    }
                    rst[len-1][i][j] = r;
                }
            }
        }
        
        return rst[n-1][0][0];
     }
}    
        
/*
// Recursion method, exponential but trim lots of unnecessary recursion

   public boolean isScramble(String s1, String s2) {
       if( s1.length() != s2.length() ){
           return false;
       }
      
       if( s1.length() == 0 || s1.equals(s2)) {
           return true;
       }
      
       if(! sorted_sequence(s1).equals(sorted_sequence(s2)))  // this could trim lots of uncessary recursion
           return false;
      
       for(int i = 1; i <= s1.length() - 1; i++) {
           String s11 = s1.substring(0, i);
           String s12 = s1.substring(i);
           String s21 = s2.substring(0, i);
           String s22 = s2.substring(i);
           String s31 = s2.substring(0, s2.length()-i);
           String s32 = s2.substring(s2.length() - i);

           if( (isScramble(s11, s21) && isScramble(s12, s22)) ||
               (isScramble(s11, s32) && isScramble(s12, s31)) ) {
               return true;
           }
       }
       return false;
   }
  
   private String sorted_sequence(String s){
       char[] arr = s.toCharArray();
       Arrays.sort(arr);
       return new String(arr);
   }
  
*/

/*

Analysis:
This is a dynamical programming problem: the two words are scrambled if only if there is a cut for s1 and a cut for s2 such that the left part of s1is a scrambled of the left part of s2 and the right part is a scrambled of the right part of s2, 
or the left part of s1 is a scrambled of the right part of s2 and the right part is a scrambled of the left part of s2.
For matrix D[i][j][k], D[i][j][k] is true when the s1(i,i+k) is a scrambled string of s2(j,j+k), then
D[i][j][k] is true is only if D[i][j][l]&&D[i+l+1][j+l+1][k-l-1]) or (D[i][j+k-l][l]&&D[i+l+1][j][k-l-1] is true for some l<k and l>=0

// 
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if(s1.length()!=s2.length()) return false;
        if(s1.equals(s2)) return true;
        int n=s1.length();
        boolean[][][] D=new boolean[n][n][n];
        for(int i=n-1;i>-1;i--){
            for(int j=n-1;j>-1;j--){
                for(int k=0;i+k<n&&j+k<n;k++){
                    if(k==0){
                        D[i][j][k]=(s1.charAt(i)==s2.charAt(j));
                    }
                    else{
                        for(int l=0;l<k;l++){
                            if((D[i][j][l]&&D[i+l+1][j+l+1][k-l-1])||(D[i][j+k-l][l]&&D[i+l+1][j][k-l-1])){
                                D[i][j][k]=true;
                                break;
                            }
                        }
                    }
            }
          }
        }
        return D[0][0][n-1];
    }
}

这道题看起来是比较复杂的，如果用brute force，每次做切割，然后递归求解，是一个非多项式的复杂度，一般来说这不是面试官想要的答案
这其实是一道三维动态规划的题目，我们提出维护量res[i][j][n]，其中i是s1的起始字符，j是s2的起始字符，而n是当前的字符串长度，res[i][j][len]表示的是以i和j分别为s1和s2起点的长度为len的字符串是不是互为scramble
有了维护量我们接下来看看递推式，也就是怎么根据历史信息来得到res[i][j][len]。判断这个是不是满足，其实我们首先是把当前s1[i...i+len-1]字符串劈一刀分成两部分，然后分两种情况：
第一种是左边和s2[j...j+len-1]左边部分是不是scramble，以及右边和s2[j...j+len-1]右边部分是不是scramble；
第二种情况是左边和s2[j...j+len-1]右边部分是不是scramble，以及右边和s2[j...j+len-1]左边部分是不是scramble
如果以上两种情况有一种成立，说明s1[i...i+len-1]和s2[j...j+len-1]是scramble的。而对于判断这些左右部分是不是scramble我们是有历史信息的，因为长度小于n的所有情况我们都在前面求解过了（也就是长度是最外层循环）
上面说的是劈一刀的情况，对于s1[i...i+len-1]我们有len-1种劈法，在这些劈法中只要有一种成立，那么两个串就是scramble的
总结起来递推式是res[i][j][len] = || (res[i][j][k]&&res[i+k][j+k][len-k] || res[i][j+len-k][k]&&res[i+k][j][len-k]) 对于所有1<=k<len，也就是对于所有len-1种劈法的结果求或运算
因为信息都是计算过的，对于每种劈法只需要常量操作即可完成，因此求解递推式是需要O(len)（因为len-1种劈法）
如此总时间复杂度因为是三维动态规划，需要三层循环，加上每一步需要线行时间求解递推式，所以是O(n^4)。虽然已经比较高了，但是至少不是指数量级的，动态规划还是有很大有事的，空间复杂度是O(n^3)

Reference:
http://www.ninechapter.com/solutions/scramble-string/
http://blog.csdn.net/linhuanmars/article/details/24506703
https://yusun2015.wordpress.com/2015/01/15/scramble-string/
*/