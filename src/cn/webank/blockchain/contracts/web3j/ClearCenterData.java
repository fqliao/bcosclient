package cn.webank.blockchain.contracts.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.Utils;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3;
import org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray5;
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
public class ClearCenterData extends Contract {
    private static final String BINARY = "606060405234156200000d57fe5b604051620013e4380380620013e4833981016040528080518201919060200180518201919050505b81815b81600090805190602001906200005092919062000076565b5080600190805190602001906200006992919062000076565b505b50505b505062000125565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000b957805160ff1916838001178555620000ea565b82800160010185558215620000ea579182015b82811115620000e9578251825591602001919060010190620000cc565b5b509050620000f99190620000fd565b5090565b6200012291905b808211156200011e57600081600090555060010162000104565b5090565b90565b6112af80620001356000396000f300606060405236156100ef576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde03146100f15780630afb21fa1461018a5780631166b54b146101b057806316023815146101e657806332c7f0ef1461021e57806348870630146102885780635f72811714610321578063679211951461035d5780637a2a66bb146103bd5780639f40439514610464578063a79af2ce146104bb578063af33b5e014610601578063c4c2694614610665578063d3fb3013146106c9578063d613fb57146106f6578063e1f726fe1461072c578063e714851214610752575bfe5b34156100f957fe5b610101610802565b6040518080602001828103825283818151815260200191508051906020019080838360008314610150575b8051825260208311156101505760208201915060208101905060208303925061012c565b505050905090810190601f16801561017c5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561019257fe5b61019a6108a0565b6040518082815260200191505060405180910390f35b34156101b857fe5b6101e4600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506108ae565b005b34156101ee57fe5b6102086004808035600019169060200190919050506108f3565b6040518082815260200191505060405180910390f35b341561022657fe5b61023c6004808035906020019091905050610919565b6040518082600560200280838360008314610276575b80518252602083111561027657602082019150602081019050602083039250610252565b50505090500191505060405180910390f35b341561029057fe5b610298610a54565b60405180806020018281038252838181518152602001915080519060200190808383600083146102e7575b8051825260208311156102e7576020820191506020810190506020830392506102c3565b505050905090810190601f1680156103135780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561032957fe5b61033f6004808035906020019091905050610af2565b60405180826000191660001916815260200191505060405180910390f35b341561036557fe5b61037b6004808035906020019091905050610b3c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156103c557fe5b6103db6004808035906020019091905050610b9d565b6040518083600360200280838360008314610415575b805182526020831115610415576020820191506020810190506020830392506103f1565b50505090500182600360200280838360008314610451575b8051825260208311156104515760208201915060208101905060208303925061042d565b5050509050019250505060405180910390f35b341561046c57fe5b6104a560048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610c77565b6040518082815260200191505060405180910390f35b34156104c357fe5b6104cb610d69565b6040518080602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838103835286818151815260200191508051906020019080838360008314610550575b8051825260208311156105505760208201915060208101905060208303925061052c565b505050905090810190601f16801561057c5780820380516001836020036101000a031916815260200191505b508381038252858181518152602001915080519060200190808383600083146105c4575b8051825260208311156105c4576020820191506020810190506020830392506105a0565b505050905090810190601f1680156105f05780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b341561060957fe5b610623600480803560001916906020019091905050610ee6565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561066d57fe5b610687600480803560001916906020019091905050610f2c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156106d157fe5b6106f4600480803560001916906020019091908035906020019091905050610f72565b005b34156106fe57fe5b61072a600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610f97565b005b341561073457fe5b61073c610fff565b6040518082815260200191505060405180910390f35b341561075a57fe5b6107ec60048080359060200190919080359060200190919080359060200190919080359060200190919080359060200190919080606001906003806020026040519081016040528092919082600360200280828437820191505050505091908060600190600380602002604051908101604052809291908260036020028082843782019150505050509190505061100d565b6040518082815260200191505060405180910390f35b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108985780601f1061086d57610100808354040283529160200191610898565b820191906000526020600020905b81548152906001019060200180831161087b57829003601f168201915b505050505081565b600060078054905090505b90565b80600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b6000600b600083600019166000191681526020019081526020016000205490505b919050565b6109216110cb565b6000600c60008481526020019081526020016000206000015481600060058110151561094957fe5b0160005b5081905550600c60008481526020019081526020016000206001015481600160058110151561097857fe5b0160005b5081905550600c6000848152602001908152602001600020600201548160026005811015156109a757fe5b0160005b5081905550600c6000848152602001908152602001600020600301548160036005811015156109d657fe5b0160005b5081905550600c600084815260200190815260200160002060040154816004600581101515610a0557fe5b0160005b508190555080600580602002604051908101604052809291908260058015610a46576020028201915b815481526020019060010190808311610a32575b505050505091505b50919050565b60018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610aea5780601f10610abf57610100808354040283529160200191610aea565b820191906000526020600020905b815481529060010190602001808311610acd57829003601f168201915b505050505081565b60006000600880549050905080831015610b2c57600883815481101515610b1557fe5b906000526020600020900160005b50549150610b36565b60009150610b36565b5b50919050565b6000600780549050821015610b8e57600782815481101515610b5a57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050610b98565b60009050610b98565b5b919050565b610ba56110f3565b610bad61111b565b610bb56110f3565b610bbd61111b565b600c6000868152602001908152602001600020600501600380602002604051908101604052809291908260038015610c0a576020028201915b815481526020019060010190808311610bf6575b50505050509150600c6000868152602001908152602001600020600801600380602002604051908101604052809291908260038015610c62576020028201915b81546000191681526020019060010190808311610c4a575b505050505090508181935093505b5050915091565b6000600060096000856000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610d045760088054806001018281610ce79190611147565b916000526020600020900160005b85909190915090600019169055505b8160096000856000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600090505b92915050565b610d71611173565b610d79611173565b600060006001600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16828054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e375780601f10610e0c57610100808354040283529160200191610e37565b820191906000526020600020905b815481529060010190602001808311610e1a57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610ed35780601f10610ea857610100808354040283529160200191610ed3565b820191906000526020600020905b815481529060010190602001808311610eb657829003601f168201915b505050505091509250925092505b909192565b600060096000836000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b919050565b600060056000836000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b919050565b80600b60008460001916600019168152602001908152602001600020819055505b5050565b60078054806001018281610fab9190611187565b916000526020600020900160005b83909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b50565b600060088054905090505b90565b600060e06040519081016040528089815260200188815260200187815260200186815260200185815260200184815260200183815250600c60008a8152602001908152602001600020600082015181600001556020820151816001015560408201518160020155606082015181600301556080820151816004015560a08201518160050190600361109f9291906111b3565b5060c0820151816008019060036110b79291906111f3565b50905050600090505b979650505050505050565b60a0604051908101604052806005905b60008152602001906001900390816110db5790505090565b6060604051908101604052806003905b60008152602001906001900390816111035790505090565b6060604051908101604052806003905b60006000191681526020019060019003908161112b5790505090565b81548183558181151161116e5781836000526020600020918201910161116d9190611239565b5b505050565b602060405190810160405280600081525090565b8154818355818115116111ae578183600052602060002091820191016111ad919061125e565b5b505050565b82600381019282156111e2579160200282015b828111156111e15782518255916020019190600101906111c6565b5b5090506111ef919061125e565b5090565b8260038101928215611228579160200282015b82811115611227578251829060001916905591602001919060010190611206565b5b5090506112359190611239565b5090565b61125b91905b8082111561125757600081600090555060010161123f565b5090565b90565b61128091905b8082111561127c576000816000905550600101611264565b5090565b905600a165627a7a723058209a6629f471419eba70261e2ec1fea594cbdcecd894902a2e2dc1b3f7ed71e4220029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_GETTRANSLISTSIZE = "getTransListSize";

