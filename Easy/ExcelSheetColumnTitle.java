/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

public class ExcelSheetColumnTitle {
    public static String convertToTitle(int n) {
        StringBuilder s = new StringBuilder();
        if(n<=0){
            return "";
        }
        while(n>0){
            int tmp=n%26;                   // get the remainder tmp;
            n=n/26;                         // if n=27, after this statement n=1 (27/2 = 1)
            if(tmp==0){                     // two case, 'Z' and others
                s.insert(0,'Z');
                n--;
            } 
            else{
               s.insert(0,(char)(tmp-1+'A')); 
            } 
        }
        return s.toString();
    }
}

/*
stringbuilder.insert()
stringbuilder.toString()
*/