/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented(https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs).

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*/

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        int count = numCourses;
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            map.get(prerequisites[i][0]).add(prerequisites[i][1]);
            indegree[prerequisites[i][1]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i : map.get(current)) {
                if (--indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            count--;
        }
        return count == 0;
    }
}

/*
Reference:
(OO)https://leetcode.com/discuss/35035/oo-easy-to-read-java-solution
(BFS TopoSort)https://leetcode.com/discuss/35578/easy-bfs-topological-sort-java
(TopoSort)https://leetcode.com/discuss/37134/java-ac-solution-with-top-sort
https://leetcode.com/discuss/39456/java-dfs-and-bfs-solution
https://leetcode.com/discuss/34762/typical-color-strategy-check-cycle-exists-analysis-included
https://leetcode.com/discuss/34791/bfs-topological-sort-and-dfs-finding-cycle-by-c
https://leetcode.com/discuss/34694/my-java-dfs-solution
https://leetcode.com/discuss/34699/my-java-bfs-solution
*/