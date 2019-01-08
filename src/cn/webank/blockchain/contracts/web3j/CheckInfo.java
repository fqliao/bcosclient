package cn.webank.blockchain.contracts.web3j;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.EthFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
public class CheckInfo extends Contract {
    private static final String BINARY = "60606040526000600e5534156200001257fe5b60405160208062001160833981016040528080519060200190919050505b602060405190810160405280600081525060206040519081016040528060008152505b81600090805190602001906200006b929190620000f4565b50806001908051906020019062000084929190620000f4565b505b5050620000a781620000af6401000000000262000b72176401000000009004565b5b50620001a3565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200013757805160ff191683800117855562000168565b8280016001018555821562000168579182015b82811115620001675782518255916020019190600101906200014a565b5b5090506200017791906200017b565b5090565b620001a091905b808211156200019c57600081600090555060010162000182565b5090565b90565b610fad80620001b36000396000f3006060604052361561020b576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680629d6e511461020d578062c9f9f11461022d57806306fdde031461024d5780630902f72f146102e657806310ae4c8d1461030a578063112ab75c146103305780631166b54b1461036457806317bc269b1461039a578063200d2ed2146103ba578063341f141d146103e0578063422347ae1461040657806348870630146104265780634c616a81146104bf57806350f43b8d146104e55780635365f1d314610505578063548ee6e81461052b5780635cbd7ceb146105595780636804e2751461057f5780636cdd94911461059f57806376f75e7f146105bf578063793740ed146105fb57806381483a7e1461061b5780638ad3af68146106415780638b6959b7146106615780639028e1e7146106815780639295cd8d146106a75780639d796bdb146106db5780639f7314f914610701578063a79af2ce14610713578063a805919014610859578063a83754ad1461087f578063b788266c146108a3578063b80777ea146108c3578063c5c0c030146108e9578063c8e2b4dd1461090f578063ca6c87a81461092f578063d2dcce8014610953578063d7e40fa414610979578063df9c36ba1461099f578063e5a6b10f146109cd578063ea8d2720146109f3578063f8fa3eab14610a13578063fdba57fb14610a39575bfe5b341561021557fe5b61022b6004808035906020019091905050610a5f565b005b341561023557fe5b61024b6004808035906020019091905050610a6a565b005b341561025557fe5b61025d610a75565b60405180806020018281038252838181518152602001915080519060200190808383600083146102ac575b8051825260208311156102ac57602082019150602081019050602083039250610288565b505050905090810190601f1680156102d85780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156102ee57fe5b610308600480803560001916906020019091905050610b13565b005b341561031257fe5b61031a610b47565b6040518082815260200191505060405180910390f35b341561033857fe5b61034e6004808035906020019091905050610b4d565b6040518082815260200191505060405180910390f35b341561036c57fe5b610398600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610b72565b005b34156103a257fe5b6103b86004808035906020019091905050610bb7565b005b34156103c257fe5b6103ca610bc2565b6040518082815260200191505060405180910390f35b34156103e857fe5b6103f0610bc8565b6040518082815260200191505060405180910390f35b341561040e57fe5b6104246004808035906020019091905050610bce565b005b341561042e57fe5b610436610bd9565b6040518080602001828103825283818151815260200191508051906020019080838360008314610485575b80518252602083111561048557602082019150602081019050602083039250610461565b505050905090810190601f1680156104b15780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156104c757fe5b6104cf610c77565b6040518082815260200191505060405180910390f35b34156104ed57fe5b6105036004808035906020019091905050610c7d565b005b341561050d57fe5b610515610c88565b6040518082815260200191505060405180910390f35b341561053357fe5b61053b610c8e565b60405180826000191660001916815260200191505060405180910390f35b341561056157fe5b610569610c94565b6040518082815260200191505060405180910390f35b341561058757fe5b61059d6004808035906020019091905050610ca2565b005b34156105a757fe5b6105bd6004808035906020019091905050610cad565b005b34156105c757fe5b6105dd6004808035906020019091905050610cb8565b60405180826000191660001916815260200191505060405180910390f35b341561060357fe5b6106196004808035906020019091905050610cdd565b005b341561062357fe5b61062b610ce8565b6040518082815260200191505060405180910390f35b341561064957fe5b61065f6004808035906020019091905050610cee565b005b341561066957fe5b61067f6004808035906020019091905050610cf9565b005b341561068957fe5b610691610d04565b6040518082815260200191505060405180910390f35b34156106af57fe5b6106c56004808035906020019091905050610d0a565b6040518082815260200191505060405180910390f35b34156106e357fe5b6106eb610d19565b6040518082815260200191505060405180910390f35b341561070957fe5b610711610d1f565b005b341561071b57fe5b610723610d2a565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352868181518152602001915080519060200190808383600083146107a8575b8051825260208311156107a857602082019150602081019050602083039250610784565b505050905090810190601f1680156107d45780820380516001836020036101000a031916815260200191505b5083810382528581815181526020019150805190602001908083836000831461081c575b80518252602083111561081c576020820191506020810190506020830392506107f8565b505050905090810190601f1680156108485780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b341561086157fe5b610869610ea7565b6040518082815260200191505060405180910390f35b341561088757fe5b6108a1600480803560001916906020019091905050610ead565b005b34156108ab57fe5b6108c16004808035906020019091905050610ebc565b005b34156108cb57fe5b6108d3610ec7565b6040518082815260200191505060405180910390f35b34156108f157fe5b6108f9610ecd565b6040518082815260200191505060405180910390f35b341561091757fe5b61092d6004808035906020019091905050610ed3565b005b341561093757fe5b610951600480803560001916906020019091905050610ede565b005b341561095b57fe5b610963610eed565b6040518082815260200191505060405180910390f35b341561098157fe5b610989610ef3565b6040518082815260200191505060405180910390f35b34156109a757fe5b6109af610ef9565b60405180826000191660001916815260200191505060405180910390f35b34156109d557fe5b6109dd610eff565b6040518082815260200191505060405180910390f35b34156109fb57fe5b610a116004808035906020019091905050610f05565b005b3415610a1b57fe5b610a23610f10565b6040518082815260200191505060405180910390f35b3415610a4157fe5b610a49610f16565b6040518082815260200191505060405180910390f35b806007819055505b50565b806009819055505b50565b60008054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b0b5780601f10610ae057610100808354040283529160200191610b0b565b820191906000526020600020905b815481529060010190602001808311610aee57829003601f168201915b505050505081565b60058054806001018281610b279190610f1c565b916000526020600020900160005b83909190915090600019169055505b50565b60035481565b601681815481101515610b5c57fe5b906000526020600020900160005b915090505481565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b80600b819055505b50565b600b5481565b60065481565b806008819055505b50565b60018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c6f5780601f10610c4457610100808354040283529160200191610c6f565b820191906000526020600020905b815481529060010190602001808311610c5257829003601f168201915b505050505081565b60125481565b806014819055505b50565b600e5481565b60105481565b600060058054905090505b90565b806015819055505b50565b80600a819055505b50565b600581815481101515610cc757fe5b906000526020600020900160005b915090505481565b806011819055505b50565b60075481565b806006819055505b50565b806012819055505b50565b60115481565b600081600f819055505b919050565b600f5481565b60016004819055505b565b610d32610f48565b610d3a610f48565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610df85780601f10610dcd57610100808354040283529160200191610df8565b820191906000526020600020905b815481529060010190602001808311610ddb57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e945780601f10610e6957610100808354040283529160200191610e94565b820191906000526020600020905b815481529060010190602001808311610e7757829003601f168201915b505050505091509250925092505b909192565b60045481565b80600d81600019169055505b50565b80600c819055505b50565b60155481565b60085481565b806003819055505b50565b80601081600019169055505b50565b60145481565b600a5481565b600d5481565b60135481565b806013819055505b50565b60095481565b600c5481565b815481835581811511610f4357818360005260206000209182019101610f429190610f5c565b5b505050565b602060405190810160405280600081525090565b610f7e91905b80821115610f7a576000816000905550600101610f62565b5090565b905600a165627a7a7230582058d01435059ee12825473b9f73d47148d70ba3e29a505506080f534b7d33e0130029";

