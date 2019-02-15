
# 权限控制台测试客户端

### 1 解压：
解压：bcosclient_authority2.0.tar.gz，进入dist目录。

### 2.客户端配置：
① 替换dist/conf/ApplicationContext.xml文件中连接实际节点的listen_ip和channel_listen_port,
```
 <list>
    <value>127.0.0.1:20200</value>
 </list>
 ```
② 拷贝节点的所在链的sdk目录下ca.crt、node.crt和node.key到dist/conf目录下。

### 3. 测试客户端的测试合约功能：
测试合约提供创建表t_test以及对t_test表的增删改查操作。
t_test表的字段信息如下：
- 主键：name,
- 其他字段: item_id, item_name

### 4 运行示例
在dist目录下，运行:
- 部署合约DBTest.sol，参数：外部账号地址序号 群组ID 部署合约命令 
    ```
    ./run.sh 1 1 deploy
    ```

- 创建t_test表，参数：外部账号地址序号 群组ID，创建表命令 
    ```
    ./run.sh 1 1 create
    ```
- 插入记录，参数：外部账号地址序号  群组ID  插入命令  name  item_id  item_name
    ```
    ./run.sh 1 1 insert fruit 1 apple1
    ```

- 查询记录，参数：外部账号地址序号  群组ID  查询命令  name
    ```
    ./run.sh 1 1 select fruit
    ```

- 更改记录，参数：外部账号地址序号  群组ID  更改命令  name  item_id  item_name
    ```
    ./run.sh 1 1 update  fruit 1 apple2
    ```
- 删除记录，参数：外部账号地址序号  群组ID  删除命令 name  item_id
    ```
    ./run.sh 1 1 remove fruit 1
    ```