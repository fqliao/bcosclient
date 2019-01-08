pragma solidity ^0.4.4;

import "IssueBankInfo.sol";
import "IssueBankData.sol";
import "CheckInfoManager.sol";
import "PopLog.sol";
import "OrderFactory.sol";
import "UserLimit.sol";

contract IssueBank is PopLog, Meta {
    address public m_checkinfo_manager; //场切管理合约
    address public m_info; //信息合约
    address public m_data; //数据合约
    address public m_pop_limit; //限额合约
    address public m_datetime; //日期合约
    address public m_order_factory; //order工厂合约

    function setCheckInfoManager(address addr) public {
        m_checkinfo_manager = addr;
    }
    
    function setInfo(address addr) public {
        m_info = addr;
    }
    
    function setData(address addr) public {
        m_data = addr;
    }
    
    function setPopLimit(address addr) public {
        m_pop_limit = addr;
    }
    
    function setDateTime(address addr) public {
        m_datetime = addr;
    }
    
    function setOrderFactory(address addr) public {
        m_order_factory = addr;
    }
    
    event transRetLog(string oper,int256 status,int check_code);
    event accountBalanceLog(int256 status,int256 balance);
    event consumeLog(int256 tx_amt_1,int256 currency_1,int256 tx_amt_2,int256 currency_2);
    
    function IssueBank(string name,string abi) Meta(name,abi){
    }
    
    /*清算行访问*/
    function currencyBase() public constant returns(int) {
        return IssueBankData(m_data).currencyBase();
    }
    
    function currentBalance() public constant returns(int) {
        return IssueBankData(m_data).currentBalance();
    }
    
    function credit() public constant returns(int) {
        return IssueBankData(m_data).credit();
    }
    
    function setCurrencyBase(int currency_base) public {
        IssueBankData(m_data).setCurrencyBase(currency_base);
    }
    
    function setCurrentBalance(int current_balance) public {
        IssueBankData(m_data).setCurrencyBalance(current_balance);
    }
    
    function setCredit(int credit) public {
        IssueBankData(m_data).setCredit(credit);
    }
    
    function checkPwd() public constant returns(bool) {
		return true;
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
    function doOrderLog(Order order) internal {
    
        bytes32[] memory bytesInputs=new bytes32[](18);
        int[] memory intInputs = new int[](10);

        bytesInputs[0] = order.ibBizNo();
        bytesInputs[1] = order.abBizNo();
        bytesInputs[2] = order.msBizNo();
        bytesInputs[3] = order.orgBizNo();
        bytesInputs[4] = order.userAccount();
        bytesInputs[5] = order.userName();
        bytesInputs[6] = order.fromOrgId();
        bytesInputs[7] = order.toOrgId();
        bytesInputs[8] = order.userAppIp();
        bytesInputs[9] = order.reportCity();
        bytesInputs[10] = order.merchantSvcId();
        bytesInputs[11] =  order.merchantSvcName();
        bytesInputs[12] = order.merchantId();
        bytesInputs[13] = order.merchantName();
        bytesInputs[14] = order.merchantOrgCode();
        bytesInputs[15] = order.mccCode();
        bytesInputs[16]  = order.txTime();
        bytesInputs[17]  = order.storeId();
        intInputs[0] = order.txType();
        intInputs[1] = order.isRefunded();
        intInputs[2] = order.rmbAmt();
        intInputs[3] = order.txAmt();
        intInputs[5] = order.currency();
        intInputs[4] = order.fxRate();
        intInputs[6] = order.exTimestamp();
        intInputs[7] = order.tradeTypeValue();
        intInputs[8] = order.txStatus();
        intInputs[9] = order.checkCode();
        
        int txStatus=order.txStatus();

        orderLog(bytesInputs,intInputs,txStatus);
    }

    function doTransactionReceipt(address order) public returns(int) {
        //用户是否存在
        bytes32 orgID = IssueBankInfo(m_info).orgID();
       
        //bytes32 account = Order(order).userAccount();

        //当前必须是13的消费
        if(Order(order).txType() != 13) {
            transRetLog("doTransactionReceipt", 2002, 0);
            
            return 2002;
        }
        

        //UserInfo userInfo = UserInfo(IssueBankData(m_data).getUser(account));
        CheckInfoManager checkInfoManager = CheckInfoManager(m_checkinfo_manager);

 
        //添加到场切 没有验证
        CheckInfo info = CheckInfo(checkInfoManager.getIssueBankCheckInfo(checkInfoManager.currentCheckCode(), orgID));

        Order(order).setCheckCode(info.checkCode());
        info.addOrder(Order(order).abBizNo());
        transRetLog("doAccountTransaction", 0, info.checkCode());  
        return 1;
    }

    function modifyAccountBalance(bytes32 account,address order,bool isSub) private returns(int)
    {
        UserInfo userInfo = UserInfo(IssueBankData(m_data).getUser(account));
        if(isSub)
        {
            if(userInfo._balance() >= Order(order).txAmt()) {
                userInfo.setBalance(userInfo._balance() - Order(order).txAmt());
                return 0;
            }
            else {
                transRetLog("doTransactionReceipt", 2007, CheckInfoManager(m_checkinfo_manager).currentCheckCode());
                
                return 2007;
            }
        }
        else
        {
            userInfo.setBalance(userInfo._balance() + Order(order).txAmt());
            return 0;
        }
    }
    
    function doAccountBalance(bytes32 account,address order) private returns(int)
    {
        bool subAmount = false;
        bool dealModifyAmount = true;
        if(Order(order).isRefunded() == 0)
        {
            if(Order(order).txType() == 22) {
                subAmount = true;
            }
            else if(Order(order).txType() == 21) {
                subAmount = false;
            }
            else
            {
                dealModifyAmount = false;
            }
        }
        else if(Order(order).isRefunded() == 1)
        {
            if(Order(order).txType() == 22) {
                subAmount = false;
            }
            else if(Order(order).txType() == 21) {
                subAmount = true;
            }
            else
            {
                dealModifyAmount = false;
            }
        }
        if(dealModifyAmount)
        {
            return modifyAccountBalance(account,order,subAmount);
        }
        else
        {
            return 0;
        }
    }

    /*清算行访问*/
    function createVirtualAccount(bytes32 org_id, bytes32 account, bytes32 acc_pwd, int currency, string curr_date, bytes32[5] user_kyc) public returns(address) {
         if(isAccountExists(account) != 2001) {
            //用户已存在
            transRetLog("doAccountTransaction", 2010, 0);
            
            return 0;
        }
        
        UserInfo info = new UserInfo();

        info.setCurrency(currency);
        info.setFirstName(user_kyc[0]);
        info.setSecondName(user_kyc[1]);
        info.setIDType(user_kyc[2]);
        info.setUserID(user_kyc[3]);
        info.addUserCard(user_kyc[4], 1);
        info.setLastUpdate(block.number);
        info.setUserPassword(acc_pwd);
        
        UserLimit userLimit = new UserLimit("", "", m_pop_limit, m_datetime);

        info.setUserLimit(userLimit);
        
        IssueBankData(m_data).addUser(account, info);
        
        transRetLog("createVirtualAccount", 0, 0);

	    newAccount(org_id,account,currency,curr_date,user_kyc[0],user_kyc[1],user_kyc[2],user_kyc[3],user_kyc[4]);
        
        return info;
    }
    
    function isAccountExists(bytes32 account) public constant returns(int) {
        IssueBankData data = IssueBankData(m_data);
        address info = data.getUser(account);
        
        return info != 0 ? 0 : 2001;
    }
    
    function getAccountCardList(bytes32 account) public constant returns(bytes32[]) {
        IssueBankData data = IssueBankData(m_data);
        UserInfo userInfo = UserInfo(data.getUser(account));
        
        bytes32[] memory userCards = new bytes32[](userInfo.getCardListLength());
        for(uint i=0; i<userInfo.getCardListLength(); ++i) {
            var (cardID,) = userInfo.cardList(i);
            
            userCards[i] = cardID;
        }
        
        return userCards;
    }
    
    // function getAccountBalance(bytes32 org_id, bytes32 account) public constant returns(int, int) {
    //     IssueBankData data = IssueBankData(m_data);
    //     UserInfo userInfo = UserInfo(data.getUser(account));
    //     int currency = userInfo.currency();
    //     int _balance = userInfo._balance();
        
    //     return (currency, _balance);
    // }

	function getClearingTrans(bytes32 orgId, int clearCode ,uint offset,uint num)public constant  returns(address[]list ){
        CheckInfo info = CheckInfo(CheckInfoManager(m_checkinfo_manager).getIssueBankCheckInfo(clearCode, orgId));
        uint transListSize = info.getOrderListSize();
        if(offset < transListSize)
        {
                uint canReadSize = transListSize - offset;
                uint readSize = num;
                if(readSize > canReadSize)
                {
                        readSize = canReadSize;
                }
                list = new address[](readSize);
                for(uint i = 0;i < readSize; ++i)
                {
                        list[i] = IssueBankData(m_data).getOrder(info.orderList(offset+i));
                }
                return list;
        }
        else
        {
                return new address[](0);
        }
	}
}
