其实找sql问题复现然后解决都是这样: 

1. 慢查询日志截取
2. explain
3. show profile
4. DBA: 改服务器性能参数，mysql配置参数调优

### Show Profile

是mysql提供的用来分析当前session中语句执行的资源消耗情况; 可用于sql调优;

- `show variables like 'profiling'; `
- `set profiling=on;`
- `show profiles;`类似`history`
- `show profile cpu,block io for query 1`

性能损耗最大可能的: 

- `converting HEAP to MyISAM`
- `creating tmp table`
- `copying to tmp table on disk`
- `locked`

e.g. 
- `Copying to tmp table: 25.8s`; ~ Using temporary
- `Sorting result: 2s` ~ Using filesort

---

### 全局日志

- `set global general_log=1;`
- `set global log_output= 'TABLE'; `
...
- `select * from mysql.general_log;`

