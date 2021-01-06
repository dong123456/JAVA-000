第十二周作业：

*（redis 的主从复制，sentinel 高可用，Cluster 集群

主从复制
1. 创建3个Redis镜像

docker run --name redis-master -p 6379:6379 -d redis redis-server - 主
docker run --name redis-slave-1 -p 6380:6379 -d redis redis-server - 从1
docker run --name redis-slave-2 -p 6381:6379 -d redis redis-server - 从2
