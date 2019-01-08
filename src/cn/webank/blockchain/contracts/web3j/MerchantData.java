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
import org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray20;
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
public class MerchantData extends Contract {
    private static final String BINARY = "606060405234156200000d57fe5b60405160208062001052833981016040528080519060200190919050505b602060405190810160405280600081525060206040519081016040528060008152505b816000908051906020019062000066929190620000ef565b5080600190805190602001906200007f929190620000ef565b505b5050620000a281620000aa6401000000000262000709176401000000009004565b5b506200019e565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200013257805160ff191683800117855562000163565b8280016001018555821562000163579182015b828111156200016257825182559160200191906001019062000145565b5b50905062000172919062000176565b5090565b6200019b91905b80821115620001975760008160009055506001016200017d565b5090565b90565b610ea480620001ae6000396000f300606060405236156100b8576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100ba5780631166b54b146101535780632737297a146101895780633b26f964146101cc57806348870630146102085780636bb28341146102a157806376f75e7f1461031b578063942b765a1461037b578063a79af2ce14610446578063a897e7bb1461058c578063ec8073ea146105ec578063fe79809214610628575bfe5b34156100c257fe5b6100ca61066b565b6040518080602001828103825283818151815260200191508051906020019080838360008314610119575b805182526020831115610119576020820191506020810190506020830392506100f5565b505050905090810190601f1680156101455780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561015b57fe5b610187600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610709565b005b341561019157fe5b6101ca60048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061074e565b005b34156101d457fe5b6101ea60048080359060200190919050506107e7565b60405180826000191660001916815260200191505060405180910390f35b341561021057fe5b61021861080c565b6040518080602001828103825283818151815260200191508051906020019080838360008314610267575b80518252602083111561026757602082019150602081019050602083039250610243565b505050905090810190601f1680156102935780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156102a957fe5b6102c860048080359060200190919080359060200190919050506108aa565b6040518083601460200280838360008314610302575b805182526020831115610302576020820191506020810190506020830392506102de565b5050509050018281526020019250505060405180910390f35b341561032357fe5b61033960048080359060200190919050506109a5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561038357fe5b61038b6109e5565b6040518080602001806020018381038352858181518152602001915080519060200190602002808383600083146103e1575b8051825260208311156103e1576020820191506020810190506020830392506103bd565b505050905001838103825284818151815260200191508051906020019060200280838360008314610431575b8051825260208311156104315760208201915060208101905060208303925061040d565b50505090500194505050505060405180910390f35b341561044e57fe5b610456610ae0565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352868181518152602001915080519060200190808383600083146104db575b8051825260208311156104db576020820191506020810190506020830392506104b7565b505050905090810190601f1680156105075780820380516001836020036101000a031916815260200191505b5083810382528581815181526020019150805190602001908083836000831461054f575b80518252602083111561054f5760208201915060208101905060208303925061052b565b505050905090810190601f16801561057b5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b341561059457fe5b6105aa6004808035906020019091905050610c5d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156105f457fe5b61060a6004808035906020019091905050610c9d565b60405180826000191660001916815260200191505060405180910390f35b341561063057fe5b61066960048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610cc2565b005b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107015780601f106106d657610100808354040283529160200191610701565b820191906000526020600020905b8154815290600101906020018083116106e457829003601f168201915b505050505081565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b600580548060010182816107629190610d5b565b916000526020600020900160005b8490919091509060001916905550600680548060010182816107929190610d87565b916000526020600020900160005b83909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b5050565b6003818154811015156107f657fe5b906000526020600020900160005b915090505481565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108a25780601f10610877576101008083540402835291602001916108a2565b820191906000526020600020905b81548152906001019060200180831161088557829003601f168201915b505050505081565b6108b2610db3565b6000600060148511806108cc575083850260048054905011155b156108d65761099d565b6000915083850290505b600480549050811080156108f8575060018401850281105b1561099c576004600182600480549050030381548110151561091657fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16838380600101945060148110151561095757fe5b602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250505b80806001019150506108e0565b5b509250929050565b6004818154811015156109b457fe5b906000526020600020900160005b915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6109ed610df2565b6109f5610e06565b6003600481805480602002602001604051908101604052809291908181526020018280548015610a4857602002820191906000526020600020905b81546000191681526020019060010190808311610a30575b5050505050915080805480602002602001604051908101604052809291908181526020018280548015610ad057602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610a86575b50505050509050915091505b9091565b610ae8610e1a565b610af0610e1a565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bae5780601f10610b8357610100808354040283529160200191610bae565b820191906000526020600020905b815481529060010190602001808311610b9157829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c4a5780601f10610c1f57610100808354040283529160200191610c4a565b820191906000526020600020905b815481529060010190602001808311610c2d57829003601f168201915b505050505091509250925092505b909192565b600681815481101515610c6c57fe5b906000526020600020900160005b915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600581815481101515610cac57fe5b906000526020600020900160005b915090505481565b60038054806001018281610cd69190610d5b565b916000526020600020900160005b849091909150906000191690555060048054806001018281610d069190610d87565b916000526020600020900160005b83909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b5050565b815481835581811511610d8257818360005260206000209182019101610d819190610e2e565b5b505050565b815481835581811511610dae57818360005260206000209182019101610dad9190610e53565b5b505050565b610280604051908101604052806014905b600073ffffffffffffffffffffffffffffffffffffffff16815260200190600190039081610dc45790505090565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b610e5091905b80821115610e4c576000816000905550600101610e34565b5090565b90565b610e7591905b80821115610e71576000816000905550600101610e59565b5090565b905600a165627a7a72305820991e4d2e87c53d4dd82f7da4841fca74123d1a29b6097188c9d9e23df3b5fcda0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_ADDCOMPENSATEABBIZNO = "addCompensateAbBizNo";

