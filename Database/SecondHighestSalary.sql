/*
Write a SQL query to get the second highest salary from the Employee table.
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
For example, given the above Employee table, the second highest salary is 200. If there is no second highest salary, then the query should return null.
*/

SELECT MAX( Salary) FROM Employee WHERE Salary < ( SELECT MAX( Salary) FROM Employee )

/*
Reference:
https://leetcode.com/discuss/21751/simple-query-which-handles-the-null-situation
https://leetcode.com/discuss/23759/my-tidy-soution
https://leetcode.com/discuss/21202/my-answer-about-this-question-using-ifnull-and-distinct
*/