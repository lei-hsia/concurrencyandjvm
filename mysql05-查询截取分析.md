内容:
1. 查询优化
2. 慢查询日志
3. 批量数据脚本
4. show profile
5. 全局查询日志

总结过程: 
1. 慢查询的开启并捕获
2. explain + 慢SQL分析
3. show profile查询SQL在mysql服务器中的执行细节和生命周期
4. SQL数据库服务器参数调优

学习过程:
1. 初级: select ... where;
2. 索引: where后面的索引条件
3. 后面: order by, group by

#### in, exist:

![小表驱动大表](/Users/xialei/Desktop/周阳/mysql高级料/s-b.png)

![说明](/Users/xialei/Desktop/周阳/mysql高级料/illustration.png)

exist: 实际上是in的变种写法; SELECT ... FROM table WHERE EXIST (subquery); 
语法可以理解为，将主查询的数据放在子查询中验证，根据查询结果(True/False)判断是否
保留主查询的结果

e.g. 
1. `select * from tbl_emp e where e.deptId in (select id from tbl_dept d);`
2. `select * from tbl_emp e where exists (select 1 from tbl_dept d where d.id = e.deptId);`
3. `select * from tbl_emp e where exists (select 3 from tbl_dept d where d.id = e.deptId);`
4. `select * from tbl_emp e where exists (select 'X' from tbl_dept d where d.id = e.deptId);`

#### order by: 

1. 应该和索引一致，否则会自己多一次索引排序: Using filesort;最佳左前缀
2. 如果order by用的列并没有用索引列: 用双路排序算法或者单路排序算法；
3. 双路排序算法： 两次扫描，从IO拿去数据
4. 单路排序： 一次扫描，mysql4.1之后的做法，但是取决于sort_buffer的buffer大小，单次
排序可能一次sort_buffer不能容纳足够大小的数据:那么会导致创建临时文件tmp并且后面要合并,
然后再次IO拿数据: 多次IO得不偿失;

优化做法: 
1. 不要select *, 只拿出需要query的字段；
2. sort_buffer大小: 用于排序所需的空间大小
3. max_length_for_sort_data: query的字段大小

---

总结：

索引: 为了查询，或者排序；

#### 为排序使用索引

1. 文件排序，或者扫描有序索引排序；
2. mysql能为排序和查询使用相同的索引；

order by排序不产生Using filesort: 

![为排序使用索引](/Users/xialei/Desktop/周阳/mysql高级料/为排序使用索引.png)