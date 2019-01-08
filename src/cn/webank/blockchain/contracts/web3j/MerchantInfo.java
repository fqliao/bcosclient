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
public class MerchantInfo extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b604051606080610866833981016040528080519060200190919080519060200190919080519060200190919050505b602060405190810160405280600081525060206040519081016040528060008152505b816000908051906020019061007492919061010f565b50806001908051906020019061008b92919061010f565b505b5050826003816000191690555081600481600019169055506100c1816100ca640100000000026103f0176401000000009004565b5b5050506101b4565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061015057805160ff191683800117855561017e565b8280016001018555821561017e579182015b8281111561017d578251825591602001919060010190610162565b5b50905061018b919061018f565b5090565b6101b191905b808211156101ad576000816000905550600101610195565b5090565b90565b6106a3806101c36000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100675780631166b54b1461010057806348870630146101365780635a9b0b89146101cf578063a79af2ce1461020c575bfe5b341561006f57fe5b610077610352565b60405180806020018281038252838181518152602001915080519060200190808383600083146100c6575b8051825260208311156100c6576020820191506020810190506020830392506100a2565b505050905090810190601f1680156100f25780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561010857fe5b610134600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506103f0565b005b341561013e57fe5b610146610435565b6040518080602001828103825283818151815260200191508051906020019080838360008314610195575b80518252602083111561019557602082019150602081019050602083039250610171565b505050905090810190601f1680156101c15780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101d757fe5b6101df6104d3565b60405180836000191660001916815260200182600019166000191681526020019250505060405180910390f35b341561021457fe5b61021c6104e6565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352868181518152602001915080519060200190808383600083146102a1575b8051825260208311156102a15760208201915060208101905060208303925061027d565b505050905090810190601f1680156102cd5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360008314610315575b805182526020831115610315576020820191506020810190506020830392506102f1565b505050905090810190601f1680156103415780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103e85780601f106103bd576101008083540402835291602001916103e8565b820191906000526020600020905b8154815290600101906020018083116103cb57829003601f168201915b505050505081565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104cb5780601f106104a0576101008083540402835291602001916104cb565b820191906000526020600020905b8154815290600101906020018083116104ae57829003601f168201915b505050505081565b60006000600354600454915091505b9091565b6104ee610663565b6104f6610663565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105b45780601f10610589576101008083540402835291602001916105b4565b820191906000526020600020905b81548152906001019060200180831161059757829003601f168201915b50505050509250818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106505780601f1061062557610100808354040283529160200191610650565b820191906000526020600020905b81548152906001019060200180831161063357829003601f168201915b505050505091509250925092505b909192565b6020604051908101604052806000815250905600a165627a7a72305820dc29d5e3bde161e083c0e30b7e1262e6fb74bc373300873832e065aa77d671890029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_GETINFO = "getInfo";

    public static final String FUNC_GETMETA = "getMeta";

    @Deprecated
    protected MerchantInfo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MerchantInfo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MerchantInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MerchantInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<byte[], byte[]>> getInfo() {
        final Function function = new Function(FUNC_GETINFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteCall<Tuple2<byte[], byte[]>>(
                new Callable<Tuple2<byte[], byte[]>>() {
                    @Override
                    public Tuple2<byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<byte[], byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue());
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

    @Deprecated
    public static MerchantInfo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MerchantInfo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MerchantInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MerchantInfo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MerchantInfo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MerchantInfo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MerchantInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MerchantInfo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MerchantInfo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, byte[] id, byte[] name, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(MerchantInfo.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<MerchantInfo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, byte[] id, byte[] name, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(MerchantInfo.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MerchantInfo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, byte[] id, byte[] name, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(MerchantInfo.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MerchantInfo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, byte[] id, byte[] name, String meta) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(meta)));
        return deployRemoteCall(MerchantInfo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
