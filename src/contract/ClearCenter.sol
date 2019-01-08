pragma solidity ^0.4.4;

import "ClearCenterInfo.sol";
import "ClearCenterData.sol";
import "OrderFactory.sol";
import "Order.sol";
import "IssueBank.sol";
import "AcquirerBank.sol";
import "CheckInfoManager.sol";
import "CheckInfo.sol";
import "Meta.sol";

//return error code with prefix 20100
contract ClearCenter is Meta {

	address public checkinfoManager; //场切管理合约
    address public clearCenterInfo; //信息合约
    address public clearCenterData; //数据合约
   		
    function setCheckInfoManager(address addr) public {
        checkinfoManager = addr;
    }
    
    function setInfo(address addr) public {
        clearCenterInfo = addr;
    }
    
    function setData(address addr) public {
        clearCenterData = addr;
    }

    event transRetLog(string oper,int256 status,int check_code);
	// event debugRetLog(bytes32 msg1,int256 msg2,int msg3);
	event accountBalancetLog(int256 status,int256 balance);
	event consumeLog(int256 tx_amt_1,int256 currency_1,int256 tx_amt_2,int256 currency_2);
	event orgAccrual(int256 accrualAcquirer,int256 accrualIssuing);

	event debugLog(string msg);
	event debugOrderLog(int length);

	function ClearCenter(string name,string abi,address infoAddr,address dataAddr,address checkInfoAddr) Meta(name,abi){
		clearCenterInfo = infoAddr;
		clearCenterData = dataAddr;
		checkinfoManager = checkInfoAddr;
		bytes32 reqID;
		newCheckCodeTag(reqID);
	}

    //注册机构信息，机构标识-机构行为合约
    function registerOrg(int orgType,bytes32 orgId,address orgAddr,int balance,int credit,int currencyBase) public{
    	int iRet = ClearCenterData(clearCenterData).registerOrg(orgId,orgAddr);
    	if( orgType == 1)
    	{
    		//IssueBank  发卡行
    		IssueBank(orgAddr).setCredit(credit);
    		IssueBank(orgAddr).setCurrentBalance(balance);
    		IssueBank(orgAddr).setCurrencyBase(currencyBase);
    		CheckInfoManager(checkinfoManager).getClearCenterCheckInfo(CheckInfoManager(checkinfoManager).currentCheckCode(),orgId);
    	}
    	else if (orgType == 2) {
    		//AcquirerBank  收单行
    		AcquirerBank(orgAddr).setCredit(credit);
    		AcquirerBank(orgAddr).setCurrentBalance(balance);
    		AcquirerBank(orgAddr).setCurrencyBase(currencyBase);
    		CheckInfoManager(checkinfoManager).getClearCenterCheckInfo(CheckInfoManager(checkinfoManager).currentCheckCode(),orgId);
    	}
    	transRetLog("registerOrg",iRet,0);
    }

    //TODO 机构创建,目前不用，机构的创建由合约部署时初始化
    //日期，机构ID，机构名称，初始余额，授信额度，币种
    // function createBankAccount(string date,bytes32 orgId,string orgName,int balance,int credit,int currencyBase) public
    // {

    // }
	
	//设置汇率
	function setExchangeRate(int currency,int fxRate,int refundFxRate ,int exTimeStamp, int currencyUnit,uint[3] exDataArr,bytes32[3]  exDataStr0) public {
			int iRet = ClearCenterData(clearCenterData).setExchangeRate(currency ,fxRate ,refundFxRate ,exTimeStamp,currencyUnit,exDataArr, exDataStr0);
			transRetLog("setExchangeRate",iRet,0);
	}

	//获取汇率
	 function getExchangeRate(int currency) public constant returns(int,int,int,int,int,uint[],bytes32[]){

	 	int[5] memory exchangeRateArr;
		exchangeRateArr = ClearCenterData(clearCenterData).getExchangeBochkRatePart1(currency);
	 	uint[3] memory exDataArr1;
	 	bytes32[3] memory exDataStr1;
	        (exDataArr1,exDataStr1)	=ClearCenterData(clearCenterData).getExchangeBochkRatePart2(currency);
	 	uint[] memory exDataArrRate;
	 	bytes32[] memory exDataStrRate;
	 	exDataArrRate = new uint[](3);
	 	exDataStrRate = new bytes32[](3);
	 	return (exchangeRateArr[0], exchangeRateArr[1], exchangeRateArr[2], exchangeRateArr[3], exchangeRateArr[4], exDataArrRate, exDataStrRate);

	 }

	//机构备付金余额变更,预留，orgType ：1-IssueBank  发卡行  2-AcquirerBank  收单行
	function modifyBankAmount(int orgType,bytes32 orgID,int balance) public {
		address orgAction = ClearCenterData(clearCenterData).getOrgAction(orgID);
		if( orgAction == 0)
		{
			debugLog("ClearCenter modifyBankAmount,no exists orgAction.");
			transRetLog("modifyBankAmount",3001,0);
			return;
		}
    	if( orgType == 1)
    	{
    		//IssueBank  发卡行
    		IssueBank(orgAction).setCurrentBalance(balance);
    	}
    	else if( orgType == 2 )
    	{
    		//AcquirerBank  收单行
    		AcquirerBank(orgAction).setCurrentBalance(balance);
    	}
    	transRetLog("modifyBankAmount",0,0);
	}

	//设置机构备付金的授信额度;预留，orgType ：1-IssueBank  发卡行  2-AcquirerBank  收单行
	function modifyBankCredit(int orgType,bytes32 orgID,int credit) public {
		address orgAction = ClearCenterData(clearCenterData).getOrgAction(orgID);
		if( orgAction == 0)
		{
			debugLog("ClearCenter modifyBankCredit,no exists orgAction.");
			transRetLog("modifyBankCredit",3001,0);
			return;
		}
    	if( orgType == 1)
    	{
    		//IssueBank  发卡行
    		IssueBank(orgAction).setCredit(credit);
    	}
    	else if( orgType == 2 )
    	{
    		//AcquirerBank  收单行
    		AcquirerBank(orgAction).setCredit(credit);
    	}
    	transRetLog("modifyBankCredit",0,0);
	}

	//设置清算中心清算结果状态
	function setClearingStatus(int lastCheckCode,int retStatus, bytes32 walletOwnerOrg,int totalRmbAmt,int totalTxAmt,int currency,int wbBalance,int timestamp) public {
		CheckInfoManager(checkinfoManager).setAllOrgCheckStatus(ClearCenterInfo(clearCenterInfo).orgID(),lastCheckCode,retStatus, walletOwnerOrg,totalRmbAmt, totalTxAmt,currency,wbBalance,timestamp);
		transRetLog("setClearingStatus",0,lastCheckCode);
	}

	//获取指定场次的清算结果状态
	function getClearingStatus(int checkCode) public constant returns(int,bytes32,int,int,int,int,int) {
		//checkinfo
		//TODO : 未来可能要加上
		int currentCheckCode = CheckInfoManager(checkinfoManager).currentCheckCode();
		if(checkCode > currentCheckCode){
		    //可能的原因是因为 拉取的节点的区块没有同步到最新
			return (20120,"",0,0,0,0,0);
		} else if (checkCode == currentCheckCode) {
			//可能的原因是因为 拉取的节点的区块没有同步到最新
			return (20121,"",0,0,0,0,0);
		}
		return CheckInfoManager(checkinfoManager).getRetOrgCheckStatus(ClearCenterInfo(clearCenterInfo).orgID(),checkCode);

	}
// not use
	function newCheckCodeTag(bytes32 reqId) private returns(int){
		//设置新场切
		int lastCheckCode = CheckInfoManager(checkinfoManager).currentCheckCode();
		int currCheckCode = int(block.number);
		if( lastCheckCode == 0)
		{
			lastCheckCode = currCheckCode;
		}

		CheckInfoManager(checkinfoManager).setCheckCode(currCheckCode);
		CheckInfoManager(checkinfoManager).getClearCenterCheckInfo(currCheckCode,ClearCenterInfo(clearCenterInfo).orgID());
        uint orgListSize = ClearCenterData(clearCenterData).getOrgListSize();
			for(uint j=0;j<orgListSize;++j){
				CheckInfoManager(checkinfoManager).getClearCenterCheckInfo(currCheckCode,ClearCenterData(clearCenterData).getOrgByIndex(j));
			}
		ClearCenterData(clearCenterData).setCheckTagReq(reqId,lastCheckCode);
		transRetLog("setCheckCodeTag",0,lastCheckCode);
		return lastCheckCode;
	}
	//可以进行拉去场切文件 (in use)
	function updateCheckCodeStatus(bytes32 orgId,int checkCode)public {
		CheckInfoManager checkInfoManager = CheckInfoManager(checkinfoManager);
		int status = checkInfoManager.setCheckCodeStatus(orgId, checkCode);
		int statusFlag = checkInfoManager.getCheckCodeStatus(orgId, checkCode);
		if(status == 0) {
           transRetLog("updateCheckCodeStatus", 0 , statusFlag);
		} else {
           transRetLog("updateCheckCodeStatus", 20125 , statusFlag);
		}
		
	}
	//change return currentCheckCode,lastCheckCode  (in use)
	// function setCheckCodeTag2(bytes32 reqId) public returns(int,int){
	// 	int checkCode = ClearCenterData(clearCenterData).getCheckTagReq(reqId);
	// 	CheckInfoManager checkInfoManager = CheckInfoManager(checkinfoManager);
    //     CheckInfo info = CheckInfo(checkInfoManager.getCheckInfo(checkCode, ClearCenterInfo(clearCenterInfo).orgID()));
	// 	if(checkCode != 0)
	// 	{
	// 		//已经存在,获取上一场场切
	// 		transRetLog("setCheckCodeTag",20122,checkCode);
	// 		return (info.currentCheckCode(),checkCode);
	// 	}
	// 	else
	// 	{
	// 		checkCode = newCheckCodeTag(reqId);
	// 	}
	// 	//获取交易数据
	// 	transRetLog("setCheckCodeTag",20122,checkCode);
    //     orgAccrual(info.accrualAcquirer(),info.accrualIssuing());
	// 	return (info.currentCheckCode(),checkCode);
	// }
	// function setCheckCodeTag(bytes32 reqId) public {
	// 	int checkCode = ClearCenterData(clearCenterData).getCheckTagReq(reqId);
	// 	CheckInfoManager checkInfoManager = CheckInfoManager(checkinfoManager);
    //     CheckInfo info = CheckInfo(checkInfoManager.getCheckInfo(checkCode, ClearCenterInfo(clearCenterInfo).orgID()));
	// 	if(checkCode != 0)
	// 	{
	// 		//已经存在,获取上一场场切
	// 		transRetLog("setCheckCodeTag",20122,checkCode);
	// 	}
	// 	else
	// 	{
	// 		checkCode = newCheckCodeTag(reqId);
	// 	}
	// 	//获取交易数据
	// 	transRetLog("setCheckCodeTag",20122,checkCode);
    //     orgAccrual(info.accrualAcquirer(),info.accrualIssuing());
	// }

	//消费操作
	function doTransaction(address orderAddr) public returns(int status)
	{
		ClearCenterData(clearCenterData).addTrans(orderAddr);
		status = 1;
		return status;
	}

	// function addTransToCheckInfo(address orderAddr,bool isReverse) public
	// {
	// 	//流水写入指定场切 并计算发生额
	// 	CheckInfo info = CheckInfo(CheckInfoManager(checkinfoManager).getClearCenterCheckInfo(CheckInfoManager(checkinfoManager).currentCheckCode(), ClearCenterInfo(clearCenterInfo).orgID()));
	// 	//添加流水
	// 	 //Order(order).setCheckCode(info.checkCode());
	//     info.addOrder(Order(orderAddr).abBizNo());
	// }




	//查询机构备付金余额,orgType ：1-IssueBank  发卡行  2-AcquirerBank  收单行
	function getBankAmount(int orgType,bytes32 orgId) public constant returns(int){
		address orgAction = ClearCenterData(clearCenterData).getOrgAction(orgId);
		if( orgType == 1)
    	{
    		//IssueBank  发卡行
    		return IssueBank(orgAction).currentBalance();
    	}
    	else if( orgType == 2 )
    	{
    		//AcquirerBank  收单行
    		return AcquirerBank(orgAction).currentBalance();
    	}
	}
	

   	//获取指定机构的清算结果,orgType : 1-IssueBank ; 2-AcquirerBank 
	//not use
   	function getClearingResult(int orgType,bytes32 orgId,int clearCode)public constant returns(int ,int ,int){
        CheckInfoManager checkInfoManager = CheckInfoManager(checkinfoManager);
        CheckInfo info = CheckInfo(checkInfoManager.getClearCenterCheckInfo(clearCode, ClearCenterInfo(clearCenterInfo).orgID()));

        if( orgType == 1)
    	{
    		//IssueBank  发卡行
        	CheckInfo issueBankCheckInfo = CheckInfo(checkInfoManager.getIssueBankCheckInfo(clearCode, orgId));
        	return (info.checkCode(),info.accrualIssuing(),issueBankCheckInfo.txCharge());
    	}
    	else if( orgType == 2 )
    	{
    		//AcquirerBank  收单行
        	CheckInfo acquirerBankCheckInfo = CheckInfo(checkInfoManager.getAcquirerBankCheckInfo(clearCode, orgId));
    		return (info.checkCode(),info.accrualAcquirer(),acquirerBankCheckInfo.txCharge());
    	}
   	}

   	//设置指定场次的对账文件拉取结果状态
   	function setTransFileTag(int clearCode,int fileTagResult,bytes32 filePath)public{
   		CheckInfoManager checkInfoManager = CheckInfoManager(checkinfoManager);
        CheckInfo info = CheckInfo(checkInfoManager.getClearCenterCheckInfo(clearCode, ClearCenterInfo(clearCenterInfo).orgID()));
        info.setSyncStatus(fileTagResult);
        info.setSyncPath(filePath);
        transRetLog("setTransFileTag",0,checkInfoManager.currentCheckCode());
   	}
}
