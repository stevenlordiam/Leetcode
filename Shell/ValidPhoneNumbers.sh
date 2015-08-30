# Given a text file file.txt that contains list of phone numbers (one per line), write a one liner bash script to print all valid phone numbers.

# You may assume that a valid phone number must appear in one of the following two formats: (xxx) xxx-xxxx or xxx-xxx-xxxx. (x means a digit)

# You may also assume each line in the text file must not contain leading or trailing white spaces.

# For example, assume that file.txt has the following content:
# 987-123-4567
# 123 456 7890
# (123) 456-7890

# Your script should output the following valid phone numbers:
# 987-123-4567
# (123) 456-7890

# Read from the file file.txt and output all valid phone numbers to stdout.
cat file.txt | grep -E '^([0-9]{3}-){2}[0-9]{4}$|^(\([0-9]{3}\) ){1}[0-9]{3}-[0-9]{4}$'

# Solution
# 使用 Regular Expession 输出符合格式的电话号码。
# ^([0-9]{3}-){2}[0-9]{4}$ 匹配 987-123-4567；
# ^(\([0-9]{3}\) ){1}[0-9]{3}-[0-9]{4}$ 匹配 (123) 456-7890。

# Command
# grep：一种强大的文本搜索工具，它能使用正则表达式搜索文本，并把匹配的行打印出来。
# -E 使用扩展正则表达式
# ^ $ 行首与行尾字节

# https://leetcode.com/problems/valid-phone-numbers/
# http://wlcoding.blogspot.com/2015/04/valid-phone-numbers.html
# https://leetcode.com/discuss/questions/oj/valid-phone-numbers