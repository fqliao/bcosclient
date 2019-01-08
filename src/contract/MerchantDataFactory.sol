pragma solidity ^0.4.4;
import "Order.sol";
import "Meta.sol";
import "MerchantData.sol";

contract MerchantDataFactory is Meta {
	address public merchantdatameta;
    function MerchantDataFactory(string name,string abi,string merchantdataname,string merchantdataabi) Meta(name,abi){
        merchantdatameta=new Meta(merchantdataname,merchantdataabi);
    } 

    function create() public returns(address) {

    	MerchantData merchantdata=new MerchantData(merchantdatameta);
    	
    	return merchantdata;
    }
}