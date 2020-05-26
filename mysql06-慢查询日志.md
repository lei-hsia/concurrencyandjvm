1. 具体sql分析: explain
2. 找慢sql:
    - `set global long_query_time=3;`
    - `show variables like 'long_query_time%';`
    - `cd /var/lib/mysql/db-slow.log`
3. mysqldumpslow: 日志分析工具:从log中抓取信息
    - e.g. `mysqldumpslow -s r -t 10 /var/lib/mysql/db-slow.log`
