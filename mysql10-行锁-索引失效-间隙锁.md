行锁操作同一行:

![行锁操作同一行](/Users/xialei/Desktop/周阳/mysql高级料/行锁操作同一行.png)

行锁操作不同行:

![行锁操作不同行](/Users/xialei/Desktop/周阳/mysql高级料/行锁操作不同行.png)

### 无索引: 行锁升级为表锁

![索引失效行锁变表锁](/Users/xialei/Desktop/周阳/mysql高级料/索引失效行锁变表锁.png)

右边修改不同的行的值: 阻塞了; 因为a,b两个字段上面都建了索引，但是因为b左边`where b=4000`
b底层自动类型转换了，所以索引失效

### 间隙锁

用范围条件查找，而不是相等条件检索数据；并且请求共享/互斥锁的时候，InnoDB会给符合条件
的已有所有数据项加锁; 对于Key在这个范围内但是实际上并不真实存在的纪录，叫间隙(Gap);

InnoDB对这种间隙也加锁了，此时要操作这种不存在的key(e.g. insert)也不行: Next-Key锁

"宁可错杀，不可放过"

![间隙锁](/Users/xialei/Desktop/周阳/mysql高级料/间隙锁.png)

所以，id最好是连续的
