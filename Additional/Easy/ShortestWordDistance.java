/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

public class ShortestWordDistance {
    // version 1, time O(N^2), space O(N)
    public int shortestDistance(String[] words, String word1, String word2) {
        HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();  // key: string, value: position list
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            List<Integer> list;
            if (map.containsKey(s)) {
                list = map.get(s);
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(s, list);
        }
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        int min = Integer.MAX_VALUE;
        for (int a : l1) {
            for (int b : l2) {
                min = Math.min(Math.abs(b - a), min);
            }
        }
        return min;
    }

    // version 2, time O(N), space O(1)
    public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) 
                p1 = i;
            if (words[i].equals(word2)) 
                p2 = i;
            if (p1 != -1 && p2 != -1)
                min = Math.min(min, Math.abs(p1 - p2));
        }
        return min;
    }
}

/*
Reference:
http://sbzhouhao.net/LeetCode/LeetCode-Shortest-Word-Distance.html
https://leetcode.com/discuss/50234/ac-java-clean-solution
https://leetcode.com/discuss/50192/java-solution-with-one-for-loop
http://www.cnblogs.com/jcliBlogger/p/4704962.html
*/