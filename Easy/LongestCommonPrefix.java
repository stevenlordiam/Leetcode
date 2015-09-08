/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

public class LongestCommonPrefix {
    // 1. Method 1, start from the first one, compare prefix with next string, until end;
    // 2. Method 2, start from the first char, compare it with all string, and then the second char
    // I am using method 1 here
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++) {
            int j = 0;
            while( j < strs[i].length() && j < prefix.length() && strs[i].charAt(j) == prefix.charAt(j)) {
                j++;
            }
            if( j == 0) {
                return "";
            }
            prefix = prefix.substring(0, j);
        }
        return prefix;
    }

    // public String longestCommonPrefix(String[] strs) {
    //     StringBuilder sb = new StringBuilder();
    //     if(strs.length==0 || strs==null){     // if empty, return "" which is empty but NOT null !
    //         return sb.toString();
    //     }
    //     boolean isSame = true;
    //     int len = 0;        // the pointer
    //     while(isSame){
    //         for(int i=0; i<strs.length; i++){
    //             if(strs[i].length()<=len || strs[i].charAt(len)!=strs[0].charAt(len)){      // for the case not the same: str[i].length<len or not the same
    //                 isSame = false;
    //                 break;          // remember to jump out of this for loop if not the same
    //             }
    //         }
    //         if(isSame){
    //             sb.append(strs[0].charAt(len));
    //             len++;
    //         }
    //     }
    //     return sb.toString(); 
    // }
}

/*
Time complexity is O(m*n) - m is max length of string, n is the number of strings
Space complexity is O(m)

Another solution is substring, run time is O(1):
result.substring(0,len)

Reference:
http://www.jiuzhang.com/solutions/longest-common-prefix/
https://yusun2015.wordpress.com/2015/01/12/longest-common-prefix
*/