    public static final String FUNC_SETMETAADDRESS = "setMetaAddress";

    public static final String FUNC_GETCHECKTAGREQ = "getCheckTagReq";

    public static final String FUNC_GETEXCHANGEBOCHKRATEPART1 = "getExchangeBochkRatePart1";

    public static final String FUNC_ABI = "abi";

    public static final String FUNC_GETORGBYINDEX = "getOrgByIndex";

    public static final String FUNC_GETTRANSBYINDEX = "getTransByIndex";

    public static final String FUNC_GETEXCHANGEBOCHKRATEPART2 = "getExchangeBochkRatePart2";

    public static final String FUNC_REGISTERORG = "registerOrg";

    public static final String FUNC_GETMETA = "getMeta";

    public static final String FUNC_GETORGACTION = "getOrgAction";

    public static final String FUNC_GETORDERADDRESS = "getOrderAddress";

    public static final String FUNC_SETCHECKTAGREQ = "setCheckTagReq";

    public static final String FUNC_ADDTRANS = "addTrans";

    public static final String FUNC_GETORGLISTSIZE = "getOrgListSize";

    public static final String FUNC_SETEXCHANGERATE = "setExchangeRate";

    @Deprecated
    protected ClearCenterData(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ClearCenterData(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ClearCenterData(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ClearCenterData(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getTransListSize() {
        final Function function = new Function(FUNC_GETTRANSLISTSIZE, 
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

    public RemoteCall<BigInteger> getCheckTagReq(byte[] reqId) {
        final Function function = new Function(FUNC_GETCHECKTAGREQ, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(reqId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> getExchangeBochkRatePart1(BigInteger currency) {
        final Function function = new Function(FUNC_GETEXCHANGEBOCHKRATEPART1, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currency)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray5<Int256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<String> abi() {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> getOrgByIndex(BigInteger index) {
        final Function function = new Function(FUNC_GETORGBYINDEX, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> getTransByIndex(BigInteger offset) {
        final Function function = new Function(FUNC_GETTRANSBYINDEX, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(offset)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<List<BigInteger>, List<byte[]>>> getExchangeBochkRatePart2(BigInteger currency) {
        final Function function = new Function(FUNC_GETEXCHANGEBOCHKRATEPART2, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currency)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Uint256>>() {}, new TypeReference<StaticArray3<Bytes32>>() {}));
        return new RemoteCall<Tuple2<List<BigInteger>, List<byte[]>>>(
                new Callable<Tuple2<List<BigInteger>, List<byte[]>>>() {
                    @Override
                    public Tuple2<List<BigInteger>, List<byte[]>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<BigInteger>, List<byte[]>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> registerOrg(byte[] orgId, String orgAddr) {
        final Function function = new Function(
                FUNC_REGISTERORG, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(orgId), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(orgAddr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<String> getOrgAction(byte[] orgId) {
        final Function function = new Function(FUNC_GETORGACTION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(orgId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getOrderAddress(byte[] abBizNo) {
        final Function function = new Function(FUNC_GETORDERADDRESS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(abBizNo)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setCheckTagReq(byte[] reqId, BigInteger newCheckCode) {
        final Function function = new Function(
                FUNC_SETCHECKTAGREQ, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(reqId), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(newCheckCode)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addTrans(String orderAddr) {
        final Function function = new Function(
                FUNC_ADDTRANS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(orderAddr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getOrgListSize() {
        final Function function = new Function(FUNC_GETORGLISTSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setExchangeRate(BigInteger currency, BigInteger fxRate, BigInteger refundFxRate, BigInteger exTimeStamp, BigInteger currencyUnit, List<BigInteger> exDataArr, List<byte[]> exDataStr) {
        final Function function = new Function(
                FUNC_SETEXCHANGERATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currency), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(fxRate), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(refundFxRate), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(exTimeStamp), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(currencyUnit), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        Utils.typeMap(exDataArr, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class)),
                new org.fisco.bcos.web3j.abi.datatypes.generated.StaticArray3<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32>(
                        Utils.typeMap(exDataStr, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ClearCenterData load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClearCenterData(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ClearCenterData load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClearCenterData(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ClearCenterData load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ClearCenterData(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ClearCenterData load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ClearCenterData(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ClearCenterData> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(ClearCenterData.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ClearCenterData> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(ClearCenterData.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ClearCenterData> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(ClearCenterData.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ClearCenterData> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String abi) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(abi)));
        return deployRemoteCall(ClearCenterData.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
