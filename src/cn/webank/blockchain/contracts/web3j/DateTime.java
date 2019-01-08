package cn.webank.blockchain.contracts.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint16;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
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
public class DateTime extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b610cfa8061001c6000396000f300606060405236156100ce576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633e239e1a146100d05780634ac1ad781461010a57806362ba96871461014457806365c72840146101ac5780637f791833146101e65780638aa001fc146102425780638c8d98a01461027c5780639054bdec146102cc57806392d6631314610340578063a324ad241461037c578063a6f0e577146103b6578063b1999937146103f2578063b238ad0e14610426578063fa93f88314610470575bfe5b34156100d857fe5b6100ee60048080359060200190919050506104aa565b604051808260ff1660ff16815260200191505060405180910390f35b341561011257fe5b61012860048080359060200190919050506104d9565b604051808260ff1660ff16815260200191505060405180910390f35b341561014c57fe5b610196600480803561ffff1690602001909190803560ff1690602001909190803560ff1690602001909190803560ff1690602001909190803560ff16906020019091905050610501565b6040518082815260200191505060405180910390f35b34156101b457fe5b6101ca600480803590602001909190505061051e565b604051808260ff1660ff16815260200191505060405180910390f35b34156101ee57fe5b61022c600480803561ffff1690602001909190803560ff1690602001909190803560ff1690602001909190803560ff16906020019091905050610535565b6040518082815260200191505060405180910390f35b341561024a57fe5b6102606004808035906020019091905050610552565b604051808260ff1660ff16815260200191505060405180910390f35b341561028457fe5b6102b6600480803561ffff1690602001909190803560ff1690602001909190803560ff16906020019091905050610569565b6040518082815260200191505060405180910390f35b34156102d457fe5b61032a600480803561ffff1690602001909190803560ff1690602001909190803560ff1690602001909190803560ff1690602001909190803560ff1690602001909190803560ff16906020019091905050610586565b6040518082815260200191505060405180910390f35b341561034857fe5b61035e6004808035906020019091905050610831565b604051808261ffff1661ffff16815260200191505060405180910390f35b341561038457fe5b61039a60048080359060200190919050506108df565b604051808260ff1660ff16815260200191505060405180910390f35b34156103be57fe5b6103d8600480803561ffff169060200190919050506108f6565b604051808215151515815260200191505060405180910390f35b34156103fa57fe5b6104106004808035906020019091905050610978565b6040518082815260200191505060405180910390f35b341561042e57fe5b610454600480803560ff1690602001909190803561ffff169060200190919050506109b2565b604051808260ff1660ff16815260200191505060405180910390f35b341561047857fe5b61048e6004808035906020019091905050610a85565b604051808260ff1660ff16815260200191505060405180910390f35b60006018603c603c848115156104bc57fe5b048115156104c657fe5b048115156104d057fe5b0690505b919050565b60006007600462015180848115156104ed57fe5b04018115156104f857fe5b0690505b919050565b600061051286868686866000610586565b90505b95945050505050565b600061052982610aa8565b6040015190505b919050565b60006105478585858560006000610586565b90505b949350505050565b6000603c8281151561056057fe5b0690505b919050565b600061057c848484600060006000610586565b90505b9392505050565b60006000610592610c4e565b6107b291505b8861ffff168261ffff1610156105dc576105b1826108f6565b156105c4576301e28500830192506105ce565b6301e13380830192505b5b8180600101925050610598565b601f816000600c811015156105ed57fe5b602002019060ff16908160ff1681525050610607896108f6565b1561063357601d816001600c8110151561061d57fe5b602002019060ff16908160ff1681525050610656565b601c816001600c8110151561064457fe5b602002019060ff16908160ff16815250505b601f816002600c8110151561066757fe5b602002019060ff16908160ff1681525050601e816003600c8110151561068957fe5b602002019060ff16908160ff1681525050601f816004600c811015156106ab57fe5b602002019060ff16908160ff1681525050601e816005600c811015156106cd57fe5b602002019060ff16908160ff1681525050601f816006600c811015156106ef57fe5b602002019060ff16908160ff1681525050601f816007600c8110151561071157fe5b602002019060ff16908160ff1681525050601e816008600c8110151561073357fe5b602002019060ff16908160ff1681525050601f816009600c8110151561075557fe5b602002019060ff16908160ff1681525050601e81600a600c8110151561077757fe5b602002019060ff16908160ff1681525050601f81600b600c8110151561079957fe5b602002019060ff16908160ff1681525050600191505b8760ff168261ffff1610156107f257806001830361ffff16600c811015156107d357fe5b602002015160ff166201518002830192505b81806001019250506107af565b6001870360ff166201518002830192508560ff16610e1002830192508460ff16603c02830192508360ff16830192508292505b50509695505050505050565b6000600060006000600092506301e133808581151561084c57fe5b046107b261ffff160191506108666107b261ffff16610978565b6108738361ffff16610978565b039050806301e285000283019250806107b2830361ffff16036301e1338002830192505b848311156108d3576108ab600183036108f6565b156108be576301e28500830392506108c8565b6301e13380830392505b600182039150610897565b8193505b505050919050565b60006108ea82610aa8565b6020015190505b919050565b6000600060048361ffff1681151561090a57fe5b0661ffff1614151561091f5760009050610973565b600060648361ffff1681151561093157fe5b0661ffff161415156109465760019050610973565b60006101908361ffff1681151561095957fe5b0661ffff1614151561096e5760009050610973565b600190505b919050565b60006001820391506101908281151561098d57fe5b0460648381151561099a57fe5b046004848115156109a757fe5b04030190505b919050565b600060018360ff1614806109c9575060038360ff16145b806109d7575060058360ff16145b806109e5575060078360ff16145b806109f3575060088360ff16145b80610a015750600a8360ff16145b80610a0f5750600c8360ff16145b15610a1d57601f9050610a7f565b60048360ff161480610a32575060068360ff16145b80610a40575060098360ff16145b80610a4e5750600b8360ff16145b15610a5c57601e9050610a7f565b610a65826108f6565b15610a7357601d9050610a7f565b601c9050610a7f565b5b5b5b92915050565b6000603c603c83811515610a9557fe5b04811515610a9f57fe5b0690505b919050565b610ab0610c7a565b600060006000600060009350610ac586610831565b856000019061ffff16908161ffff1681525050610ae76107b261ffff16610978565b610af8866000015161ffff16610978565b039250826301e285000284019350826107b286600001510361ffff16036301e133800284019350600191505b600c8260ff16111515610b7d57610b3f8286600001516109b2565b60ff1662015180029050858482011115610b6a5781856020019060ff16908160ff1681525050610b7d565b80840193505b8180600101925050610b24565b600191505b610b94856020015186600001516109b2565b60ff168260ff16111515610bdc57858462015180011115610bc65781856040019060ff16908160ff1681525050610bdc565b62015180840193505b8180600101925050610b82565b610be5866104aa565b856060019060ff16908160ff1681525050610bff86610a85565b856080019060ff16908160ff1681525050610c1986610552565b8560a0019060ff16908160ff1681525050610c33866104d9565b8560c0019060ff16908160ff16815250505b50505050919050565b61018060405190810160405280600c905b600060ff16815260200190600190039081610c5f5790505090565b60e060405190810160405280600061ffff168152602001600060ff168152602001600060ff168152602001600060ff168152602001600060ff168152602001600060ff168152602001600060ff16815250905600a165627a7a7230582053013302cad09e326f6cbfa8d25ca9e017fc3aaa243fac814734ed8794982e540029";

    public static final String FUNC_GETHOUR = "getHour";

    public static final String FUNC_GETWEEKDAY = "getWeekday";

    public static final String FUNC_TOTIMESTAMP = "toTimestamp";

    public static final String FUNC_GETDAY = "getDay";

    public static final String FUNC_GETSECOND = "getSecond";

    public static final String FUNC_GETYEAR = "getYear";

    public static final String FUNC_GETMONTH = "getMonth";

    public static final String FUNC_ISLEAPYEAR = "isLeapYear";

    public static final String FUNC_LEAPYEARSBEFORE = "leapYearsBefore";

    public static final String FUNC_GETDAYSINMONTH = "getDaysInMonth";

    public static final String FUNC_GETMINUTE = "getMinute";

    @Deprecated
    protected DateTime(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DateTime(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DateTime(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DateTime(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getHour(BigInteger timestamp) {
        final Function function = new Function(FUNC_GETHOUR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getWeekday(BigInteger timestamp) {
        final Function function = new Function(FUNC_GETWEEKDAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> toTimestamp(BigInteger year, BigInteger month, BigInteger day, BigInteger hour, BigInteger minute) {
        final Function function = new Function(FUNC_TOTIMESTAMP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint16(year), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(month), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(day), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(hour), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(minute)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getDay(BigInteger timestamp) {
        final Function function = new Function(FUNC_GETDAY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> toTimestamp(BigInteger year, BigInteger month, BigInteger day, BigInteger hour) {
        final Function function = new Function(FUNC_TOTIMESTAMP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint16(year), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(month), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(day), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(hour)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getSecond(BigInteger timestamp) {
        final Function function = new Function(FUNC_GETSECOND, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> toTimestamp(BigInteger year, BigInteger month, BigInteger day) {
        final Function function = new Function(FUNC_TOTIMESTAMP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint16(year), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(month), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(day)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> toTimestamp(BigInteger year, BigInteger month, BigInteger day, BigInteger hour, BigInteger minute, BigInteger second) {
        final Function function = new Function(FUNC_TOTIMESTAMP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint16(year), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(month), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(day), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(hour), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(minute), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(second)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getYear(BigInteger timestamp) {
        final Function function = new Function(FUNC_GETYEAR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMonth(BigInteger timestamp) {
        final Function function = new Function(FUNC_GETMONTH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> isLeapYear(BigInteger year) {
        final Function function = new Function(FUNC_ISLEAPYEAR, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint16(year)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<BigInteger> leapYearsBefore(BigInteger year) {
        final Function function = new Function(FUNC_LEAPYEARSBEFORE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(year)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getDaysInMonth(BigInteger month, BigInteger year) {
        final Function function = new Function(FUNC_GETDAYSINMONTH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(month), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint16(year)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMinute(BigInteger timestamp) {
        final Function function = new Function(FUNC_GETMINUTE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DateTime load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DateTime(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DateTime load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DateTime(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DateTime load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DateTime(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DateTime load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DateTime(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DateTime> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DateTime.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DateTime> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DateTime.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DateTime> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DateTime.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DateTime> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DateTime.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
