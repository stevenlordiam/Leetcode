/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

public class AddBinary {
    public String addBinary(String a, String b) {
        if(a.length() < b.length()){
            String tmp = a;
            a = b;
            b = tmp;
        }
        
        int pa = a.length()-1;
        int pb = b.length()-1;
        int carries = 0;
        String rst = "";
        
        while(pb >= 0){
            int sum = (int)(a.charAt(pa) - '0') + (int)(b.charAt(pb) - '0') + carries;
            rst = String.valueOf(sum % 2) + rst;
            carries = sum / 2;
            pa --;
            pb --;
        }
        
        while(pa >= 0){
            int sum = (int)(a.charAt(pa) - '0') + carries;
            rst = String.valueOf(sum % 2) + rst;
            carries = sum / 2;
            pa --;
        }       
        
        if (carries == 1)
            rst = "1" + rst;
        return rst;
    }
    
    // public String addBinary(String a, String b) {
    //     StringBuilder s=new StringBuilder();
    //     boolean carry = false;
    //     int i = a.length()-1;
    //     int j = b.length()-1;

    //     while(i>=0 || j>=0 || carry==true){
            
    //         char aChar='0',bChar='0';
    //         int counter=0;
    //         if(i>=0) aChar=a.charAt(i);
    //         if(j>=0) bChar=b.charAt(j);
            
    //         if(aChar=='1') counter++;
    //         if(bChar=='1') counter++;
    //         if(carry==true)  counter++;

    //         if(counter>1) carry=true;
    //         else carry=false;

    //         if(counter%2==1) s.insert(0,'1');
    //         else s.insert(0,'0');
    //         i--;
    //         j--;
    //     }
    //     return s.toString();
    // }
}

/*
Using a boolean type carry to check, if(counter%2==1) is a good way of checking

Reference:
https://yusun2015.wordpress.com/2015/01/13/add-binary/
http://www.jiuzhang.com/solutions/add-binary/
*/