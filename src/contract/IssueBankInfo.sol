pragma solidity ^0.4.4;

import "Meta.sol";

contract IssueBankInfo is Meta {
    bytes32 public orgID; //机构id
    string public orgName; //机构名
    int public credit; //备付金授信额度

    function IssueBankInfo(string name,string abi) Meta(name,abi){
    }
    
    function setOrgID(bytes32 _orgID) {
        orgID = _orgID;
    }
    
    function setOrgName(string _orgName) {
        orgName = _orgName;
    }
    
    function setCredit(int _credit) {
        credit = _credit;
    }
}
