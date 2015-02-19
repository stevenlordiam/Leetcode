/*
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if(haystack.equals(needle)||needle.length()==0) return 0;
        if(needle.length()>haystack.length()) return -1;
        for(int i=0;i<haystack.length()-needle.length()+1;i++){
            for(int j=0;j<needle.length();j++){
                if(haystack.charAt(i+j)!=needle.charAt(j)) break; 		// if not equal, jump out of loop, otherwise j increases until the end(needle.length()-1), in this case, it's matched
                if(j==needle.length()-1) return i;			// i is the index
            }
        }
        return -1;
    }
}

/*
Find if one string is the substring of other string
Need to handle the corner case, strStr(Any String.””)  return 0

O(nm) runtime, O(1) space – Brute force:
You could demonstrate to your interviewer that this problem can be solved using known efficient algorithms 
such as Rabin-Karp algorithm, KMP algorithm, and the Boyer-Moore algorithm. Since these algorithms are usually studied in an advanced algorithms class, 
it is sufficient to solve it using the most direct method in an interview – The brute force method.

The brute force method is straightforward to implement. We scan the needle with the haystack from its first position and start matching all subsequent letters one by one. 
If one of the letters does not match, we start over again with the next position in the haystack.

The key is to implement the solution cleanly without dealing with each edge case separately.

Brute force: let the length of original string be n, the length of compared string be m. We can
compare every substring whose length is m of the orginal string to see if they are equal. 
In total there are (n-m+1) substring. Time complexity is O((n-m+1)*m)=O(n*m), space complexity is O(1)

Reference:
http://www.ninechapter.com/solutions/implement-strstr/
https://yusun2015.wordpress.com/2015/01/15/implement-strstr/
(rolling hash method)http://blog.csdn.net/linhuanmars/article/details/20276833
*/