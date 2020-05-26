### mysql的读写锁

**表锁: MyISAM读写锁**

- `show open tables;`
- `show status like 'table%';`: 
    - `Table_lock_immediate`
    - `Table_lock_waited`


![读阻塞写](/Users/xialei/Desktop/周阳/mysql高级料/读阻塞写.png)

- 当前session并不能改表的值: `Table xxx was locked with a READ lock and can't be updated`
- 其它session改变这个表的值: 会阻塞, 直到这个session解锁,就自动update;
- 当前session正有事，不能读别的table;
- 其它session读别的table无所谓;


![写阻塞读](/Users/xialei/Desktop/周阳/mysql高级料/写阻塞读.png)

MyISAM: 读写锁调度是写优先，所以MyISAM不适合做写为主表的引擎；因为写为主表，很多写，
写锁，其它线程不能做任何操作，如果有大量更新，使得其它查询(读)很难得到锁，甚至永久阻塞

所以MyISAM引擎需要多读少写;

**InnoDB**

与MyISAM最大的不同: 
1. 支持transaction: Atomic, Consistent, Isolated, Durable
2. 行锁

### 并发transaction可能导致的问题:
1. 更新丢失(Lost Update): 类似JMM三大特性中，丢失了 "可见性"
2. 脏读(数据变, Dirty Read): 事务A读到了事务B已update但是没有commit的数据: 可能B会rollback，结果A不知道就GG了；
i.e. 读未提交
3. 不可重复读(时间段, Non-repeatable Read): 读提交的之后再读，数据因别的事务修改已不同
4. 幻读(数据增, Phantom-Read):  A读到了事务B提交的新增数据;

### 事务隔离级别分为四种（递减）：

1、Serializable （串行化）：最严格的级别，事务串行执行，资源消耗最大；

2、*REPEATABLE READ（重复读）* ：保证了一个事务不会修改已经由另一个事务读取但未提交（回滚）的数据。避免了“脏读取”和“不可重复读取”的情况，但不能避免“幻读”，但是带来了更多的性能损失。
> InnoDB, 可重复读，通过行锁，解决幻读问题: `show variables like 'tx_isolation';`

3、READ COMMITTED （提交读）：大多数主流数据库的默认事务等级，保证了一个事务不会读到另一个并行事务已修改但未提交的数据，避免了“脏读取”，但不能避免“幻读”和“不可重复读取”。该级别适用于大多数系统。

4、Read Uncommitted（未提交读） ：事务中的修改，即使没有提交，其他事务也可以看得到，会导致“脏读”、“幻读”和“不可重复读取”。

**读写一致性: `事务级`; mysql的默认级别: RR(Repeatable Read)**


