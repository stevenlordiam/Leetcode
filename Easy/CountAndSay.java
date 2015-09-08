/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

public class CountAndSay {
    public String countAndSay(int n) {
        String oldString = "1";
        while (--n > 0) {
            StringBuilder sb = new StringBuilder();
            char [] oldChars = oldString.toCharArray();

            for (int i = 0; i < oldChars.length; i++) {
                int count = 1;
                while ((i+1) < oldChars.length && oldChars[i] == oldChars[i+1]) {
                    count++;
                    i++;
                }
                sb.append(String.valueOf(count) + String.valueOf(oldChars[i]));
            }
            oldString = sb.toString();
        }

        return oldString;
    }

    // public String countAndSay(int n) {
    //     if(n<=0){
    //     	return "";
    //     }  
    //     String result = "1";
    //     for(int i=2; i<=n ;i++){
    //         StringBuilder sb = new StringBuilder();
    //         int count = 1;
    //         for(int j=1;j<result.length();j++){
    //             if(result.charAt(j)==result.charAt(j-1)){
    //                 count++;
    //             }else{
    //                 sb.append(count);
    //                 sb.append(result.charAt(j-1));
    //                 count = 1;
    //             }
    //         }
    //         sb.append(count);
    //         sb.append(result.charAt(result.length()-1));
    //         result = sb.toString();
    //     }
    //     return result;
    // }
}

/*
Remember to use double loop, one is calculating each result, one is calculating the whole sequence

Reference:
http://www.jiuzhang.com/solutions/count-and-say/
*/