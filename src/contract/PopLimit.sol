pragma solidity ^0.4.4;
import "Order.sol";
import "Meta.sol";

contract PopLimit is Meta {
    
    
    int  public todayLimit;    //每天限额
    int  public yearLimit;     //每年限额
    int  public orderLimit;    //每笔限额

    function PopLimit(string name,string abi,int today,int year,int order) Meta(name,abi){
        todayLimit=today;
        yearLimit=year;
        orderLimit=order;
    }
    function setTodayLimit(int today) public {
        todayLimit = today;
    }
    
    function setYearLimit(int year) public {
        yearLimit = year;
    }

    function setOrderLimit(int order) public {
        orderLimit = order;
    }
    
    
    
   
    
}
