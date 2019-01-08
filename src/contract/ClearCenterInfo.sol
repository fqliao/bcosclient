pragma solidity ^0.4.4;
import "Meta.sol";
contract ClearCenterInfo is Meta{
    bytes32 public orgID; //机构id
    string public orgName; //机构名
    int public exchangeCurrency; //外汇基本币种,RMB:1


    function ClearCenterInfo (string n,string abi,bytes32 id,string name,int currency) Meta(n,abi)
    {
    	orgID = id;
    	orgName = name;
    	exchangeCurrency = currency;
    }
}
