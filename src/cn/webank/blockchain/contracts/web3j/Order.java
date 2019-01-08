package cn.webank.blockchain.contracts.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.Utils;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray16;
import org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray24;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
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
public class Order extends Contract {
    private static final String BINARY = "606060405234156200000d57fe5b604051602080620018f7833981016040528080519060200190919050505b602060405190810160405280600081525060206040519081016040528060008152505b816000908051906020019062000066929190620000ef565b5080600190805190602001906200007f929190620000ef565b505b5050620000a281620000aa6401000000000262000e25176401000000009004565b5b506200019e565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200013257805160ff191683800117855562000163565b8280016001018555821562000163579182015b828111156200016257825182559160200191906001019062000145565b5b50905062000172919062000176565b5090565b6200019b91905b80821115620001975760008160009055506001016200017d565b5090565b90565b61174980620001ae6000396000f30060606040523615610239576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630113c0b21461023b578063068635e01461026157806306a7983d1461028f57806306fdde03146102c35780630768923f1461035c57806310ae4c8d1461038a5780631166b54b146103b057806312f33a40146103e65780631a6c779b1461041457806326941053146104345780632722b3111461045a5780632b18102214610488578063323046b1146104a857806333d2c954146104d657806338d06942146104fc578063405f50f11461052257806347ee6a0714610550578063484787f41461057e57806348870630146105ac5780634be39b69146106455780634da8f356146106735780634e41c9de146106995780634e427852146106c757806353294bfc146106f5578063566caa4b1461078c578063598b0a2f146107ba578063681dc055146107e85780636ad875191461080c57806370019b3514610832578063779cd08314610860578063922082281461088657806393016e741461092e578063a2d69ad51461094e578063a464e5ab1461097c578063a79af2ce146109aa578063abda23d314610af0578063aec2b87914610b14578063b574cf5514610b48578063c168236414610b76578063c8e2b4dd14610b9c578063cbed176d14610bbc578063d9c973e214610bf8578063e1676eab14610c26578063e5a6b10f14610c4c578063e73ccd5c14610c72578063ea8d272014610ca6578063ec86cda814610cc6575bfe5b341561024357fe5b61024b610cf4565b6040518082815260200191505060405180910390f35b341561026957fe5b610271610d12565b60405180826000191660001916815260200191505060405180910390f35b341561029757fe5b6102ad6004808035906020019091905050610d30565b6040518082815260200191505060405180910390f35b34156102cb57fe5b6102d3610d4b565b6040518080602001828103825283818151815260200191508051906020019080838360008314610322575b805182526020831115610322576020820191506020810190506020830392506102fe565b505050905090810190601f16801561034e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561036457fe5b61036c610de9565b60405180826000191660001916815260200191505060405180910390f35b341561039257fe5b61039a610e07565b6040518082815260200191505060405180910390f35b34156103b857fe5b6103e4600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610e25565b005b34156103ee57fe5b6103f6610e6a565b60405180826000191660001916815260200191505060405180910390f35b341561041c57fe5b6104326004808035906020019091905050610e88565b005b341561043c57fe5b610444610ea6565b6040518082815260200191505060405180910390f35b341561046257fe5b61046a610ec4565b60405180826000191660001916815260200191505060405180910390f35b341561049057fe5b6104a66004808035906020019091905050610ee2565b005b34156104b057fe5b6104b8610f2a565b60405180826000191660001916815260200191505060405180910390f35b34156104de57fe5b6104e6610f30565b6040518082815260200191505060405180910390f35b341561050457fe5b61050c610f4e565b6040518082815260200191505060405180910390f35b341561052a57fe5b610532610f5c565b60405180826000191660001916815260200191505060405180910390f35b341561055857fe5b610560610f7a565b60405180826000191660001916815260200191505060405180910390f35b341561058657fe5b61058e610f98565b60405180826000191660001916815260200191505060405180910390f35b34156105b457fe5b6105bc610fb6565b604051808060200182810382528381815181526020019150805190602001908083836000831461060b575b80518252602083111561060b576020820191506020810190506020830392506105e7565b505050905090810190601f1680156106375780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561064d57fe5b610655611054565b60405180826000191660001916815260200191505060405180910390f35b341561067b57fe5b610683611072565b6040518082815260200191505060405180910390f35b34156106a157fe5b6106a9611090565b60405180826000191660001916815260200191505060405180910390f35b34156106cf57fe5b6106d76110ae565b60405180826000191660001916815260200191505060405180910390f35b34156106fd57fe5b61078a600480803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919050506110cc565b005b341561079457fe5b61079c611173565b60405180826000191660001916815260200191505060405180910390f35b34156107c257fe5b6107ca611191565b60405180826000191660001916815260200191505060405180910390f35b34156107f057fe5b61080a6004808035600019169060200190919050506111af565b005b341561081457fe5b61081c6111be565b6040518082815260200191505060405180910390f35b341561083a57fe5b6108426111dc565b60405180826000191660001916815260200191505060405180910390f35b341561086857fe5b6108706111fa565b6040518082815260200191505060405180910390f35b341561088e57fe5b610896611218565b604051808460001916600019168152602001836018602002808383600083146108de575b8051825260208311156108de576020820191506020810190506020830392506108ba565b5050509050018260106020028083836000831461091a575b80518252602083111561091a576020820191506020810190506020830392506108f6565b505050905001935050505060405180910390f35b341561093657fe5b61094c60048080359060200190919050506112ed565b005b341561095657fe5b61095e61130b565b60405180826000191660001916815260200191505060405180910390f35b341561098457fe5b61098c611329565b60405180826000191660001916815260200191505060405180910390f35b34156109b257fe5b6109ba611347565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838103835286818151815260200191508051906020019080838360008314610a3f575b805182526020831115610a3f57602082019150602081019050602083039250610a1b565b505050905090810190601f168015610a6b5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360008314610ab3575b805182526020831115610ab357602082019150602081019050602083039250610a8f565b505050905090810190601f168015610adf5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b3415610af857fe5b610b126004808035600019169060200190919050506114c4565b005b3415610b1c57fe5b610b3260048080359060200190919050506114e6565b6040518082815260200191505060405180910390f35b3415610b5057fe5b610b58611509565b60405180826000191660001916815260200191505060405180910390f35b3415610b7e57fe5b610b86611527565b6040518082815260200191505060405180910390f35b3415610ba457fe5b610bba600480803590602001909190505061156e565b005b3415610bc457fe5b610bda600480803590602001909190505061158c565b60405180826000191660001916815260200191505060405180910390f35b3415610c0057fe5b610c086115a7565b60405180826000191660001916815260200191505060405180910390f35b3415610c2e57fe5b610c366115c5565b6040518082815260200191505060405180910390f35b3415610c5457fe5b610c5c6115e3565b6040518082815260200191505060405180910390f35b3415610c7a57fe5b610c906004808035906020019091905050611601565b6040518082815260200191505060405180910390f35b3415610cae57fe5b610cc46004808035906020019091905050611626565b005b3415610cce57fe5b610cd6611644565b60405180826000191660001916815260200191505060405180910390f35b6000601b6002601081101515610d0657fe5b0160005b505490505b90565b60006003600a601881101515610d2457fe5b0160005b505490505b90565b601b81601081101515610d3f57fe5b0160005b915090505481565b60008054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610de15780601f10610db657610100808354040283529160200191610de1565b820191906000526020600020905b815481529060010190602001808311610dc457829003601f168201915b505050505081565b600060036002601881101515610dfb57fe5b0160005b505490505b90565b6000601b6009601081101515610e1957fe5b0160005b505490505b90565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b60006003600c601881101515610e7c57fe5b0160005b505490505b90565b80601b6005601081101515610e9957fe5b0160005b50819055505b50565b6000601b6003601081101515610eb857fe5b0160005b505490505b90565b60006003600b601881101515610ed657fe5b0160005b505490505b90565b602c8054806001018281610ef69190611662565b916000526020600020900160005b839091909150555080601b6008601081101515610f1d57fe5b0160005b50819055505b50565b602b5481565b6000601b6006601081101515610f4257fe5b0160005b505490505b90565b6000602c8054905090505b90565b60006003600f601881101515610f6e57fe5b0160005b505490505b90565b600060036004601881101515610f8c57fe5b0160005b505490505b90565b600060036008601881101515610faa57fe5b0160005b505490505b90565b60018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561104c5780601f106110215761010080835404028352916020019161104c565b820191906000526020600020905b81548152906001019060200180831161102f57829003601f168201915b505050505081565b60006003600960188110151561106657fe5b0160005b505490505b90565b6000601b600560108110151561108457fe5b0160005b505490505b90565b60006003600e6018811015156110a257fe5b0160005b505490505b90565b6000600360116018811015156110c057fe5b0160005b505490505b90565b60006000600091505b60188210156111205783828151811015156110ec57fe5b9060200190602002015160038360188110151561110557fe5b0160005b5081600019169055505b81806001019250506110d5565b600090505b601081101561116c57828181518110151561113c57fe5b90602001906020020151601b8260108110151561115557fe5b0160005b50819055505b8080600101915050611125565b5b50505050565b60006003600760188110151561118557fe5b0160005b505490505b90565b6000600360036018811015156111a357fe5b0160005b505490505b90565b80602b81600019169055505b50565b6000601b60006010811015156111d057fe5b0160005b505490505b90565b6000600360056018811015156111ee57fe5b0160005b505490505b90565b6000601b600160108110151561120c57fe5b0160005b505490505b90565b600061122261168e565b61122a6116bb565b61123261168e565b600061123c6116bb565b6000600092505b60188310156112905760038360188110151561125b57fe5b0160005b5054848460188110151561126f57fe5b602002019060001916908160001916815250505b8280600101935050611243565b600090505b60108110156112d857601b816010811015156112ad57fe5b0160005b505482826010811015156112c157fe5b6020020181815250505b8080600101915050611295565b602b5484839650965096505b50505050909192565b80601b60036010811015156112fe57fe5b0160005b50819055505b50565b60006003600060188110151561131d57fe5b0160005b505490505b90565b60006003601060188110151561133b57fe5b0160005b505490505b90565b61134f6116e4565b6113576116e4565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114155780601f106113ea57610100808354040283529160200191611415565b820191906000526020600020905b8154815290600101906020018083116113f857829003601f168201915b50505050509250818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156114b15780601f10611486576101008083540402835291602001916114b1565b820191906000526020600020905b81548152906001019060200180831161149457829003601f168201915b505050505091509250925092505b909192565b806003600f6018811015156114d557fe5b0160005b5081600019169055505b50565b600081601b60016010811015156114f957fe5b0160005b5081905590505b919050565b60006003600660188110151561151b57fe5b0160005b505490505b90565b60006000602c80549050111515611541576000905061156b565b602c6001602c805490500381548110151561155857fe5b906000526020600020900160005b505490505b90565b80601b600960108110151561157f57fe5b0160005b50819055505b50565b60038160188110151561159b57fe5b0160005b915090505481565b60006003600d6018811015156115b957fe5b0160005b505490505b90565b6000601b60076010811015156115d757fe5b0160005b505490505b90565b6000601b60046010811015156115f557fe5b0160005b505490505b90565b602c8181548110151561161057fe5b906000526020600020900160005b915090505481565b80601b600460108110151561163757fe5b0160005b50819055505b50565b60006003600160188110151561165657fe5b0160005b505490505b90565b8154818355818115116116895781836000526020600020918201910161168891906116f8565b5b505050565b610300604051908101604052806018905b60006000191681526020019060019003908161169f5790505090565b610200604051908101604052806010905b60008152602001906001900390816116cc5790505090565b602060405190810160405280600081525090565b61171a91905b808211156117165760008160009055506001016116fe565b5090565b905600a165627a7a72305820bbb8a228029dade21967a61fe20c23e5c077520685549ba1817dcd076c9fee6e0029";

