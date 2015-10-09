/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of words from the dictionary, wherewords are sorted lexicographically by the rules of this new language. 
Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
- You may assume all letters are in lowercase.
- If the order is invalid, return an empty string.
- There may be multiple valid order of letters, return any one of them is fine 
*/

public class AlienDictionary { //26 letters 
	public static int N = 26;
	public String alienOrder(String[] words) {
	    if(words == null || words.length == 0) return "";
	    if(words.length == 1) return words[0];

	    //the dependence relationship N*N array.
	    boolean[][] dependence = new boolean[N][N];
	    //indegree for every letter
	    int[] indegree = new int[N];
	    //existence
	    boolean[] existence = new boolean[N];

	    for(int i = 0; i < words.length; ++i){
	        for(int j = 0; j < words[i].length(); ++j){
	            //set existence
	            existence[words[i].charAt(j)-'a'] = true;
	        }
	        if(i >= 1) checkTwoStrings(words[i-1], words[i], dependence, indegree);
	    }

	    return toplogical_sort(dependence, indegree, existence);
	}

	public String toplogical_sort(boolean[][] dependence, int[] indegree, boolean[] existence){
	    //queue used to store those whose indegree = 0
	    Queue<Integer> q = new LinkedList<Integer>();
	    //result
	    StringBuilder res = new StringBuilder();

	    for(int i = 0; i < N; ++i){
	        if(indegree[i] == 0 && existence[i]) q.add(i);
	    }

	    while(!q.isEmpty()){
	        int poll = q.poll();
	        res.append((char)(poll+'a'));
	        for(int i = 0; i < N; ++i){
	            if(dependence[i][poll] == true) {
	                indegree[i] -= 1;
	                if(indegree[i] == 0) q.add(i);
	            }
	        }
	    }

	    //check cycle!
	    for(int i = 0; i < N; ++i){
	        if(indegree[i] != 0) return "";
	    }
	    return res.toString();
	}

	//check two adjencent strings, e.g. : "wer" and "wet", then 'r' is absolutely before 't' 
	public void checkTwoStrings(String s1, String s2, boolean[][] dependence, int[] indegree){

	    int minlen = Math.min(s1.length(), s2.length());
	    for(int i = 0; i < minlen; ++i){
	        if(s1.charAt(i) != s2.charAt(i) && 
	            dependence[s2.charAt(i)-'a'][s1.charAt(i)-'a'] == false){
	            dependence[s2.charAt(i)-'a'][s1.charAt(i)-'a'] = true;
	            indegree[s2.charAt(i) - 'a'] += 1;
	            break;
	        }
	    }
	}
}

/*
Reference:
http://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
http://www.cnblogs.com/jcliBlogger/p/4758761.html
http://buttercola.blogspot.com/2015/09/leetcode-alien-dictionary.html
https://aquahillcf.wordpress.com/2015/09/07/leetcode-alien-dictionary/
https://leetcode.com/discuss/54471/best-ever-java-solution
https://github.com/jiemingxin/LeetCode/blob/master/tutorials/graph/AlienDict.java
*/

/*
Give a String array, return a minimum length String that satisfies the requirement that the relative orders between the chars in the output are consistent with the relative orders in every array element. Assume the output exists
要满足三个条件: 
1. Output unique character
2. output的string里,character相对顺序不变。Input不会有“bc ”,“cb”这种情况出现 
3. 满足以上两个条件,按照lexicographical order, 输出最小的可能性 

For example: 
{"ab, bc, bz"} -> "abcz"
{"fbz, jb, ebj"} -> "efbjz" (实际"febzj","febjz""efbjz"啥的都是正确解!)
{ "cba", "bd", "ce", "ed" } -> "cbaed" 
{ "gcd", "jd", "fcj" } -> "fgcjd 

follow up： how to detect invalidate input? this means how to detect cycle in direct graph

这题算是他家的必考题⺫ 思路应该是topology sort (参考CC150+Leetcode 有这道题原题)

用一个Map<Character, Set<Character>>记录出现在每个字母前面的所有字母，然后就有点类似拓扑排序了：
先找到Set为空的字母放在第一位，把它从Map中除去，再在所有Set中把这个字母去掉，然后就是循环找下一个Set为空的字母

import java.util.*;

public class ShortestStringInOrder {
	public static void toposort(Map<Character, Set<Character>> map,
			Set<Character> visited, Stack<Character> stack, char c) {
		visited.add(c);
		if (map.containsKey(c)) {
			for (Character neigbor : map.get(c)) {
				if (!visited.contains(neigbor)) {
					toposort(map, visited, stack, neigbor);
				}
			}
		}
		stack.push(c);
	}

	public static String findOrder(List<String> strs) {
		Map<Character, Set<Character>> map = new HashMap<>();
		Set<Character> set = new HashSet<>();
		Set<Character> visited = new HashSet<>();
		
		for (String s : strs) {
			set.add(s.charAt(0));
			for (int i = 1; i < s.length(); i++) {
				if (!map.containsKey(s.charAt(i - 1))) {
					Set<Character> adjacent = new HashSet<>();
					adjacent.add(s.charAt(i));
					map.put(s.charAt(i - 1), adjacent);
				} else {
					map.get(s.charAt(i - 1)).add(s.charAt(i));
				}
				set.add(s.charAt(i));
			}
		}

		Stack<Character> stack = new Stack<>();
		for (char c : set) {
			if (!visited.contains(c)) {
				toposort(map, visited, stack, c);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		list.add("fbz");
		list.add("jb");
		list.add("ebj");

		String res = findOrder(list);
		System.out.println(res);
	}
}
*/