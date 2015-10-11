/*
Given a pattern and a string str, find if str follows the same pattern.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.

Notes:
Both pattern and str contains only lowercase alphabetical letters.
Both pattern and str do not have leading or trailing spaces.
Each word in str is separated by a single space.
Each letter in pattern must map to a word with length that is at least 1
*/

public class WordPattern {
    public boolean wordPattern(String pattern, String str) { 	// version1
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (int i=0; i<words.length; ++i)
            if (!Objects.equals(index.put(pattern.charAt(i), i),
                            index.put(words[i], i)))
                return false;
        return true;
    }

    public boolean wordPattern(String pattern, String str) { 	// version2
	    String[] array = str.split(" ");
	    int size = pattern.length();

	    if (array.length != size) return false;

	    Map<Character, String> map = new HashMap<>();
	    Map<String, Character> dup = new HashMap<>();

	    for (int i=0; i<size; i++) {
	        char c = pattern.charAt(i);
	        String s = array[i];
	        if (map.containsKey(c) && !s.equals(map.get(c))) {
	            return false;
	        }
	        if (dup.containsKey(s) && c != dup.get(s)) {
	            return false;
	        }
	        map.put(c, s);
	        dup.put(s, c);
	    }
	    return true;
	}
}

/*
Reference:
https://leetcode.com/discuss/62374/9-lines-simple-java
https://leetcode.com/discuss/62363/simple-and-fast-java-solution
*/