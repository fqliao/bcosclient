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
public class IssueBankInfo extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b604051610ac1380380610ac1833981016040528080518201919060200180518201919050505b81815b816000908051906020019061004b92919061006e565b50806001908051906020019061006292919061006e565b505b50505b5050610113565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100af57805160ff19168380011785556100dd565b828001600101855582156100dd579182015b828111156100dc5782518255916020019190600101906100c1565b5b5090506100ea91906100ee565b5090565b61011091905b8082111561010c5760008160009055506001016100f4565b5090565b90565b61099f806101226000396000f300606060405236156100a2576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100a45780631166b54b1461013d5780631386dc2d146101735780631c08303d1461020c5780632eef28151461023a5780633f7644331461025e578063488706301461027e578063a06d083c14610317578063a79af2ce1461033d578063d62d311f14610483575bfe5b34156100ac57fe5b6100b46104dd565b6040518080602001828103825283818151815260200191508051906020019080838360008314610103575b805182526020831115610103576020820191506020810190506020830392506100df565b505050905090810190601f16801561012f5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561014557fe5b610171600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061057b565b005b341561017b57fe5b6101836105c0565b60405180806020018281038252838181518152602001915080519060200190808383600083146101d2575b8051825260208311156101d2576020820191506020810190506020830392506101ae565b505050905090810190601f1680156101fe5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561021457fe5b61021c61065e565b60405180826000191660001916815260200191505060405180910390f35b341561024257fe5b61025c600480803560001916906020019091905050610664565b005b341561026657fe5b61027c6004808035906020019091905050610673565b005b341561028657fe5b61028e61067e565b60405180806020018281038252838181518152602001915080519060200190808383600083146102dd575b8051825260208311156102dd576020820191506020810190506020830392506102b9565b505050905090810190601f1680156103095780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561031f57fe5b61032761071c565b6040518082815260200191505060405180910390f35b341561034557fe5b61034d610722565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352868181518152602001915080519060200190808383600083146103d2575b8051825260208311156103d2576020820191506020810190506020830392506103ae565b505050905090810190601f1680156103fe5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360008314610446575b80518252602083111561044657602082019150602081019050602083039250610422565b505050905090810190601f1680156104725780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b341561048b57fe5b6104db600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190505061089f565b005b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105735780601f1061054857610100808354040283529160200191610573565b820191906000526020600020905b81548152906001019060200180831161055657829003601f168201915b505050505081565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b60048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106565780601f1061062b57610100808354040283529160200191610656565b820191906000526020600020905b81548152906001019060200180831161063957829003601f168201915b505050505081565b60035481565b80600381600019169055505b50565b806005819055505b50565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107145780601f106106e957610100808354040283529160200191610714565b820191906000526020600020905b8154815290600101906020018083116106f757829003601f168201915b505050505081565b60055481565b61072a6108ba565b6107326108ba565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107f05780601f106107c5576101008083540402835291602001916107f0565b820191906000526020600020905b8154815290600101906020018083116107d357829003601f168201915b50505050509250818054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561088c5780601f106108615761010080835404028352916020019161088c565b820191906000526020600020905b81548152906001019060200180831161086f57829003601f168201915b505050505091509250925092505b909192565b80600490805190602001906108b59291906108ce565b505b50565b602060405190810160405280600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061090f57805160ff191683800117855561093d565b8280016001018555821561093d579182015b8281111561093c578251825591602001919060010190610921565b5b50905061094a919061094e565b5090565b61097091905b8082111561096c576000816000905550600101610954565b5090565b905600a165627a7a7230582081ddf601f19fa716b773bed1c9cf315f74ffbcbba276f2d72948009caafc49070029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_ORGNAME = "orgName";

    public static final String FUNC_ORGID = "orgID";

    public static final String FUNC_SETORGID = "setOrgID";

    public static final String FUNC_SETCREDIT = "setCredit";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_CREDIT = "credit";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_SETORGNAME = "setOrgName";

    @Deprecated
    protected IssueBankInfo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IssueBankInfo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IssueBankInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IssueBankInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<String> orgName() {
        final Function function = new Function(FUNC_ORGNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> orgID() {
        final Function function = new Function(FUNC_ORGID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setOrgID(byte[] _orgID) {
        final Function function = new Function(
                FUNC_SETORGID, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_orgID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setCredit(BigInteger _credit) {
        final Function function = new Function(
                FUNC_SETCREDIT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_credit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> credit() {
        final Function function = new Function(FUNC_CREDIT, 
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

    public RemoteCall<TransactionReceipt> setOrgName(String _orgName) {
        final Function function = new Function(
                FUNC_SETORGNAME, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_orgName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IssueBankInfo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IssueBankInfo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IssueBankInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IssueBankInfo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IssueBankInfo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IssueBankInfo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IssueBankInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IssueBankInfo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IssueBankInfo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(IssueBankInfo.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<IssueBankInfo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(IssueBankInfo.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IssueBankInfo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(IssueBankInfo.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IssueBankInfo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(IssueBankInfo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
