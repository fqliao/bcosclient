pragma solidity ^0.4.4;


import "Order.sol";
import "Meta.sol";


contract OrderFactory is Meta {
    address public ordermeta;

    function OrderFactory(string name, string abi, string ordername, string orderabi) Meta(name, abi) {
        ordermeta = new Meta(ordername, orderabi);
    }
    /*
    参数：
        date	  String	8byte	交易日期, YYYYMMDD
        ibBizNo	String	20byte	境外合作行交易流水号
        abBizNo	String	32byte	WeBank订单id
        msBizNo	String	最长64byte	聚合支付服务商订单id
        orgBizNo	String	32byte	目前未使用
        userAccount	String	最长32byte	境外合作行的用户id（Wallet id）
        userName	String	最长64byte	userName
        fromOrgId	String	最长8byte	境外合作行的机构id
        toOrgId	String	最长8byte	WeBank的机构id
        txTime	Long		订单的时间戳，精确到毫秒
        userAppIp	String	最长32byte	用户消费的时候上报的用户手机ip ，需要可以判断出用户当前在哪个城市消费
        reportCity	String	最长32byte	用户消费的时候上报的ip对应的城市
        merchantSvcId	 String	最长32byte	聚合支付服务商id
        merchantSvcName	String		聚合支付服务商name,目前固定为空
        merchantId	String	32byte	商户的id
        merchantName	String	256byte	商户名称
        mccCode	String	最长16byte	商户类别
        merchantOrgCode	String	最长32byte	商户的组织机构代码
        storeId  最长32byte	门店ID

        checkCode	Integer		场切码
        isRefunded	Integer		目前未使用
        rmbAmt	Integer		订单的人民币金额,精度到分
        txAmt	Integer		付款方钱包原币扣款金额,精度到分,例如BOCHK就是传入HKD消费的金额
        currency	Integer		付款方钱包原币币种
        fxRate	Integer		结汇汇率,整数，精确到分
        exTimestamp	Integer		清算行上报汇率的序列号
        txType	Integer		交易类型(值的含义见SDK文档)
        tradeTypeValue	Integer		支付类型(值的含义见SDK文档)
        txStatus	Integer		交易状态；1：处理成功； 2：退货完成（924版本无退货功能）；3：交易已被冲正（废弃）；4：交易已经撤销(924版本无撤销功能)

    */
    function create(bytes32 date, bytes32[] bytesArgs, int[] intArgs) public returns (address) {
        Order order = new Order(ordermeta);

        order.initOrder(bytesArgs, intArgs);
        //重新添加
        order.setDate(date);
        order.setTxStatus(0);
        //交易状态

        return order;
    }
}