    public static final String FUNC_SETTXCHARGE = "setTxCharge";

    public static final String FUNC_SETACCRUALACQUIRER = "setAccrualAcquirer";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_ADDORDER = "addOrder";

    public static final String FUNC_CHECKCODE = "checkCode";

    public static final String FUNC_CHECKCODELIST = "checkCodeList";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_SETSTATUS = "setStatus";

    public static final String FUNC_STATUS = "status";

    public static final String FUNC_ACCRUAL = "accrual";

    public static final String FUNC_SETCURRENCYCHARGE = "setCurrencyCharge";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_TOTALTXAMT = "totalTxAmt";

    public static final String FUNC_SETWBBALANCE = "setWbBalance";

    public static final String FUNC_LASTCHECKCODESTATUS = "lastCheckCodeStatus";

    public static final String FUNC_WALLETOWNERORG = "walletOwnerOrg";

    public static final String FUNC_GETORDERLISTSIZE = "getOrderListSize";

    public static final String FUNC_SETTIMESTAMP = "setTimestamp";

    public static final String FUNC_SETACCRUALISSUING = "setAccrualIssuing";

    public static final String FUNC_ORDERLIST = "orderList";

    public static final String FUNC_SETTOTALRMBAMT = "setTotalRmbAmt";

    public static final String FUNC_TXCHARGE = "txCharge";

