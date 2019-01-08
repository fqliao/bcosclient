pragma solidity ^0.4.4;
import "Meta.sol";

contract MerchantData is Meta{
	event Debug(uint code,string msg,address addr);

    bytes32[] public abBizNoList;//这个没什么用？
    address[] public orderList; 

    bytes32[] public compensateAbBizNoList;
    address[]public compensateOrderList;//冲销订单
    function MerchantData(address meta) Meta("",""){
        setMetaAddress(meta);
    }
    
    //这里不验重
    function addAbBizNo(bytes32 abBizNo,address order){
    	
    	abBizNoList.push(abBizNo);
    	orderList.push(order);
    	
    }

    function addCompensateAbBizNo(bytes32 abBizNo,address order){
        
        compensateAbBizNoList.push(abBizNo);
        compensateOrderList.push(order);
        
    }

    function getList() public constant returns(bytes32[],address[]){
    	return (abBizNoList,orderList);
    }
    function getOrderList(uint pageSize,uint pageIndex) constant returns(address[20] list,uint num){
    	if( pageSize>20 || (orderList.length<=pageSize*(pageIndex)) )
    		return;
    	
    	num=0;
    	for( uint i=pageSize*(pageIndex);(i<orderList.length)&&(i<(pageSize*(pageIndex+1))) ;i++){
    		list[num++]=orderList[orderList.length - i - 1];//倒叙取
    	}
    }
}