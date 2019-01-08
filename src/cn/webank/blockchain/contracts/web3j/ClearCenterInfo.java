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
public class ClearCenterInfo extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b604051610972380380610972833981016040528080518201919060200180518201919060200180519060200190919080518201919060200180519060200190919050505b84845b81600090805190602001906100699291906100b8565b5080600190805190602001906100809291906100b8565b505b5050826003816000191690555081600490805190602001906100a59291906100b8565b50806005819055505b505050505061015d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100f957805160ff1916838001178555610127565b82800160010185558215610127579182015b8281111561012657825182559160200191906001019061010b565b5b5090506101349190610138565b5090565b61015a91905b8082111561015657600081600090555060010161013e565b5090565b90565b6108068061016c6000396000f30060606040523615610081576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100835780631166b54b1461011c5780631386dc2d146101525780631c08303d146101eb578063293b671414610219578063488706301461023f578063a79af2ce146102d8575bfe5b341561008b57fe5b61009361041e565b60405180806020018281038252838181518152602001915080519060200190808383600083146100e2575b8051825260208311156100e2576020820191506020810190506020830392506100be565b505050905090810190601f16801561010e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561012457fe5b610150600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506104bc565b005b341561015a57fe5b610162610501565b60405180806020018281038252838181518152602001915080519060200190808383600083146101b1575b8051825260208311156101b15760208201915060208101905060208303925061018d565b505050905090810190601f1680156101dd5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101f357fe5b6101fb61059f565b60405180826000191660001916815260200191505060405180910390f35b341561022157fe5b6102296105a5565b6040518082815260200191505060405180910390f35b341561024757fe5b61024f6105ab565b604051808060200182810382528381815181526020019150805190602001908083836000831461029e575b80518252602083111561029e5760208201915060208101905060208303925061027a565b505050905090810190601f1680156102ca5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156102e057fe5b6102e8610649565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183810383528681815181526020019150805190602001908083836000831461036d575b80518252602083111561036d57602082019150602081019050602083039250610349565b505050905090810190601f1680156103995780820380516001836020036101000a031916815260200191505b508381038252858181518152602001915080519060200190808383600083146103e1575b8051825260208311156103e1576020820191506020810190506020830392506103bd565b505050905090810190601f16801561040d5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104b45780601f10610489576101008083540402835291602001916104b4565b820191906000526020600020905b81548152906001019060200180831161049757829003601f168201915b505050505081565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b60048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105975780601f1061056c57610100808354040283529160200191610597565b820191906000526020600020905b81548152906001019060200180831161057a57829003601f168201915b505050505081565b60035481565b60055481565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106415780601f1061061657610100808354040283529160200191610641565b820191906000526020600020905b81548152906001019060200180831161062457829003601f168201915b505050505081565b6106516107c6565b6106596107c6565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107175780601f106106ec57610100808354040283529160200191610717565b820191906000526020600020905b8154815290600101906020018083116106fa57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107b35780601f10610788576101008083540402835291602001916107b3565b820191906000526020600020905b81548152906001019060200180831161079657829003601f168201915b505050505091509250925092505b909192565b6020604051908101604052806000815250905600a165627a7a7230582027bb7ae0712a5b92533dd737193adb0581768d36cc9b10c712d4443c78b45d4d0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_ORGNAME = "orgName";

    public static final String FUNC_ORGID = "orgID";

    public static final String FUNC_EXCHANGECURRENCY = "exchangeCurrency";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_GETMETA = "getMeta";

    @Deprecated
    protected ClearCenterInfo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ClearCenterInfo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ClearCenterInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ClearCenterInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<BigInteger> exchangeCurrency() {
        final Function function = new Function(FUNC_EXCHANGECURRENCY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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
    public static ClearCenterInfo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClearCenterInfo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ClearCenterInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClearCenterInfo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ClearCenterInfo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ClearCenterInfo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ClearCenterInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ClearCenterInfo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ClearCenterInfo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String n, String abi, byte[] id, String name, BigInteger currency) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currency)));
        return deployRemoteCall(ClearCenterInfo.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ClearCenterInfo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String n, String abi, byte[] id, String name, BigInteger currency) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currency)));
        return deployRemoteCall(ClearCenterInfo.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ClearCenterInfo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String n, String abi, byte[] id, String name, BigInteger currency) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currency)));
        return deployRemoteCall(ClearCenterInfo.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ClearCenterInfo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String n, String abi, byte[] id, String name, BigInteger currency) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currency)));
        return deployRemoteCall(ClearCenterInfo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
