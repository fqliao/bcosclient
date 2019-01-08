pragma solidity ^0.4.4;
import "Meta.sol";
contract AcquirerBankData is Meta{
   

	address checkmgr;//场切管理合约

	mapping(bytes32 => address) ordersmap;//Order m_biz_no ->Order address
	address[] ordersList;   //交易流水列表
//	bytes32[] orders;

	mapping(bytes32 => address) compensateOrdersmap;//Order m_biz_no ->Order address
	bytes32[] compensateOrders;
	
	mapping(bytes32=>address) merchantsmap;//MerchantInfo m_merchantid -> Merchant address
	bytes32[] merchants;

	int charge;//手续费

	int   public credit;//备付金授信额度

	int public currentBalance;//备付金余额
	int public currencyBase;//基本币种
	 event transRetLog(string oper,int256 status,int check_code);
	 event debugRetLog(bytes32 msg1,int256 msg2,int msg3);
	function AcquirerBankData(string name,string abi) Meta(name,abi){
		
	}
	//添加订单
	function addOrder(bytes32 abBizNo,address order)returns(bool){
		
		address oldorder=ordersmap[abBizNo];

		if( 0x0000000000000000000000000000000000000000 != oldorder ){
			return false;
		}
		else{
			ordersmap[abBizNo]=order;
			ordersList.push(order);
//			orders.push(abBizNo);
		}
		
		return true;
	}

	function getTransListSize() public constant returns(uint){
		return ordersList.length;
	}

	function getTransByIndex(uint offset)constant returns(address addr){
		if(offset < ordersList.length) {
			return ordersList[offset];
		} else {
			return 0;
		}
	}

	function getOrder(bytes32 abBizNo) returns(address){
		return ordersmap[abBizNo];
	}
	//添加商户
	function addMerchant(bytes32 merchantid,address merchant)returns(bool){
		address oldmerchant=merchantsmap[merchantid];
		if( 0x0000000000000000000000000000000000000000 != oldmerchant ){
			return false;
		}
		else{
			merchantsmap[merchantid]=merchant;
			merchants.push(merchantid);
		}

		return true;
	}

	function getMerchant(bytes32 merchantid) constant returns(address){
		return merchantsmap[merchantid];
	}

	function getMerchantList(uint pageIndex)constant returns(address[] list,uint count){
		// uint pageSize=10;
		list=new address[](10);
		count=0;

		uint j=10*(pageIndex);
		
   		for(;(count<10) && (j<merchants.length);){
   			list[count++]=merchantsmap[merchants[j++]];
   		}
   	}

   	 function setCurrencyBase(int current_base) public {
        currencyBase = current_base;
    }
    
    function setCurrentBalance(int current_balance) public {
        currentBalance = current_balance;
    }
    
    function setCredit(int _credit) public {
        credit = _credit;
    }
   
}