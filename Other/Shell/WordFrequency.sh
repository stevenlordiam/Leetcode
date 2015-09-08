# Write a bash script to calculate the frequency of each word in a text file words.txt.

# For simplicity sake, you may assume:

# words.txt contains only lowercase characters and space ' ' characters.
# Each word must consist of lowercase characters only.
# Words are separated by one or more whitespace characters.
# For example, assume that words.txt has the following content:

# the day is sunny the the
# the sunny is is
# Your script should output the following, sorted by descending frequency:
# the 4
# is 3
# sunny 2
# day 1
# Note:
# Don't worry about handling ties, it is guaranteed that each word's frequency count is unique.

# Hint:
# Could you write it in one-line using Unix pipes(http://tldp.org/HOWTO/Bash-Prog-Intro-HOWTO-4.html) ?

# Read from the file words.txt and output the word frequency list to stdout.
cat words.txt | tr -s " " "\n"| sort | uniq -c | sort -nr | awk '{ print $2 " " $1}'

# Solution:
# 1. 读入文档；
# 2. 将所有空格替换为换行；
# 3. 将所有 word 排序，相同词放一起；
# 4. 统计所有 word 的出现次数，得到 "count word"；
# 5. 将结果按 count 逆序排序（注意使用数字而非文本排序）；
# 6. 将结果按"列 2 (word) 列1 (count)"的格式打印。

# Command
# tr：对来自标准输入的字符进行替换、压缩和删除
# -s：取代掉重复的字符
# sort -nr：对 File 参数指定的文件中的行排序，并将结果写到标准输出。
# -n  ：使用『纯数字』进行排序(默认是以文字型态来排序的)；
# -r  ：反向排序；
# uniq：可以去除排序过的文件中的重复行，因此uniq经常和sort合用
# -c：进行计数
# awk：一个强大的文本分析工具，对数据分析并生成报告。
# awk '{pattern + action}' {filenames}

# https://leetcode.com/problems/word-frequency/
# http://wlcoding.blogspot.com/2015/04/word-frequency.html
# https://leetcode.com/discuss/questions/oj/word-frequency