    public static final String FUNC_ABBIZNOLIST = "abBizNoList";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_GETORDERLIST = "getOrderList";

    public static final String FUNC_ORDERLIST = "orderList";

    public static final String FUNC_GETLIST = "getList";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_COMPENSATEORDERLIST = "compensateOrderList";

    public static final String FUNC_COMPENSATEABBIZNOLIST = "compensateAbBizNoList";

    public static final String FUNC_ADDABBIZNO = "addAbBizNo";

    public static final Event DEBUG_EVENT = new Event("Debug", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected MerchantData(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MerchantData(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MerchantData(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MerchantData(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<TransactionReceipt> addCompensateAbBizNo(byte[] abBizNo, String order) {
        final Function function = new Function(
                FUNC_ADDCOMPENSATEABBIZNO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(abBizNo), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(order)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> abBizNoList(BigInteger param0) {
        final Function function = new Function(FUNC_ABBIZNOLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<List<String>, BigInteger>> getOrderList(BigInteger pageSize, BigInteger pageIndex) {
        final Function function = new Function(FUNC_GETORDERLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(pageSize), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(pageIndex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray20<Address>>() {}, new TypeReference<Uint256>() {}));
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

    public RemoteCall<String> orderList(BigInteger param0) {
        final Function function = new Function(FUNC_ORDERLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<List<byte[]>, List<String>>> getList() {
        final Function function = new Function(FUNC_GETLIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<Tuple2<List<byte[]>, List<String>>>(
                new Callable<Tuple2<List<byte[]>, List<String>>>() {
                    @Override
                    public Tuple2<List<byte[]>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<byte[]>, List<String>>(
                                convertToNative((List<Bytes32>) results.get(0).getValue()), 
                                convertToNative((List<Address>) results.get(1).getValue()));
                    }
                });
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

    public RemoteCall<String> compensateOrderList(BigInteger param0) {
        final Function function = new Function(FUNC_COMPENSATEORDERLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> compensateAbBizNoList(BigInteger param0) {
        final Function function = new Function(FUNC_COMPENSATEABBIZNOLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> addAbBizNo(byte[] abBizNo, String order) {
        final Function function = new Function(
                FUNC_ADDABBIZNO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(abBizNo), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(order)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<DebugEventResponse> getDebugEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEBUG_EVENT, transactionReceipt);
        ArrayList<DebugEventResponse> responses = new ArrayList<DebugEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebugEventResponse typedResponse = new DebugEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.code = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.msg = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DebugEventResponse> debugEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, DebugEventResponse>() {
            @Override
            public DebugEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEBUG_EVENT, log);
                DebugEventResponse typedResponse = new DebugEventResponse();
                typedResponse.log = log;
                typedResponse.code = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.msg = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DebugEventResponse> debugEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEBUG_EVENT));
        return debugEventFlowable(filter);
    }

    @Deprecated
    public static MerchantData load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MerchantData(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MerchantData load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MerchantData(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MerchantData load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MerchantData(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MerchantData load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MerchantData(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MerchantData> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(MerchantData.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<MerchantData> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(MerchantData.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MerchantData> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(MerchantData.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MerchantData> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(MerchantData.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class DebugEventResponse {
        public Log log;

        public BigInteger code;

        public String msg;

        public String addr;
    }
}