    public static final String FUNC_SETACCRUAL = "setAccrual";

    public static final String FUNC_SETTOTALTXAMT = "setTotalTxAmt";

    public static final String FUNC_TOTALRMBAMT = "totalRmbAmt";

    public static final String FUNC_SETLASTCHECKCODE = "setLastCheckCode";

    public static final String FUNC_LASTCHECKCODE = "lastCheckCode";

    public static final String FUNC_SETCHECKCODESTATUS = "setCheckCodeStatus";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_CHECKCODESTATUS = "checkCodeStatus";

    public static final String FUNC_SETSYNCPATH = "setSyncPath";

    public static final String FUNC_SETSYNCSTATUS = "setSyncStatus";

    public static final String FUNC_TIMESTAMP = "timestamp";

    public static final String FUNC_CURRENCYCHARGE = "currencyCharge";

    public static final String FUNC_SETCHECKCODE = "setCheckCode";

    public static final String FUNC_SETWALLETOWNERORG = "setWalletOwnerOrg";

    public static final String FUNC_WBBALANCE = "wbBalance";

    public static final String FUNC_ACCRUALISSUING = "accrualIssuing";

    public static final String FUNC_SYNCPATH = "syncPath";

    public static final String FUNC_CURRENCY = "currency";

    public static final String FUNC_SETCURRENCY = "setCurrency";

    public static final String FUNC_ACCRUALACQUIRER = "accrualAcquirer";

    public static final String FUNC_SYNCSTATUS = "syncStatus";

