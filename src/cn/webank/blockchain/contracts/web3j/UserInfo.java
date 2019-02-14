package cn.webank.blockchain.contracts.web3j;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
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
public class UserInfo extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b610c0c8061001c6000396000f30060606040523615610173576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806303f365b8146101755780630bbb3fb71461019b57806334ca585e146101bf57806337463899146101f55780633d1c40aa146102195780634589fe0c146102635780634a7c01ec146102915780634fc68c75146102e357806356c4e05a146103035780635854b4c5146103295780635dab24201461034d5780637607f98d1461037b57806376f75e7f146103db5780637fbd56181461043b57806386b588e514610469578063935f5b331461048d57806397c2730a146104b3578063a2423ab1146104e0578063b7efb86c14610506578063c046371114610534578063c3262dfd1461055a578063c719063c1461057e578063c728f8bb146105a2578063d7edcd9b146105d0578063d83b066e146105fe578063d9f035aa1461061e578063e302418a14610661578063e5a6b10f1461068f578063ea8d2720146106b5575bfe5b341561017d57fe5b6101856106d5565b6040518082815260200191505060405180910390f35b34156101a357fe5b6101bd6004808035600019169060200190919050506106db565b005b34156101c757fe5b6101f3600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506106ea565b005b34156101fd57fe5b61021760048080356000191690602001909190505061072f565b005b341561022157fe5b61024d600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061073e565b6040518082815260200191505060405180910390f35b341561026b57fe5b610273610938565b60405180826000191660001916815260200191505060405180910390f35b341561029957fe5b6102a161093e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156102eb57fe5b6103016004808035906020019091905050610964565b005b341561030b57fe5b61031361096f565b6040518082815260200191505060405180910390f35b341561033157fe5b61034b600480803560001916906020019091905050610975565b005b341561035557fe5b61035d610984565b60405180826000191660001916815260200191505060405180910390f35b341561038357fe5b610399600480803590602001909190505061098a565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156103e357fe5b6103f960048080359060200190919050506109ca565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561044357fe5b61044b610a0a565b60405180826000191660001916815260200191505060405180910390f35b341561047157fe5b61048b600480803560001916906020019091905050610a10565b005b341561049557fe5b61049d610a1f565b6040518082815260200191505060405180910390f35b34156104bb57fe5b6104de600480803560001916906020019091908035906020019091905050610a2d565b005b34156104e857fe5b6104f0610a93565b6040518082815260200191505060405180910390f35b341561050e57fe5b610516610aa1565b60405180826000191660001916815260200191505060405180910390f35b341561053c57fe5b610544610aa7565b6040518082815260200191505060405180910390f35b341561056257fe5b61057c600480803560001916906020019091905050610aad565b005b341561058657fe5b6105a0600480803560001916906020019091905050610abc565b005b34156105aa57fe5b6105b2610acb565b60405180826000191660001916815260200191505060405180910390f35b34156105d857fe5b6105e0610ad1565b60405180826000191660001916815260200191505060405180910390f35b341561060657fe5b61061c6004808035906020019091905050610ad7565b005b341561062657fe5b61063c6004808035906020019091905050610ae2565b6040518083600019166000191681526020018281526020019250505060405180910390f35b341561066957fe5b610671610b16565b60405180826000191660001916815260200191505060405180910390f35b341561069757fe5b61069f610b1c565b6040518082815260200191505060405180910390f35b34156106bd57fe5b6106d36004808035906020019091905050610b22565b005b600a5481565b80600081600019169055505b50565b80600e60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b50565b80600881600019169055505b50565b60007fb09b6d7f808ba533a7829e0b216f80fb3204b2d95a672b1cc5ef640b1818a3c28273ffffffffffffffffffffffffffffffffffffffff1663779cd0836000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156107cd57fe5b6102c65a03f115156107db57fe5b5050506040518051905060006040518080602001848152602001838152602001828103825260088152602001807f696e207569203738000000000000000000000000000000000000000000000000815250602001935050505060405180910390a16107d19050610933565b6102c65a03f1151561085457fe5b5050506040518051905014156108cd57600680548060010182816108789190610b2d565b916000526020600020900160005b84909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610932565b600780548060010182816108e19190610b2d565b916000526020600020900160005b84909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b5b919050565b60085481565b600e60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b80600d819055505b50565b60045481565b80600c81600019169055505b50565b60005481565b60078181548110151561099957fe5b906000526020600020900160005b915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6006818154811015156109d957fe5b906000526020600020900160005b915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60095481565b80600981600019169055505b50565b600060058054905090505b90565b60058054806001018281610a419190610b59565b916000526020600020906002020160005b6040604051908101604052808660001916815260200185815250909190915060008201518160000190600019169055602082015181600101555050505b5050565b600060068054905090505b90565b600b5481565b600d5481565b80600181600019169055505b50565b80600281600019169055505b50565b60025481565b600c5481565b806004819055505b50565b600581815481101515610af157fe5b906000526020600020906002020160005b915090508060000154908060010154905082565b60015481565b60035481565b806003819055505b50565b815481835581811511610b5457818360005260206000209182019101610b539190610b8b565b5b505050565b815481835581811511610b8657600202816002028360005260206000209182019101610b859190610bb0565b5b505050565b610bad91905b80821115610ba9576000816000905550600101610b91565b5090565b90565b610bdd91905b80821115610bd95760006000820160009055600182016000905550600201610bb6565b5090565b905600a165627a7a72305820e0294e3c58ace56cd364a1422ca0191f2a6b1fa0e00cad051a26f49b496195c80029";

    public static final String FUNC_CARDTYPE = "cardType";

    public static final String FUNC_SETACCOUNT = "setAccount";

    public static final String FUNC_SETUSERLIMIT = "setUserLimit";

    public static final String FUNC_SETFIRSTNAME = "setFirstName";

    public static final String FUNC_ADDORDER = "addOrder";

    public static final String FUNC_FIRSTNAME = "firstName";

    public static final String FUNC_USERLIMIT = "userLimit";

    public static final String FUNC_SETLASTUPDATE = "setLastUpdate";

    public static final String FUNC__BALANCE = "_balance";

    public static final String FUNC_SETIDTYPE = "setIDType";

    public static final String FUNC_ACCOUNT = "account";

    public static final String FUNC_ROLLBACKORDERLIST = "rollbackOrderList";

    public static final String FUNC_ORDERLIST = "orderList";

    public static final String FUNC_SECONDNAME = "secondName";

    public static final String FUNC_SETSECONDNAME = "setSecondName";

    public static final String FUNC_GETCARDLISTLENGTH = "getCardListLength";

    public static final String FUNC_ADDUSERCARD = "addUserCard";

    public static final String FUNC_GETORDERLISTLENGTH = "getOrderListLength";

    public static final String FUNC_CARDID = "cardID";

    public static final String FUNC_LASTUPDATE = "lastUpdate";

    public static final String FUNC_SETUSERID = "setUserID";

    public static final String FUNC_SETUSERPASSWORD = "setUserPassword";

    public static final String FUNC_USERPASSWORD = "userPassword";

    public static final String FUNC_IDTYPE = "IDType";

    public static final String FUNC_SETBALANCE = "setBalance";

    public static final String FUNC_CARDLIST = "cardList";

    public static final String FUNC_USERID = "userID";

    public static final String FUNC_CURRENCY = "currency";

    public static final String FUNC_SETCURRENCY = "setCurrency";

    public static final Event TRANSRETLOG_EVENT = new Event("transRetLog", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected UserInfo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserInfo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UserInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UserInfo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> cardType() {
        final Function function = new Function(FUNC_CARDTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setAccount(byte[] _account) {
        final Function function = new Function(
                FUNC_SETACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(_account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setUserLimit(String addr) {
        final Function function = new Function(
                FUNC_SETUSERLIMIT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setFirstName(byte[] first_name) {
        final Function function = new Function(
                FUNC_SETFIRSTNAME, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(first_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> addOrder(String order) {
        final Function function = new Function(FUNC_ADDORDER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(order)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> firstName() {
        final Function function = new Function(FUNC_FIRSTNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> userLimit() {
        final Function function = new Function(FUNC_USERLIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setLastUpdate(BigInteger last_update) {
        final Function function = new Function(
                FUNC_SETLASTUPDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(last_update)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> _balance() {
        final Function function = new Function(FUNC__BALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setIDType(byte[] id_type) {
        final Function function = new Function(
                FUNC_SETIDTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(id_type)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> account() {
        final Function function = new Function(FUNC_ACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> rollbackOrderList(BigInteger param0) {
        final Function function = new Function(FUNC_ROLLBACKORDERLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> orderList(BigInteger param0) {
        final Function function = new Function(FUNC_ORDERLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> secondName() {
        final Function function = new Function(FUNC_SECONDNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setSecondName(byte[] second_name) {
        final Function function = new Function(
                FUNC_SETSECONDNAME, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(second_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getCardListLength() {
        final Function function = new Function(FUNC_GETCARDLISTLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addUserCard(byte[] cardID, BigInteger tag) {
        final Function function = new Function(
                FUNC_ADDUSERCARD, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(cardID), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(tag)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getOrderListLength() {
        final Function function = new Function(FUNC_GETORDERLISTLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> cardID() {
        final Function function = new Function(FUNC_CARDID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> lastUpdate() {
        final Function function = new Function(FUNC_LASTUPDATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setUserID(byte[] user_id) {
        final Function function = new Function(
                FUNC_SETUSERID, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(user_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setUserPassword(byte[] user_password) {
        final Function function = new Function(
                FUNC_SETUSERPASSWORD, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32(user_password)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> userPassword() {
        final Function function = new Function(FUNC_USERPASSWORD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<byte[]> IDType() {
        final Function function = new Function(FUNC_IDTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> setBalance(BigInteger balance) {
        final Function function = new Function(
                FUNC_SETBALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(balance)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<byte[], BigInteger>> cardList(BigInteger param0) {
        final Function function = new Function(FUNC_CARDLIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Int256>() {}));
        return new RemoteCall<Tuple2<byte[], BigInteger>>(
                new Callable<Tuple2<byte[], BigInteger>>() {
                    @Override
                    public Tuple2<byte[], BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<byte[], BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<byte[]> userID() {
        final Function function = new Function(FUNC_USERID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> currency() {
        final Function function = new Function(FUNC_CURRENCY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCurrency(BigInteger _currency) {
        final Function function = new Function(
                FUNC_SETCURRENCY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(_currency)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<TransRetLogEventResponse> getTransRetLogEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSRETLOG_EVENT, transactionReceipt);
        ArrayList<TransRetLogEventResponse> responses = new ArrayList<TransRetLogEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransRetLogEventResponse typedResponse = new TransRetLogEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oper = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.check_code = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransRetLogEventResponse> transRetLogEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, TransRetLogEventResponse>() {
            @Override
            public TransRetLogEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSRETLOG_EVENT, log);
                TransRetLogEventResponse typedResponse = new TransRetLogEventResponse();
                typedResponse.log = log;
                typedResponse.oper = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.check_code = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransRetLogEventResponse> transRetLogEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSRETLOG_EVENT));
        return transRetLogEventFlowable(filter);
    }

    @Deprecated
    public static UserInfo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserInfo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UserInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserInfo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UserInfo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserInfo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserInfo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UserInfo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UserInfo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserInfo.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserInfo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserInfo.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<UserInfo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserInfo.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserInfo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserInfo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class TransRetLogEventResponse {
        public Log log;

        public String oper;

        public BigInteger status;

        public BigInteger check_code;
    }
}
