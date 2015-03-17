/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class WordLadder {
    public int ladderLength(String start, String end, HashSet<String> dict) {
        if (dict == null || dict.size() == 0) {
            return 0;
        }

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start); 	// same as add()
        dict.remove(start);
        int length = 1;

        while(!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i<count; i++){
                String current = queue.poll(); 	// same as remove()
                for (char c = 'a'; c <= 'z'; c++) {
                    for (int j=0; j < current.length(); j++) {      // string长度用(), array长度不用()
                        if (c == current.charAt(j)) {
                            continue;
                        }
                        String tmp = replace(current, j, c);
                        if (tmp.equals(end)) {
                            return length + 1;
                        }
                        if (dict.contains(tmp)){
                            queue.offer(tmp);
                            dict.remove(tmp);
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }

    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}

/*
Similar to CC150 (18-10) Word Ladder

这道题看似一个关于字符串操作的题目，其实要解决这个问题得用图的方法。我们先给题目进行图的映射，顶点则是每个字符串，然后两个字符串如果相差一个字符则我们进行连边
接下来看看这个方法的优势，注意到我们的字符集只有小写字母，而且字符串长度固定，假设是L。那么可以注意到每一个字符可以对应的边则有25个（26个小写字母减去自己），那么一个字符串可能存在的边是25*L条
接下来就是检测这些边对应的字符串是否在字典里，就可以得到一个完整的图的结构了。根据题目的要求，等价于求这个图一个顶点到另一个顶点的最短路径，一般我们用广度优先搜索（不熟悉搜索的朋友可以看看Clone Graph）即可
这个算法中最坏情况是把所有长度为L的字符串都看一下，或者把字典中的字符串都看一下，而长度为L的字符串总共有26^L，所以时间复杂度是O(min(26^L, size(dict))，空间上需要存储访问情况，也是O(min(26^L, size(dict))
可以看出代码框架其实就是广度优先搜索的基本代码，就是判断边的时候需要换字符和查字典的操作，对于这些图的搜索等基本算法，还是要熟悉哈

Reference:
http://www.ninechapter.com/solutions/word-ladder/
http://blog.csdn.net/linhuanmars/article/details/23029973
https://yusun2015.wordpress.com/2015/01/26/word-ladder/
*/