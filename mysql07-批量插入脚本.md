### create function
```roomsql
DELIMITER $$

CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
BEGIN
    DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    DECLARE return_str VARCHAR(255) DEFAULT '';
    DECLARE i INT DEFAULT 0;
    WHILE  i < n DO
    SET return_str = CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52,1)));
    SET i = i + 1;
    END WHILE;
    RETURN return_str;
END$$
```

### create PROCEDURE
```roomsql
DELIMITER $$

CREATE PROCEDURE insert_dept(IN START INT(10), IN max_num INT(10))
BEGIN
    DECLARE i INT DEFAULT 0;
    SET autocommit = 0;
    REPEAT
        SET i = i + 1;
        INSERT INTO dept (deptno, dname, loc) VALUES ((START+i),rand_string(10),rand_string(8));
        UNTIL i = max_num
    END REPEAT;
    COMMIT;
END $$

DELIMITER;
CALL insert_emp(100001, 500000); 
```

这个PROCEDURE的定义(start, max_num)类似C++中的substr(start, len)而不是java中的substring(start, end)

插入: 

- 50w条: 78s; 500w: 1700s ~ 30min
- select * (50w): 1.52s;
- 超过1000w: mysql性能明显下降: 需explain, 开启mysqldumpslow之类的分析; 或者show profiles; 或者用hive

---

每个核心系统都有另外的辅助监控系统看着