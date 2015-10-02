/*
An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it --> it    (no abbreviation)	 
b) d|o|g --> d1g
c) i|nternationalizatio|n  --> i18n
d) l|ocalizatio|n          --> l10n

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary
A word's abbreviation is unique if no other word from the dictionary has the same abbreviation

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") -> false 
isUnique("cart") -> true
isUnique("cane") -> false 
isUnique("make") -> true 
*/

import java.util.*;

public class UniqueWordAbbreviation {
	
	static HashMap<String, Set<String>> map = new HashMap<String, Set<String>>(); 
	// Use hashmap to track a set of words that have the same abbreviation
	// The word is unique when its abbreviation does not exist in the map or it's the only one in the set
	// key: abbrWord, value: hashset of the words that have the same abbreviation
	
	public static void ValidWordAbbr(String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			String a = getAbbr(dictionary[i]);
			Set<String> set = new HashSet<String>();
			if(map.containsKey(a)) {
				set = map.get(a);
			}
			set.add(dictionary[i]);
			map.put(a, set);
		}
	}

	public static boolean isUnique(String word) {
		// it's unique when the abbreviation does not exist in the map or it's the only word in the set
		String a = getAbbr(word);
		return !map.containsKey(a) || (map.get(a).contains(word) && map.get(a).size() == 1);
	}

	public static String getAbbr(String s) {
		int len = s.length();
		if(len < 3) return s;
		return s.substring(0, 1) + String.valueOf(len - 2) + s.substring(len - 1);
	}
	
	public static void main(String[] args) {
		String[] dictionary = {"deer", "door", "cake", "card"};
		// String[] dictionary = {"dog"};
		ValidWordAbbr(dictionary);
		System.out.println(isUnique("deer"));
		System.out.println(isUnique("cart"));
		System.out.println(isUnique("cane"));
		System.out.println(isUnique("make"));
	}
}

/*
Reference:
http://www.cnblogs.com/jcliBlogger/p/4851799.html
https://leetcode.com/discuss/61546/8-lines-in-c
https://leetcode.com/discuss/61658/share-my-java-solution
https://leetcode.com/discuss/61555/java-ac-solution-with-hashmap-and-hashset
*/