package org.bcosliteclient;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.EthFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
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
public class DBTest extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b611ef98061001c6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063487a5a1014610067578063616ffe8314610121578063c4f41ab31461028a578063ebf3b24f14610301578063efc81a8c146103bb575bfe5b341561006f57fe5b61010b600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506103cd565b6040518082815260200191505060405180910390f35b341561012957fe5b610179600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610a5e565b604051808060200180602001806020018481038452878181518152602001915080519060200190602002808383600083146101d3575b8051825260208311156101d3576020820191506020810190506020830392506101af565b505050905001848103835286818151815260200191508051906020019060200280838360008314610223575b805182526020831115610223576020820191506020810190506020830392506101ff565b505050905001848103825285818151815260200191508051906020019060200280838360008314610273575b8051825260208311156102735760208201915060208101905060208303925061024f565b505050905001965050505050505060405180910390f35b341561029257fe5b6102eb600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919080359060200190919050506112eb565b6040518082815260200191505060405180910390f35b341561030957fe5b6103a5600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050611796565b6040518082815260200191505060405180910390f35b34156103c357fe5b6103cb611d6a565b005b60006000600060006000600061100194508473ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260068152602001807f745f746573740000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b151561048357fe5b6102c65a03f1151561049157fe5b5050506040518051905093508373ffffffffffffffffffffffffffffffffffffffff166313db93466000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561050957fe5b6102c65a03f1151561051757fe5b5050506040518051905092508273ffffffffffffffffffffffffffffffffffffffff1663e942b516886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260098152602001807f6974656d5f6e616d6500000000000000000000000000000000000000000000008152506020018381038252848181518152602001915080519060200190808383600083146105f2575b8051825260208311156105f2576020820191506020810190506020830392506105ce565b505050905090810190601f16801561061e5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b151561063a57fe5b6102c65a03f1151561064857fe5b5050508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156106b757fe5b6102c65a03f115156106c557fe5b5050506040518051905091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d18a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d65000000000000000000000000000000000000000000000000000000008152506020018381038252848181518152602001915080519060200190808383600083146107a0575b8051825260208311156107a05760208201915060208101905060208303925061077c565b505050905090810190601f1680156107cc5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15156107e857fe5b6102c65a03f115156107f657fe5b5050508173ffffffffffffffffffffffffffffffffffffffff1663e44594b9896040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260078152602001807f6974656d5f69640000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b151561089d57fe5b6102c65a03f115156108ab57fe5b5050508373ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18a85856000604051602001526040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281038252858181518152602001915080519060200190808383600083146109b4575b8051825260208311156109b457602082019150602081019050602083039250610990565b505050905090810190601f1680156109e05780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b15156109fd57fe5b6102c65a03f11515610a0b57fe5b5050506040518051905090507f0bdcb3b747cf033ae78b4b6e1576d2725709d03f68ad3d641b12cb72de614354816040518082815260200191505060405180910390a18095505b50505050509392505050565b610a66611ea5565b610a6e611eb9565b610a76611ea5565b6000600060006000610a86611ea5565b610a8e611eb9565b610a96611ea5565b6000600061100198508873ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260068152602001807f745f746573740000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1515610b4457fe5b6102c65a03f11515610b5257fe5b5050506040518051905097508773ffffffffffffffffffffffffffffffffffffffff16637857d7c96000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610bca57fe5b6102c65a03f11515610bd857fe5b5050506040518051905096508773ffffffffffffffffffffffffffffffffffffffff1663e8434e398e896000604051602001526040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360008314610cb7575b805182526020831115610cb757602082019150602081019050602083039250610c93565b505050905090810190601f168015610ce35780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1515610cff57fe5b6102c65a03f11515610d0d57fe5b5050506040518051905095508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610d8557fe5b6102c65a03f11515610d9357fe5b50505060405180519050604051805910610daa5750595b908082528060200260200182016040525b5094508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610e2a57fe5b6102c65a03f11515610e3857fe5b50505060405180519050604051805910610e4f5750595b908082528060200260200182016040525b5093508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610ecf57fe5b6102c65a03f11515610edd57fe5b50505060405180519050604051805910610ef45750595b908082528060200260200182016040525b509250600091505b8573ffffffffffffffffffffffffffffffffffffffff1663949d225d6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610f7957fe5b6102c65a03f11515610f8757fe5b505050604051805190508212156112d1578573ffffffffffffffffffffffffffffffffffffffff1663846719e0836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561100c57fe5b6102c65a03f1151561101a57fe5b5050506040518051905090508073ffffffffffffffffffffffffffffffffffffffff166327314f796000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15156110cb57fe5b6102c65a03f115156110d957fe5b5050506040518051905085838151811015156110f157fe5b9060200190602002019060001916908160001916815250508073ffffffffffffffffffffffffffffffffffffffff1663fda69fae6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260078152602001807f6974656d5f696400000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15156111ae57fe5b6102c65a03f115156111bc57fe5b5050506040518051905084838151811015156111d457fe5b90602001906020020181815250508073ffffffffffffffffffffffffffffffffffffffff166327314f796000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260098152602001807f6974656d5f6e616d650000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b151561128757fe5b6102c65a03f1151561129557fe5b5050506040518051905083838151811015156112ad57fe5b9060200190602002019060001916908160001916815250505b816001019150610f0d565b8484849b509b509b505b5050505050505050509193909250565b6000600060006000600061100193508373ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260068152602001807f745f746573740000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b151561139f57fe5b6102c65a03f115156113ad57fe5b5050506040518051905092508273ffffffffffffffffffffffffffffffffffffffff16637857d7c96000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561142557fe5b6102c65a03f1151561143357fe5b5050506040518051905091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d650000000000000000000000000000000000000000000000000000000081525060200183810382528481815181526020019150805190602001908083836000831461150e575b80518252602083111561150e576020820191506020810190506020830392506114ea565b505050905090810190601f16801561153a5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b151561155657fe5b6102c65a03f1151561156457fe5b5050508173ffffffffffffffffffffffffffffffffffffffff1663e44594b9876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260078152602001807f6974656d5f69640000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b151561160b57fe5b6102c65a03f1151561161957fe5b5050508273ffffffffffffffffffffffffffffffffffffffff166328bb211788846000604051602001526040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281038252848181518152602001915080519060200190808383600083146116ef575b8051825260208311156116ef576020820191506020810190506020830392506116cb565b505050905090810190601f16801561171b5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b151561173757fe5b6102c65a03f1151561174557fe5b5050506040518051905090507f896358cb98e9e8e891ae04efd1bc177efbe5cffd7eca2e784b16ed7468553e08816040518082815260200191505060405180910390a18094505b5050505092915050565b6000600060006000600061100193508373ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260068152602001807f745f746573740000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b151561184a57fe5b6102c65a03f1151561185857fe5b5050506040518051905092508273ffffffffffffffffffffffffffffffffffffffff166313db93466000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156118d057fe5b6102c65a03f115156118de57fe5b5050506040518051905091508173ffffffffffffffffffffffffffffffffffffffff1663e942b516896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d65000000000000000000000000000000000000000000000000000000008152506020018381038252848181518152602001915080519060200190808383600083146119b9575b8051825260208311156119b957602082019150602081019050602083039250611995565b505050905090810190601f1680156119e55780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1515611a0157fe5b6102c65a03f11515611a0f57fe5b5050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260078152602001807f6974656d5f69640000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b1515611ab657fe5b6102c65a03f11515611ac457fe5b5050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260098152602001807f6974656d5f6e616d650000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360008314611b96575b805182526020831115611b9657602082019150602081019050602083039250611b72565b505050905090810190601f168015611bc25780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1515611bde57fe5b6102c65a03f11515611bec57fe5b5050508273ffffffffffffffffffffffffffffffffffffffff166331afac3689846000604051602001526040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360008314611cc2575b805182526020831115611cc257602082019150602081019050602083039250611c9e565b505050905090810190601f168015611cee5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1515611d0a57fe5b6102c65a03f11515611d1857fe5b5050506040518051905090507f66f7705280112a4d1145399e0414adc43a2d6974b487710f417edcf7d4a39d71816040518082815260200191505060405180910390a18094505b505050509392505050565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a6000604051602001526040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845260068152602001807f745f746573740000000000000000000000000000000000000000000000000000815250602001848103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001848103825260118152602001807f6974656d5f69642c6974656d5f6e616d650000000000000000000000000000008152506020019350505050602060405180830381600087803b1515611e8857fe5b6102c65a03f11515611e9657fe5b50505060405180519050505b50565b602060405190810160405280600081525090565b6020604051908101604052806000815250905600a165627a7a7230582052d25257067385ddbefac6e5ee25e891e7bd5f140fe0d4b57207fa882e8c4fad0029";

    public static final String FUNC_UPDATE = "update";

    public static final String FUNC_READ = "read";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_INSERT = "insert";

    public static final String FUNC_CREATE = "create";

    public static final Event READRESULT_EVENT = new Event("readResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Int256>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event INSERTRESULT_EVENT = new Event("insertResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event UPDATERESULT_EVENT = new Event("updateResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    public static final Event REMOVERESULT_EVENT = new Event("removeResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected DBTest(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DBTest(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DBTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DBTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> update(String name, BigInteger item_id, String item_name) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(item_id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(item_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<List<byte[]>, List<BigInteger>, List<byte[]>>> read(String name) {
        final Function function = new Function(FUNC_READ, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Int256>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        return new RemoteCall<Tuple3<List<byte[]>, List<BigInteger>, List<byte[]>>>(
                new Callable<Tuple3<List<byte[]>, List<BigInteger>, List<byte[]>>>() {
                    @Override
                    public Tuple3<List<byte[]>, List<BigInteger>, List<byte[]>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<List<byte[]>, List<BigInteger>, List<byte[]>>(
                                convertToNative((List<Bytes32>) results.get(0).getValue()), 
                                convertToNative((List<Int256>) results.get(1).getValue()), 
                                convertToNative((List<Bytes32>) results.get(2).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> remove(String name, BigInteger item_id) {
        final Function function = new Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(item_id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> insert(String name, BigInteger item_id, String item_name) {
        final Function function = new Function(
                FUNC_INSERT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(item_id), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(item_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> create() {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<ReadResultEventResponse> getReadResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(READRESULT_EVENT, transactionReceipt);
        ArrayList<ReadResultEventResponse> responses = new ArrayList<ReadResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReadResultEventResponse typedResponse = new ReadResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.item_id = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.item_name = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ReadResultEventResponse> readResultEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ReadResultEventResponse>() {
            @Override
            public ReadResultEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(READRESULT_EVENT, log);
                ReadResultEventResponse typedResponse = new ReadResultEventResponse();
                typedResponse.log = log;
                typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.item_id = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.item_name = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ReadResultEventResponse> readResultEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(READRESULT_EVENT));
        return readResultEventFlowable(filter);
    }

    public List<InsertResultEventResponse> getInsertResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INSERTRESULT_EVENT, transactionReceipt);
        ArrayList<InsertResultEventResponse> responses = new ArrayList<InsertResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InsertResultEventResponse typedResponse = new InsertResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<InsertResultEventResponse> insertResultEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, InsertResultEventResponse>() {
            @Override
            public InsertResultEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(INSERTRESULT_EVENT, log);
                InsertResultEventResponse typedResponse = new InsertResultEventResponse();
                typedResponse.log = log;
                typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<InsertResultEventResponse> insertResultEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INSERTRESULT_EVENT));
        return insertResultEventFlowable(filter);
    }

    public List<UpdateResultEventResponse> getUpdateResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UPDATERESULT_EVENT, transactionReceipt);
        ArrayList<UpdateResultEventResponse> responses = new ArrayList<UpdateResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UpdateResultEventResponse typedResponse = new UpdateResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UpdateResultEventResponse> updateResultEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, UpdateResultEventResponse>() {
            @Override
            public UpdateResultEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UPDATERESULT_EVENT, log);
                UpdateResultEventResponse typedResponse = new UpdateResultEventResponse();
                typedResponse.log = log;
                typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UpdateResultEventResponse> updateResultEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPDATERESULT_EVENT));
        return updateResultEventFlowable(filter);
    }

    public List<RemoveResultEventResponse> getRemoveResultEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REMOVERESULT_EVENT, transactionReceipt);
        ArrayList<RemoveResultEventResponse> responses = new ArrayList<RemoveResultEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RemoveResultEventResponse typedResponse = new RemoveResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RemoveResultEventResponse> removeResultEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, RemoveResultEventResponse>() {
            @Override
            public RemoveResultEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REMOVERESULT_EVENT, log);
                RemoveResultEventResponse typedResponse = new RemoveResultEventResponse();
                typedResponse.log = log;
                typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RemoveResultEventResponse> removeResultEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REMOVERESULT_EVENT));
        return removeResultEventFlowable(filter);
    }

    @Deprecated
    public static DBTest load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DBTest(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DBTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DBTest(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DBTest load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DBTest(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DBTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DBTest(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DBTest> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DBTest.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DBTest> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DBTest.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DBTest> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DBTest.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DBTest> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DBTest.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ReadResultEventResponse {
        public Log log;

        public byte[] name;

        public BigInteger item_id;

        public byte[] item_name;
    }

    public static class InsertResultEventResponse {
        public Log log;

        public BigInteger count;
    }

    public static class UpdateResultEventResponse {
        public Log log;

        public BigInteger count;
    }

    public static class RemoveResultEventResponse {
        public Log log;

        public BigInteger count;
    }
}
