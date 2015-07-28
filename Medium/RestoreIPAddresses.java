/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/ 

public class RestoreIPAddresses {
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> list = new ArrayList<String>();
        
        if(s.length() <4 || s.length() > 12)
            return result;
        
        helper(result, list, s , 0);
        return result;
    }
    
    public void helper(ArrayList<String> result, ArrayList<String> list, String s, int start){
        if(list.size() == 4){
            if(start != s.length())
                return;
            
            StringBuffer sb = new StringBuffer();
            for(String tmp: list){
                sb.append(tmp);
                sb.append(".");
            }
            sb.deleteCharAt(sb.length()-1);		// delete the last '.'
            result.add(sb.toString());
            return;
        }
        
        for(int i=start; i < s.length() && i <= start+3; i++) {
            String tmp = s.substring(start, i+1);
            if(isvalid(tmp)){
                list.add(tmp);
                helper(result, list, s, i+1);
                list.remove(list.size()-1);
            }
        }
    }
    
    private boolean isvalid(String s){
        if(s.charAt(0) == '0')
            return s.equals("0"); // to eliminate cases like "00", "10"
        int digit = Integer.valueOf(s);
        return digit >= 0 && digit <= 255;
    }
}




/*
DFS
基本思路就是取出一个合法的数字，作为IP地址的一项，然后递归处理剩下的项。可以想象出一颗树，每个结点有三个可能的分支（因为范围是0-255，所以可以由一位两位或者三位组成）
并且这里树的层数不会超过四层，因为IP地址由四段组成，到了之后我们就没必要再递归下去，可以结束了。这里除了上述的结束条件外，另一个就是字符串读完了
可以看出这棵树的规模是固定的，不会像平常的NP问题那样，时间复杂度取决于输入的规模，是指数量级的，所以这道题并不是NP问题，因为他的分支是四段，有限制
实现中需要一个判断数字是否为合法ip地址的一项的函数，首先要在0-255之间，其次前面字符不能是0

Reference:
https://leetcodenotes.wordpress.com/2013/08/11/leetcode-restore-ip-address-%E6%8A%8A%E4%B8%80%E4%B8%AAstring-parse%E6%88%90valid-ip%E5%9C%B0%E5%9D%80%EF%BC%8C%E8%BF%94%E5%9B%9E%E6%89%80%E6%9C%89valid-parse/
http://www.ninechapter.com/solutions/restore-ip-addresses/
http://blog.csdn.net/linhuanmars/article/details/24683699
https://yusun2015.wordpress.com/2015/01/16/restore-ip-addresses/
*/