    public static final String FUNC_RMBAMT = "rmbAmt";

    public static final String FUNC_MERCHANTSVCID = "merchantSvcId";

    public static final String FUNC_INTARR = "intArr";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_MSBIZNO = "msBizNo";

    public static final String FUNC_CHECKCODE = "checkCode";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_MERCHANTID = "merchantId";

    public static final String FUNC_SETFXRATE = "setFxRate";

    public static final String FUNC_TXAMT = "txAmt";

    public static final String FUNC_MERCHANTSVCNAME = "merchantSvcName";

    public static final String FUNC_SETTXSTATUS = "setTxStatus";

    public static final String FUNC_DATE = "date";

    public static final String FUNC_EXTIMESTAMP = "exTimestamp";

    public static final String FUNC_TXSTATUSLISTSIZE = "txStatusListSize";

    public static final String FUNC_MCCCODE = "mccCode";

    public static final String FUNC_USERACCOUNT = "userAccount";

    public static final String FUNC_USERAPPIP = "userAppIp";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_REPORTCITY = "reportCity";

    public static final String FUNC_FXRATE = "fxRate";

    public static final String FUNC_MERCHANTORGCODE = "merchantOrgCode";

    public static final String FUNC_STOREID = "storeId";

    public static final String FUNC_INITORDER = "initOrder";

