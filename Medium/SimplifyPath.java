/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/

public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> s = new Stack<String>();
        String[] split = path.split("/");
        for (String a : split) {
            if (!a.equals(".") && !a.isEmpty()) {
                if (a.equals("..")) {
                    if (!s.isEmpty()) {
                        s.pop();			//  '..' 表示上层，pop出上层的地址
                    }
                } else {
                    s.push(a);				// 遇到地址, push
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.insert(0, s.pop());
            sb.insert(0, "/");
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}

/*
Stack:
- 先用/来split string
- 然后看每一小段，若是”.”或者是“”（说明两个/连着），不入栈；若是”..”，pop；若是正常，push
- 最后再用string builder把”/”和栈中元素一个一个连起来。

这道题目是Linux内核中比较常见的一个操作，就是对一个输入的文件路径进行简化。
思路比较明确，就是维护一个栈，对于每一个块（以‘/’作为分界）进行分析，如果遇到‘../’则表示要上一层，
那么就是进行出栈操作，如果遇到‘./’则是停留当前，直接跳过，其他文件路径则直接进栈即可。
最后根据栈中的内容转换成路径即可（这里是把栈转成数组，然后依次添加）。
时间上不会超过两次扫描（一次是进栈得到简化路径，一次是出栈获得最后结果），时间复杂度是O(n)，空间上是栈的大小，也是O(n)

[解题思路]
利用栈的特性，如果sub string element
1. 等于“/”，跳过，直接开始寻找下一个element
2. 等于“.”，什么都不需要干，直接开始寻找下一个element
3. 等于“..”，弹出栈顶元素，寻找下一个element
4. 等于其他，插入当前elemnt为新的栈顶，寻找下一个element
最后，再根据栈的内容，重新拼path。这样可以避免处理连续多个“/”的问题。

题解：
这是一道简化路径的题，路径简化的依据是：
当遇到“/../"则需要返回上级目录，需检查上级目录是否为空。
当遇到"/./"则表示是本级目录，无需做任何特殊操作。 
当遇到"//"则表示是本级目录，无需做任何操作。
当遇到其他字符则表示是文件夹名，无需简化。
当字符串是空或者遇到”/../”，则需要返回一个"/"。
当遇见"/a//b"，则需要简化为"/a/b"。

根据这些要求，我需要两个栈来解决问题。
先将字符串依"/"分割出来，然后检查每个分割出来的字符串。
当字符串为空或者为"."，不做任何操作。
当字符串不为".."，则将字符串入栈。
当字符串为"..", 则弹栈（返回上级目录）。

当对所有分割成的字符串都处理完后，检查第一个栈是否为空，如果栈为空，则证明没有可以重建的目录名，返回"/"即可。
当第一个栈不为空时，这时候我们需要还原path。但是不能弹出栈，因为按照要求栈底元素应该为最先还原的目录path。
例如：原始path是 /a/b/c/，栈里的顺序是：a b c，如果依次弹栈还原的话是：/c/b/a（错误！），正确答案为：/a/b/c
所以这里我应用了第二个栈，先将第一个栈元素弹出入栈到第二个栈，然后再利用第二个栈还原回初始path。
这里注意：
判断字符串相等与否要用.equals()，因为是引用类型。
要注意split函数是可以split出空字符的，例如：//b/ 会被split结果为["","b"]。
最后使用StringBuilder进行拼接，由于String在每次对字符串修改时候均会生成一个新的String，效率较低，一般会采用StringBuilder或者StringBuffer来进行字符串修改的操作，
StringBuilder是StringBuffer的简易替换，是非线程安全的，而StringBuffer是线程安全的。

用栈来做，先把输入字符串以'/'为分隔符分来，如果遇到'.'或者空输入什么都不做。如果遇到'..'就弹栈。
然后每从栈里退出一个元素就用'/'连接起来，注意顺序。

发现Java里面的LinkedList实现了栈和队列的所有方法，而且还有重复的！
值得注意的是，LinkedList中的pop()对应的是remove()或者removeHead()  即从链表头移除，而不是removeLast()。
所以在LinkedList中，进栈(push())出栈(pop())都是在链表头部进行，进队列（add()）是从尾部进入，出队列是从头部被移除(remove())。

Reference:
(Stack)https://leetcodenotes.wordpress.com/2013/10/31/leetcode-simplify-path-unix%E7%BB%9D%E5%AF%B9%E8%B7%AF%E5%BE%84simplify/
http://blog.csdn.net/linhuanmars/article/details/23972563
(ArrayList)http://www.ninechapter.com/solutions/simplify-path/
http://www.cnblogs.com/springfor/p/3869666.html
*/