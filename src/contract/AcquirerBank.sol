pragma solidity ^0.4.4;


import "AcquirerBankData.sol";
import "AcquirerBankInfo.sol";
import "Merchant.sol";
import "MerchantData.sol";
import "MerchantInfo.sol";
import "MerchantInfoFactory.sol";
import "MerchantDataFactory.sol";
import "MerchantFactory.sol";
import "Order.sol";
import "OrderFactory.sol";
import "CheckInfoManager.sol";
import "CheckInfo.sol";
import "StringTool.sol";
import "ClearCenter.sol";
import "CheckInfoManager.sol";
import "CheckInfo.sol";
import "Meta.sol";
import "PopLog.sol";


// error code 20200
contract AcquirerBank is StringTool, Meta, PopLog {
    address m_info;

    address m_data;

    address m_orderfactory;

    address m_checkinfomanager;

    address m_clearcenter;

    address m_merchantinfofactory;

    address m_merchantdatafactory;

    address m_merchantfactory;

    function AcquirerBank(string name, string abi, address info, address data, address orderfactory, address checkinfomanager, address clearcenter, address merchantinfofactory, address merchantdatafactory, address merchantfactory) Meta(name, abi) {

        m_info = info;
        m_data = data;
        m_orderfactory = orderfactory;
        m_checkinfomanager = checkinfomanager;
        m_clearcenter = clearcenter;

        m_merchantinfofactory = merchantinfofactory;
        m_merchantdatafactory = merchantdatafactory;
        m_merchantfactory = merchantfactory;
    }

    //链上注册新商户
    function createVirtualAccount() public returns (address) {
        return 0x0;
        // MerchantInfo merchantinfo = MerchantInfo(MerchantInfoFactory(m_merchantinfofactory).create(merchantId, merchantName));
        // MerchantData merchantdata = MerchantData(MerchantDataFactory(m_merchantdatafactory).create());
        // Merchant merchant = Merchant(MerchantFactory(m_merchantfactory).create(merchantinfo, merchantdata));
        // AcquirerBankData(m_data).addMerchant(merchantId, merchant);
        // return merchant;
    }

    //用户消费 int code ,string msg
    function doTransaction(bytes32 date, bytes32[] bytesArgs, int[] intArgs) returns (int code, string msg) {

        //0 验证WeBank订单id是否已经存在,abBizNo
        address oldOrder = AcquirerBankData(m_data).getOrder(bytesArgs[1]);
        if ((0x0000000000000000000000000000000000000000 != oldOrder)) {
            transRetLog("doTransaction", 20202, 0);
            return (20202, "重复订单号");
        }

        if (intArgs[1] == 0 && intArgs[2] < 0) {
            //非冲账交易，金额小于0
            transRetLog("doTransaction", 20204, 0);
            return (20204, "错误金额格式");
        }

        //2.新建订单
        bytes32[] memory bytesInputs = new bytes32[](24);
        int[] memory intInputs = new int[](16);

        //only use 16 parameters right now
        for (uint i = 0; i < 24; i++) {
            bytesInputs[i] = bytesArgs[i];
        }

        for (uint j = 0; j < 16; j++) {
            intInputs[j] = intArgs[j];
        }

        Order order = Order(OrderFactory(m_orderfactory).create((date), bytesInputs, intInputs));

        //4.保存流水
        //第一个参数:境外合作行交易流水号
        //记到收单行
        AcquirerBankData(m_data).addOrder(bytesArgs[1], order);

        //intArgs[9]是场切码
        addCheckInfo(intArgs[9],Order(order).fromOrgId(), order);

        //更新订单状态, 1标识记账成功
        order.setTxStatus(1);
        //以外部传进去的参数为准 做一次验证
        Order(order).setCheckCode(intArgs[9]);
        transRetLog("doTransaction", 0, Order(order).checkCode());

        //记EventLog        
        //doOrderLog(order);

        return (0, "doTransaction success");
    }

    // TODO: mingzhenliu dump service
    function doOrderLog(Order order) internal {
        bytes32[] memory bytesInputs = new bytes32[](18);
        int[] memory intInputs = new int[](10);

        bytesInputs[0] = order.ibBizNo();
        bytesInputs[1] = order.abBizNo();
        bytesInputs[2] = order.msBizNo();
        bytesInputs[3] = order.orgBizNo();
        bytesInputs[4] = order.userAccount();
        bytesInputs[5] = order.userName();
        bytesInputs[6] = order.fromOrgId();
        bytesInputs[7] = order.toOrgId();
        bytesInputs[8] = order.userAppIp();
        bytesInputs[9] = order.reportCity();
        bytesInputs[10] = order.merchantSvcId();
        bytesInputs[11] = order.merchantSvcName();
        bytesInputs[12] = order.merchantId();
        bytesInputs[13] = order.merchantName();
        bytesInputs[14] = order.merchantOrgCode();
        bytesInputs[15] = order.mccCode();
        bytesInputs[16] = order.txTime();
        bytesInputs[17] = order.storeId();
        intInputs[0] = order.txType();
        intInputs[1] = order.isRefunded();
        intInputs[2] = order.rmbAmt();
        intInputs[3] = order.txAmt();
        intInputs[4] = order.currency();
        intInputs[5] = order.fxRate();
        intInputs[6] = order.exTimestamp();
        intInputs[7] = order.tradeTypeValue();
        intInputs[8] = order.txStatus();
        intInputs[9] = order.checkCode();

        int txStatus = order.txStatus();

        orderLog(bytesInputs, intInputs, txStatus);
    }


    //存入场切
    function addCheckInfo(int checkCode,bytes32 orgId, address order) internal {
        CheckInfoManager checkInfoManager = CheckInfoManager(m_checkinfomanager);
        CheckInfo info = CheckInfo(checkInfoManager.getCheckInfo(checkCode, orgId));
        info.addOrder(Order(order).abBizNo());
    }


    // 查询商户交易流水详情
    // 调用getCCTransList的时候，返回到api service后，通过api service调用这个得到具体的订单
    function getTransInfo(address order) public constant returns (bytes32 date, bytes32[], int[]) {
       bytes32[24] memory bytesArgs;
        int[16]  memory intArgs;

        (date, bytesArgs, intArgs) = Order(order).getAllFields();
        bytes32[] memory transDetailsBytes;
        int[] memory transDetailsInts;

        transDetailsBytes = new bytes32[](24);
	 	transDetailsInts = new int[](16);

        for(uint i = 0;i < 16;i++) {
            transDetailsInts[i] = intArgs[i];
        }
        for(uint j = 0;j < 24;j++) {
            transDetailsBytes[j] = bytesArgs[j];
        }
        return (date,transDetailsBytes,transDetailsInts);
    }
    //获取交易流水 in use
    function getCCTransList(uint startOffset, uint transNums) public constant returns (address[] list) {
        // in case of transNums is too large
        if (transNums > 20) {
            // TODO: retur error code instead
            return (list);
        }
        uint transListSize = AcquirerBankData(m_data).getTransListSize();
        if (startOffset >= transListSize) {
            return (list);
        }
        uint canReadSize = transListSize - startOffset;
        uint returnSize = transNums;
        if (canReadSize < transNums) {
            returnSize = canReadSize;
        }
        list = new address[](returnSize);
        for (uint i = 0; i < returnSize; ++i) {
            list[i] = AcquirerBankData(m_data).getTransByIndex(startOffset + i);
        }
    }

    //获取对账流水  in use
    function getClearingTrans(bytes32 orgId, int checkCode, uint offset, uint num) public constant returns (int lastCheckCodeStatus,int errorCode, address[] list) {
  
        if (num > 20) {
            //每次拉去数据的步长不能超过20条
            return (0,20222, new address[](0));
        }
        // int currentCheckCode = CheckInfoManager(m_checkinfomanager).currentCheckCode();
         //return (0,currentCheckCode, new address[](0));
        // if (checkCode < 0) {
        //     //有几种可能的原因: 1.还没有场切 2.拉取的节点的区块没有同步到最新
        //     return (0,20220, new address[](0));
        // }
        // else if (checkCode == currentCheckCode) {
        //     //有几种可能的原因: 1.还没有场切 2.拉取的节点的区块没有同步到最新
        //     return (0,20221, new address[](0));
        // }
        address checkInfoAddress = CheckInfoManager(m_checkinfomanager).getCheckCodeInfoByCheckCode(checkCode, orgId);
        if (checkInfoAddress == 0) {
            //可能原因是还未进行场切
            return (0,20201, new address[](0));
        }
        int checkCodeFlag = CheckInfoManager(m_checkinfomanager).getCheckCodeStatus(orgId,checkCode);
        //是否可以拉去对账文件
        if(checkCodeFlag!=1){
            //list = new address[](0);
           // list[0] = checkInfoAddress;
            // return (CheckInfoManager(m_checkinfomanager).getCheckCodeStatus(orgId,checkCode),20205, new address[](0));
            return (checkCodeFlag,20205, new address[](0));
        }
        // return (2,18, new address[](0));
        // CheckInfo info = CheckInfo(checkInfoAddress);
        uint transListSize = CheckInfo(checkInfoAddress).getOrderListSize();
        
        if (offset < transListSize) {
            uint canReadSize = transListSize - offset;
            uint readSize = num;
            if (readSize > canReadSize) {
                readSize = canReadSize;
            }
            list = new address[](readSize);
            for (uint i = 0; i < readSize; ++i) {
                list[i] = AcquirerBankData(m_data).getOrder(CheckInfo(checkInfoAddress).orderList(offset + i));
            }
            return (checkCodeFlag,0, list);
        }
        else {
            list = new address[](0);
            return (12 ,0 , list);
        }
    }
    //获取清算结果
    function getClearingResult(int clearCode) public constant returns (int, int, int) {
        bytes32 orgId = AcquirerBankInfo(m_info).id();

        CheckInfoManager checkInfoManager = CheckInfoManager(m_checkinfomanager);
        CheckInfo info = CheckInfo(checkInfoManager.getAcquirerBankCheckInfo(clearCode, orgId));

        return (clearCode, info.accrual(), info.currencyCharge());
    }

    //设置指定场次的对账文件拉取结果状态
    function setTransFileTag(int clearCode, int fileTagResult, bytes32 filePath)returns (bool) {
        bytes32 orgId = AcquirerBankInfo(m_info).id();
        CheckInfoManager checkInfoManager = CheckInfoManager(m_checkinfomanager);
        CheckInfo info = CheckInfo(checkInfoManager.getIssueBankCheckInfo(clearCode, orgId));
        info.setSyncStatus(fileTagResult);
        info.setSyncPath(filePath);
    }


    //获取执行场次的对账文件状态
    function getTransFileTag(int clearCode) public constant returns (int fileTagResult, bytes32 filePath) {
        bytes32 orgId = AcquirerBankInfo(m_info).id();
        CheckInfoManager checkInfoManager = CheckInfoManager(m_checkinfomanager);
        CheckInfo info = CheckInfo(checkInfoManager.getIssueBankCheckInfo(clearCode, orgId));
        return (info.syncStatus(), info.syncPath());
    }


    function currencyBase() public constant returns (int) {
        return AcquirerBankData(m_data).currencyBase();
    }

    function setCurrencyBase(int current_base) public {
        AcquirerBankData(m_data).setCurrencyBase(current_base);
    }

    function currentBalance() public constant returns (int) {
        return AcquirerBankData(m_data).currentBalance();
    }

    function setCurrentBalance(int current_balance) public {
        AcquirerBankData(m_data).setCurrentBalance(current_balance);
    }

    function credit() public constant returns (int) {
        return AcquirerBankData(m_data).credit();
    }

    function setCredit(int credit) public {
        AcquirerBankData(m_data).setCredit(credit);
    }
    // in use
    function addTransaction(bytes32 date,bytes32[] bytesArgs, int[] intArgs) public returns(int) {
        //2.新建订单
        bytes32[] memory bytesInputs = new bytes32[](24);
        int[] memory intInputs = new int[](16);

        //only use 16 parameters right now
        for (uint i = 0; i < 24; i++) {
            bytesInputs[i] = bytesArgs[i];
        }

        for (uint j = 0; j < 16; j++) {
            intInputs[j] = intArgs[j];
        }

        Order order = Order(OrderFactory(m_orderfactory).create(date, bytesInputs, intInputs));

        //4.保存流水
        //第一个参数:境外合作行交易流水号
        //记到收单行
        AcquirerBankData(m_data).addOrder(bytesArgs[1], order);

        //intArgs[9]是场切码
        addCheckInfo(intArgs[9],Order(order).fromOrgId(), order);
        return 0;   
    }
    // modifier costs(uint price) {
    //     if (price>0) {
    //         _;
    //     }
    // }

    //撤销订单 TODO:add modifier
    function cancelTransaction(bytes32 date,bytes32[] bytesArgs, int[] intArgs) public returns (int code, string msg) {
      
        address oldOrder = AcquirerBankData(m_data).getOrder(bytesArgs[1]);
        if (0x0000000000000000000000000000000000000000 == oldOrder) {
            if (4 == intArgs[8]) {
                addTransaction(date,bytesArgs,intArgs);
                transRetLog("cancelTrans2",  0, 0);
                return (0, "cancelTrans");
            }else {
                transRetLog("cancelTrans3",  20301, 0);
                return (0, "cancelTrans3");
            }
           
        } else {
            if (1 == intArgs[8]) {
                //设置订单状态为撤销
                Order(oldOrder).setTxStatus(4);
                transRetLog("cancelTrans6",  0, Order(oldOrder).checkCode());
                return (0, "cancelTrans");
            } else if (4 == intArgs[8]) {
                transRetLog("cancelTrans4",  20302, Order(oldOrder).checkCode());
                return (0, "already");
            } else {
                transRetLog("cancelTrans5",  20313, Order(oldOrder).checkCode());
                return (0, "txStatus error");
            }
        }
        return (0, "cancelTrans");
    }
// 退货  bytes32 date, bytes32 abBizNo, bytes32 orgBizNo, int txStatus, int txType
    function refundedTransaction(bytes32 date,bytes32[] bytesArr, int[] intArr) public returns (int code, string msg) {
        //查询原订单信息
        address oldOrder = AcquirerBankData(m_data).getOrder(bytesArr[1]);
        if ((0x0000000000000000000000000000000000000000 == oldOrder)) {
            transRetLog("refundedTransaction",  20321, 0);
            return (0, "refundedTransaction");
        }else {
            if (1 == intArr[8]) {
                //2.新建订单
                bytes32[] memory bytesInputs = new bytes32[](24);
                int[] memory intInputs = new int[](16);
                bytes32[24] memory bytesArgs;
                int[16] memory intArgs;
                (, bytesArgs, intArgs) = Order(oldOrder).getAllFields();
                
                for (uint i = 0; i < 24; i++) {
                    bytesInputs[i] = bytesArgs[i];
                }

                for (uint j = 0; j < 16; j++) {
                    intInputs[j] = intArgs[j];
                }
                bytesInputs[1] =  bytesArr[0];

                Order order = Order(OrderFactory(m_orderfactory).create(date, bytesInputs, intInputs));
                
                Order(order).setTxStatus(2);
                
                //将原订单设置成 IsRefunded为1
                Order(oldOrder).setIsRefunded(1);
                transRetLog("refundedTransaction5", 0, Order(oldOrder).checkCode());
                return (0, "refundedTransaction");
                
            } else if (4 == intArr[8]) {
                transRetLog("refundedTransaction",  20322, 0);
                return (0, "refundedTransaction");
            } else {
                transRetLog("refundedTransaction",  20323, 0);
                return (0, "refundedTransaction");
            }
        }
    }
    // 根据AbBizNo返回订单详细信息
    // in use
     function getOrderDetailByAbBizNo(bytes32 abBizNo) public constant returns (bytes32 date, bytes32[], int[]) {
        address order = AcquirerBankData(m_data).getOrder(abBizNo);
        bytes32[24] memory bytesArgs;
        int[16]  memory intArgs;
        // 旧交易流水不存在
        if (0x0000000000000000000000000000000000000000 == order) {
            transRetLog("getOrderDetailByAbBizNo", 20250, 0);
            // return (date,bytesArgs,intArgs);
        }

        (date, bytesArgs, intArgs) = Order(order).getAllFields();
        bytes32[] memory transDetailsBytes;
        int[] memory transDetailsInts;

        transDetailsBytes = new bytes32[](24);
	 	transDetailsInts = new int[](16);

        for(uint i = 0;i < 16;i++) {
            transDetailsInts[i] = intArgs[i];
        }
        for(uint j = 0;j < 24;j++) {
            transDetailsBytes[j] = bytesArgs[j];
        }
        return (date,transDetailsBytes,transDetailsInts);
        
    }

    // no use
    // 拉取checkCodeList in use
    function getCheckCodeList(uint startOffset, uint num) constant returns (int[] list) {
        if (num > 140) {
            return (list);
        }
        uint checkCodeListLength = CheckInfoManager(m_checkinfomanager).getCheckCodeListLength();
        if (startOffset >= checkCodeListLength) {
            return (list);
        }
        uint canReadSize = checkCodeListLength - startOffset;
        uint returnSize = num;
        if (canReadSize < num) {
            returnSize = canReadSize;
        }
        list = new int[](returnSize);
        for (uint i = 0; i < returnSize; ++i) {
            list[i] = CheckInfoManager(m_checkinfomanager).getCheckCodeByIndex(startOffset + i);
        }
    }
    //辅助验证
    function bytes32ToStringDemo(bytes32 x) constant returns (string) {
        bytes memory bytesString = new bytes(32);
        uint charCount = 0;
        for (uint j = 0; j < 32; j++) {
            byte char = byte(bytes32(uint(x) * 2 ** (8 * j)));
            if (char != 0) {
                bytesString[charCount] = char;
                charCount++;
            }
        }
        bytes memory bytesStringTrimmed = new bytes(charCount);
        for (j = 0; j < charCount; j++) {
            bytesStringTrimmed[j] = bytesString[j];
        }
        return string(bytesStringTrimmed);
    }
}


