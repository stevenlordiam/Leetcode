/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class WordLadderII {
    public List<List<String>> findLadders(String start, String end,
            Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);
 
        bfs(map, distance, start, end, dict);
        List<String> path = new ArrayList<String>();
        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
            String start, Map<String, Integer> distance,
            Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(start)) {        // 参见九章算法6-Graph & Search
            // 使用crt在DFS过程记录每个节点，当遇到end时，反向遍历到start并将这个路径加入结果
            // 反向DFS，具体分析见九章算法笔记
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) { 
                    dfs(ladders, path, next, start, distance, map);
                }
            }           
        }
        path.remove(path.size() - 1);   // 回溯法
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
            String start, String end, Set<String> dict) {
        
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        
        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }

        return expansion;
    }
}

/*
这道题是LeetCode中AC率最低的题目，确实是比较难。一方面是因为对时间有比较严格的要求（容易超时），另一方面是它有很多细节需要实现
思路上和Word Ladder是比较类似的，但是因为是要求出所有路径，仅仅保存路径长度是不够的，而且这里还有更多的问题，那就是为了得到所有路径，不是每个结点访问一次就可以标记为visited了，因为有些访问过的结点也会是别的路径上的结点，所以访问的集合要进行回溯（也就是标记回未访问）
所以时间上不再是一次广度优先搜索的复杂度了，取决于结果路径的数量。同样空间上也是相当高的复杂度，因为我们要保存过程中满足的中间路径到某个数据结构中，以便最后可以获取路径，这里我们维护一个HashMap，把一个结点前驱结点都进行保存
在LeetCode中用Java实现上述算法非常容易超时。为了提高算法效率，需要注意一下两点：
1）在替换String的某一位的字符时，先转换成char数组再操作；
2）如果按照正常的方法从start找end，然后根据这个来构造路径，代价会比较高，因为保存前驱结点容易，而保存后驱结点则比较困难。所以我们在广度优先搜索时反过来先从end找start，最后再根据生成的前驱结点映射从start往end构造路径，这样算法效率会有明显提高

Reference:
http://www.ninechapter.com/solutions/word-ladder-ii/
http://blog.csdn.net/linhuanmars/article/details/23071455
https://yusun2015.wordpress.com/2015/03/17/word-ladder-ii/
http://yucoding.blogspot.com/2014/01/leetcode-question-word-ladder-ii.html
http://blog.csdn.net/perfect8886/article/details/19645691
http://slientcode.blogspot.com/2014/11/word-ladder-ii.html
http://www.cnphp6.com/archives/52998
http://www.cnblogs.com/yuzhangcmu/p/4119492.html
*/