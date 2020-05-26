```roomsql
CREATE TABLE staffs(
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(24)NOT NULL DEFAULT'' COMMENT'姓名',
`age` INT NOT NULL DEFAULT 0 COMMENT'年龄',
`pos` VARCHAR(20) NOT NULL DEFAULT'' COMMENT'职位',
`add_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'入职时间'
)CHARSET utf8 COMMENT'员工记录表';
INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('z3',22,'manager',NOW());
INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('July',23,'dev',NOW());
INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('2000',23,'dev',NOW());

ALTER TABLE staffs ADD INDEX index_staffs_nameAgePos(`name`,`age`,`pos`)
```

避免索引失效: 

1. 最佳左前缀法则: 建什么索引就要从它开始,中间也不能断;所以要用最后一个索引，
也要从前面所有的开始; "带头大哥不能死，中间兄弟不能断": 要查最后的结果，
中间的过程对mysql也很重要
![prefix](/Users/xialei/Desktop/prefix.png)
2. 不在索引上做任何操作，否则导致索引失效并转向全表扫描: "索引列上不计算"
![noop](/Users/xialei/Desktop/noop.png) 

3. 范围之后全失效: `where name='July' and age > 25;`: name用于检索，age只是排序

4. 尽量使用覆盖索引, 少用 select *; 会在type, Extra, key_len上都会有更好的表现: 
![cover](/Users/xialei/Desktop/cover.png)
5. 不要用! <>, 无法使用索引导致全表扫描
![!](/Users/xialei/Desktop/!.png)
6. is null, is not null: 不能使用索引
7. like: '%July%','%July','July%'✅: like的百分号只写在右边;左边相当于精确匹配; 如果非两边都用，
那么建立**覆盖索引**，避免索引失效，避免全表扫描; i.e. *覆盖索引解决like %%的问题, 不过必须是按建索引的顺序,
如果是where条件，中间是可以有and连接的*

![cover1](/Users/xialei/Desktop/cover1.png)
8. 字符串varchar类型不加单引号: 发生了隐式类型转换: 2: baka

索引 总结: 
- 带头大哥不能死
- 中间兄弟不能断
- 索引列上不计算
- like百分加右边
- 范围之后全失效
- varchar引号不能丢

案例: 

![eg](/Users/xialei/Desktop/e.g..png)

注: mysql这些高效索引, 也可以用在mybatis中

---

order by: 
    - c2, c3: 顺序，用于查找; using where;
    - c3, c2: 先排序再查找: Using filesort;
    
group by: 分组，必排序;
    - c2 c3: 只用c1, etc.查找；
    - c3,c2: Using temporary

---

supplementary examples:

1. where a = 3 and b like 'kk%' and c = 4; // 93
2. where a = 3 and b like '%kk' and c = 4; // 31
3. where a = 3 and b like '%kk%' and c = 4; // 31
4. where a = 3 and b like 'k%kk%' and c = 4; // 93