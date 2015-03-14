/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {

        if (s == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();        // use stack to store parentheses
        int maxLen = 0;
        int accumulatedLen = 0;

        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    accumulatedLen = 0;
                } else {
                    int matchedPos = stack.pop();   // position
                    int matchedLen = i - matchedPos + 1;

                    if (stack.isEmpty()) {
                        accumulatedLen += matchedLen;
                        matchedLen = accumulatedLen;
                    } else {
                        matchedLen = i - stack.peek();
                    }

                    maxLen = Math.max(maxLen, matchedLen);
                }
            }
        }

        return maxLen;
   }
}

/*
括号题也不是只用stack才能做，这题是算长度，所以stack那种一match就俩一起pop了的方式就不适合了。求极值，一维dp最好使
- d[i]: 以i开头的最长valid parentheses有多长。
- d[i] =
  - if (str[i] == ‘)’)，以右括号开头必定invalid，d[i] = 0
  - if (str[i] == ‘(‘)，以左括号开头
    - 我们想看对应的有木有右括号。因为d[i + 1]代表的sequence肯定是左括号开头右括号结尾，所以我们想catch((…))这种情况。j = i + 1 + d[i + 1]，正好就是str[i]后面越过完整sequence的下一个，若是右括号，d[i] = 2 + d[i + 1]
    - 除此之外，还有包起来后因为把后面的右括号用了而导致跟再往后也连起来了的情况，如((..))()()()。所以d[i]还要再加上j后面那一段的d[j + 1]
这个定义和最长公共字串那题的定义类似，都是“以某个固定位置开始/结束”。看两头的方式又像palindrome。从后往前的一维dp也不常见。挺好玩的，一下复习好多东西
public int longestValidParentheses(String s) {
    if (s.length() == 0)
        return 0;
    int maxLen = 0;
    int[] d = new int[s.length()];
    // d[i] means substring starts with i has max valid lenth of d[i]
    d[s.length() - 1] = 0;
    for (int i = s.length() - 2; i >= 0; i--) {
        if (s.charAt(i) == ')')
            d[i] = 0;
        else {
            int j = (i + 1) + d[i + 1];
            if (j < s.length() && s.charAt(j) == ')') {
                d[i] = d[i + 1] + 2; //(()())的外包情况
                if (j + 1 < s.length())
                    d[i] += d[j + 1];//()()的后面还有的情况
            }
        }
        maxLen = Math.max(maxLen, d[i]);
    }
    return maxLen;
}
———————————用stack的做法———————————–
- stack里面装的一直是“还没配好对的那些可怜的括号的index”
- 是'(‘的时候push
- 是’)’的时候，说明可能配对了；看stack top是不是左括号，不是的话，push当前右括号
- 是的话，pop那个配对的左括号，然后update res：i和top的（最后一个配不成对的）index相减，就是i属于的这一段的当前最长。如果一pop就整个栈空了，说明前面全配好对了，那res就是最大=i+1
public int longestValidParentheses(String s) {
    int res = 0;
    Stack<Integer> stack = new Stack<Integer>();
    char[] arr = s.toCharArray();
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == ')' && !stack.isEmpty() && arr[stack.peek()] == '(') {
            stack.pop();
            if (stack.isEmpty())
                res = i + 1;
            else
                res = Math.max(res, i - stack.peek());
        } else {
            stack.push(i);
        }
    }
    return res;
}

这道题是求最长的括号序列，比较容易想到用栈这个数据结构。基本思路就是维护一个栈，遇到左括号就进栈，遇到右括号则出栈，并且判断当前合法序列是否为最长序列
不过这道题看似思路简单，但是有许多比较刁钻的测试集。具体来说，主要问题就是遇到右括号时如何判断当前的合法序列的长度。比较健壮的方式如下：
(1) 如果当前栈为空，则说明加上当前右括号没有合法序列（有也是之前判断过的）；
(2) 否则弹出栈顶元素，如果弹出后栈为空，则说明当前括号匹配，我们会维护一个合法开始的起点start，合法序列的长度即为当前元素的位置-start+1；
否则如果栈内仍有元素，则当前合法序列的长度为当前栈顶元素的位置下一位到当前元素的距离，因为栈顶元素后面的括号对肯定是合法的，而且左括号出过栈了
因为只需要一遍扫描，算法的时间复杂度是O(n)，空间复杂度是栈的空间，最坏情况是都是左括号，所以是O(n)
这种用剩余栈的栈顶元素位置信息作为当前合法数据的判断依据是比较重要的技巧，在Largest Rectangle in Histogram这道题里面也用到了

Reference:
https://leetcodenotes.wordpress.com/2013/10/19/leetcode-longest-valid-parentheses-%E8%BF%99%E7%A7%8D%E6%8B%AC%E5%8F%B7%E7%BB%84%E5%90%88%EF%BC%8C%E6%9C%80%E9%95%BF%E7%9A%84valid%E6%8B%AC%E5%8F%B7%E7%BB%84%E5%90%88%E6%9C%89%E5%A4%9A/
http://www.ninechapter.com/solutions/longest-valid-parentheses/
http://blog.csdn.net/linhuanmars/article/details/20439613
*/