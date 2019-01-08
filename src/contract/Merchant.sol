pragma solidity ^0.4.4;
import "MerchantData.sol";
import "MerchantInfo.sol";
import "Meta.sol";

contract Merchant is Meta{
    event Debug(uint code,string msg,address addr);

    address m_info;
    address m_data;
    
    
    function Merchant(address info,address data,address meta) Meta("",""){
    	m_info=info;
    	m_data=data;

        setMetaAddress(meta);
    }


    function addAbBizNo(bytes32 abBizNo,address order){
    	MerchantData(m_data).addAbBizNo(abBizNo,order);
    }

     function addCompensateAbBizNo(bytes32 abBizNo,address order){
        
        MerchantData(m_data).addCompensateAbBizNo(abBizNo,order);
    }

    function getOrderList(uint pageSize,uint pageIndex) public constant returns(address[20] list,uint num){
    	return MerchantData(m_data).getOrderList( pageSize, pageIndex) ;
    }

    function getInfo() public constant returns(address){
    	return m_info;
    }
    function getData() public constant returns(address){
    	return m_data;
    }
    
}