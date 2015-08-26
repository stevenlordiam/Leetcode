/*
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6]
*/

public class Flatten2DVector {

    private int[] arrCounters;
    private int counter = 0;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        int cnt = 0;
        if (vec2d == null) {
            arrCounters = new int[0];
        } else {
            for (List<Integer> l : vec2d) {
                cnt += (l == null) ? 0 : l.size();
            }
            arrCounters = new int[cnt];
            cnt = 0;
            for (List<Integer> l : vec2d) {
                for (int in : l) {
                    arrCounters[cnt++] = in;
                }
            }
        }
    }

    public int next() {
        int val = Integer.MIN_VALUE;
        if (counter < arrCounters.length) {
            val = arrCounters[counter];
        }
        counter++;
        return val;
    }

    public boolean hasNext() {
        return counter < arrCounters.length;
    }
}

/*
Reference:
http://sbzhouhao.net/LeetCode/LeetCode-Flatten-2D-Vector.html
*/