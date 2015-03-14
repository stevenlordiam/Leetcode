/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class MaxPointsOnALine {
    public  int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }  

        HashMap<Double, Integer> map=new HashMap<Double, Integer>();
        int max = 1;

        for(int i = 0 ; i < points.length; i++) {
            // shared point changed, map should be cleared and server the new point
            map.clear();

            // maybe all points contained in the list are same points,and same points' k is 
            // represented by Integer.MIN_VALUE
            map.put((double)Integer.MIN_VALUE, 1);

            int dup = 0;
            for(int j = i + 1; j < points.length; j++) {
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    dup++;
                    continue;
                }

                // look 0.0+(double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x)
                // because (double)0/-1 is -0.0, so we should use 0.0+-0.0=0.0 to solve 0.0 !=-0.0
                // problem

                // if the line through two points are parallel to y coordinator, then K(slop) is 
                // Integer.MAX_VALUE
                double key=points[j].x - points[i].x == 0 ? 
                    Integer.MAX_VALUE :
                    0.0 + (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);

                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 2);
                }
            }
            for (int temp: map.values()) {
                // duplicate may exist
                if (temp + dup > max) {
                    max = temp + dup;
                }
            }
        }
        return max;
    }
}

/*
这道题属于计算几何的题目，要求给定一个点集合，是求出最多点通过一条直线的数量。我的思路是n个点总共构成n*(n-1)/2条直线，然后对每条直线看看是有多少点在直线上，记录下最大的那个，最后返回结果
而判断一个点p_k在p_i, p_j构成的直线上的条件是(p_k.y-p_i.y)*(p_j.x-p_i.x)==(p_j.y-p_i.y)*(p_k.x-p_i.x)。算法总共是三层循环，时间复杂度是O(n^3),空间复杂度则是O(1),因为不需要额外空间做存储
大家看到代码中还写了一个allSamePoints的函数，这是一个非常corner的情况，就是所有点都是一个点的情况，因为下面我们要跳过重复的点（否则两个重合点会认为任何点都在他们构成的直线上），但是偏偏当所有点都重合时，我们需要返回所有点
除了这种情况，只要有一个点不重合，我们就会从那个点得到结果，这是属于比较tricky的情况
经过一个朋友的提醒，这道题还可以有另一种做法，基本思路是这样的，每次迭代以某一个点为基准，看后面每一个点跟它构成的直线，维护一个HashMap，key是跟这个点构成直线的斜率的值，而value就是该斜率对应的点的数量，计算它的斜率，如果已经存在，那么就多添加一个点，否则创建新的key
这里只需要考虑斜率而不用考虑截距是因为所有点都是对应于一个参考点构成的直线，只要斜率相同就必然在同一直线上。最后取map中最大的值，就是通过这个点的所有直线上最多的点的数量。对于每一个点都做一次这种计算，并且后面的点不需要看扫描过的点的情况了，因为如果这条直线是包含最多点的直线并且包含前面的点，那么前面的点肯定统计过这条直线了
因此算法总共需要两层循环，外层进行点的迭代，内层扫描剩下的点进行统计，时间复杂度是O（n^2)，空间复杂度是哈希表的大小，也就是O（n)，比起上一种做法用这里用哈希表空间省去了一个量级的时间复杂度 

Analysis: 
The tricky part is to calculate the duplicates of the elements and add it up to count the numbers of points on the same line.
public class Solution {
    public int maxPoints(Point[] points) {
        int max=0;
        for(int i=0;i<points.length;i++){
            int count=0;
            Map<Double,Integer> map=new HashMap<>();
            int dup=0;
            int v=0;
            for(int j=0;j<points.length;j++){
                if(points[j].x==points[i].x){
                    if(points[j].y==points[i].y) dup++;
                    else v++;
                }
                else{
                    double slope=(double)(points[j].y-points[i].y)/(points[j].x-points[i].x);
                    if(map.containsKey(slope)) map.put(slope,map.get(slope)+1);
                    else map.put(slope,1);
                }
            }
            max=Math.max(max,v+dup);
            for(int k:map.values()) max=Math.max(max,k+dup);
        }
        return max;
    }
}

Reference:
http://www.ninechapter.com/solutions/max-points-on-a-line/
http://blog.csdn.net/linhuanmars/article/details/21060933
(LeetCode总结 - 数值计算篇)http://blog.csdn.net/linhuanmars/article/details/38771593
https://yusun2015.wordpress.com/2015/01/27/max-points-on-a-line/
*/