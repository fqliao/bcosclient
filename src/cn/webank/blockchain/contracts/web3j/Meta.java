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
public class Meta extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b604051610764380380610764833981016040528080518201919060200180518201919050505b8160009080519060200190610048929190610068565b50806001908051906020019061005f929190610068565b505b505061010d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100a957805160ff19168380011785556100d7565b828001600101855582156100d7579182015b828111156100d65782518255916020019190600101906100bb565b5b5090506100e491906100e8565b5090565b61010a91905b808211156101065760008160009055506001016100ee565b5090565b90565b6106488061011c6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde031461005c5780631166b54b146100f5578063488706301461012b578063a79af2ce146101c4575bfe5b341561006457fe5b61006c61030a565b60405180806020018281038252838181518152602001915080519060200190808383600083146100bb575b8051825260208311156100bb57602082019150602081019050602083039250610097565b505050905090810190601f1680156100e75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156100fd57fe5b610129600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506103a8565b005b341561013357fe5b61013b6103ed565b604051808060200182810382528381815181526020019150805190602001908083836000831461018a575b80518252602083111561018a57602082019150602081019050602083039250610166565b505050905090810190601f1680156101b65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101cc57fe5b6101d461048b565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838103835286818151815260200191508051906020019080838360008314610259575b80518252602083111561025957602082019150602081019050602083039250610235565b505050905090810190601f1680156102855780820380516001836020036101000a031916815260200191505b508381038252858181518152602001915080519060200190808383600083146102cd575b8051825260208311156102cd576020820191506020810190506020830392506102a9565b505050905090810190601f1680156102f95780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103a05780601f10610375576101008083540402835291602001916103a0565b820191906000526020600020905b81548152906001019060200180831161038357829003601f168201915b505050505081565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104835780601f1061045857610100808354040283529160200191610483565b820191906000526020600020905b81548152906001019060200180831161046657829003601f168201915b505050505081565b610493610608565b61049b610608565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105595780601f1061052e57610100808354040283529160200191610559565b820191906000526020600020905b81548152906001019060200180831161053c57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105f55780601f106105ca576101008083540402835291602001916105f5565b820191906000526020600020905b8154815290600101906020018083116105d857829003601f168201915b505050505091509250925092505b909192565b6020604051908101604052806000815250905600a165627a7a72305820a441f9a8646c5b0668248fe8394b60db2fe337736428d8fec2b231034997596e0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_GETMETA = "getMeta";

    @Deprecated
    protected Meta(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Meta(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Meta(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Meta(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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
    public static Meta load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Meta(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Meta load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Meta(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Meta load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Meta(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Meta load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Meta(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Meta> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String n, String a) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(a)));
        return deployRemoteCall(Meta.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Meta> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String n, String a) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(a)));
        return deployRemoteCall(Meta.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Meta> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String n, String a) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(a)));
        return deployRemoteCall(Meta.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Meta> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String n, String a) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(a)));
        return deployRemoteCall(Meta.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
