pragma solidity ^0.4.4;
import "Order.sol";
import "Meta.sol";
import "MerchantInfo.sol";

contract MerchantInfoFactory is Meta {
	address public merchantinfometa;
    function MerchantInfoFactory(string name,string abi,string merchantinfoname,string merchantinfoabi) Meta(name,abi){
        merchantinfometa=new Meta(merchantinfoname,merchantinfoabi);
    }

    function create(bytes32 id,bytes32 name) public returns(address) {

    	MerchantInfo merchantinfo=new MerchantInfo(id,name,merchantinfometa);
    	
    	return merchantinfo;
    }
}