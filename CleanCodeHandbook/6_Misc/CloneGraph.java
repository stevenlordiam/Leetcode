/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        ArrayList<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map
            = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        // clone nodes    
        nodes.add(node);
        map.put(node, new UndirectedGraphNode(node.label));

        int start = 0;
        while (start < nodes.size()) {
            UndirectedGraphNode head = nodes.get(start++);
            for (int i = 0; i < head.neighbors.size(); i++) {
                UndirectedGraphNode neighbor = head.neighbors.get(i);
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    nodes.add(neighbor);
                }
            }
        }

        // clone neighbors
        for (int i = 0; i < nodes.size(); i++) {
            UndirectedGraphNode newNode = map.get(nodes.get(i));
            for (int j = 0; j < nodes.get(i).neighbors.size(); j++) {
                newNode.neighbors.add(map.get(nodes.get(i).neighbors.get(j)));
            }
        }

        return map.get(node);
    }
}

/*
这个题就是基本BFS，要点是生成新节点的同时，怎么把新节点和其他新节点连起来呢？
这就需要一个新旧节点对应map，每次生成新节点A’，要把dequeue的这个parent B对应的新节点B’和当前作为child的新节点A’连起来
注意单向连就行了(B’->A)’，因为下次把A当作root的时候，还会经历B作为child，这时再A’->B’

这道题是LeetCode中为数不多的关于图的题目，不过这道题还是比较基础，就是考察图非常经典的方法：深度优先搜索和广度优先搜索
这道题用两种方法都可以解决，因为只是一个图的复制，用哪种遍历方式都可以。这里恰好可以用旧结点和新结点的HashMap来做visited的记录
这几种方法的时间复杂度都是O(n)（每个结点访问一次），而空间复杂度则是栈或者队列的大小加上HashMap的大小，也不会超过O(n)

// BSF
public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node==null)
        return null;
    LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
    map.put(node,copy);
    queue.offer(node);
    while(!queue.isEmpty())
    {
        UndirectedGraphNode cur = queue.poll();
        for(int i=0;i<cur.neighbors.size();i++)
        {
            if(!map.containsKey(cur.neighbors.get(i)))
            {
                copy = new UndirectedGraphNode(cur.neighbors.get(i).label);
                map.put(cur.neighbors.get(i),copy);
                queue.offer(cur.neighbors.get(i));
            }
            map.get(cur).neighbors.add(map.get(cur.neighbors.get(i)));
        }
    }
    return map.get(node);
}

// DSF
public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node == null)
        return null;
    LinkedList<UndirectedGraphNode> stack = new LinkedList<UndirectedGraphNode>();
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    stack.push(node);
    UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
    map.put(node,copy);
    while(!stack.isEmpty())
    {
        UndirectedGraphNode cur = stack.pop();
        for(int i=0;i<cur.neighbors.size();i++)
        {
            if(!map.containsKey(cur.neighbors.get(i)))
            {
                copy = new UndirectedGraphNode(cur.neighbors.get(i).label);
                map.put(cur.neighbors.get(i),copy);
                stack.push(cur.neighbors.get(i));
            }
            map.get(cur).neighbors.add(map.get(cur.neighbors.get(i)));
        }
    }
    return map.get(node);
}

// DSF with recursion
public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node == null)
        return null;
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
    map.put(node,copy);
    helper(node,map);
    return copy;
}
private void helper(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map)
{
    for(int i=0;i<node.neighbors.size();i++)
    { 
        UndirectedGraphNode cur = node.neighbors.get(i);
        if(!map.containsKey(cur))
        {
            UndirectedGraphNode copy = new UndirectedGraphNode(cur.label);
            map.put(cur,copy);
            helper(cur,map);
        }
        map.get(node).neighbors.add(map.get(cur));
    }
}

Reference:
https://leetcodenotes.wordpress.com/2013/10/08/leetcode-clone-graph-%E5%85%8B%E9%9A%86%E6%97%A0%E5%90%91%E5%9B%BE/
http://www.ninechapter.com/solutions/clone-graph/
http://blog.csdn.net/linhuanmars/article/details/22715747
https://yusun2015.wordpress.com/2015/01/15/clone-graph/
*/