    public static final String FUNC_TOORGID = "toOrgId";

    public static final String FUNC_ORGBIZNO = "orgBizNo";

    public static final String FUNC_SETDATE = "setDate";

    public static final String FUNC_TXTYPE = "txType";

    public static final String FUNC_USERNAME = "userName";

    public static final String FUNC_ISREFUNDED = "isRefunded";

    public static final String FUNC_GETALLFIELDS = "getAllFields";

    public static final String FUNC_SETTXAMT = "setTxAmt";

    public static final String FUNC_IBBIZNO = "ibBizNo";

    public static final String FUNC_TXTIME = "txTime";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_SETTXTIME = "setTxTime";

    public static final String FUNC_SETISREFUNDED = "setIsRefunded";

    public static final String FUNC_FROMORGID = "fromOrgId";

    public static final String FUNC_TXSTATUS = "txStatus";

    public static final String FUNC_SETCHECKCODE = "setCheckCode";

    public static final String FUNC_BYTES32ARR = "bytes32Arr";

    public static final String FUNC_MERCHANTNAME = "merchantName";

    public static final String FUNC_TRADETYPEVALUE = "tradeTypeValue";

    public static final String FUNC_CURRENCY = "currency";

    public static final String FUNC_TXSTATUSLIST = "txStatusList";

