# How would you print just the 10th line of a file?

# For example, assume that file.txt has the following content:
# Line 1
# Line 2
# Line 3
# Line 4
# Line 5
# Line 6
# Line 7
# Line 8
# Line 9
# Line 10

# Your script should output the tenth line, which is:
# Line 10

# Hint:
# 1. If the file contains less than 10 lines, what should you output?
# 2. There's at least three different solutions. Try to explore all possibilities.

# Read from the file file.txt and output the tenth line to stdout.
awk 'NR == 10' file.txt

# Solution
# Method 1:
# awk 'NR == 10' file.txt

# Method 2: 
# sed -n '10p' file.txt

# Method 3: 
# cat file.txt | tail -n+10 | head -n+1

# Command
# sed：一种在线编辑器，它一次处理一行内容。
# sed [-nefr] [action]
# -n：只有经过sed 特殊处理的那一行(或者动作)才会被列出来；
# action: [n1[,n2]]functionn1, n2 为选择进行动作的行数；
# function：a/c/d/i/p/s，e.g., p 为列印。
# tail & head：tail -n+k 从第 k 行取到尾部，head -n+k从头部取到第 k 行。

# https://leetcode.com/problems/tenth-line/
# http://wlcoding.blogspot.com/2015/04/tenth-line.html
# https://leetcode.com/discuss/questions/oj/tenth-line