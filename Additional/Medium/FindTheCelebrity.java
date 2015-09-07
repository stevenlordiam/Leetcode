/*
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1
*/

// Forward declaration of the knows API.
boolean knows(int a, int b);

public class FindTheCelebrity extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate, i))
                candidate = i;
        }
        for(int i = 0; i < n; i++){
            if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }
}

/*
方法一:
列一个N by N的表格, 然后用1表示认识, 0表示不认识, 如果有celebrity, 那么那一行应该是全1的, 然后剩下的就是一行一行的搜索, 所以时间复杂度是O(n^2).

方法二:
O(n)时间,具体解法是:把这些人编号1,2,3,4….n。1问2认不认识1, 2的回答只有两种,Yes or No,如果是No,1就不可能是celebrity,保留2;
如果是Yes,2就不可能是celebrity,保留1;然后如此类推,这样每问一次可以消去1个人,最后n-1次之后只有一个编号为i的人有可能是celebrity
(注意,是可能而已,我们还不能确定),我们只要再扫一遍所有的人,如果答案都是别人认识i,i不认识别人,那么我们找到了celebrity,如果有例外,那么就不存在celebrity.
In total, time complexity is O(n)

方法三:
可以分析几种情况
- 如果A认识B，那么A不可能是celebrity。去掉A，B则有可能是
- 如果A不认识B，那么B不可能是celebrity。去掉B，A则有可能是
- 重复以上两个步骤直到只剩下一个候选人
- 再次确认是否这最后一个人是否为celebrity
- 这里用stack来做。

把所有的celebrity压栈，弹出最上面的两个celebrity，根据HaveAcquaintance(A, B)的结果来去掉一个一定不是celebrity的人
将2中剩下的那一位压栈，重复以上两个步骤，直到stack中只剩一个人，确认这个人不认识其他任何人
以上算法需要调用HaveAcquaintance(A,B) 3(N-1)次

Reference:
http://www.cnblogs.com/easonliu/p/4785253.html
http://www.fgdsb.com/2015/01/03/the-celebrity-problem/
http://www.geeksforgeeks.org/the-celebrity-problem/
http://likemyblogger.blogspot.com/2015/09/leetcode-277-find-celebrity.html
https://leetcode.com/discuss/56350/straight-forward-c-solution-with-explaination
https://leetcode.com/discuss/56413/java-solution-two-pass
*/