    public static final String FUNC_SETCURRENCY = "setCurrency";

    public static final String FUNC_ABBIZNO = "abBizNo";

    @Deprecated
    protected Order(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Order(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Order(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Order(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> rmbAmt() {
        final Function function = new Function(FUNC_RMBAMT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> merchantSvcId() {
        final Function function = new Function(FUNC_MERCHANTSVCID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> intArr(BigInteger param0) {
        final Function function = new Function(FUNC_INTARR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> msBizNo() {
        final Function function = new Function(FUNC_MSBIZNO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> checkCode() {
        final Function function = new Function(FUNC_CHECKCODE, 
                Arrays.<Type>asList(), 
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

    public RemoteCall<byte[]> merchantId() {
        final Function function = new Function(FUNC_MERCHANTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setFxRate(BigInteger _fxRate) {
        final Function function = new Function(
                FUNC_SETFXRATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_fxRate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> txAmt() {
        final Function function = new Function(FUNC_TXAMT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> merchantSvcName() {
        final Function function = new Function(FUNC_MERCHANTSVCNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setTxStatus(BigInteger _txStatus) {
        final Function function = new Function(
                FUNC_SETTXSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_txStatus)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> date() {
        final Function function = new Function(FUNC_DATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> exTimestamp() {
        final Function function = new Function(FUNC_EXTIMESTAMP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> txStatusListSize() {
        final Function function = new Function(
                FUNC_TXSTATUSLISTSIZE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> mccCode() {
        final Function function = new Function(FUNC_MCCCODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> userAccount() {
        final Function function = new Function(FUNC_USERACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> userAppIp() {
        final Function function = new Function(FUNC_USERAPPIP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> reportCity() {
        final Function function = new Function(FUNC_REPORTCITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> fxRate() {
        final Function function = new Function(FUNC_FXRATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> merchantOrgCode() {
        final Function function = new Function(FUNC_MERCHANTORGCODE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> storeId() {
        final Function function = new Function(FUNC_STOREID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> initOrder(List<byte[]> bytesArrParam, List<BigInteger> intArrParam) {
        final Function function = new Function(
                FUNC_INITORDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        Utils.typeMap(bytesArrParam, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class)),
                new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Int256>(
                        Utils.typeMap(intArrParam, org.fisco.bcos.web3j.abi.datatypes.generated.Int256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> toOrgId() {
        final Function function = new Function(FUNC_TOORGID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> orgBizNo() {
        final Function function = new Function(FUNC_ORGBIZNO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setDate(byte[] _date) {
        final Function function = new Function(
                FUNC_SETDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_date)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> txType() {
        final Function function = new Function(FUNC_TXTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> userName() {
        final Function function = new Function(FUNC_USERNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> isRefunded() {
        final Function function = new Function(FUNC_ISREFUNDED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<byte[], List<byte[]>, List<BigInteger>>> getAllFields() {
        final Function function = new Function(FUNC_GETALLFIELDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<StaticArray24<Bytes32>>() {}, new TypeReference<StaticArray16<Int256>>() {}));
        return new RemoteCall<Tuple3<byte[], List<byte[]>, List<BigInteger>>>(
                new Callable<Tuple3<byte[], List<byte[]>, List<BigInteger>>>() {
                    @Override
                    public Tuple3<byte[], List<byte[]>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<byte[], List<byte[]>, List<BigInteger>>(
                                (byte[]) results.get(0).getValue(), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                                convertToNative((List<Int256>) results.get(2).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setTxAmt(BigInteger _txAmt) {
        final Function function = new Function(
                FUNC_SETTXAMT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_txAmt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> ibBizNo() {
        final Function function = new Function(FUNC_IBBIZNO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> txTime() {
        final Function function = new Function(FUNC_TXTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
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

    public RemoteCall<TransactionReceipt> setTxTime(byte[] _txTime) {
        final Function function = new Function(
                FUNC_SETTXTIME, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_txTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setIsRefunded(BigInteger _isRefunded) {
        final Function function = new Function(
                FUNC_SETISREFUNDED, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_isRefunded)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> fromOrgId() {
        final Function function = new Function(FUNC_FROMORGID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> txStatus() {
        final Function function = new Function(
                FUNC_TXSTATUS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setCheckCode(BigInteger _checkCode) {
        final Function function = new Function(
                FUNC_SETCHECKCODE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_checkCode)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> bytes32Arr(BigInteger param0) {
        final Function function = new Function(FUNC_BYTES32ARR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> merchantName() {
        final Function function = new Function(FUNC_MERCHANTNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> tradeTypeValue() {
        final Function function = new Function(FUNC_TRADETYPEVALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> currency() {
        final Function function = new Function(FUNC_CURRENCY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> txStatusList(BigInteger param0) {
        final Function function = new Function(FUNC_TXSTATUSLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
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

    public RemoteCall<byte[]> abBizNo() {
        final Function function = new Function(FUNC_ABBIZNO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    @Deprecated
    public static Order load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Order(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Order load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Order(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Order load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Order(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Order load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Order(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Order> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(Order.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Order> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(Order.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Order> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(Order.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Order> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(Order.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
