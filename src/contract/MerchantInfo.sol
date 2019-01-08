pragma solidity ^0.4.4;
import "Meta.sol";

contract MerchantInfo is Meta{
    
   
	bytes32  merchantId;
	bytes32  merchantName;//商户名称 

    function MerchantInfo(bytes32 id,bytes32 name,address meta) Meta("",""){
    	merchantId=id;
    	merchantName=name;

        setMetaAddress(meta);
    }

    function getInfo() constant returns(bytes32,bytes32){
    	return (merchantId,merchantName);
    }
    
}