pragma solidity ^0.4.4;

import "UserInfo.sol";
import "Order.sol";
import "Meta.sol";

contract IssueBankData is Meta {
    int txCharge; //手续费
    int currencyCharge; //手续费币种 默认RMB
    
    int public currencyBase; //备付金币种
    int public currentBalance; //备付金金额
    
    int public credit; //备付金授信额度
    
    mapping(bytes32 => address) orders;
    bytes32[] public orderList;
    
    mapping(bytes32 => address) users;
    bytes32[] public usersList;
    event transRetLog(string oper,int256 status,int check_code);
    function IssueBankData(string name,string abi) Meta(name,abi){
    }
    
    function setCurrencyBase(int current_base) public {
        currencyBase = current_base;
    }
    
    function setCurrencyBalance(int current_balance) public {
        currentBalance = current_balance;
    }
    
    function setCredit(int _credit) public {
        credit = _credit;
    }
    
    function getOrder(bytes32 abBizNo) public constant returns(address) {
        return orders[abBizNo];
    }
    
    function getUser(bytes32 user_id) public constant returns(address) {
        return users[user_id];
    }
    
    function addUser(bytes32 account, address user) public {
        users[account] = user;
        
        if(users[account] == 0) {
            return;
        }
        
        if(UserInfo(users[account]).lastUpdate() == 0 ) {
            usersList.push(account);
        }
    }
    
    function addOrder(address addr) public constant returns (int){
        transRetLog("In addOrder",60 ,0);    //add
        return 1;
        Order order = Order(addr);
        bytes32 abBizNo = order.abBizNo();

        orders[abBizNo] = addr;
        if(Order(orders[abBizNo]).txTime() == 0) {
            orderList.push(abBizNo);
        }
        return 0;
    }


     //辅助验证
    function bytes32ToStringDemo(bytes32 x) constant returns (string) {
        bytes memory bytesString = new bytes(32);
        uint charCount = 0;
        for (uint j = 0; j < 32; j++) {
            byte char = byte(bytes32(uint(x) * 2 ** (8 * j)));
            if (char != 0) {
                bytesString[charCount] = char;
                charCount++;
            }
        }
        bytes memory bytesStringTrimmed = new bytes(charCount);
        for (j = 0; j < charCount; j++) {
            bytesStringTrimmed[j] = bytesString[j];
        }
        return string(bytesStringTrimmed);
    }
}