    public static final Event DEBUGORDERLOG_EVENT = new Event("debugOrderLog", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected CheckInfo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CheckInfo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CheckInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CheckInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setTxCharge(BigInteger tx_charge) {
        final Function function = new Function(
                FUNC_SETTXCHARGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(tx_charge)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setAccrualAcquirer(BigInteger accrual_acquirer) {
        final Function function = new Function(
                FUNC_SETACCRUALACQUIRER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(accrual_acquirer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addOrder(byte[] order) {
        final Function function = new Function(
                FUNC_ADDORDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(order)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> checkCode() {
        final Function function = new Function(FUNC_CHECKCODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> checkCodeList(BigInteger param0) {
        final Function function = new Function(FUNC_CHECKCODELIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setMetaAddress(String meta) {
        final Function function = new Function(
                FUNC_SETMETAADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setStatus(BigInteger _status) {
        final Function function = new Function(
                FUNC_SETSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> status() {
        final Function function = new Function(FUNC_STATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> accrual() {
        final Function function = new Function(FUNC_ACCRUAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCurrencyCharge(BigInteger currency_charge) {
        final Function function = new Function(
                FUNC_SETCURRENCYCHARGE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currency_charge)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> totalTxAmt() {
        final Function function = new Function(FUNC_TOTALTXAMT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setWbBalance(BigInteger _wbBalance) {
        final Function function = new Function(
                FUNC_SETWBBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_wbBalance)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> lastCheckCodeStatus() {
        final Function function = new Function(FUNC_LASTCHECKCODESTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> walletOwnerOrg() {
        final Function function = new Function(FUNC_WALLETOWNERORG, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> getOrderListSize() {
        final Function function = new Function(FUNC_GETORDERLISTSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setTimestamp(BigInteger _timestamp) {
        final Function function = new Function(
                FUNC_SETTIMESTAMP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setAccrualIssuing(BigInteger accrual_issuing) {
        final Function function = new Function(
                FUNC_SETACCRUALISSUING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(accrual_issuing)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> orderList(BigInteger param0) {
        final Function function = new Function(FUNC_ORDERLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setTotalRmbAmt(BigInteger _totalRmbAmt) {
        final Function function = new Function(
                FUNC_SETTOTALRMBAMT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_totalRmbAmt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> txCharge() {
        final Function function = new Function(FUNC_TXCHARGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setAccrual(BigInteger _accrual) {
        final Function function = new Function(
                FUNC_SETACCRUAL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_accrual)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setTotalTxAmt(BigInteger _totalTxAmt) {
        final Function function = new Function(
                FUNC_SETTOTALTXAMT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_totalTxAmt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalRmbAmt() {
        final Function function = new Function(FUNC_TOTALRMBAMT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> setLastCheckCode(BigInteger _lastCheckCode) {
        final Function function = new Function(FUNC_SETLASTCHECKCODE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_lastCheckCode)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> lastCheckCode() {
        final Function function = new Function(FUNC_LASTCHECKCODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCheckCodeStatus() {
        final Function function = new Function(
                FUNC_SETCHECKCODESTATUS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, String, String>> getMeta() {
        final Function function = new Function(FUNC_GETMETA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple3<String, String, String>>(
                new Callable<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> checkCodeStatus() {
        final Function function = new Function(FUNC_CHECKCODESTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setSyncPath(byte[] sync_path) {
        final Function function = new Function(
                FUNC_SETSYNCPATH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(sync_path)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setSyncStatus(BigInteger sync_status) {
        final Function function = new Function(
                FUNC_SETSYNCSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(sync_status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> timestamp() {
        final Function function = new Function(FUNC_TIMESTAMP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> currencyCharge() {
        final Function function = new Function(FUNC_CURRENCYCHARGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCheckCode(BigInteger check_code) {
        final Function function = new Function(
                FUNC_SETCHECKCODE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(check_code)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setWalletOwnerOrg(byte[] _walletOwnerOrg) {
        final Function function = new Function(
                FUNC_SETWALLETOWNERORG, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_walletOwnerOrg)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> wbBalance() {
        final Function function = new Function(FUNC_WBBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> accrualIssuing() {
        final Function function = new Function(FUNC_ACCRUALISSUING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> syncPath() {
        final Function function = new Function(FUNC_SYNCPATH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> currency() {
        final Function function = new Function(FUNC_CURRENCY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCurrency(BigInteger _currency) {
        final Function function = new Function(
                FUNC_SETCURRENCY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_currency)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> accrualAcquirer() {
        final Function function = new Function(FUNC_ACCRUALACQUIRER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> syncStatus() {
        final Function function = new Function(FUNC_SYNCSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<DebugOrderLogEventResponse> getDebugOrderLogEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEBUGORDERLOG_EVENT, transactionReceipt);
        ArrayList<DebugOrderLogEventResponse> responses = new ArrayList<DebugOrderLogEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebugOrderLogEventResponse typedResponse = new DebugOrderLogEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.length = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DebugOrderLogEventResponse> debugOrderLogEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, DebugOrderLogEventResponse>() {
            @Override
            public DebugOrderLogEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEBUGORDERLOG_EVENT, log);
                DebugOrderLogEventResponse typedResponse = new DebugOrderLogEventResponse();
                typedResponse.log = log;
                typedResponse.length = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DebugOrderLogEventResponse> debugOrderLogEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEBUGORDERLOG_EVENT));
        return debugOrderLogEventFlowable(filter);
    }

    @Deprecated
    public static CheckInfo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CheckInfo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CheckInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CheckInfo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CheckInfo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CheckInfo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CheckInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CheckInfo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CheckInfo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(CheckInfo.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<CheckInfo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(CheckInfo.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CheckInfo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(CheckInfo.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CheckInfo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(CheckInfo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class DebugOrderLogEventResponse {
        public Log log;

        public BigInteger length;
    }
}
