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
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
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
public class UserLimit extends Contract {
    private static final String BINARY = "606060405234156200000d57fe5b6040516200158738038062001587833981016040528080518201919060200180518201919060200180519060200190919080519060200190919050505b83835b816000908051906020019062000065929190620003be565b5080600190805190602001906200007e929190620003be565b505b505081600860006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600960006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555062000122620001516401000000000262000847176401000000009004565b6005600060036000849190505583919050555050600060048190555060006006819055505b505050506200046d565b60006000600060006000600060006103e86103e8428115156200017057fe5b0642038115156200017d57fe5b049450600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166392d66313866000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15156200021757fe5b6102c65a03f115156200022657fe5b5050506040518051905061ffff169350600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a324ad24866000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515620002cd57fe5b6102c65a03f11515620002dc57fe5b5050506040518051905060ff169250600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166365c72840866000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15156200038257fe5b6102c65a03f115156200039157fe5b5050506040518051905060ff16915081606484026127108602010190508381965096505b50505050509091565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200040157805160ff191683800117855562000432565b8280016001018555821562000432579182015b828111156200043157825182559160200191906001019062000414565b5b50905062000441919062000445565b5090565b6200046a91905b80821115620004665760008160009055506001016200044c565b5090565b90565b61110a806200047d6000396000f300606060405236156100d8576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806211e6fb146100da57806306fdde03146101005780630b5a006b146101995780631166b54b146101bf5780633a20c2f0146101f55780634171dd7f1461021b57806343a6f4ee1461025157806348870630146102a357806348c8cd411461033c5780635504160e1461038e578063a167cfc9146103e0578063a79af2ce1461040d578063e553c60d14610553578063efedcc6414610589578063f4e456f3146105d7575bfe5b34156100e257fe5b6100ea6105fd565b6040518082815260200191505060405180910390f35b341561010857fe5b610110610603565b604051808060200182810382528381815181526020019150805190602001908083836000831461015f575b80518252602083111561015f5760208201915060208101905060208303925061013b565b505050905090810190601f16801561018b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101a157fe5b6101a96106a1565b6040518082815260200191505060405180910390f35b34156101c757fe5b6101f3600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506106a7565b005b34156101fd57fe5b6102056106ec565b6040518082815260200191505060405180910390f35b341561022357fe5b61024f600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506106f2565b005b341561025957fe5b610261610737565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156102ab57fe5b6102b361075d565b6040518080602001828103825283818151815260200191508051906020019080838360008314610302575b805182526020831115610302576020820191506020810190506020830392506102de565b505050905090810190601f16801561032e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561034457fe5b61034c6107fb565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561039657fe5b61039e610821565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156103e857fe5b6103f0610847565b604051808381526020018281526020019250505060405180910390f35b341561041557fe5b61041d610aac565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381038352868181518152602001915080519060200190808383600083146104a2575b8051825260208311156104a25760208201915060208101905060208303925061047e565b505050905090810190601f1680156104ce5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360008314610516575b805182526020831115610516576020820191506020810190506020830392506104f2565b505050905090810190601f1680156105425780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b341561055b57fe5b610587600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610c29565b005b341561059157fe5b6105bd600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610c6e565b604051808215151515815260200191505060405180910390f35b34156105df57fe5b6105e76110c4565b6040518082815260200191505060405180910390f35b60065481565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106995780601f1061066e57610100808354040283529160200191610699565b820191906000526020600020905b81548152906001019060200180831161067c57829003601f168201915b505050505081565b60055481565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b60045481565b80600860006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b600760009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107f35780601f106107c8576101008083540402835291602001916107f3565b820191906000526020600020905b8154815290600101906020018083116107d657829003601f168201915b505050505081565b600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006000600060006000600060006103e86103e84281151561086557fe5b06420381151561087157fe5b049450600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166392d66313866000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561090a57fe5b6102c65a03f1151561091857fe5b5050506040518051905061ffff169350600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a324ad24866000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15156109be57fe5b6102c65a03f115156109cc57fe5b5050506040518051905060ff169250600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166365c72840866000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515610a7157fe5b6102c65a03f11515610a7f57fe5b5050506040518051905060ff16915081606484026127108602010190508381965096505b50505050509091565b610ab46110ca565b610abc6110ca565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b7a5780601f10610b4f57610100808354040283529160200191610b7a565b820191906000526020600020905b815481529060010190602001808311610b5d57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c165780601f10610beb57610100808354040283529160200191610c16565b820191906000526020600020905b815481529060010190602001808311610bf957829003601f168201915b505050505091509250925092505b909192565b80600760006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b6000600060006000600060008673ffffffffffffffffffffffffffffffffffffffff1663269410536000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610ce657fe5b6102c65a03f11515610cf457fe5b5050506040518051905094508673ffffffffffffffffffffffffffffffffffffffff16636ad875196000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610d6c57fe5b6102c65a03f11515610d7a57fe5b5050506040518051905093508673ffffffffffffffffffffffffffffffffffffffff1663779cd0836000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610df257fe5b6102c65a03f11515610e0057fe5b505050604051805190509250600d84148015610e1d575060018314155b156110b557600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166375c303c96000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610eb057fe5b6102c65a03f11515610ebe57fe5b50505060405180519050851315610ed857600095506110ba565b610ee0610847565b809250819350505060055482141515610f03578160058190555060006006819055505b60035481141515610f1e578060038190555060006004819055505b600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663313f05c76000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610fac57fe5b6102c65a03f11515610fba57fe5b5050506040518051905085600454011315610fd857600095506110ba565b600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f56972926000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561106657fe5b6102c65a03f1151561107457fe5b505050604051805190508560065401131561109257600095506110ba565b84600460008282540192505081905550846006600082825401925050819055505b5b5b600195505b5050505050919050565b60035481565b6020604051908101604052806000815250905600a165627a7a7230582003dc4998d410e5eaa7ca2b5b9bddfce63b8a5118cb19b8fab4125d675d04af560029";

