/*
Suppose that a website contains two tables, the Customers table and the Orders table. Write a SQL query to find all customers who never order anything.

Table: Customers.

+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Table: Orders.

+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
Using the above tables as example, return the following:

+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
*/

SELECT A.Name from Customers A
WHERE NOT EXISTS (SELECT 1 FROM Orders B WHERE A.Id = B.CustomerId)

SELECT Name as Customers from Customers
LEFT JOIN Orders
ON Customers.Id = Orders.CustomerId
WHERE Orders.CustomerId IS NULL;

SELECT Name as Customers from Customers
WHERE Id NOT IN (SELECT CustomerId from Orders);

/*
Reference:
https://leetcode.com/discuss/22624/three-accepted-solutions
https://leetcode.com/discuss/53213/a-solution-using-not-in-and-another-one-using-left-join
https://leetcode.com/discuss/26311/share-my-first-db-answer
https://github.com/kamyu104/LeetCode/blob/master/MySQL/customers-who-never-order.sql
*/