pragma solidity ^0.4.4;
import "Meta.sol";
contract AcquirerBankInfo is Meta{
    
	bytes32 public id;
	bytes32 public name;
	
	function AcquirerBankInfo(string n,string abi,bytes32 _id,bytes32 _name) Meta(n,abi){
		id=_id;
		name=_name;
	}

}