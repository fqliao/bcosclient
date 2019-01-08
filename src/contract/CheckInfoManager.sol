pragma solidity ^0.4.4;
import "CheckInfo.sol";
import "Meta.sol";

//error code start from 20400
contract CheckInfoManager is Meta {
    int private m_current_check_code;
    // int private m_check_code_status = 0;
    mapping(int => mapping(bytes32 => address) ) private m_check_map;
    int[] public m_check_code_list;

    address public check_info_meta;
    function CheckInfoManager(string name,string abi,string check_info_name,string check_info_abi) Meta(name,abi){
        check_info_meta=new Meta(check_info_name,check_info_abi);
    }
    
    function setCheckCode(int check_code) {
        m_current_check_code = check_code;
        m_check_code_list.push(check_code);
    }

     event transRetLog(string oper,int256 status,int check_code);

    //checkCodeList
    
    function currentCheckCode() public constant returns(int) {
        return m_current_check_code;
    }
    function getCheckCodeByIndex(uint offset) public constant returns(int) {
        	//return uint256(m_check_code_list[offset]);
            return m_check_code_list[offset];
    }
    function getCheckCodeListLength() public constant returns(uint) { 
        return m_check_code_list.length;
    }
    //获取发卡行场切数据
    function getIssueBankCheckInfo(int check_code, bytes32 org_id) public returns(address){
        return getCheckInfo(check_code, org_id);
    }
    
    //获取收单行场切数据
    function getAcquirerBankCheckInfo(int check_code, bytes32 org_id) public returns(address) {
        return getCheckInfo(check_code, org_id);
    }
    
    //获取清算行场切数据
    function getClearCenterCheckInfo(int check_code, bytes32 org_id) public returns(address) {
        return getCheckInfo(check_code, org_id);
    }
    //added by crisiticmei 
    function getCheckCodeInfoByCheckCode(int check_code, bytes32 org_id) public returns(address) {
        address info = m_check_map[check_code][org_id];
        return info;
    }

    function getCheckInfo(int check_code, bytes32 org_id) public returns(address) {
        address info = m_check_map[check_code][org_id];
        if(info == 0) {
            info = new CheckInfo(check_info_meta);
            CheckInfo(info).setCheckCode(check_code);
//            CheckInfo(info).setCheckCode(m_current_check_code);
            CheckInfo(info).setTxCharge(1);
            m_check_map[check_code][org_id] = info;
        }
        
        return info;
    }
    //update set多个状态码
    function setAllOrgCheckStatus(
        bytes32 orgId,
        int lastCheckCode,
        int retStatus,
        bytes32 walletOwnerOrg,
        int totalRmbAmt,
        int totalTxAmt,
        int currency,
        int wbBalance,
        int timestamp) public {

        address checkInfoAddress = getClearCenterCheckInfo(lastCheckCode,orgId);
        CheckInfo(checkInfoAddress).setLastCheckCode(lastCheckCode);
        CheckInfo(checkInfoAddress).setStatus(retStatus);
        CheckInfo(checkInfoAddress).setWalletOwnerOrg(walletOwnerOrg);
        CheckInfo(checkInfoAddress).setTotalRmbAmt(totalRmbAmt);
        CheckInfo(checkInfoAddress).setTotalTxAmt(totalTxAmt);
        CheckInfo(checkInfoAddress).setCurrency(currency);
        CheckInfo(checkInfoAddress).setWbBalance(wbBalance);
        CheckInfo(checkInfoAddress).setTimestamp(timestamp);
    }

    function setOrgCheckStatus(bytes32 orgId,int checkCode,int retStatus) public
    {
        CheckInfo(getClearCenterCheckInfo(checkCode,orgId)).setStatus(retStatus);
    }

    function getOrgCheckStatus(bytes32 orgId,int checkCode) public constant returns(int)
    {
        return CheckInfo(getClearCenterCheckInfo(checkCode,orgId)).status();
    }
    //update 获取多个状态码
    function getAllOrgCheckStatus(bytes32 orgId,int checkCode) public constant returns(int,int,bytes32,int,int,int,int,int)
    {
        address checkInfoAddress = getClearCenterCheckInfo(checkCode,orgId);
        // int status = CheckInfo(checkInfoAddress).status();
        // int lastCheckCode = CheckInfo(checkInfoAddress).lastCheckCode();
        // bytes32 walletOwnerOrg = CheckInfo(checkInfoAddress).walletOwnerOrg();
        // int totalRmbAmt = CheckInfo(checkInfoAddress).totalRmbAmt();
        // int totalTxAmt = CheckInfo(checkInfoAddress).totalTxAmt();
        // int wbBalance = CheckInfo(checkInfoAddress).wbBalance();

        return (CheckInfo(checkInfoAddress).status(),CheckInfo(checkInfoAddress).lastCheckCode(),CheckInfo(checkInfoAddress).walletOwnerOrg(),CheckInfo(checkInfoAddress).totalRmbAmt(),CheckInfo(checkInfoAddress).totalTxAmt(),CheckInfo(checkInfoAddress).currency(),CheckInfo(checkInfoAddress).wbBalance(),CheckInfo(checkInfoAddress).timestamp());
    }

    //add by crisiticmei 
    function getRetOrgCheckStatus(bytes32 orgId,int checkCode) public constant returns(int,bytes32,int,int,int,int,int) {
        address checkInfoAddress = getCheckCodeInfoByCheckCode(checkCode,orgId);
        if(0x0000000000000000000000000000000000000000 == checkInfoAddress) {
            // 可能原因是还没有发起场切
            return (20401,"",0,0,0,0,0);
        }
        CheckInfo info = CheckInfo(checkInfoAddress);
        return (info.status(), info.walletOwnerOrg(), info.totalRmbAmt(), info.totalTxAmt(), info.currency(), info.wbBalance(), info.timestamp());
    }

    // function setLastCheckCodeStatus(bytes32 orgId,int checkCode) public returns(int status) {
    //     address checkInfoAddress = getCheckCodeInfoByCheckCode(checkCode,orgId);
    //     if(0x0000000000000000000000000000000000000000 == checkInfoAddress) {
    //         // 可能原因是还没有发起场切
    //         return 1;
    //     } else {
    //         CheckInfo info = CheckInfo(checkInfoAddress);
    //         info.setCheckCodeStatus();
    //         return 0;
    //     }
        
    // }
    function setCheckCodeStatus(bytes32 orgId,int checkCode) public returns(int status) {
        address checkInfoAddress = getCheckCodeInfoByCheckCode(checkCode,orgId);
        CheckInfo info = CheckInfo(checkInfoAddress);
        if(0x0000000000000000000000000000000000000000 == checkInfoAddress) {
            // 可能原因是还没有发起场切
            // transRetLog("setCheckCodeStatus", 0 , status);
            //创建一个场切
            getCheckInfo(checkCode,orgId);
            address checkInfoAddressNew = getCheckCodeInfoByCheckCode(checkCode,orgId);
            CheckInfo infoNew = CheckInfo(checkInfoAddressNew);
            infoNew.setCheckCodeStatus();
            return 0;
        }else {
            info.setCheckCodeStatus();
            return 0;
        }

    }

    function getCheckCodeStatus(bytes32 orgId,int checkCode) public returns(int) {
        address checkInfoAddress = getCheckCodeInfoByCheckCode(checkCode,orgId);
        CheckInfo info = CheckInfo(checkInfoAddress);
        if(0x0000000000000000000000000000000000000000 == checkInfoAddress) {
            // 可能原因是还没有发起场切
            // transRetLog("setCheckCodeStatus", 0 , status);
            return 2;
        } else {
            return info.checkCodeStatus();
        }
       
    }
}
