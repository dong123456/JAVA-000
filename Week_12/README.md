第十二周作业：

*1.（redis 的主从复制，sentinel 高可用，Cluster 集群

主从复制
127.0.0.1:6379> info replication
# Replication
role:slave
master_host:127.0.0.1
master_port:6381
master_link_status:up
master_last_io_seconds_ago:0
master_sync_in_progress:0
slave_repl_offset:20949
slave_priority:100
slave_read_only:1
connected_slaves:0
master_repl_offset:0
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
sentinel 高可用
127.0.0.1:26379> info
# Server
redis_version:3.0.501
redis_git_sha1:00000000
redis_git_dirty:0
redis_build_id:ba05b51e58eb9205
redis_mode:sentinel
os:Windows
arch_bits:64
multiplexing_api:WinSock_IOCP
process_id:4808
run_id:41c919de327f54242606504436fde118289a6e54
tcp_port:26379
uptime_in_seconds:324
uptime_in_days:0
hz:16
lru_clock:15831721
config_file:D:\install\redis\sentinel26379.conf

# Sentinel
sentinel_masters:1
sentinel_tilt:0
sentinel_running_scripts:0
sentinel_scripts_queue_length:0
master0:name=mymaster,status=ok,address=127.0.0.1:6381,slaves=2,sentinels=3
Cluster 集群
redis-trib.rb create --replicas 1 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385
>>> Creating cluster
Connecting to node 127.0.0.1:6380: OK
Connecting to node 127.0.0.1:6381: OK
Connecting to node 127.0.0.1:6382: OK
Connecting to node 127.0.0.1:6383: OK
Connecting to node 127.0.0.1:6384: OK
Connecting to node 127.0.0.1:6385: OK
>>> Performing hash slots allocation on 6 nodes...
Using 3 masters:
127.0.0.1:6380
127.0.0.1:6381
127.0.0.1:6382
Adding replica 127.0.0.1:6383 to 127.0.0.1:6380
Adding replica 127.0.0.1:6384 to 127.0.0.1:6381
Adding replica 127.0.0.1:6385 to 127.0.0.1:6382
M: db4712669500b212790e01e0f3761b30417b1fa9 127.0.0.1:6380
   slots:0-5460 (5461 slots) master
M: af81b3ec8883758a441c277b251477777cf95331 127.0.0.1:6381
   slots:5461-10922 (5462 slots) master
M: 59fd4bf6813665ed781e066a343717d2a0833729 127.0.0.1:6382
   slots:10923-16383 (5461 slots) master
S: bd8addd7b86d6e5d178c66b5441e5ace8b2e67ee 127.0.0.1:6383
   replicates db4712669500b212790e01e0f3761b30417b1fa9
S: 331c145eb0527a93d2e6a4d52b781b039ddf4b0a 127.0.0.1:6384
   replicates af81b3ec8883758a441c277b251477777cf95331
S: a7e8e30b8a70b4eee28523fa56e516edfbeee385 127.0.0.1:6385
   replicates 59fd4bf6813665ed781e066a343717d2a0833729
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join...
>>> Performing Cluster Check (using node 127.0.0.1:6380)
M: db4712669500b212790e01e0f3761b30417b1fa9 127.0.0.1:6380
   slots:0-5460 (5461 slots) master
M: af81b3ec8883758a441c277b251477777cf95331 127.0.0.1:6381
   slots:5461-10922 (5462 slots) master
M: 59fd4bf6813665ed781e066a343717d2a0833729 127.0.0.1:6382
   slots:10923-16383 (5461 slots) master
M: bd8addd7b86d6e5d178c66b5441e5ace8b2e67ee 127.0.0.1:6383
   slots: (0 slots) master
   replicates db4712669500b212790e01e0f3761b30417b1fa9
M: 331c145eb0527a93d2e6a4d52b781b039ddf4b0a 127.0.0.1:6384
   slots: (0 slots) master
   replicates af81b3ec8883758a441c277b251477777cf95331
M: a7e8e30b8a70b4eee28523fa56e516edfbeee385 127.0.0.1:6385
   slots: (0 slots) master
   replicates 59fd4bf6813665ed781e066a343717d2a0833729
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.