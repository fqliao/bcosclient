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
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
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
public class IssueBankData extends Contract {
    private static final String BINARY = "606060405234156200000d57fe5b6040516200127338038062001273833981016040528080518201919060200180518201919050505b81815b81600090805190602001906200005092919062000076565b5080600190805190602001906200006992919062000076565b505b50505b505062000125565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000b957805160ff1916838001178555620000ea565b82800160010185558215620000ea579182015b82811115620000e9578251825591602001919060010190620000cc565b5b509050620000f99190620000fd565b5090565b6200012291905b808211156200011e57600081600090555060010162000104565b5090565b90565b61113e80620001356000396000f300606060405236156100ef576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100f15780631166b54b1461018a578063249f6393146101c05780633d1c40aa1461026b5780633f764433146102b557806348870630146102d5578063502aa3b51461036e5780635778472a146103aa5780636517579c1461040e57806376f75e7f14610472578063a06d083c146104ae578063a79af2ce146104d4578063aa9c54261461061a578063ce845d1d1461065d578063d4c2700e14610683578063db548d82146106a9578063e410d1b4146106c9575bfe5b34156100f957fe5b6101016106e9565b6040518080602001828103825283818151815260200191508051906020019080838360008314610150575b8051825260208311156101505760208201915060208101905060208303925061012c565b505050905090810190601f16801561017c5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561019257fe5b6101be600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610787565b005b34156101c857fe5b6101e26004808035600019169060200190919050506107cc565b6040518080602001828103825283818151815260200191508051906020019080838360008314610231575b8051825260208311156102315760208201915060208101905060208303925061020d565b505050905090810190601f16801561025d5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561027357fe5b61029f600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506109b7565b6040518082815260200191505060405180910390f35b34156102bd57fe5b6102d36004808035906020019091905050610bbf565b005b34156102dd57fe5b6102e5610bca565b6040518080602001828103825283818151815260200191508051906020019080838360008314610334575b80518252602083111561033457602082019150602081019050602083039250610310565b505050905090810190601f1680156103605780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561037657fe5b61038c6004808035906020019091905050610c68565b60405180826000191660001916815260200191505060405180910390f35b34156103b257fe5b6103cc600480803560001916906020019091905050610c8d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561041657fe5b610430600480803560001916906020019091905050610cd3565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561047a57fe5b6104906004808035906020019091905050610d19565b60405180826000191660001916815260200191505060405180910390f35b34156104b657fe5b6104be610d3e565b6040518082815260200191505060405180910390f35b34156104dc57fe5b6104e4610d44565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838103835286818151815260200191508051906020019080838360008314610569575b80518252602083111561056957602082019150602081019050602083039250610545565b505050905090810190601f1680156105955780820380516001836020036101000a031916815260200191505b508381038252858181518152602001915080519060200190808383600083146105dd575b8051825260208311156105dd576020820191506020810190506020830392506105b9565b505050905090810190601f1680156106095780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b341561062257fe5b61065b60048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610ec1565b005b341561066557fe5b61066d611077565b6040518082815260200191505060405180910390f35b341561068b57fe5b61069361107d565b6040518082815260200191505060405180910390f35b34156106b157fe5b6106c76004808035906020019091905050611083565b005b34156106d157fe5b6106e7600480803590602001909190505061108e565b005b60008054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561077f5780601f106107545761010080835404028352916020019161077f565b820191906000526020600020905b81548152906001019060200180831161076257829003601f168201915b505050505081565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b6107d4611099565b6107dc6110ad565b6000600060006107ea6110ad565b60206040518059106107f95750595b908082528060200260200182016040525b50945060009350600092505b60208310156108d5578260080260020a876001900402600102915060007f010000000000000000000000000000000000000000000000000000000000000002827effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415156108c75781858581518110151561088e57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a90535083806001019450505b5b8280600101935050610816565b836040518059106108e35750595b908082528060200260200182016040525b509050600092505b838310156109a957848381518110151561091257fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f010000000000000000000000000000000000000000000000000000000000000002818481518110151561096b57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053505b82806001019350506108fc565b8095505b5050505050919050565b6000600060007fb09b6d7f808ba533a7829e0b216f80fb3204b2d95a672b1cc5ef640b1818a3c2603c600060405180806020018481526020018381526020018281038252600b8152602001807f496e206164644f72646572000000000000000000000000000000000000000000815250602001935050505060405180910390a160019250610bb8565b6102c65a03f11515610a4e57fe5b5050506040518051905090508360086000836000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060010260086000836000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a464e5ab6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610b6057fe5b6102c65a03f11515610b6e57fe5b50505060405180519050600019161415610bb35760098054806001018281610b9691906110c1565b916000526020600020900160005b83909190915090600019169055505b600092505b5050919050565b806007819055505b50565b60018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c605780601f10610c3557610100808354040283529160200191610c60565b820191906000526020600020905b815481529060010190602001808311610c4357829003601f168201915b505050505081565b600b81815481101515610c7757fe5b906000526020600020900160005b915090505481565b600060086000836000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b919050565b6000600a6000836000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b919050565b600981815481101515610d2857fe5b906000526020600020900160005b915090505481565b60075481565b610d4c611099565b610d54611099565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e125780601f10610de757610100808354040283529160200191610e12565b820191906000526020600020905b815481529060010190602001808311610df557829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610eae5780601f10610e8357610100808354040283529160200191610eae565b820191906000526020600020905b815481529060010190602001808311610e9157829003601f168201915b505050505091509250925092505b909192565b80600a6000846000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600a6000846000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610f7a57611073565b6000600a6000846000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c04637116000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561102357fe5b6102c65a03f1151561103157fe5b50505060405180519050141561107257600b805480600101828161105591906110c1565b916000526020600020900160005b84909190915090600019169055505b5b5050565b60065481565b60055481565b806005819055505b50565b806006819055505b50565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b8154818355818115116110e8578183600052602060002091820191016110e791906110ed565b5b505050565b61110f91905b8082111561110b5760008160009055506001016110f3565b5090565b905600a165627a7a72305820ff61a45c8f954e6e2188edf33ad40d3633ffe334cbbfd0b6831a7f38afd277be0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_BYTES32TOSTRINGDEMO = "bytes32ToStringDemo";

