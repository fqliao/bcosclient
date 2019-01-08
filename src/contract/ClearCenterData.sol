pragma solidity ^0.4.4;
import "Meta.sol";
contract ClearCenterData is Meta{
    int txCharge;//手续费
    int currencyCharge;//手续费币种，默认为RMB

    mapping(bytes32 => address) ordersMap;//交易流水列表
    mapping(address => int) ordersCheckTags;// 清算中心链下同步流水完成确认
    address[] ordersList;   //交易流水列表
    bytes32[] orgList;      //发卡收单机构列表
    mapping(bytes32 => address) orgMap;//机构行为合约地址列表
    mapping(int =>int) exchangeMap;//外币汇率牌价,同基础币种兑换，map<币种，汇率>10000港元=8862人民币元 13-8862
    //uint[] exDataArr;
    mapping(bytes32 =>int) checkCodeTagReqMap;//场切请求列表，用于排重

    //汇率结构体
    struct ExchangeRate {
        int currency;
        int fxRate;
        int refundFxRate ;
        int exTimeStamp;
        int currencyUnit;
        uint[3] exDataArr;
        bytes32[3] exDataStr;
    }
   //ExchangeRate[] public ExchangeRates;

    mapping(int =>ExchangeRate) ExchangeRates;

    function ClearCenterData(string name,string abi) Meta(name,abi){
        
    }


    //设置汇率   currency币种 fxRate兑换汇率 refundFxRate 退货时的退换汇率 exTimeStamp清算行上报时的时间戳 exDataArr 扩展字段（int类型） exDataStr扩展字段（bytes32类型）
    function setExchangeRate(int currency,int fxRate,int refundFxRate ,int exTimeStamp,int currencyUnit,uint[3] exDataArr,bytes32[3]  exDataStr) public returns(int){
        ExchangeRates[currency] = ExchangeRate({currency:currency,fxRate:fxRate,refundFxRate:refundFxRate,exTimeStamp:exTimeStamp,currencyUnit:currencyUnit,exDataArr: exDataArr, exDataStr: exDataStr});
        return 0;
    }


    //获取汇率
    function getExchangeBochkRatePart2(int currency) public constant returns(uint[3],bytes32[3]) {
        uint[3] memory exDataInt = ExchangeRates[currency].exDataArr;
        bytes32[3] memory exDataStr = ExchangeRates[currency].exDataStr;
        
        return (exDataInt,exDataStr);
    }

    function getExchangeBochkRatePart1(int currency) public constant returns(int[5]) {
       
	int[5] exchangeRateArr;
	exchangeRateArr[0] = ExchangeRates[currency].currency;
	exchangeRateArr[1] = ExchangeRates[currency].fxRate;
	exchangeRateArr[2] = ExchangeRates[currency].refundFxRate;
	exchangeRateArr[3] = ExchangeRates[currency].exTimeStamp;
	exchangeRateArr[4] = ExchangeRates[currency].currencyUnit;
	return exchangeRateArr;
    }


    //注册机构
    function registerOrg(bytes32 orgId,address orgAddr) public returns(int){
        if(orgMap[orgId] == 0) {
            orgList.push(orgId);
        }
        orgMap[orgId] = orgAddr;
        return 0;
    }

    function getOrgListSize() public constant returns(uint){
        return orgList.length;
    }

    function getOrgByIndex(uint index) public constant returns(bytes32 orgId){
        uint listSize = orgList.length;
        if(index < listSize)
        {
            return orgList[index];
        }
        else
        {
            return "";
        }
    }

    //添加交易流水 TODO: ordersMap 移除
    function addTrans(address orderAddr) public{
       // ordersMap[abBizNo] = orderAddr;
        ordersList.push(orderAddr);
    }

    function getTransListSize() public constant returns(uint){
        return ordersList.length;
    }

    function getTransByIndex(uint offset)constant returns(address addr){
        if(offset < ordersList.length)
        {
            return ordersList[offset];
        }
        else
        {
            return 0;
        }
    }

    //获取机构行为合约地址
    function getOrgAction(bytes32 orgId) public constant returns(address){
        return orgMap[orgId];
    }

    // function isTransExists(bytes32 abBizNo) public constant returns(bool)
    // {
    //     if(ordersMap[abBizNo] == 0)
    //     {
    //         return false;
    //     }
    //     else
    //     {
    //         return true;
    //     }
    // }

    //获取订单合约地址
    function getOrderAddress(bytes32 abBizNo) public constant returns(address)
    {
        return ordersMap[abBizNo];
    }

    //判断请求是否已经存在
    function getCheckTagReq(bytes32 reqId) public constant returns(int)
    {
        return checkCodeTagReqMap[reqId];
    }

    function setCheckTagReq(bytes32 reqId,int newCheckCode) public
    {
        checkCodeTagReqMap[reqId] = newCheckCode;
    }
}
