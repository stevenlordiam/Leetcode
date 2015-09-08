# Given a text file file.txt, transpose its content.

# You may assume that each row has the same number of columns and each field is separated by the ' ' character.

# For example, if file.txt has the following content:

# name age
# alice 21
# ryan 30
# Output the following:

# name alice ryan
# age 21 30

# Read from the file file.txt and print its transposed content to stdout.
awk '
{ 
    for (i = 1; i <= NF; i++)  {
        a[NR, i] = $i
    }
}
END {    
    for (j = 1; j <= NF; j++) {
        str = a[1, j]
        for (i = 2; i <= NR; i++){
            str = str " " a[i, j]
        }
        print str
    }
}' file.txt

# Solution
# 现将文本存入到一个 array 中，再按列到行的顺序输出。

# Command
# awk 中可以加入 BEGIN {} 和 END {}，作为初始和结束时的 action。
# NF：浏览记录的域的个数
# NR：已读的记录数

# https://leetcode.com/problems/transpose-file/
# http://wlcoding.blogspot.com/2015/04/transpose-file.html
# https://leetcode.com/discuss/questions/oj/transpose-file