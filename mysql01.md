mysql 配置文件;

- `frm`
- `myd`
- `myi`

#### server存储引擎的四层架构: 
插件式的存储引擎架构将**查询处理**和其它**系统任务**和**数据存储提取**分离

- `Connectors`(client)
- `Server` components: (following)
- `Connection Pool`(链接层)
- `SQL interface` (服务层:4部分)
- `parser`
- `optimizer`
- `Cache & Buffer`(4部分跟Hive很像: `Compiler, Parser, Optimizer, Executor`)
- `Pluggable Storage Engines` (引擎层)
- `FS` (存储层)

连接层，服务层，引擎层，存储层，实际上跟Controller-Service-Dao很像;

InnoDB是引擎中唯一一个支持transaction的，对应用Seata支持分布式事务支持

MyiSAM关注性能，InnoDB关注事务；因为MyiSAM多查少改变，所以少commit, 其实类似hive/HDFS; 
InnoDB经常改所以查询性能不如MyISAM

- Percona: XtraDB: 引擎性能明显优于InnoDB;
- Alibaba: AliSql, AliRedis

---
index: 

e.g. id,name,email,wechatNum;  `select * from user where name=' '`:
`create index idx_user_name on user(name)`

SQL性能下降原因:
1. 查询语句写得烂
2. 索引失效
3. 关联查询join太多
4. 服务器调优以及各个参数设置(buffer, #threads, etc)

SQL执行顺序: 
1. 手写: select from join on where group by having order by limit
2. 机读: from, on, join, where, group by, having, select, order by, limit
