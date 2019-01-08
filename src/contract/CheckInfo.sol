pragma solidity ^0.4.4;
import "Meta.sol";

contract CheckInfo is Meta {
    int public checkCode;
    int public checkCodeStatus;
    bytes32[] public orderList;
    int public accrual;
    int public txCharge;
    int public currencyCharge;
    int public accrualAcquirer;
    int public accrualIssuing;
    
    int public status;
    int public syncStatus;
    bytes32 public syncPath;
    // update 
    int public lastCheckCodeStatus = 0;//设置可以进行清算标识
    int public lastCheckCode;  //清算场次标识
    bytes32 public walletOwnerOrg;  //境外合作行的机构id，用于区分不同境外合作行的清算 "BOCHK"
    int public totalRmbAmt; //lastCheckCode对应的场次所有订单累加的人民币金额，精度到分
    int public totalTxAmt; 
    int public currency; 
    int public wbBalance;//清算完成后，WeBank备付金余额(单位，RMB)
    int public timestamp;
    int[] public checkCodeList;//场切码列表

    event debugOrderLog(int length);

    function CheckInfo(address meta) Meta("",""){
        setMetaAddress(meta);
    }
    
    function addOrder(bytes32 order) public {
        // debugOrderLog(1024);
        // debugOrderLog(int256(orderList.length));
        orderList.push(order);
        // debugOrderLog(int256(orderList.length));
    }
    
    function setCheckCode(int check_code) public {
        checkCode = check_code;
    }
    
    function setAccrual(int _accrual) public {
        accrual = _accrual;
    }
    
    function setTxCharge(int tx_charge) public {
        txCharge = tx_charge;
    }
    
    function setCurrencyCharge(int currency_charge) public {
        currencyCharge = currency_charge;
    }
    
    function setAccrualAcquirer(int accrual_acquirer) public {
        accrualAcquirer = accrual_acquirer;
    }
    
    function setAccrualIssuing(int accrual_issuing) public {
        accrualIssuing = accrual_issuing;
    }
    
    function setStatus(int _status) public {
        status = _status;
    }
    //update
    function setCheckCodeStatus()public {
        checkCodeStatus = 1;
    }

    function setSyncStatus(int sync_status) public {
        syncStatus = sync_status;
    }
    
    function setSyncPath(bytes32 sync_path) public {
        syncPath = sync_path;
    }

    function getOrderListSize() public constant returns(uint) {
        return orderList.length;
    }
    function setLastCheckCode(int _lastCheckCode) public constant returns(uint) {
        // checkCodeList.push(_lastCheckCode);
        lastCheckCode = _lastCheckCode;
    }
    //update set the tag
    // function setLastCheckCodeStatus(int _lastCheckCodeStatus) public constant returns(uint){
    //     _lastCheckCodeStatus = 1;
    // }
    function setWalletOwnerOrg(bytes32 _walletOwnerOrg) public {
        walletOwnerOrg = _walletOwnerOrg;
    }
    function setTotalRmbAmt(int _totalRmbAmt) public {
        totalRmbAmt = _totalRmbAmt;
    }
    function setTotalTxAmt(int _totalTxAmt) public {
        totalTxAmt = _totalTxAmt;
    }
    function setCurrency(int _currency) public {
        currency = _currency;
    }
    function setWbBalance(int _wbBalance) public {
        wbBalance = _wbBalance;
    }
    function setTimestamp(int _timestamp) public {
        timestamp = _timestamp;
    }
    // function checkListSize() public returns(uint) {
    //     return checkCodeList.length;
    // }
}
