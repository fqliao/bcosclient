
contract Authority {
    function insert(string table_name, string addr) public returns(int);
    function remove(string table_name, string addr) public returns(int);
}

contract DBAuthority {

    event insertResult(int count);
    event removeResult(int count);
    
    function insert(string table_name, string addr) public returns(int) {
        Authority authorityTable = Authority(0x1005);
        
        int count = authorityTable.insert(table_name, addr);
        insertResult(count);
        
        return count;
    }

    function remove(string table_name, string addr) public returns(int){
        Authority authorityTable = Authority(0x1005);
        
        int count = authorityTable.remove(table_name, addr);
        removeResult(count);
        
        return count;
    }
}