pragma solidity ^0.4.4;


import "Meta.sol";


contract Order is Meta {

    bytes32[24] public bytes32Arr;

    int[16] public intArr;

    bytes32 public date; //交易日期

    int[] public txStatusList;//订单状态，0:初始化，1：处理成功， 2：退货完成  3：交易已被冲正

    function Order(address meta) Meta("", "") {
        setMetaAddress(meta);
    }

    function initOrder(bytes32[] bytesArrParam, int[] intArrParam) {
        for (uint i = 0; i < 24; i++) {
            bytes32Arr[i] = bytesArrParam[i];
        }
        for (uint j = 0; j < 16; j++) {
            intArr[j] = intArrParam[j];
        }
    }

    function getAllFields() public constant returns (bytes32, bytes32[24], int[16]) {

        bytes32[24] memory allBytes;
        for (uint i = 0; i < 24; i++) {
            allBytes[i] = bytes32Arr[i];
        }

        int[16] memory allInts;
        for (uint j = 0; j < 16; j++) {
            allInts[j] = intArr[j];
        }
        return (date, allBytes, allInts);
    }

    //以前的就是一个list
    function txStatusListSize() public returns (uint) {
        return txStatusList.length;
    }

    function txStatus() public returns (int) {
        if (txStatusList.length <= 0) {
            return 0;
        }
        return txStatusList[txStatusList.length - 1];
    }

    function setTxStatus(int _txStatus) public {
        txStatusList.push(_txStatus);
        intArr[8] = _txStatus;
        //txStatus = _txStatus;
    }

    function setDate(bytes32 _date) public {
        date = _date;
    }
    function setTxTime(bytes32 _txTime) public {
        bytes32Arr[15] = _txTime;
    }

    function setIsRefunded(int _isRefunded) public returns (int) {
        return intArr[1] = _isRefunded;
    }
    function setTxAmt(int _txAmt) public {
        intArr[3] = _txAmt;
    }

    function setCurrency(int _currency) public {
        intArr[4] = _currency;
    }

    function setFxRate(int _fxRate) public {
        intArr[5] = _fxRate;
    }

    function setCheckCode(int _checkCode) public {
        intArr[9] = _checkCode;
    }


    //境外合作行交易流水号
    function ibBizNo() public constant returns (bytes32) {
        return bytes32Arr[0];
    }
    // 收单行订单id 收单行的订单号  作为唯一订单号
    function abBizNo() public constant returns (bytes32) {
        return bytes32Arr[1];
    }
    // 聚合支付服务商订单id
    function msBizNo() public constant returns (bytes32) {
        return bytes32Arr[2];
    }
    // 目前未使用
    function orgBizNo() public constant returns (bytes32) {
        return bytes32Arr[3];
    }
    // 境外合作行的用户id（Wallet id）
    function userAccount() public constant returns (bytes32) {
        return bytes32Arr[4];
    }
    // 用户的名字
    function userName() public constant returns (bytes32) {
        return bytes32Arr[5];
    }
    // 机构id
    function fromOrgId() public constant returns (bytes32) {
        return bytes32Arr[6];
    }
    // 机构id
    function toOrgId() public constant returns (bytes32) {
        return bytes32Arr[7];
    }
    // 用户消费的时候上报的用户手机ip ，需要可以判断出用户当前在哪个城市消费
    function userAppIp() public constant returns (bytes32) {
        return bytes32Arr[8];
    }
    // 用户消费的时候上报的ip对应的城市
    function reportCity() public constant returns (bytes32) {
        return bytes32Arr[9];
    }
    // 聚合支付服务商id
    function merchantSvcId() public constant returns (bytes32) {
        return bytes32Arr[10];
    }
    // 聚合支付服务商name
    function merchantSvcName() public constant returns (bytes32) {
        return bytes32Arr[11];
    }
    // 商户的id
    function merchantId() public constant returns (bytes32) {
        return bytes32Arr[12];
    }
    // 商户名称
    function merchantName() public constant returns (bytes32) {
        return bytes32Arr[13];
    }
    // 商户的组织机构代码
    function merchantOrgCode() public constant returns (bytes32) {
        return bytes32Arr[14];
    }
    // 商户类别 int
    function mccCode() public constant returns (bytes32) {
        return bytes32Arr[15];
    }

    // 订单的时间戳，精确到毫秒
    function txTime() public constant returns (bytes32) {
        return bytes32Arr[16];
    }
    // storeId
    function storeId() public constant returns (bytes32) {
        return bytes32Arr[17];
    }

    ///////////////

    // 交易类型(值的含义见SDK文档 退款)
    function txType() public constant returns (int) {
        return intArr[0];
    }
    // 目前未使用
    function isRefunded() public constant returns (int) {
        return intArr[1];
    }
    // 订单的人民币金额,精度到分
    function rmbAmt() public constant returns (int) {
        return intArr[2];
    }
    // 付款方钱包原币扣款金额,精度到分,例如BOCHK就是传入HKD消费的金额
    function txAmt() public constant returns (int) {
        return intArr[3];
    }
    // 付款方钱包原币币种
    function currency() public constant returns (int) {
        return intArr[4];
    }
    // 结汇汇率
    function fxRate() public constant returns (int) {
        return intArr[5];
    }
    // 清算行上报汇率的序列号
    function exTimestamp() public constant returns (int) {
        return intArr[6];
    }
    // 交易类型(值的含义见SDK文档 主扫 被扫)
    function tradeTypeValue() public constant returns (int) {
        return intArr[7];
    }

    // 场切码 （int）
    function checkCode() public constant returns (int) {
        return intArr[9];
    }
}
