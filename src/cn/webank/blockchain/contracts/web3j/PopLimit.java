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
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
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
public class PopLimit extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b6040516108ed3803806108ed833981016040528080518201919060200180518201919060200180519060200190919080519060200190919080519060200190919050505b84845b81600090805190602001906100699291906100a4565b5080600190805190602001906100809291906100a4565b505b50508260038190555081600481905550806005819055505b5050505050610149565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100e557805160ff1916838001178555610113565b82800160010185558215610113579182015b828111156101125782518255916020019190600101906100f7565b5b5090506101209190610124565b5090565b61014691905b8082111561014257600081600090555060010161012a565b5090565b90565b610795806101586000396000f300606060405236156100a2576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100a45780631166b54b1461013d5780631a0eddcc14610173578063313f05c7146101935780633f4cc662146101b957806348870630146101d957806367b7dce01461027257806375c303c914610292578063a79af2ce146102b8578063f5697292146103fe575bfe5b34156100ac57fe5b6100b4610424565b6040518080602001828103825283818151815260200191508051906020019080838360008314610103575b805182526020831115610103576020820191506020810190506020830392506100df565b505050905090810190601f16801561012f5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561014557fe5b610171600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506104c2565b005b341561017b57fe5b6101916004808035906020019091905050610507565b005b341561019b57fe5b6101a3610512565b6040518082815260200191505060405180910390f35b34156101c157fe5b6101d76004808035906020019091905050610518565b005b34156101e157fe5b6101e9610523565b6040518080602001828103825283818151815260200191508051906020019080838360008314610238575b80518252602083111561023857602082019150602081019050602083039250610214565b505050905090810190601f1680156102645780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561027a57fe5b61029060048080359060200190919050506105c1565b005b341561029a57fe5b6102a26105cc565b6040518082815260200191505060405180910390f35b34156102c057fe5b6102c86105d2565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528681815181526020019150805190602001908083836000831461034d575b80518252602083111561034d57602082019150602081019050602083039250610329565b505050905090810190601f1680156103795780820380516001836020036101000a031916815260200191505b508381038252858181518152602001915080519060200190808383600083146103c1575b8051825260208311156103c15760208201915060208101905060208303925061039d565b505050905090810190601f1680156103ed5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b341561040657fe5b61040e61074f565b6040518082815260200191505060405180910390f35b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104ba5780601f1061048f576101008083540402835291602001916104ba565b820191906000526020600020905b81548152906001019060200180831161049d57829003601f168201915b505050505081565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b806004819055505b50565b60035481565b806003819055505b50565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105b95780601f1061058e576101008083540402835291602001916105b9565b820191906000526020600020905b81548152906001019060200180831161059c57829003601f168201915b505050505081565b806005819055505b50565b60055481565b6105da610755565b6105e2610755565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106a05780601f10610675576101008083540402835291602001916106a0565b820191906000526020600020905b81548152906001019060200180831161068357829003601f168201915b50505050509250818054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561073c5780601f106107115761010080835404028352916020019161073c565b820191906000526020600020905b81548152906001019060200180831161071f57829003601f168201915b505050505091509250925092505b909192565b60045481565b6020604051908101604052806000815250905600a165627a7a723058200bec4728627a7286bdd9e5bdfe7b4f87e52030a46f2f62d4ccecb9ddafa88a560029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_SETYEARLIMIT = "setYearLimit";

    public static final String FUNC_TODAYLIMIT = "todayLimit";

    public static final String FUNC_SETTODAYLIMIT = "setTodayLimit";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_SETORDERLIMIT = "setOrderLimit";

    public static final String FUNC_ORDERLIMIT = "orderLimit";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_YEARLIMIT = "yearLimit";

    @Deprecated
    protected PopLimit(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PopLimit(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PopLimit(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PopLimit(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<TransactionReceipt> setYearLimit(BigInteger year) {
        final Function function = new Function(
                FUNC_SETYEARLIMIT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(year)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> todayLimit() {
        final Function function = new Function(FUNC_TODAYLIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setTodayLimit(BigInteger today) {
        final Function function = new Function(
                FUNC_SETTODAYLIMIT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(today)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setOrderLimit(BigInteger order) {
        final Function function = new Function(
                FUNC_SETORDERLIMIT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(order)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> orderLimit() {
        final Function function = new Function(FUNC_ORDERLIMIT, 
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

    public RemoteCall<BigInteger> yearLimit() {
        final Function function = new Function(FUNC_YEARLIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static PopLimit load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PopLimit(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PopLimit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PopLimit(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PopLimit load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PopLimit(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PopLimit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PopLimit(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PopLimit> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String abi, BigInteger today, BigInteger year, BigInteger order) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(today), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(year), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(order)));
        return deployRemoteCall(PopLimit.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PopLimit> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String abi, BigInteger today, BigInteger year, BigInteger order) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(today), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(year), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(order)));
        return deployRemoteCall(PopLimit.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PopLimit> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String abi, BigInteger today, BigInteger year, BigInteger order) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(today), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(year), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(order)));
        return deployRemoteCall(PopLimit.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PopLimit> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String abi, BigInteger today, BigInteger year, BigInteger order) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(today), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(year), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(order)));
        return deployRemoteCall(PopLimit.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
