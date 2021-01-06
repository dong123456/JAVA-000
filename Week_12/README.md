### 第十二周作业：

### *（redis 的主从复制，sentinel 高可用，Cluster 集群

### 主从复制
### 1. 将redis中redis.conf文件拷贝两份，修改端口号为6380和6381
具体配置如下：
```
port 6380
daemonize yes
slaveof 127.0.0.1 6379
replica-read-only yes
```

### 2. 打开三个终端，分别启动6379、6380、6381节点服务
```
redis-server redis.conf
redis-server redis6380.conf
redis-server redis6381.conf
```

### 3. 使用redis-cli -p端口   进入redis客户端，使用info replication命令查看当前状态
```
info relication
```

### 4. 验证主从, 进入master 6379， 观测主从同步
```
set test1 aaa
```






