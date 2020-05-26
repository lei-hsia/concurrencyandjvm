### 如何锁一行

1. `begin`
2. `SQL语句... for update`
3. `commit`;

for update锁定一行之后，其它操作会被阻塞, 直到锁定的行的session执行commit;

![锁一行](/Users/xialei/Desktop/周阳/mysql高级料/锁一行.png)

InnoDB: 行锁性能开销高于MyISAM的表锁，但是因为只是锁了一行，并发能力是明显高于MyISAM的

**查看行锁状态:**
-  `show status like 'innodb_row_lock%'; `
-   `show profile;`

![行锁状态](/Users/xialei/Desktop/周阳/mysql高级料/行锁状态.png)