    public static final String FUNC_ADDORDER = "addOrder";

    public static final String FUNC_SETCREDIT = "setCredit";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_USERSLIST = "usersList";

    public static final String FUNC_GETORDER = "getOrder";

    public static final String FUNC_GETUSER = "getUser";

    public static final String FUNC_ORDERLIST = "orderList";

    public static final String FUNC_CREDIT = "credit";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_ADDUSER = "addUser";

    public static final String FUNC_CURRENTBALANCE = "currentBalance";

    public static final String FUNC_CURRENCYBASE = "currencyBase";

    public static final String FUNC_SETCURRENCYBASE = "setCurrencyBase";

    public static final String FUNC_SETCURRENCYBALANCE = "setCurrencyBalance";

    public static final Event TRANSRETLOG_EVENT = new Event("transRetLog", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected IssueBankData(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IssueBankData(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IssueBankData(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IssueBankData(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setMetaAddress(String meta) {
        final Function function = new Function(
                FUNC_SETMETAADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> bytes32ToStringDemo(byte[] x) {
        final Function function = new Function(FUNC_BYTES32TOSTRINGDEMO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(x)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> addOrder(String addr) {
        final Function function = new Function(FUNC_ADDORDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCredit(BigInteger _credit) {
        final Function function = new Function(
                FUNC_SETCREDIT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_credit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> usersList(BigInteger param0) {
        final Function function = new Function(FUNC_USERSLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> getOrder(byte[] abBizNo) {
        final Function function = new Function(FUNC_GETORDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(abBizNo)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getUser(byte[] user_id) {
        final Function function = new Function(FUNC_GETUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(user_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> orderList(BigInteger param0) {
        final Function function = new Function(FUNC_ORDERLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> credit() {
        final Function function = new Function(FUNC_CREDIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteCall<TransactionReceipt> addUser(byte[] account, String user) {
        final Function function = new Function(
                FUNC_ADDUSER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(account), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(user)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> currentBalance() {
        final Function function = new Function(FUNC_CURRENTBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> currencyBase() {
        final Function function = new Function(FUNC_CURRENCYBASE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCurrencyBase(BigInteger current_base) {
        final Function function = new Function(
                FUNC_SETCURRENCYBASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(current_base)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setCurrencyBalance(BigInteger current_balance) {
        final Function function = new Function(
                FUNC_SETCURRENCYBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(current_balance)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<TransRetLogEventResponse> getTransRetLogEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSRETLOG_EVENT, transactionReceipt);
        ArrayList<TransRetLogEventResponse> responses = new ArrayList<TransRetLogEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransRetLogEventResponse typedResponse = new TransRetLogEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oper = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.check_code = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransRetLogEventResponse> transRetLogEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, TransRetLogEventResponse>() {
            @Override
            public TransRetLogEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSRETLOG_EVENT, log);
                TransRetLogEventResponse typedResponse = new TransRetLogEventResponse();
                typedResponse.log = log;
                typedResponse.oper = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.check_code = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransRetLogEventResponse> transRetLogEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSRETLOG_EVENT));
        return transRetLogEventFlowable(filter);
    }

    @Deprecated
    public static IssueBankData load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IssueBankData(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IssueBankData load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IssueBankData(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IssueBankData load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IssueBankData(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IssueBankData load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IssueBankData(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IssueBankData> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(IssueBankData.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<IssueBankData> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(IssueBankData.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IssueBankData> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(IssueBankData.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IssueBankData> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(IssueBankData.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class TransRetLogEventResponse {
        public Log log;

        public String oper;

        public BigInteger status;

        public BigInteger check_code;
    }
}
