/*
Write a SQL query to find all duplicate emails in a table named Person.

+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
For example, your query should return the following for the above table:

+---------+
| Email   |
+---------+
| a@b.com |
+---------+
Note: All emails are in lowercase
*/

Select Email
From Person
GROUP BY Email
Having count(Email)>1

/*
Reference:
https://leetcode.com/discuss/22128/i-have-this-simple-approach-anybody-has-some-other-way
https://leetcode.com/discuss/27527/share-my-solution
https://leetcode.com/discuss/33679/my-simple-accepted-solution
https://github.com/kamyu104/LeetCode/blob/master/MySQL/duplicate-emails.sql
*/