pragma solidity ^0.4.25;

import "./Table.sol";

contract StudentScore {
    event insertResult(int count);
    event updateResult(int count);
    event removeResult(int count);

    function StudentScore() public {
        createTable();
    }

    function createTable() public {
        TableFactory tf = TableFactory(0x1001); 
        // 学生成绩表, key为ame, field为ubject, score
        // | 主键        |  科目                | 成绩           |
        // |------|---------|-------|
        // | name | subject | score |     
        // |------|---------|-------|
        //
        // 创建表
        tf.createTable("t_student_score", "name", "subject,score");
    }

    function openTable() public returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_student_score");
        return table;
    }

    // 根据name查找成绩单
    function select(string name) public constant returns(bytes32[], bytes32[], int[]) {

        Table table = openTable();
        Condition condition = table.newCondition();
        
        Entries entries = table.select(name, condition);
        bytes32[] memory name_list = new bytes32[](uint256(entries.size()));
        bytes32[] memory subject_list = new bytes32[](uint256(entries.size()));
        int[] memory score_list = new int[](uint256(entries.size()));
        
        for(int i=0; i<entries.size(); ++i) {
            Entry entry = entries.get(i);
            
            name_list[uint256(i)] = entry.getBytes32("name");
            subject_list[uint256(i)] = entry.getBytes32("subject");
            score_list[uint256(i)] = entry.getInt("score");
        }
 
        return (name_list, subject_list, score_list);
    }

    // 插入一条成绩记录
    function insert(string name, string subject, int score) public returns(int) {

        Table table = openTable();
        
        Entry entry = table.newEntry();
        entry.set("name", name);
        entry.set("subject", subject);
        entry.set("score", score);
        
        int count = table.insert(name, entry);
        insertResult(count);
        
        return count;
    }

    // 更新记录
    function update(string name, string subject, int score) public returns(int) {

        Table table = openTable();
       
        Entry entry = table.newEntry();
        entry.set("name", name);
        entry.set("subject", subject);
        entry.set("score", score);
        
        Condition condition = table.newCondition();
        condition.EQ("name", name);
        condition.EQ("subject", subject);
        
        int count = table.update(name, entry, condition);
        updateResult(count);
        
        return count;
    }

    // 根据name删除成绩记录
    function remove(string name) public returns(int) {

        Table table = openTable();
       
        Condition condition = table.newCondition();
        condition.EQ("name", name);
        
        int count = table.remove(name, condition);
        removeResult(count);
        
        return count;
    }
}