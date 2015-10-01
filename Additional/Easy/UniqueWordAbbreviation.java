/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it --> it    (no abbreviation)	 
b) d|o|g --> d1g
c) i|nternationalizatio|n  --> i18n
d) l|ocalizatio|n          --> l10n

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") -> false 
isUnique("cart") -> true
isUnique("cane") -> false 
isUnique("make") -> true 
*/

import java.util.*;

public class UniqueWordAbbreviation {
	
	static HashMap<String, String> map = new HashMap<String, String>(); // key: word, value: abbrWord
	
	public static void ValidWordAbbr(String[] dictionary) {
		for (String word : dictionary) {
			if (word == null || word.length() <= 2) continue;
			String abbrWord = getAbbr(word);
			if (!map.containsKey(abbrWord)) {
				map.put(word, abbrWord);
			} 
		}
	}

	public static boolean isUnique(String word) {
		if (word == null || word.length() <= 2)
			return true;
		String abbrWord = getAbbr(word);
		if (map.containsValue(abbrWord)) {
			return false;
		}
		return true;
	}

	public static String getAbbr(String s) {
		int len = s.length();
		return s.substring(0, 1) + (len - 2) + s.substring(len - 1);
	}
	
	public static void main(String[] args) {
		String[] dictionary = {"deer", "door", "cake", "card"};
		ValidWordAbbr(dictionary);
		System.out.println(isUnique("dear"));
		System.out.println(isUnique("cart"));
		System.out.println(isUnique("cane"));
		System.out.println(isUnique("make"));
	}
}

/*
Reference:
http://www.cnblogs.com/jcliBlogger/p/4851799.html
https://leetcode.com/discuss/61546/8-lines-in-c
https://leetcode.com/discuss/61555/java-ac-solution-with-hashmap-and-hashset
*/