/*
Given two strings S and T, determine if they are both one edit distance apart.
*/

public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
		for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
    		if (s.charAt(i) != t.charAt(i)) {
        		return s.substring(i + (s.length() >= t.length() ? 1 : 0)).equals(t.substring(i + (s.length() <= t.length() ? 1 : 0)));
    		}
		}
		return Math.abs(s.length() - t.length()) == 1;
	}
}

/*

// version 1
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m=s.length(),n=t.length();
        if(Math.abs(m-n)>1) return false;
        if(m==n){
            int d=0;
            for(int i=0;i&lt;m;i++){
                if(s.charAt(i)!=t.charAt(i)){
                    d++;
                    if(d>1) return false;
                }
            }
            if(d==0) return false;
        } else {
            for(int i=0;i<Math.min(m,n);i++){
                if(s.charAt(i)!=t.charAt(i)){
                    return m>n?s.substring(i+1).equals(t.substring(i)):t.substring(i+1).equals(s.substring(i));
                }
            }
        }
        return true;
    }
}

// version 2
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m=s.length(),n=t.length();
        for(int i=0;i<Math.min(m,n);i++){
            if(s.charAt(i)!=t.charAt(i)){
                if(m==n) return s.substring(i+1).equals(t.substring(i+1));
                else if(m>n) return s.substring(i+1).equals(t.substring(i));
                else return s.substring(i).equals(t.substring(i+1));
            }
        }
        return Math.abs(m-n)==1;
    }
}

Reference:
http://blog.csdn.net/u013325815/article/details/41846191
https://yusun2015.wordpress.com/2015/01/28/one-edit-distance/
https://leetcode.com/discuss/20038/my-solutions-in-3-languages-with-one-for-loop
https://leetcode.com/discuss/17711/recursion-solution-in-java
http://chaocharleswang.blogspot.com/2015/01/leetcode-161-one-edit-distance.html
*/