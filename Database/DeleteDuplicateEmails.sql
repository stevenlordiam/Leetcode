/*
Write a SQL query to delete all duplicate email entries in a table named Person, keeping only unique emails based on its smallest Id.

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
Id is the primary key column for this table.
For example, after running your query, the above Person table should have the following rows:

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
*/

DELETE p from Person p, Person q where p.Id>q.Id AND q.Email=p.Email 

/*
Reference:
https://leetcode.com/discuss/39239/solution-in-a-single-query-without-any-conflicts
https://leetcode.com/discuss/31343/a-skillful-mysql-solution-avoid-select-and-update-conflict
https://leetcode.com/discuss/29787/simple-solution
https://github.com/kamyu104/LeetCode/blob/master/MySQL/delete-duplicate-emails.sql
*/