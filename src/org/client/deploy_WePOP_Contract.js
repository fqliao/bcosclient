var Web3 = require('web3');
var config = require('./config_bank');
var fs = require('fs');
var execSync = require('child_process').execSync;
var BigNumber = require('bignumber.js');
var net = require('net');
var coder = require('./codeUtils');
var web3sync_forinit = require('./web3sync_forinit');
// var unitTest = require('./deploy_WePOP_DEV_doTran-unit-test')


if (typeof web3 !== 'undefined') {
    web3 = new Web3(web3.currentProvider);
} else {
    web3 = new Web3(new Web3.providers.HttpProvider(config.HttpProvider));
}

function getAbi(file) {
    var abi = JSON.parse(fs.readFileSync(config.comp_path + file + ".abi", 'utf-8'));
    return abi;
}

function getAbiJson(file) {
    var abi = (fs.readFileSync(config.comp_path + file + ".abi", 'utf-8'));
    return abi;
}


var contract_file = './abi_params.js';
var verify_file = './abi_params.js';

var issuing_bank_contract_address_file = './IssueBank.address';
var acquirer_bank_contract_address_file = './AcquirerBank.address';
var clearing_center_contract_address_file = './ClearCenter.address';

(async function() {
    //TODO 初始化合约地址及其映射关系文件
    var abi_params_buf = "var abiList={};\n";
    var verify_params_buf = "";

    await web3sync_forinit.unlockAccount(config.account, "123");
    await web3sync_forinit.compileContract("CheckInfoManager");
    var checkinfo_manager = await web3sync_forinit.rawDeploy(config.account, config.privKey, "CheckInfoManager", ['string', 'string', 'string', 'string'], ['CheckInfoManager', getAbiJson('CheckInfoManager'), 'CheckInfo', getAbiJson('CheckInfo')]);
    console.log("场切合约部署结束:" + checkinfo_manager.address);

    await web3sync_forinit.compileContract("ClearCenter");

    var ClearCenterData = await web3sync_forinit.rawDeploy(config.account, config.privKey, "ClearCenterData", ['string', 'string'], ["ClearCenterData", getAbiJson("ClearCenterData")]);
    var ClearCenterInfo = await web3sync_forinit.rawDeploy(config.account, config.privKey, "ClearCenterInfo", ['string', 'string', 'bytes32', 'string', 'int256'], ["ClearCenterInfo", getAbiJson("ClearCenterInfo"), "BOC", "BOC", 1]);
    console.log("准备部署清算合约....");
    var ClearCenter = await web3sync_forinit.rawDeploy(config.account, config.privKey, "ClearCenter", ['string', 'string', 'address', 'address', 'address'], ["ClearCenter", getAbiJson("ClearCenter"), ClearCenterInfo.address, ClearCenterData.address, checkinfo_manager.address]);

    //初始化IssueBank
    func = "setCheckInfoManager(address)";
    params = [checkinfo_manager.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, ClearCenter.address, func, params);
    //初始化ClearCenter
    func = "setInfo(address)";
    params = [ClearCenterInfo.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, ClearCenter.address, func, params);
    func = "setData(address)";
    params = [ClearCenterData.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, ClearCenter.address, func, params);
    console.log("清算行部署结束:" + ClearCenter.address);
    //TODO 导出合约地址及其映射关系文件

    abi_params_buf += "abiList['" + ClearCenter.address + "']='" + getAbiJson("ClearCenter") + "';\n";
    verify_params_buf += "var ClearCenter_Addr='" + ClearCenter.address + "';\n";
    verify_params_buf += 'exports.ClearCenter_Addr = ClearCenter_Addr;\n';

    // func = "setExchangeRate(int256,int256,int256)";
    // params = [13, 8000, 1490232576];
    // receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, ClearCenter.address, func, params);
    // console.log("设置汇率");

    //部署合约
    var ClearCenterData = await web3sync_forinit.rawDeploy(config.account, config.privKey, "ClearCenterData", ['string', 'string'], ["ClearCenterData", getAbiJson("ClearCenterData")]);
    var issue_bank_data = await web3sync_forinit.rawDeploy(config.account, config.privKey, "IssueBankData", ['string', 'string'], ['IssueBankData', getAbiJson('IssueBankData')]);
    //var issue_bank_info= await web3sync_forinit.rawDeploy(config.account, config.privKey, "IssueBankInfo", ['bytes32','string'], ['BOCHK','BOCHK']);
    var issue_bank_info = await web3sync_forinit.rawDeploy(config.account, config.privKey, "IssueBankInfo", ['string', 'string'], ['IssueBankInfo', getAbiJson('IssueBankInfo')]);
    var issue_bank = await web3sync_forinit.rawDeploy(config.account, config.privKey, "IssueBank", ['string', 'string'], ['IssueBank', getAbiJson('IssueBank')]);

    console.log("发卡行地址:" + issue_bank.address);
    //TODO 导出合约地址及其映射关系文件
    abi_params_buf += "abiList['" + issue_bank.address + "']='" + getAbiJson("IssueBank") + "';\n";
    verify_params_buf += "IssueBank_Addr='" + issue_bank.address + "';\n";
    verify_params_buf += 'exports.IssueBank_Addr = IssueBank_Addr;\n';

    var func;
    var params;
    var receipt;


    var PopLimit = await web3sync_forinit.rawDeploy(config.account, config.privKey, "PopLimit", ['string', 'string', 'uint256', 'uint256', 'uint256'], ["PopLimit", getAbiJson("PopLimit"), 2000000, 30000000, 300000]);

    var DateTime = await web3sync_forinit.rawDeploy(config.account, config.privKey, "DateTime", [], []);

    var UserLimit = await web3sync_forinit.rawDeploy(config.account, config.privKey, "UserLimit", ['string', 'string', 'address', 'address'], ["UserLimit", getAbiJson("UserLimit"), PopLimit.address, DateTime.address]);

    var OrderFactory = await web3sync_forinit.rawDeploy(config.account, config.privKey, "OrderFactory", ['string', 'string', 'string', 'string'], ['OrderFactory', getAbiJson('OrderFactory'), 'Order', getAbiJson('Order')]);

    console.log("当前日期：" + UserLimit.getYearAndDate());

    //初始化IssueBank
    func = "setInfo(address)";
    params = [issue_bank_info.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank.address, func, params);

    func = "setData(address)";
    params = [issue_bank_data.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank.address, func, params);
    console.log(JSON.stringify(receipt));
    func = "setCheckInfoManager(address)";
    params = [checkinfo_manager.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank.address, func, params);

    func = "setPopLimit(address)";
    params = [PopLimit.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank.address, func, params);

    func = "setDateTime(address)";
    params = [DateTime.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank.address, func, params);

    func = "setOrderFactory(address)";
    params = [OrderFactory.address];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank.address, func, params);


    func = "setOrgID(bytes32)";
    params = ['BOCHK'];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank_info.address, func, params);


    func = "setOrgName(string)";
    params = ['BOCHK'];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank_info.address, func, params);


    func = "setOrgID(bytes32)";
    params = ['WB'];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank_info.address, func, params);


    func = "setOrgName(string)";
    params = ['WB'];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, issue_bank_info.address, func, params);



    func = "registerOrg(int256,bytes32,address,int256,int256,int256)";
    params = [1, "BOCHK", issue_bank.address, 1000000000, 1000000000, 13];
    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, ClearCenter.address, func, params);
    console.log("注册发卡行结束");
    var issue_balance = issue_bank.currentBalance();
    var issue_credit = issue_bank.credit();
    console.log("issue : ", issue_balance, " - ", issue_credit);


    //部署合约

    var MerchantInfoFactory = await web3sync_forinit.rawDeploy(config.account, config.privKey, "MerchantInfoFactory", ['string', 'string', 'string', 'string'], ['MerchantInfoFactory', getAbiJson('MerchantInfoFactory'), 'MerchantInfo', getAbiJson('MerchantInfo')]);

    var MerchantDataFactory = await web3sync_forinit.rawDeploy(config.account, config.privKey, "MerchantDataFactory", ['string', 'string', 'string', 'string'], ['MerchantDataFactory', getAbiJson('MerchantDataFactory'), 'MerchantData', getAbiJson('MerchantData')]);

    var MerchantFactory = await web3sync_forinit.rawDeploy(config.account, config.privKey, "MerchantFactory", ['string', 'string', 'string', 'string'], ['MerchantFactory', getAbiJson('MerchantFactory'), 'Merchant', getAbiJson('Merchant')]);


    var AcquirerBankData = await web3sync_forinit.rawDeploy(config.account, config.privKey, "AcquirerBankData", ['string', 'string'], ["AcquirerBankData", getAbiJson("AcquirerBankData")]);
    var AcquirerBankInfo = await web3sync_forinit.rawDeploy(config.account, config.privKey, "AcquirerBankInfo", ['string', 'string', 'bytes32', 'bytes32'], ["AcquirerBankInfo", getAbiJson("AcquirerBankInfo"), "WB", "WB"]);
    var AcquirerBank = await web3sync_forinit.rawDeploy(config.account, config.privKey, "AcquirerBank", ['string', 'string', 'address', 'address', 'address', 'address', 'address', 'address', 'address', 'address'], ["AcquirerBank", getAbiJson("AcquirerBank"), AcquirerBankInfo.address, AcquirerBankData.address, OrderFactory.address, checkinfo_manager.address, ClearCenter.address, MerchantInfoFactory.address, MerchantDataFactory.address, MerchantFactory.address]);

    //console.log("收单行Meta："+AcquirerBank.getMeta());
    //TODO 导出合约地址及其映射关系文件
    abi_params_buf += "abiList['" + AcquirerBank.address + "']='" + getAbiJson("AcquirerBank") + "';\n";
    verify_params_buf += "AcquirerBank_Addr='" + AcquirerBank.address + "';\n";
    verify_params_buf += 'exports.AcquirerBank_Addr = AcquirerBank_Addr;\n';

    //TODO 写合约地址及其映射关系文件
    abi_params_buf += "exports.abiList = abiList;\n";
    await fs.writeFile(contract_file, abi_params_buf, 'utf8');
    await fs.writeFile(verify_file, verify_params_buf, 'utf8');

    func = "registerOrg(int256,bytes32,address,int256,int256,int256)";
    params = [2, "WB", AcquirerBank.address, 1000000000, 1000000000, 1];

    receipt = await web3sync_forinit.sendRawTransaction(config.account, config.privKey, ClearCenter.address, func, params);

    console.log("收单行部署结束:" + AcquirerBank.address);
    acquirer_balance = AcquirerBank.currentBalance();
    acquirer_credit = AcquirerBank.credit();
    console.log("acquirer: ", acquirer_balance, " - ", acquirer_credit);

    console.log("==============================End==============================");
    console.log("业务合约部署完成.");
    // func = "reverseTrans(bytes32,bytes32,bytes32,bytes32,int256,int256,int256)";
    console.log('clearingBank : ' + ClearCenter.address);
    console.log('issuingBank : ' + issue_bank.address);
    console.log('acquirerBank : ' + AcquirerBank.address);

    await fs.writeFile("clearingBankAddress.txt", ClearCenter.address, (err) => {
        if (err) {
            throw err;
        }
        console.log("ClearCenter is ok");
    })
    await fs.writeFile("issuingBankAddress.txt", issue_bank.address, (err) => {
        if (err) {
            throw err;
        }
        console.log("issue_bank is ok");
    })

    await fs.writeFile("acquirerBankAddress.txt", AcquirerBank.address, (err) => {
        if (err) {
            throw err;
        }
        console.log("AcquirerBank is ok");
    })
    process.exit();
})();

//用户消费，查询用户信息，撤销，场切，退货