    public static final String FUNC_YEARTOTAL = "yearTotal";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_CURRENTYEAR = "currentYear";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_TODAYTOTAL = "todayTotal";

    public static final String FUNC_SETPOPLIMIT = "setPopLimit";

    public static final String FUNC_USERINFO = "userInfo";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_DATETIME = "dateTime";

    public static final String FUNC_POPLIMIT = "popLimit";

    public static final String FUNC_GETYEARANDDATE = "getYearAndDate";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_SETUSERINFO = "setUserInfo";

    public static final String FUNC_LIMITCHECK = "limitCheck";

    public static final String FUNC_CURRENTTODAY = "currentToday";

    @Deprecated
    protected UserLimit(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserLimit(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UserLimit(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UserLimit(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> yearTotal() {
        final Function function = new Function(FUNC_YEARTOTAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> currentYear() {
        final Function function = new Function(FUNC_CURRENTYEAR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setMetaAddress(String meta) {
        final Function function = new Function(
                FUNC_SETMETAADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(meta)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> todayTotal() {
        final Function function = new Function(FUNC_TODAYTOTAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setPopLimit(String _poplimit) {
        final Function function = new Function(
                FUNC_SETPOPLIMIT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_poplimit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> userInfo() {
        final Function function = new Function(FUNC_USERINFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> dateTime() {
        final Function function = new Function(FUNC_DATETIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> popLimit() {
        final Function function = new Function(FUNC_POPLIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> getYearAndDate() {
        final Function function = new Function(FUNC_GETYEARANDDATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
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

    public RemoteCall<TransactionReceipt> setUserInfo(String _userinfo) {
        final Function function = new Function(
                FUNC_SETUSERINFO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userinfo)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> limitCheck(String order) {
        final Function function = new Function(
                FUNC_LIMITCHECK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(order)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> currentToday() {
        final Function function = new Function(FUNC_CURRENTTODAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static UserLimit load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserLimit(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UserLimit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserLimit(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UserLimit load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserLimit(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserLimit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UserLimit(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UserLimit> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String abi, String _poplimit, String _datetime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_poplimit), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_datetime)));
        return deployRemoteCall(UserLimit.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<UserLimit> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String abi, String _poplimit, String _datetime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_poplimit), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_datetime)));
        return deployRemoteCall(UserLimit.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<UserLimit> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String abi, String _poplimit, String _datetime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_poplimit), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_datetime)));
        return deployRemoteCall(UserLimit.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<UserLimit> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String abi, String _poplimit, String _datetime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_poplimit), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_datetime)));
        return deployRemoteCall(UserLimit.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
