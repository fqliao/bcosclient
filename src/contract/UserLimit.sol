pragma solidity ^0.4.4;
import "Order.sol";
import "UserInfo.sol";
import "PopLimit.sol";
import "DateTime.sol";
import "Meta.sol";

contract UserLimit is Meta{
    
    
    uint  public currentToday;  //当天的日期
    int  public todayTotal;    //每天累计
    uint  public currentYear;  //当年
    int  public yearTotal;     //每年累计
    UserInfo public userInfo;    //指向用户合约

    PopLimit public popLimit;    //业务限额合约
    DateTime public dateTime;    //时间工具合约

    function UserLimit(string name,string abi,address _poplimit,address _datetime) Meta(name,abi){
        popLimit=PopLimit(_poplimit);
        dateTime=DateTime(_datetime); 

        (currentYear,currentToday)=getYearAndDate();
        todayTotal=0;
        yearTotal=0;
    }
    function setPopLimit(address _poplimit) public {
        popLimit = PopLimit(_poplimit);
    }
    function setUserInfo(address _userinfo) public {
        userInfo = UserInfo(_userinfo);
    }
    
    //检查是否超过限额
    function limitCheck(Order order) public returns(bool) {
        int txamtbasic=order.txAmt();
        int txtype=order.txType();
        int isRefunded=order.isRefunded();

        if( (txtype == 13) && ( isRefunded != 1 ) ){//消费且不是冲正
            if(txamtbasic >popLimit.orderLimit() ){
                //加错误码event
                return false;
            }
            uint nowyear;
            uint nowdate;
            (nowyear,nowdate)=getYearAndDate();
            if( nowyear != currentYear){
                currentYear=nowyear;
                yearTotal=0;
            }
            if( nowdate != currentToday ){
                currentToday=nowdate;
                todayTotal=0;
            }
            if( (todayTotal+txamtbasic) >popLimit.todayLimit() ){
                //加错误码event @陈翔
                return false;
            }
            else if( (yearTotal+txamtbasic) >popLimit.yearLimit() ){
                //加错误码event
                return false;
            }
            else{
                todayTotal+=txamtbasic;
                yearTotal+=txamtbasic;
            }

        }

        return true;
    }
    
    function getYearAndDate()public constant returns(uint,uint){
        uint time=(block.timestamp-block.timestamp%1000)/1000;
        uint year=DateTime(dateTime).getYear(time);
        uint month=DateTime(dateTime).getMonth(time);
        uint day=DateTime(dateTime).getDay(time);
        uint date=year*10000+month*100+day;
        return (year,date);
    }
    
    
   
    
}
