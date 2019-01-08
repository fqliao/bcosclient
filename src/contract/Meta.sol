pragma solidity ^0.4.4;


contract Meta {
    
    
    string  public name;
    string  public abi;
    address metaAddress;

    function Meta(string n,string a){
        name=n;
        abi=a;
    }
   
    function getMeta()public constant returns(string,string,address){
        return (name,abi,metaAddress);
    }
   
    function setMetaAddress(address meta) public {
        metaAddress=meta;
    }

    
}
