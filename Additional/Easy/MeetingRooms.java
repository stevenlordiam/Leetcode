/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false
*/

public class MeetingRooms {
    public boolean canAttendMeetings(Interval[] intervals) {
        assert intervals != null : "null input";
        Arrays.sort(intervals, (o1, o2) -> {
            int r = o1.start - o2.start;
            return r == 0 ? o1.end - o2.end : r;
        });
        for (int i = 1; i < intervals.length; i++) {
            Interval i1 = intervals[i - 1];
            Interval i2 = intervals[i];
            if (i1.end > i2.start) {
                return false;
            }
        }
        return true;
    }
}

/*
Reference:
http://sbzhouhao.net/LeetCode/LeetCode-Meeting-Rooms.html
*/