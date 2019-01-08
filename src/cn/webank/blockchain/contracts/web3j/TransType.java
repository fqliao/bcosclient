package cn.webank.blockchain.contracts.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
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
public class TransType extends Contract {
    private static final String BINARY = "6060604052600d60005560156001556016600255601f600355341561002057fe5b5b610128806100306000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633c7669501460585780636ee678b914607b57806386c9512d14609e578063ffcd9dac1460c1575bfe5b3415605f57fe5b606560e4565b6040518082815260200191505060405180910390f35b3415608257fe5b608860ea565b6040518082815260200191505060405180910390f35b341560a557fe5b60ab60f0565b6040518082815260200191505060405180910390f35b341560c857fe5b60ce60f6565b6040518082815260200191505060405180910390f35b60025481565b60005481565b60035481565b600154815600a165627a7a723058203fac53552c1c912514b07fe7f668b63d04807fe86522588a94817847c0a1a9160029";

    public static final String FUNC_TYPEDRAW = "typeDraw";

    public static final String FUNC_TYPECONSUME = "typeConsume";

    public static final String FUNC_TYPEREVERSE = "typeReverse";

    public static final String FUNC_TYPERECHARGE = "typeRecharge";

    @Deprecated
    protected TransType(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TransType(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TransType(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TransType(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> typeDraw() {
        final Function function = new Function(FUNC_TYPEDRAW, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> typeConsume() {
        final Function function = new Function(FUNC_TYPECONSUME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> typeReverse() {
        final Function function = new Function(FUNC_TYPEREVERSE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> typeRecharge() {
        final Function function = new Function(FUNC_TYPERECHARGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static TransType load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransType(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TransType load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransType(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TransType load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TransType(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TransType load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TransType(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TransType> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransType.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransType> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransType.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TransType> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransType.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransType> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransType.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
