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
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
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
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
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
public class AcquirerBankData extends Contract {
    private static final String BINARY = "606060405234156200000d57fe5b604051620010ca380380620010ca833981016040528080518201919060200180518201919050505b81815b81600090805190602001906200005092919062000076565b5080600190805190602001906200006992919062000076565b505b50505b505062000125565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000b957805160ff1916838001178555620000ea565b82800160010185558215620000ea579182015b82811115620000e9578251825591602001919060010190620000cc565b5b509050620000f99190620000fd565b5090565b6200012291905b808211156200011e57600081600090555060010162000104565b5090565b90565b610f9580620001356000396000f300606060405236156100ef576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100f15780630afb21fa1461018a5780631166b54b146101b05780632443e1b0146101e6578063359b582d146102065780633f7644331461026a578063488706301461028a5780635778472a146103235780636792119514610387578063a06d083c146103e7578063a79af2ce1461040d578063c3eb64f014610553578063ce845d1d146105dd578063d4c2700e14610603578063db548d8214610629578063eb9125fd14610649578063ec13fd27146106a4575bfe5b34156100f957fe5b6101016106ff565b6040518080602001828103825283818151815260200191508051906020019080838360008314610150575b8051825260208311156101505760208201915060208101905060208303925061012c565b505050905090810190601f16801561017c5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561019257fe5b61019a61079d565b6040518082815260200191505060405180910390f35b34156101b857fe5b6101e4600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506107ab565b005b34156101ee57fe5b61020460048080359060200190919050506107f0565b005b341561020e57fe5b6102286004808035600019169060200190919050506107fb565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561027257fe5b6102886004808035906020019091905050610841565b005b341561029257fe5b61029a61084c565b60405180806020018281038252838181518152602001915080519060200190808383600083146102e9575b8051825260208311156102e9576020820191506020810190506020830392506102c5565b505050905090810190601f1680156103155780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561032b57fe5b6103456004808035600019169060200190919050506108ea565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561038f57fe5b6103a56004808035906020019091905050610930565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156103ef57fe5b6103f7610991565b6040518082815260200191505060405180910390f35b341561041557fe5b61041d610997565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352868181518152602001915080519060200190808383600083146104a2575b8051825260208311156104a25760208201915060208101905060208303925061047e565b505050905090810190601f1680156104ce5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360008314610516575b805182526020831115610516576020820191506020810190506020830392506104f2565b505050905090810190601f1680156105425780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b341561055b57fe5b6105716004808035906020019091905050610b14565b60405180806020018381526020018281038252848181518152602001915080519060200190602002808383600083146105c9575b8051825260208311156105c9576020820191506020810190506020830392506105a5565b505050905001935050505060405180910390f35b34156105e557fe5b6105ed610c24565b6040518082815260200191505060405180910390f35b341561060b57fe5b610613610c2a565b6040518082815260200191505060405180910390f35b341561063157fe5b6106476004808035906020019091905050610c30565b005b341561065157fe5b61068a60048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610c3b565b604051808215151515815260200191505060405180910390f35b34156106ac57fe5b6106e560048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610d53565b604051808215151515815260200191505060405180910390f35b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107955780601f1061076a57610100808354040283529160200191610795565b820191906000526020600020905b81548152906001019060200180831161077857829003601f168201915b505050505081565b600060058054905090505b90565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b80600c819055505b50565b600060086000836000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b919050565b80600b819055505b50565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108e25780601f106108b7576101008083540402835291602001916108e2565b820191906000526020600020905b8154815290600101906020018083116108c557829003601f168201915b505050505081565b600060046000836000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b919050565b60006005805490508210156109825760058281548110151561094e57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905061098c565b6000905061098c565b5b919050565b600b5481565b61099f610e9f565b6109a7610e9f565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a655780601f10610a3a57610100808354040283529160200191610a65565b820191906000526020600020905b815481529060010190602001808311610a4857829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b015780601f10610ad657610100808354040283529160200191610b01565b820191906000526020600020905b815481529060010190602001808311610ae457829003601f168201915b505050505091509250925092505b909192565b610b1c610eb3565b60006000600a604051805910610b2f5750595b908082528060200260200182016040525b5092506000915083600a0290505b600a82108015610b62575060098054905081105b15610c1d5760086000600983806001019450815481101515610b8057fe5b906000526020600020900160005b50546000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168383806001019450815181101515610bdb57fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b610b4e565b5b50915091565b600c5481565b600d5481565b80600d819055505b50565b6000600060086000856000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff16141515610cbc5760009150610d4c565b8260086000866000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060098054806001018281610d2a9190610ec7565b916000526020600020900160005b86909190915090600019169055505b600191505b5092915050565b6000600060046000856000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff16141515610dd45760009150610e98565b8260046000866000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060058054806001018281610e429190610ef3565b916000526020600020900160005b85909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b600191505b5092915050565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b815481835581811511610eee57818360005260206000209182019101610eed9190610f1f565b5b505050565b815481835581811511610f1a57818360005260206000209182019101610f199190610f44565b5b505050565b610f4191905b80821115610f3d576000816000905550600101610f25565b5090565b90565b610f6691905b80821115610f62576000816000905550600101610f4a565b5090565b905600a165627a7a72305820321f58dfccb76bcd9e46aa856282f4ce63135e8b1d84f9b4892895639f87d6180029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_GETTRANSLISTSIZE = "getTransListSize";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_SETCURRENTBALANCE = "setCurrentBalance";

