/*
Given a string that contains only digits 0-9 and a target value, return all possibilities to add operators +, -, or * between the digits so they evaluate to the target value

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/

import java.util.*;

public class ExpressionAddOperators {
	
	// solution 1
	public static List<String> addOperators(String num, int target) { 	// backtracking
		List<String> rst = new ArrayList<String>();
		if(num == null || num.length() == 0) return rst;
		StringBuilder path = new StringBuilder();
		helper(rst, path, num, target, 0, 0, 0);
		return rst;
	}
	
	public static void helper(List<String> rst, StringBuilder path, String num, int target, int pos, int eval, int multed){
		if(pos == num.length()){
			if(target == eval)
				rst.add(path.toString());
			return;
		}

		int tmp = path.length();

		for(int i = pos; i < num.length(); i++){
			if(i != pos && num.charAt(pos) == '0') break;
			long curLong = Long.parseLong(num.substring(pos, i + 1));
			if(curLong > Integer.MAX_VALUE || -curLong < Integer.MIN_VALUE) continue;
			int cur = (int)curLong;

			if(pos == 0){
				path.append(cur);
				helper(rst, path, num, target, i + 1, cur, cur);
				path.delete(tmp, path.length());
			}
			else{
				path.append("+" + cur);
				helper(rst, path, num, target, i + 1, eval + cur , cur);
				path.delete(tmp, path.length());

				path.append("-" + cur);
				helper(rst, path, num, target, i + 1, eval -cur, -cur);
				path.delete(tmp, path.length());

				path.append("*" + cur);
				helper(rst, path, num, target, i + 1, eval - multed + multed * cur, multed * cur );
				path.delete(tmp, path.length());
			}
		}
	}

	// solution 2
    public static List<String> addOperators2(String num, int target) {
        List<String> res = new ArrayList<String>();
        if(num == null || num.length() == 0)
            return res;
        helper(num,target,res,"",0,0,0);
        return res;
    }
    
    public static void helper(String num, int target, List<String> res, String path,int index,long multiprev, long sum){
        if(index == num.length() && sum == target){
            res.add(path);
            return;
        }
        
        for(int i = index; i<num.length(); i++){
            if(i!=index && num.charAt(index) == '0')
                break;
            
            long value = Long.parseLong(num.substring(index,i+1));
            if(index == 0)
                helper(num, target, res, path+value,i+1,value,value);
            else{
                helper(num,target, res, path+"+"+value,i+1,value,sum+value);
                helper(num,target, res, path+"-"+value,i+1,-value,sum-value);
                helper(num,target, res, path+"*"+value,i+1,multiprev*value,sum-multiprev+multiprev*value);
            }
        }
    }
	
	public static void main(String[] args) {
		System.out.println(addOperators2("123", 6));
		System.out.println(addOperators2("232", 8));
		System.out.println(addOperators2("1231231234",11353));
	}
}

/*
Reference:
http://www.cnblogs.com/jcliBlogger/p/4814732.html
https://leetcode.com/discuss/58535/17-lines-solution-dfs-c
https://leetcode.com/discuss/58614/java-backtrace-solutoin
*/