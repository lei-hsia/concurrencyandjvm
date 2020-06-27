https://juejin.im/entry/5a4b52eef265da431120954b

这篇文章有深入讲解

innodb为DB的每行数据后面添加的三个字段:
1. `DB_TRX_ID` 事务ID
2. `DB_ROLL_PTR` 回滚指针
3. `DB_ROW_ID`: row id

![innodb-3fields](/Users/xialei/Desktop/cs/mysql/innodb-3fields.png)