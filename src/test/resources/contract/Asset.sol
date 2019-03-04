pragma solidity ^0.4.25;

import "./Table.sol";

contract Asset {

    event RegisterEvent(int256 ret, string asset_account, uint256 amount);
    event TransferEvent(int256 ret, string from_asset_account, string to_asset_account, uint256 amount);

    function Asset() public {
        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001); 
        // 资产管理表, key : asset_account, field : asset_amount
        // |  资产账户(主键)      |     资产金额       |
        // |-------------------- |-------------------|
        // |  asset_account      |  asset_amount     |     
        // |---------------------|-------------------|
        //
        // 创建表
        tf.createTable("t_asset_management", "asset_account", "asset_amount");
    }

    function openTable() private returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_asset_management");
        return table;
    }

    /*
    描述 : 根据资产账户查询资产金额
    参数 ： 
            asset_account : 资产账户

    返回值：
            第一个参数： 成功返回0, 账户不存在返回-1
            第二个参数： 第一个参数为0时有效，资产金额
    */
    function select(string asset_account) public constant returns(int256, uint256) {

        Table table = openTable();
        Condition condition = table.newCondition();
        condition.EQ("asset_account", asset_account);
        
        Entries entries = table.select(asset_account, table.newCondition());
        uint256 amount = 0;
        if (0 == uint256(entries.size())) {
            return (-1, amount);
        } else {
            Entry entry = entries.get(0);
            return (0, uint256(entry.getInt("asset_amount")));
        }
    }

    /*
    描述 : 资产注册
    参数 ： 
            asset_account : 资产账户
            amount        : 资产金额
    返回值：
            0  资产注册成功
            -1 参数错误
            -1 资产账户已存在
            -2 其他错误
    */
    function register(string asset_account, uint256 amount) public returns(int256){
        int256 ret_code = 0;
        var (ret, temp_amount) = select(asset_account);
        if(ret != 0) {
            Table table = openTable();
            //插入
            Entry entry = table.newEntry();
            entry.set("asset_account", asset_account);
            entry.set("asset_amount", int256(amount));

            int count = table.insert(asset_account, entry);
            if (count == 1) {
                ret_code = 0;
            } else {
                ret_code = -2;
            }
        } else {
            //资产账户已存在
            ret_code = -1;
        }

        RegisterEvent(ret_code, asset_account, amount);

        return ret_code;
    }

    /*
    描述 : 资产转移
    参数 ： 
            from_asset_account : 转移资产账户
            to_asset_account ： 接收资产账户
            amount ： 转移金额
    返回值：
            0  资产转移成功
            -1 转移资产账户不存在
            -2 接收资产账户不存在
            -3 金额不足
            -4 金额溢出
            -5 其他错误
    */
    function transfer(string from_asset_account, string to_asset_account, uint256 amount) public returns(int256) {
        // 查询转移资产账户信息
        int ret_code = 0;
        int256 ret = 0;
        uint256 from_asset_amount = 0;
        uint256 to_asset_amount = 0;
        
        (ret, from_asset_amount) = select(from_asset_account);
        if(ret != 0) {
            ret_code = -1;
            //转移资产的账户不存在
            TransferEvent(ret_code, from_asset_account, to_asset_account, amount);
            return ret_code;

        }

        // 查询接收资产账户信息
        (ret, to_asset_amount) = select(to_asset_account);
        if(ret != 0) {
            ret_code = -2;
            //接收资产的账户不存在
            TransferEvent(ret_code, from_asset_account, to_asset_account, amount);
            return ret_code;
        }

        if(from_asset_amount < amount) {
            ret_code = -3;
            //转移资产的账户金额不足
            TransferEvent(ret_code, from_asset_account, to_asset_account, amount);
            return ret_code;
        } 

        if (to_asset_amount + amount < to_asset_amount) {
            ret_code = -4;
            //接收资产的账户金额溢出
            TransferEvent(ret_code, from_asset_account, to_asset_account, amount);
            return ret_code;
        }

        Table table = openTable();
        Condition condition0 = table.newCondition();
        condition0.EQ("asset_account", from_asset_account);

        //插入
        Entry entry0 = table.newEntry();
        entry0.set("asset_account", from_asset_account);
        entry0.set("asset_amount", int256(from_asset_amount - amount));
        
        int count = table.update(from_asset_account, entry0, condition0);
        if(count != 1) {
            ret_code = -5;
            //更新错误
            TransferEvent(ret_code, from_asset_account, to_asset_account, amount);
            return ret_code;
        }

        Condition condition1 = table.newCondition();
        condition1.EQ("asset_account", to_asset_account);

        Entry entry1 = table.newEntry();
        entry1.set("asset_account", to_asset_account);
        entry1.set("asset_amount", int256(to_asset_amount + amount));
        table.update(to_asset_account, entry1, condition1);

        TransferEvent(ret_code, from_asset_account, to_asset_account, amount);

        return ret_code;

    }
}