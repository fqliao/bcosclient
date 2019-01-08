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
public class Merchant extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b604051606080610d83833981016040528080519060200190919080519060200190919080519060200190919050505b602060405190810160405280600081525060206040519081016040528060008152505b816000908051906020019061007492919061017b565b50806001908051906020019061008b92919061017b565b505b505082600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061012d8161013664010000000002610589176401000000009004565b5b505050610220565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106101bc57805160ff19168380011785556101ea565b828001600101855582156101ea579182015b828111156101e95782518255916020019190600101906101ce565b5b5090506101f791906101fb565b5090565b61021d91905b80821115610219576000816000905550600101610201565b5090565b90565b610b548061022f6000396000f30060606040523615610097576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100995780631166b54b146101325780632737297a146101685780633bc5de30146101ab57806348870630146101fd5780635a9b0b89146102965780636bb28341146102e8578063a79af2ce14610362578063fe798092146104a8575bfe5b34156100a157fe5b6100a96104eb565b60405180806020018281038252838181518152602001915080519060200190808383600083146100f8575b8051825260208311156100f8576020820191506020810190506020830392506100d4565b505050905090810190601f1680156101245780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561013a57fe5b610166600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610589565b005b341561017057fe5b6101a960048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506105ce565b005b34156101b357fe5b6101bb6106ad565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561020557fe5b61020d6106d8565b604051808060200182810382528381815181526020019150805190602001908083836000831461025c575b80518252602083111561025c57602082019150602081019050602083039250610238565b505050905090810190601f1680156102885780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561029e57fe5b6102a6610776565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156102f057fe5b61030f60048080359060200190919080359060200190919050506107a1565b6040518083601460200280838360008314610349575b80518252602083111561034957602082019150602081019050602083039250610325565b5050509050018281526020019250505060405180910390f35b341561036a57fe5b610372610879565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352868181518152602001915080519060200190808383600083146103f7575b8051825260208311156103f7576020820191506020810190506020830392506103d3565b505050905090810190601f1680156104235780820380516001836020036101000a031916815260200191505b5083810382528581815181526020019150805190602001908083836000831461046b575b80518252602083111561046b57602082019150602081019050602083039250610447565b505050905090810190601f1680156104975780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34156104b057fe5b6104e960048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506109f6565b005b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105815780601f1061055657610100808354040283529160200191610581565b820191906000526020600020905b81548152906001019060200180831161056457829003601f168201915b505050505081565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632737297a83836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b151561069757fe5b6102c65a03f115156106a557fe5b5050505b5050565b6000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b90565b60018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561076e5780601f106107435761010080835404028352916020019161076e565b820191906000526020600020905b81548152906001019060200180831161075157829003601f168201915b505050505081565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b90565b6107a9610ad5565b6000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16636bb28341858560006040516102a001526040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180838152602001828152602001925050506102a060405180830381600087803b151561084b57fe5b6102c65a03f1151561085957fe5b5050506040518061028001805190602001604052915091505b9250929050565b610881610b14565b610889610b14565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109475780601f1061091c57610100808354040283529160200191610947565b820191906000526020600020905b81548152906001019060200180831161092a57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109e35780601f106109b8576101008083540402835291602001916109e3565b820191906000526020600020905b8154815290600101906020018083116109c657829003601f168201915b505050505091509250925092505b909192565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fe79809283836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b1515610abf57fe5b6102c65a03f11515610acd57fe5b5050505b5050565b610280604051908101604052806014905b600073ffffffffffffffffffffffffffffffffffffffff16815260200190600190039081610ae65790505090565b6020604051908101604052806000815250905600a165627a7a723058205eb616e48e03706d7fac624d17655ba62748808fecd8a1f408bb22eff34124200029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_ADDCOMPENSATEABBIZNO = "addCompensateAbBizNo";

    public static final String FUNC_GETDATA = "getData";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_GETINFO = "getInfo";

    public static final String FUNC_GETORDERLIST = "getOrderList";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_ADDABBIZNO = "addAbBizNo";

    public static final Event DEBUG_EVENT = new Event("Debug", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Merchant(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Merchant(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Merchant(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Merchant(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<String> getData() {
        final Function function = new Function(FUNC_GETDATA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getInfo() {
        final Function function = new Function(FUNC_GETINFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
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
    public static Merchant load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Merchant(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Merchant load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Merchant(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Merchant load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Merchant(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Merchant load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Merchant(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Merchant> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String info, String data, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(data), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(Merchant.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Merchant> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String info, String data, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(data), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(Merchant.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Merchant> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String info, String data, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(data), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(Merchant.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Merchant> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String info, String data, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(data), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(Merchant.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class DebugEventResponse {
        public Log log;

        public BigInteger code;

        public String msg;

        public String addr;
    }
}
