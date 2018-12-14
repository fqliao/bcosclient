package org.bcosliteclient;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.EthFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class DBAuthority extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b6105688061001c6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306e63ff81461004657806344590a7e146100f7575bfe5b341561004e57fe5b6100e1600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506101a8565b6040518082815260200191505060405180910390f35b34156100ff57fe5b610192600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610372565b6040518082815260200191505060405180910390f35b60006000600061100591508173ffffffffffffffffffffffffffffffffffffffff166306e63ff886866000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835285818151815260200191508051906020019080838360008314610258575b80518252602083111561025857602082019150602081019050602083039250610234565b505050905090810190601f1680156102845780820380516001836020036101000a031916815260200191505b508381038252848181518152602001915080519060200190808383600083146102cc575b8051825260208311156102cc576020820191506020810190506020830392506102a8565b505050905090810190601f1680156102f85780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b151561031557fe5b6102c65a03f1151561032357fe5b5050506040518051905090507f66f7705280112a4d1145399e0414adc43a2d6974b487710f417edcf7d4a39d71816040518082815260200191505060405180910390a18092505b505092915050565b60006000600061100591508173ffffffffffffffffffffffffffffffffffffffff166344590a7e86866000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835285818151815260200191508051906020019080838360008314610422575b805182526020831115610422576020820191506020810190506020830392506103fe565b505050905090810190601f16801561044e5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360008314610496575b80518252602083111561049657602082019150602081019050602083039250610472565b505050905090810190601f1680156104c25780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b15156104df57fe5b6102c65a03f115156104ed57fe5b5050506040518051905090507f896358cb98e9e8e891ae04efd1bc177efbe5cffd7eca2e784b16ed7468553e08816040518082815260200191505060405180910390a18092505b5050929150505600a165627a7a72305820ce3a2661932369b56ca5da7ac5f7ded18b43750eaabee6495c40c3403d4c10b40029";

    public static final String FUNC_INSERT = "insert";

    public static final String FUNC_REMOVE = "remove";

    public static final Event INSERTRESULT_EVENT = new Event("insertResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event REMOVERESULT_EVENT = new Event("removeResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected DBAuthority(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DBAuthority(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DBAuthority(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DBAuthority(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> insert(String table_name, String addr) {
        final Function function = new Function(
                FUNC_INSERT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(table_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> remove(String table_name, String addr) {
        final Function function = new Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(table_name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<InsertResultEventResponse> getInsertResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INSERTRESULT_EVENT, transactionReceipt);
        ArrayList<InsertResultEventResponse> responses = new ArrayList<InsertResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InsertResultEventResponse typedResponse = new InsertResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<InsertResultEventResponse> insertResultEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, InsertResultEventResponse>() {
            @Override
            public InsertResultEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(INSERTRESULT_EVENT, log);
                InsertResultEventResponse typedResponse = new InsertResultEventResponse();
                typedResponse.log = log;
                typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<InsertResultEventResponse> insertResultEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INSERTRESULT_EVENT));
        return insertResultEventFlowable(filter);
    }

    public List<RemoveResultEventResponse> getRemoveResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REMOVERESULT_EVENT, transactionReceipt);
        ArrayList<RemoveResultEventResponse> responses = new ArrayList<RemoveResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RemoveResultEventResponse typedResponse = new RemoveResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RemoveResultEventResponse> removeResultEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, RemoveResultEventResponse>() {
            @Override
            public RemoveResultEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REMOVERESULT_EVENT, log);
                RemoveResultEventResponse typedResponse = new RemoveResultEventResponse();
                typedResponse.log = log;
                typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RemoveResultEventResponse> removeResultEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REMOVERESULT_EVENT));
        return removeResultEventFlowable(filter);
    }

    @Deprecated
    public static DBAuthority load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DBAuthority(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DBAuthority load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DBAuthority(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DBAuthority load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DBAuthority(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DBAuthority load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DBAuthority(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DBAuthority> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DBAuthority.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DBAuthority> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DBAuthority.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DBAuthority> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DBAuthority.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DBAuthority> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DBAuthority.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class InsertResultEventResponse {
        public Log log;

        public BigInteger count;
    }

    public static class RemoveResultEventResponse {
        public Log log;

        public BigInteger count;
    }
}
