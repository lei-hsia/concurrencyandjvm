### redis 主从

主从复制，读写分离；原地待命，反客为主

1. 主从: 配从不配主: e.g. 80: `SLAVEOF 127.0.0.1 6379`
2. 读写分离: e.g. 从: `set k v` : `READONLY:Err`
3. 主: shutdown之后，所有从: 原地待命; 主回来: 恢复
4. 从: shutdown之后回来: 脱离了体系，需要重新配置, 除非配置不是命令行`SLAVEOF xxx`,
而是写进了`redis.conf`;
5. 从手动反客为主: 主: shutdown; 从: `SLAVEOF no one`
6. sentinel: 监控; <=> 从的`SLAVEOF no one`、从投票选出主的自动化; `vi sentinel.conf`
7. CLI: `sentinel monitor <名字> 主IP  主PORT  quorum`
8. 启动sentinel: `redis-sentinel /myredis/redis.conf`
9. 经过sentinel反客为主之后，主回来: 变为slave