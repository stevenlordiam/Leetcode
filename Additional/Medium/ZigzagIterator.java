/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:
v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
*/

public class ZigzagIterator {
    int i1 = 0;
    int i2 = 0;

    boolean flag = false;

    List<Integer> l1;
    List<Integer> l2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        l1 = v1;
        l2 = v2;
    }

    public int next() {
        flag = !flag;

        if (i1 < l1.size() && (flag  || i2 >= l2.size())) 
            return l1.get(i1++);

        if (i2 < l2.size() && (!flag || i1 >= l1.size())) 
            return l2.get(i2++);

        return -1;
    }

    public boolean hasNext() {
        return i1 < l1.size() || i2 < l2.size();
    }
}

/**
* Your ZigzagIterator object will be instantiated and called as such:
* ZigzagIterator i = new ZigzagIterator(v1, v2);
* while (i.hasNext()) v[f()] = i.next();
*/

/*
Reference:
https://leetcode.com/discuss/58012/short-java-o-1-space
https://leetcode.com/discuss/58000/another-ac-java-solution
https://leetcode.com/discuss/57933/java-o-n-solution-for-k-vector

http://www.cnblogs.com/easonliu/p/4806896.html
http://www.cnblogs.com/jcliBlogger/p/4807033.html

http://www.meetqun.com/thread-653-1-38.html
http://www.meetqun.com/thread-10863-1-1.html
http://www.fgdsb.com/2015/01/30/zigzag-iterator/
http://likemyblogger.blogspot.com/2015/09/leetcode-281-zigzag-iterator.html
http://www.bubuko.com/infodetail-1093502.html
*/