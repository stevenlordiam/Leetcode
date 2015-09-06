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
Reference:
http://www.cnblogs.com/easonliu/p/4785253.html
http://www.fgdsb.com/2015/01/03/the-celebrity-problem/
http://www.geeksforgeeks.org/the-celebrity-problem/
http://likemyblogger.blogspot.com/2015/09/leetcode-277-find-celebrity.html
https://leetcode.com/discuss/56350/straight-forward-c-solution-with-explaination
https://leetcode.com/discuss/56413/java-solution-two-pass
*/