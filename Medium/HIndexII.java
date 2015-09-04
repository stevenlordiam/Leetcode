/*
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:
Expected runtime complexity is in O(log n) and the input is sorted
*/

public class HIndexII {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int left=0;
        int right= len-1;
        while(left <= right) {
            int mid= (left+right) / 2;
            if(citations[mid]== (len-mid)) return citations[mid];
            else if(citations[mid] > (len-mid)) right = mid - 1;
            else left = mid + 1;
        }
        return len - (right+1);
    }
}

/*
Reference:
https://leetcode.com/discuss/56109/space-easy-solution-with-detailed-explanations-java-python
https://leetcode.com/discuss/56122/standard-binary-search
https://leetcode.com/discuss/56065/java-ac-solution-by-binary-search
http://www.cnblogs.com/jcliBlogger/p/4782471.html
*/