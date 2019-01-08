package cn.webank.blockchain.contracts.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
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
public class AcquirerBankInfo extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b6040516106d33803806106d3833981016040528080518201919060200180518201919060200180519060200190919080519060200190919050505b83835b816000908051906020019061006092919061009b565b50806001908051906020019061007792919061009b565b505b5050816003816000191690555080600481600019169055505b50505050610140565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100dc57805160ff191683800117855561010a565b8280016001018555821561010a579182015b828111156101095782518255916020019190600101906100ee565b5b509050610117919061011b565b5090565b61013d91905b80821115610139576000816000905550600101610121565b5090565b90565b6105848061014f6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100675780631166b54b1461009557806348870630146100cb578063a79af2ce14610164578063af640d0f146102aa575bfe5b341561006f57fe5b6100776102d8565b60405180826000191660001916815260200191505060405180910390f35b341561009d57fe5b6100c9600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506102de565b005b34156100d357fe5b6100db610323565b604051808060200182810382528381815181526020019150805190602001908083836000831461012a575b80518252602083111561012a57602082019150602081019050602083039250610106565b505050905090810190601f1680156101565780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561016c57fe5b6101746103c1565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352868181518152602001915080519060200190808383600083146101f9575b8051825260208311156101f9576020820191506020810190506020830392506101d5565b505050905090810190601f1680156102255780820380516001836020036101000a031916815260200191505b5083810382528581815181526020019150805190602001908083836000831461026d575b80518252602083111561026d57602082019150602081019050602083039250610249565b505050905090810190601f1680156102995780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34156102b257fe5b6102ba61053e565b60405180826000191660001916815260200191505060405180910390f35b60045481565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103b95780601f1061038e576101008083540402835291602001916103b9565b820191906000526020600020905b81548152906001019060200180831161039c57829003601f168201915b505050505081565b6103c9610544565b6103d1610544565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561048f5780601f106104645761010080835404028352916020019161048f565b820191906000526020600020905b81548152906001019060200180831161047257829003601f168201915b50505050509250818054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561052b5780601f106105005761010080835404028352916020019161052b565b820191906000526020600020905b81548152906001019060200180831161050e57829003601f168201915b505050505091509250925092505b909192565b60035481565b6020604051908101604052806000815250905600a165627a7a72305820120b29e7bf4a0e0aadbdd02cf068c36a0b6e5959fd3cb4fa538e74af73a3adbc0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_ID = "id";

    @Deprecated
    protected AcquirerBankInfo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AcquirerBankInfo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AcquirerBankInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AcquirerBankInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<byte[]> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setMetaAddress(String meta) {
        final Function function = new Function(
                FUNC_SETMETAADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<byte[]> id() {
        final Function function = new Function(FUNC_ID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    @Deprecated
    public static AcquirerBankInfo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AcquirerBankInfo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AcquirerBankInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AcquirerBankInfo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AcquirerBankInfo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AcquirerBankInfo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AcquirerBankInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AcquirerBankInfo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AcquirerBankInfo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String n, String abi, byte[] _id, byte[] _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_name)));
        return deployRemoteCall(AcquirerBankInfo.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<AcquirerBankInfo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String n, String abi, byte[] _id, byte[] _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_name)));
        return deployRemoteCall(AcquirerBankInfo.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AcquirerBankInfo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String n, String abi, byte[] _id, byte[] _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_name)));
        return deployRemoteCall(AcquirerBankInfo.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AcquirerBankInfo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String n, String abi, byte[] _id, byte[] _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_name)));
        return deployRemoteCall(AcquirerBankInfo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
