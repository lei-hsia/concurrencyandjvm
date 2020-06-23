### keepalived

[LVS 4工作模式和10种调度算法](https://www.jianshu.com/p/86c618c40234)

[LVS-DR集群ipvsadm脚本配置VS,RS](https://www.jianshu.com/p/1a753906bb88)

[keepalived.org](www.keepalived.org)

keepalived核心:
    - VRRP协议
    - 竞选机制
    - heartbeat
    
自己总结的:  
1. LVS集群本身用来做LB，自己没有HA，也不能知道集群运行状态是什么样的;
2. keepalived: 通过VRRP协议, keepalived高可用对之间通信
3. VRRP: 通过"竞选"机制, 得到集群种VRRP服务器的Master, 从而实现集群的HA
4. heartbeat: 通过心跳让backups都知道Master还在，Master向所有热备的backups
一直发送VRRP广播包; 

keepalived实际上有2个功能: 
1. `keepalived.conf`配置实现LVS功能(directors failover)
2. 管理LVS，对集群做健康检查(cluster nodes healthchecks) 

keepalived安装和配置:
1. [安装](https://www.keepalived.org/download.html)

