/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
*/

public class TextJustification {            // ???
    public ArrayList<String> fullJustify(String[] words, int L) {
        int wordsCount = words.length;
        ArrayList<String> result = new ArrayList<String>();
        int curLen = 0;
        int lastI = 0;
        for (int i = 0; i <= wordsCount; i++) {
            //curLen是上一次计算的单词的长度，words[i].length()是当前尝试放的一个单词的长度，
            //假设当前放上了这个单词，那么这一行单词跟单词间的间隔数就是i-lastI
            //判断这些总的长度加起来是不是大于L（超行数了）
            if (i == wordsCount || curLen + words[i].length() + i - lastI > L) {
                StringBuffer buf = new StringBuffer();
                int spaceCount = L - curLen;
                int spaceSlots = i - lastI - 1;
                if (spaceSlots == 0 || i == wordsCount) {
                    for(int j = lastI; j < i; j++){
                        buf.append(words[j]);
                        if(j != i - 1)
                            appendSpace(buf, 1);
                    }
                    appendSpace(buf, L - buf.length());
                } else {
                    int spaceEach = spaceCount / spaceSlots;
                    int spaceExtra = spaceCount % spaceSlots;
                    for (int j = lastI; j < i; j++) {
                        buf.append(words[j]);
                        if (j != i - 1)
                            appendSpace(buf, spaceEach + (j - lastI < spaceExtra ? 1 : 0));
                    }
                }
                result.add(buf.toString());
                lastI = i;
                curLen = 0;
            }
            if (i < wordsCount)
                curLen += words[i].length();
        }
        return result;
    }

    private void appendSpace(StringBuffer sb, int count) {
        for (int i = 0; i < count; i++)
            sb.append(' ');
    }
}

/*
这道题属于纯粹的字符串操作，要把一串单词安排成多行限定长度的字符串。主要难点在于空格的安排，首先每个单词之间必须有空格隔开，而当当前行放不下更多的单词并且字符又不能填满长度L时，我们要把空格均匀的填充在单词之间
如果剩余的空格量刚好是间隔倍数那么就均匀分配即可，否则还必须把多的一个空格放到前面的间隔里面。实现中我们维护一个count计数记录当前长度，超过之后我们计算共同的空格量以及多出一个的空格量，然后将当行字符串构造出来
最后一个细节就是最后一行不需要均匀分配空格，句尾留空就可以，所以要单独处理一下。时间上我们需要扫描单词一遍，然后在找到行尾的时候在扫描一遍当前行的单词，不过总体每个单词不会被访问超过两遍，所以总体时间复杂度是O(n)
而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，如果知道最后行数r，则是O(r*L)）

思路：
比较麻烦的字符串细节实现题。需要解决以下几个问题：
1. 首先要能判断多少个word组成一行：
这里统计读入的所有words的总长curLen，并需要计算空格的长度。假如已经读入words[0:i]。当curLen + i <=L 且加curLen + 1 + word[i+1].size() > L时，一行结束
2. 知道一行的所有n个words，以及总长curLen之后要决定空格分配：
平均空格数：k = (L - curLen) / (n-1)
前m组每组有空格数k+1：m = (L - curLen) % (n-1)
例子：L = 21，curLen = 14，n = 4
k = (21 - 14) / (4-1) = 2
m = (21 - 14) % (4-1)  = 1
A---B--C--D
3. 特殊情况：
(a) 最后一行：当读入到第i = words.size()-1 个word时为最后一行。该行k = 1，m = 0
(b) 一行只有一个word：此时n-1 = 0，计算(L - curLen)/(n-1)会出错。该行k = L-curLen, m = 0
(c) 当word[i].size() == L时

Reference:
http://www.ninechapter.com/solutions/text-justification/
http://blog.csdn.net/linhuanmars/article/details/24063271
https://yusun2015.wordpress.com/2015/03/08/text-justification/
http://bangbingsyb.blogspot.com/2014/11/leetcode-text-justification.html
http://www.cnblogs.com/springfor/p/3896168.html
*/