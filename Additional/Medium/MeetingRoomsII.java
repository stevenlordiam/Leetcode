/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2
*/

public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> {
            int r = o1.start - o2.start;
            return r == 0 ? o1.end - o2.end : r;
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            int val = queue.peek();
            Interval in = intervals[i];
            if (in.start >= val) {
                queue.remove(val);
            }
            queue.add(in.end);
        }
        return queue.size();
    }
}

/*
Reference:
http://sbzhouhao.net/LeetCode/LeetCode-Meeting-Rooms-II.html
*/