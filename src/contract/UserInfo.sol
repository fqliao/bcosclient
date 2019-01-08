pragma solidity ^0.4.4;

import "Order.sol";

contract UserInfo {
    bytes32 public account;
    
    bytes32 public userID;
    bytes32 public userPassword;
    int public currency;
    
    int public _balance;
    UserCard[] public cardList;
    address[] public orderList;
    address[] public rollbackOrderList;

    bytes32 public firstName;
    bytes32 public secondName;
    int public cardType;
    bytes32 public cardID;
    
    bytes32 public IDType;

    uint public lastUpdate;
    
    address public userLimit;
    event transRetLog(string oper,int256 status,int check_code);
    struct UserCard {
        bytes32 cardID;
        int tag;
    }
    
    function setAccount(bytes32 _account) public {
        account = _account;
    }
    
    function setUserID(bytes32 user_id) public {
        userID = user_id;
    }
    
    function setUserPassword(bytes32 user_password) public {
        userPassword = user_password;
    }
    
    function setCurrency(int _currency) public {
        currency = _currency;
    }
    
    function setBalance(int balance) public {
        _balance = balance;
    }
    
    function setFirstName(bytes32 first_name) public {
        firstName = first_name;
    }
    
    function setSecondName(bytes32 second_name) public {
        secondName = second_name;
    }
    
    function setIDType(bytes32 id_type) public {
        IDType = id_type;
    }
    
    function setLastUpdate(uint last_update) public {
        lastUpdate = last_update;
    }
    
    function addUserCard(bytes32 cardID, int tag) public {
        cardList.push(UserCard(cardID, tag));
    }
    
    function getCardListLength() public constant returns(uint) {
        return cardList.length;
    }
    
    function addOrder(address order) public constant returns(int){
         transRetLog("in ui 78",Order(order).isRefunded() ,0);    //add
        return 2001;
        if(Order(order).isRefunded() == 0) {//非冲消
            orderList.push(order);
        }
        else {
            rollbackOrderList.push(order);
        }
    }
    
    function getOrderListLength() public constant returns(uint) {
        return orderList.length;
    }
    
    function setUserLimit(address addr) public {
        userLimit = addr;
    }
    
    //function getUserPassword()
    
}

