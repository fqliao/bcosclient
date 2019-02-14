package cn.webank.blockchain.contracts.web3j;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
import org.fisco.bcos.web3j.abi.datatypes.Event;
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
public class PopLog extends Contract {
    private static final String BINARY = "60606040523415600b57fe5b5b60338060196000396000f30060606040525bfe00a165627a7a723058209bd859a06a159f76d58df2bad821c6e58d305b6013904d56722da5928a0e38d60029";

    public static final Event TRANSRETLOG_EVENT = new Event("transRetLog", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
    ;

    public static final Event ORDERLOG_EVENT = new Event("orderLog", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Int256>>() {}, new TypeReference<Int256>() {}));
    ;

    public static final Event DEBUGRETLOG_EVENT = new Event("debugRetLog", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
    ;

    public static final Event NEWACCOUNT_EVENT = new Event("newAccount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    @Deprecated
    protected PopLog(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PopLog(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PopLog(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PopLog(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
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
        return web3j.logFlowable(filter).map(new Function<Log, TransRetLogEventResponse>() {
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

    public List<OrderLogEventResponse> getOrderLogEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORDERLOG_EVENT, transactionReceipt);
        ArrayList<OrderLogEventResponse> responses = new ArrayList<OrderLogEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrderLogEventResponse typedResponse = new OrderLogEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.bytesArgs = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.intArgs = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.txStatus = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OrderLogEventResponse> orderLogEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new Function<Log, OrderLogEventResponse>() {
            @Override
            public OrderLogEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORDERLOG_EVENT, log);
                OrderLogEventResponse typedResponse = new OrderLogEventResponse();
                typedResponse.log = log;
                typedResponse.bytesArgs = (List<byte[]>) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.intArgs = (List<BigInteger>) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.txStatus = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OrderLogEventResponse> orderLogEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORDERLOG_EVENT));
        return orderLogEventFlowable(filter);
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

    public Flowable<DebugRetLogEventResponse> debugRetLogEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new Function<Log, DebugRetLogEventResponse>() {
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
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEBUGRETLOG_EVENT));
        return debugRetLogEventFlowable(filter);
    }

    public List<NewAccountEventResponse> getNewAccountEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWACCOUNT_EVENT, transactionReceipt);
        ArrayList<NewAccountEventResponse> responses = new ArrayList<NewAccountEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewAccountEventResponse typedResponse = new NewAccountEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.org_id = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.account = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.currency = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.curr_date = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.firstName = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.lastName = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.idType = (byte[]) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.userID = (byte[]) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.UserCard = (byte[]) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewAccountEventResponse> newAccountEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new Function<Log, NewAccountEventResponse>() {
            @Override
            public NewAccountEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWACCOUNT_EVENT, log);
                NewAccountEventResponse typedResponse = new NewAccountEventResponse();
                typedResponse.log = log;
                typedResponse.org_id = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.account = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.currency = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.curr_date = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.firstName = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.lastName = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.idType = (byte[]) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse.userID = (byte[]) eventValues.getNonIndexedValues().get(7).getValue();
                typedResponse.UserCard = (byte[]) eventValues.getNonIndexedValues().get(8).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewAccountEventResponse> newAccountEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWACCOUNT_EVENT));
        return newAccountEventFlowable(filter);
    }

    @Deprecated
    public static PopLog load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PopLog(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PopLog load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PopLog(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PopLog load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PopLog(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PopLog load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PopLog(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PopLog> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PopLog.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PopLog> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PopLog.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<PopLog> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PopLog.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PopLog> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PopLog.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class TransRetLogEventResponse {
        public Log log;

        public String oper;

        public BigInteger status;

        public BigInteger check_code;
    }

    public static class OrderLogEventResponse {
        public Log log;

        public List<byte[]> bytesArgs;

        public List<BigInteger> intArgs;

        public BigInteger txStatus;
    }

    public static class DebugRetLogEventResponse {
        public Log log;

        public byte[] msg1;

        public BigInteger msg2;

        public BigInteger msg3;
    }

    public static class NewAccountEventResponse {
        public Log log;

        public byte[] org_id;

        public byte[] account;

        public BigInteger currency;

        public String curr_date;

        public byte[] firstName;

        public byte[] lastName;

        public byte[] idType;

        public byte[] userID;

        public byte[] UserCard;
    }
}
