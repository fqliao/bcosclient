pragma solidity ^0.4.4;
import "Order.sol";
import "Meta.sol";
import "Merchant.sol";

contract MerchantFactory is Meta {
	address public merchantmeta;
    function MerchantFactory(string name,string abi,string merchantname,string merchantabi) Meta(name,abi){
        merchantmeta=new Meta(merchantname,merchantabi);
    } 

    function create(address info,address data) public returns(address) {

    	Merchant merchant=new Merchant(info,data,merchantmeta);
    	
    	return merchant;
    }
}