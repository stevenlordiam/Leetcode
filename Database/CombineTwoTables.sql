/*
Table: Person
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId is the primary key column for this table.

Table: Address
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId is the primary key column for this table.
Write a SQL query for a report that provides the following information for each person in the Person table, regardless if there is an address for each of those people:
FirstName, LastName, City, State
*/

SELECT Person.FirstName, Person.LastName, Address.City, Address.State from Person LEFT JOIN Address on Person.PersonId = Address.PersonId;

/* Left Join

Whenever we need to combine records from two or more tables, we need to join the tables. There are two common types of join and it is important to understand their differences:
Inner Join - Selects only records from both tables that have matching values. This is also the default join.
Outer Join - Does not require each record in the two joined tables to have a matching record.
Left Outer Join - Returns all values from the left table, even if there is no match with the right table.
Since the question requires information for each person regardless if there is an address for that person, the answer is to use an outer join.
You may use either a LEFT JOIN (Person LEFT JOIN Address) or a RIGHT JOIN (Address RIGHT JOIN Person).

Reference:
https://leetcode.com/problems/combine-two-tables/solution/
http://stackoverflow.com/questions/5706437/whats-the-difference-between-inner-join-left-join-right-join-and-full-join
*/