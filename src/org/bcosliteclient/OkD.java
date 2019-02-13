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
public class OkD extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b6000600060006001600060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506402540be4006000600101819055506002600260000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060026001018190555061100192508273ffffffffffffffffffffffffffffffffffffffff166356004b6a6000604051602001526040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845260048152602001807f745f6f6b000000000000000000000000000000000000000000000000000000008152506020018481038352600b8152602001807f66726f6d5f6163636f7574000000000000000000000000000000000000000000815250602001848103825260218152602001807f66726f6d5f62616c616e63652c746f5f6163636f75742c746f5f62616c616e6381526020017f65000000000000000000000000000000000000000000000000000000000000008152506040019350505050602060405180830381600087803b15156101f957fe5b6102c65a03f1151561020757fe5b505050604051805190505061100192508273ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260048152602001807f745f6f6b00000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15156102bc57fe5b6102c65a03f115156102ca57fe5b5050506040518051905091508173ffffffffffffffffffffffffffffffffffffffff166313db93466000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561034257fe5b6102c65a03f1151561035057fe5b5050506040518051905090508073ffffffffffffffffffffffffffffffffffffffff1663e942b5166040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600b8152602001807f66726f6d5f6163636f7574000000000000000000000000000000000000000000815250602001838103825260038152602001807f307831000000000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b151561043157fe5b6102c65a03f1151561043f57fe5b5050508073ffffffffffffffffffffffffffffffffffffffff16632ef8ba746402540be4006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600c8152602001807f66726f6d5f62616c616e6365000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15156104eb57fe5b6102c65a03f115156104f957fe5b5050508073ffffffffffffffffffffffffffffffffffffffff1663e942b5166040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260098152602001807f746f5f6163636f75740000000000000000000000000000000000000000000000815250602001838103825260038152602001807f307832000000000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15156105d157fe5b6102c65a03f115156105df57fe5b5050508073ffffffffffffffffffffffffffffffffffffffff16632ef8ba7460006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600a8152602001807f746f5f62616c616e63650000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b151561068757fe5b6102c65a03f1151561069557fe5b5050505b5050505b610776806106ac6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680636d4ce63c14610046578063abe181b51461006c575bfe5b341561004e57fe5b6100566100cf565b6040518082815260200191505060405180910390f35b341561007457fe5b6100cd600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919080359060200190919050506100dd565b005b600060026001015490505b90565b600060006000600084600060010154036000600101819055508460026001016000828254019250508190555061100193508373ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260048152602001807f745f6f6b00000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15156101b357fe5b6102c65a03f115156101c157fe5b5050506040518051905092508273ffffffffffffffffffffffffffffffffffffffff166313db93466000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561023957fe5b6102c65a03f1151561024757fe5b5050506040518051905091508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600b8152602001807f66726f6d5f6163636f7574000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360008314610322575b805182526020831115610322576020820191506020810190506020830392506102fe565b505050905090810190601f16801561034e5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b151561036a57fe5b6102c65a03f1151561037857fe5b5050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba746000600101546040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600c8152602001807f66726f6d5f62616c616e6365000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b151561042457fe5b6102c65a03f1151561043257fe5b5050508173ffffffffffffffffffffffffffffffffffffffff1663e942b5166040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260098152602001807f746f5f6163636f75740000000000000000000000000000000000000000000000815250602001838103825260038152602001807f307832000000000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b151561050a57fe5b6102c65a03f1151561051857fe5b5050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba746002600101546040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600a8152602001807f746f5f62616c616e63650000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15156105c457fe5b6102c65a03f115156105d257fe5b5050508273ffffffffffffffffffffffffffffffffffffffff166331afac3687846000604051602001526040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281038252848181518152602001915080519060200190808383600083146106a8575b8051825260208311156106a857602082019150602081019050602083039250610684565b505050905090810190601f1680156106d45780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15156106f057fe5b6102c65a03f115156106fe57fe5b5050506040518051905090507f66f7705280112a4d1145399e0414adc43a2d6974b487710f417edcf7d4a39d71816040518082815260200191505060405180910390a15b5050505050505600a165627a7a72305820892722b34a0880058c2336c7d35cc341fb2988e2c69c03fdd6ca6729a91a1abd0029";

    public static final String FUNC_GET = "get";

    public static final String FUNC_TRANS = "trans";

    public static final Event INSERTRESULT_EVENT = new Event("insertResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected OkD(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected OkD(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected OkD(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected OkD(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> get() {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> trans(String from_accout, BigInteger num) {
        final Function function = new Function(
                FUNC_TRANS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_accout), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(num)), 
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

    public Flowable<InsertResultEventResponse> insertResultEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, InsertResultEventResponse>() {
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
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INSERTRESULT_EVENT));
        return insertResultEventFlowable(filter);
    }

    @Deprecated
    public static OkD load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new OkD(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static OkD load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new OkD(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static OkD load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new OkD(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static OkD load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new OkD(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<OkD> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OkD.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<OkD> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(OkD.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<OkD> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OkD.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<OkD> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(OkD.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class InsertResultEventResponse {
        public Log log;

        public BigInteger count;
    }
}
