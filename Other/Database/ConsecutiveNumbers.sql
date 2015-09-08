/*
Write a SQL query to find all numbers that appear at least three times consecutively.

+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+
For example, given the above Logs table, 1 is the only number that appears consecutively for at least three times
*/

select distinct Num from (
    select
        Num,
        case
            when @prevNum = Num then @count := @count + 1
            when (@prevNum := Num) is not null then @count := 1
        end n
    from Logs, (select @prevNum := NULL) r
    order by Id
) a where n >= 3

/*
Reference:
https://leetcode.com/discuss/24641/accepted-solution-without-consecutive-slight-modification
https://github.com/kamyu104/LeetCode/blob/master/MySQL/consecutive-numbers.sql
*/