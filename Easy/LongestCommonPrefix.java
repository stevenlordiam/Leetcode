/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if(strs.length==0 || strs==null){     // if empty, return "" which is empty but NOT null !
            return sb.toString();
        }
        boolean isSame = true;
        int len = 0;		// the pointer
        while(isSame){
            for(int i=0; i<strs.length; i++){
                if(strs[i].length()<=len || strs[i].charAt(len)!=strs[0].charAt(len)){		// for the case not the same: str[i].length<len or not the same
                    isSame = false;
                    break;			// remember to jump out of this for loop if not the same
                }
            }
        	if(isSame){
            	sb.append(strs[0].charAt(len));
            	len++;
        	}
        }
        return sb.toString(); 
    }
}

/*
Time complexity is O(m*n) - m is max length of string, n is the number of strings
Space complexity is O(m)

Another solution is substring, run time is O(1):
result.substring(0,len)

See this solution at:
https://yusun2015.wordpress.com/2015/01/12/longest-common-prefix
*/