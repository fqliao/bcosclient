package cn.webank.blockchain.contracts.web3j;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.Utils;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class StringTool extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b6107b58061001c6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063045ccf7b146100515780633a96fdd71461011c57806346bdca9a146101cd575bfe5b341561005957fe5b6100936004808061080001906040806020026040519081016040528092919082604060200280828437820191505050505091905050610282565b60405180806020018281038252838181518152602001915080519060200190808383600083146100e2575b8051825260208311156100e2576020820191506020810190506020830392506100be565b505050905090810190601f16801561010e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561012457fe5b6101b7600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190505061047c565b6040518082815260200191505060405180910390f35b34156101d557fe5b610268600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610749565b604051808215151515815260200191505060405180910390f35b61028a610761565b610292610775565b6000600061029e610775565b60406040518059106102ad5750595b908082528060200260200182016040525b50935060009250600091505b604082101561039b5760007f010000000000000000000000000000000000000000000000000000000000000002868360408110151561030557fe5b60200201517effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614151561038d57858260408110151561034157fe5b6020020151848481518110151561035457fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a90535082806001019350505b5b81806001019250506102ca565b826040518059106103a95750595b908082528060200260200182016040525b509050600091505b8282101561046f5783828151811015156103d857fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f010000000000000000000000000000000000000000000000000000000000000002818381518110151561043157fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053505b81806001019250506103c2565b8094505b50505050919050565b6000610486610775565b61048e610775565b600060008693508592508351915081835110156104aa57825191505b600090505b818110156106ef5782818151811015156104c557fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916848281518110151561054057fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191610156105db577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff945061073f565b82818151811015156105e957fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916848281518110151561066457fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191611156106e0576001945061073f565b5b5b80806001019150506104af565b825184511015610721577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff945061073f565b825184511115610734576001945061073f565b6000945061073f565b5b5b5050505092915050565b60006000610757848461047c565b1490505b92915050565b602060405190810160405280600081525090565b6020604051908101604052806000815250905600a165627a7a7230582017be090e7b1fa12a9e655d87d8e5f143c13e4ff607a7f1764c9fe6cbac7c63e90029";

    public static final String FUNC_BYTE64TOSTRING = "byte64ToString";

    public static final String FUNC_COMPARE = "compare";

    public static final String FUNC_EQUAL = "equal";

    @Deprecated
    protected StringTool(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StringTool(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StringTool(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StringTool(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> byte64ToString(List<byte[]> x) {
        final Function function = new Function(FUNC_BYTE64TOSTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.StaticArray<org.fisco.bcos.web3j.abi.datatypes.generated.Bytes1>(
                        Utils.typeMap(x, org.fisco.bcos.web3j.abi.datatypes.generated.Bytes1.class))),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> compare(String _a, String _b) {
        final Function function = new Function(
                FUNC_COMPARE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_a), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> equal(String _a, String _b) {
        final Function function = new Function(
                FUNC_EQUAL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_a), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static StringTool load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StringTool(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StringTool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StringTool(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StringTool load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new StringTool(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StringTool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StringTool(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<StringTool> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StringTool.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StringTool> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StringTool.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<StringTool> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StringTool.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StringTool> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StringTool.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
