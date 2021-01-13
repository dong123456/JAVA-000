### 第十二周作业：

### *（redis 的主从复制，sentinel 高可用，Cluster 集群

### 1. 主从复制
### 1.1. 将redis中redis.conf文件拷贝两份，修改端口号为6380和6381
具体配置如下：
```
port 6380
daemonize yes
slaveof 127.0.0.1 6379
replica-read-only yes
```

### 1.2. 打开三个终端，分别启动6379、6380、6381节点服务
```
redis-server redis.conf
redis-server redis6380.conf
redis-server redis6381.conf
```

### 1.3. 使用redis-cli -p端口   进入redis客户端，使用info replication命令查看当前状态
```
info relication
```

### 1.4. 验证主从, 进入master 6379， 观测主从同步
```
set test1 aaa
```

### 2. 哨兵模式 配置sentinel

### 2.1. 将redis安装目录下sentinel.conf文件拷贝两份，修改端口号
具体配置如下：
```
daemonize yes
port 26379
protected-mode no
sentinel monitor macrog-master 127.0.0.1 6379 2
```

### 2.2. 启动哨兵命令
```
redis-server sentinel.conf --sentinel
```

### 2.3. 登入查看集群信息
```
redis-cli -p 26379
sentinel master macrog-master //查看master状态
sentinel slaves macrog-master //查看slaves状态
sentinel get-master-addr-by-name macrog-master 
info sentinel //查看哨兵信息
```

### 2.4. 模拟哨兵节点挂掉
```
将master服务kill掉， 观测其中一个slave变成了主
```