    public static final String FUNC_GETMERCHANT = "getMerchant";

    public static final String FUNC_SETCREDIT = "setCredit";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_GETORDER = "getOrder";

    public static final String FUNC_GETTRANSBYINDEX = "getTransByIndex";

    public static final String FUNC_CREDIT = "credit";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_GETMERCHANTLIST = "getMerchantList";

    public static final String FUNC_CURRENTBALANCE = "currentBalance";

    public static final String FUNC_CURRENCYBASE = "currencyBase";

    public static final String FUNC_SETCURRENCYBASE = "setCurrencyBase";

    public static final String FUNC_ADDMERCHANT = "addMerchant";

    public static final String FUNC_ADDORDER = "addOrder";

    public static final Event TRANSRETLOG_EVENT = new Event("transRetLog", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
    ;

    public static final Event DEBUGRETLOG_EVENT = new Event("debugRetLog", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected AcquirerBankData(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AcquirerBankData(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AcquirerBankData(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AcquirerBankData(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getTransListSize() {
        final Function function = new Function(FUNC_GETTRANSLISTSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setMetaAddress(String meta) {
        final Function function = new Function(
                FUNC_SETMETAADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setCurrentBalance(BigInteger current_balance) {
        final Function function = new Function(
                FUNC_SETCURRENTBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(current_balance)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getMerchant(byte[] merchantid) {
        final Function function = new Function(FUNC_GETMERCHANT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(merchantid)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<TransactionReceipt> getOrder(byte[] abBizNo) {
        final Function function = new Function(
                FUNC_GETORDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(abBizNo)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getTransByIndex(BigInteger offset) {
        final Function function = new Function(FUNC_GETTRANSBYINDEX, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(offset)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<Tuple2<List<String>, BigInteger>> getMerchantList(BigInteger pageIndex) {
        final Function function = new Function(FUNC_GETMERCHANTLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(pageIndex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<List<String>, BigInteger>>(
                new Callable<Tuple2<List<String>, BigInteger>>() {
                    @Override
                    public Tuple2<List<String>, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<String>, BigInteger>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
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

    public RemoteCall<TransactionReceipt> addMerchant(byte[] merchantid, String merchant) {
        final Function function = new Function(
                FUNC_ADDMERCHANT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(merchantid), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(merchant)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addOrder(byte[] abBizNo, String order) {
        final Function function = new Function(
                FUNC_ADDORDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(abBizNo), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(order)), 
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

    public Flowable<TransRetLogEventResponse> transRetLogEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransRetLogEventResponse>() {
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
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSRETLOG_EVENT));
        return transRetLogEventFlowable(filter);
    }

    public List<DebugRetLogEventResponse> getDebugRetLogEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEBUGRETLOG_EVENT, transactionReceipt);
        ArrayList<DebugRetLogEventResponse> responses = new ArrayList<DebugRetLogEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebugRetLogEventResponse typedResponse = new DebugRetLogEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.msg1 = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.msg2 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.msg3 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DebugRetLogEventResponse> debugRetLogEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, DebugRetLogEventResponse>() {
            @Override
            public DebugRetLogEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEBUGRETLOG_EVENT, log);
                DebugRetLogEventResponse typedResponse = new DebugRetLogEventResponse();
                typedResponse.log = log;
                typedResponse.msg1 = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.msg2 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.msg3 = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DebugRetLogEventResponse> debugRetLogEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEBUGRETLOG_EVENT));
        return debugRetLogEventFlowable(filter);
    }

    @Deprecated
    public static AcquirerBankData load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AcquirerBankData(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AcquirerBankData load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AcquirerBankData(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AcquirerBankData load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AcquirerBankData(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AcquirerBankData load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AcquirerBankData(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AcquirerBankData> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(AcquirerBankData.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<AcquirerBankData> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(AcquirerBankData.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AcquirerBankData> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(AcquirerBankData.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AcquirerBankData> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(AcquirerBankData.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class TransRetLogEventResponse {
        public Log log;

        public String oper;

        public BigInteger status;

        public BigInteger check_code;
    }

    public static class DebugRetLogEventResponse {
        public Log log;

        public byte[] msg1;

        public BigInteger msg2;

        public BigInteger msg3;
    }
}
