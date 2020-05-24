### explain

*id*, select_type, table, 索引的*type*, *possible_keys*, *key*, key_len, ref, rows, Extra

分类: 
1. 表的读取顺序: id
2. 数据读取操作数据类型: type;
3. 哪些索引可以使用: possible_keys
4. 哪些索引实际被使用: key
5. 表之间的引用: ref
6. 每张表有多少行被优化器查询: rows
7. 其它: Extra


具体:
1. id: 
    - id相同, 执行顺序从上到下
    - id不同, id越大优先级越高，越先执行；如果是子查询 id序号递增
    
2. select_type
    - simple
    - primary
    - subquery: select, where中子查询
    - derived: from中的子查询, 结果放在临时表中
    - union
    - union result
3. table

4. type: **system > const(PK, 一行满足) > eq_ref(只有一行) > 
ref(可能多行) > `range(片区)` > index > ALL(全表扫描)**
    - const: 通过索引一次就找到，e.g. PK, unique索引: 多行纪录，只有一行满足条件
    - eq_ref: 唯一性索引: 这个索引:表中只有一条纪录
    - ref: 非唯一性索引: 可能多行纪录，查找和扫描的混合体
    - 注: where条件过滤返回一条就是const，返回多条就是ref, 两个还都是单挑出来纪录；而
    range就已经是一片整个找了
    - range
    - index: Full Index Scan; e.g. `select id from t1`
    - ALL: Full Table Scan
    
5. possible_keys: 可能能应用在这个表上的索引，但不一定实际查询使用;

6. key: 真实使用索引情况, NULL:可能是没建索引，或者建了没用
7. key_len: 显示值为索引最大可能长度，并非实际使用长度，一般条件越多越长，查询越精确

8. ref: 显示索引的哪一列被使用了，如果可能是一个常数，

9. rows: 越少越好，表示由索引真实查询的行数

最重要: **id, type, key, rows, Extra**
10. Extra: 
    - Using filesort(文件内排序): 如果查询按照索引的顺序: 借索引的力，优化
    - Using temporary: **order by**, group by, 临时文件存在单独的表中了: 非常耗性能
    - Using index: select操作使用了覆盖索引(Covering index), 不回表, 避免访问了表的数据行, OK;
        - 覆盖索引: 查询列要被建立的索引覆盖
        - 出现using where: 表明索引被用来执行索引KV的查找；
        - 没有using where: 表明索引被用来读取数据而不是执行查找动作;
    - Using where
    - Using buffer: `my.cnf: buffer`
    - *filesort, temporary, index三种最重要*
    
---

后面是mysql-case的案例: 注意 subquery & derived的区别: 有明显outer/inner select的
内层select是subquery, (这个例子中) from (select ...)是derived: 也就是非subquery类型
的select都是derived类型
     