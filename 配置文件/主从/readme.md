##2台mysql8.0.12 uuids不能一样
# 开启binlog
##### 主开启binlog，设置服务id，设置需要同步的数据库，屏蔽系统库同步
 `log-bin=mysql-bin
 server-id=1
 binlog-do-db=user_db
 binlog-ignore-db=mysql
 binlog-ignore-db=information_schema
 binlog-ignore-db=performance_schema`

##### 从 配置
`log-bin=mysql-bin
server-id=2
replicate_wild_do_table=user_db.%
replicate_wild_ignore_table=mysql.%
replicate_wild_ignore_table=information_schema.%
replicate_wild_ignore_table=performance_schema.%
`

# 主创建同步账户并授权
`CREATE USER 'db_sync'@'%' IDENTIFIED BY 'db_sync';
GRANT REPLICATION SLAVE ON *.* TO 'db_sync'@'%' ;
FLUSH PRIVILEGES;`
#### 查询binlogfilename 和pos位置
`SHOW MASTER STATUS;`

#### 从数据库配置同步
`STOP SLAVE;
 CHANGE MASTER TO
 MASTER_HOST = 'localhost',
 MASTER_USER = 'db_sync',
 MASTER_PASSWORD = 'db_sync',
 MASTER_PORT=3306,
 MASTER_LOG_FILE = 'mysql-bin.000002',
 MASTER_LOG_POS = 1639;
 START SLAVE;
 `
#### 查询同步是否成功
`SHOW SLAVE STATUS`


