package org.bcosliteclient;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.bcos.channel.client.TransactionSucCallback;
import org.bcos.web3j.abi.EventEncoder;
import org.bcos.web3j.abi.EventValues;
import org.bcos.web3j.abi.TypeReference;
import org.bcos.web3j.abi.datatypes.DynamicArray;
import org.bcos.web3j.abi.datatypes.Event;
import org.bcos.web3j.abi.datatypes.Function;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Int256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.bcos.web3j.protocol.core.methods.request.EthFilter;
import org.bcos.web3j.protocol.core.methods.response.Log;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.bcos.web3j.tx.Contract;
import org.bcos.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.bcos.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version none.
 */
public final class DBTest extends Contract {
    private static String BINARY = "6060604052341561000c57fe5b5b611ef98061001c6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063487a5a1014610067578063c4f41ab314610121578063ebf3b24f14610198578063efc81a8c14610252578063fcd7e3c114610264575bfe5b341561006f57fe5b61010b600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506103cd565b6040518082815260200191505060405180910390f35b341561012957fe5b610182600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091908035906020019091905050610a5e565b6040518082815260200191505060405180910390f35b34156101a057fe5b61023c600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050610f09565b6040518082815260200191505060405180910390f35b341561025a57fe5b6102626114dd565b005b341561026c57fe5b6102bc600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050611618565b60405180806020018060200180602001848103845287818151815260200191508051906020019060200280838360008314610316575b805182526020831115610316576020820191506020810190506020830392506102f2565b505050905001848103835286818151815260200191508051906020019060200280838360008314610366575b80518252602083111561036657602082019150602081019050602083039250610342565b5050509050018481038252858181518152602001915080519060200190602002808383600083146103b6575b8051825260208311156103b657602082019150602081019050602083039250610392565b505050905001965050505050505060405180910390f35b60006000600060006000600061100194508473ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260078152602001807f745f746573743100000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b151561048357fe5b6102c65a03f1151561049157fe5b5050506040518051905093508373ffffffffffffffffffffffffffffffffffffffff166313db93466000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561050957fe5b6102c65a03f1151561051757fe5b5050506040518051905092508273ffffffffffffffffffffffffffffffffffffffff1663e942b516886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f6974656d5f6e616d6531000000000000000000000000000000000000000000008152506020018381038252848181518152602001915080519060200190808383600083146105f2575b8051825260208311156105f2576020820191506020810190506020830392506105ce565b505050905090810190601f16801561061e5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b151561063a57fe5b6102c65a03f1151561064857fe5b5050508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156106b757fe5b6102c65a03f115156106c557fe5b5050506040518051905091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d18a6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d65000000000000000000000000000000000000000000000000000000008152506020018381038252848181518152602001915080519060200190808383600083146107a0575b8051825260208311156107a05760208201915060208101905060208303925061077c565b505050905090810190601f1680156107cc5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15156107e857fe5b6102c65a03f115156107f657fe5b5050508173ffffffffffffffffffffffffffffffffffffffff1663e44594b9896040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260078152602001807f6974656d5f69640000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b151561089d57fe5b6102c65a03f115156108ab57fe5b5050508373ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18a85856000604051602001526040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281038252858181518152602001915080519060200190808383600083146109b4575b8051825260208311156109b457602082019150602081019050602083039250610990565b505050905090810190601f1680156109e05780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b15156109fd57fe5b6102c65a03f11515610a0b57fe5b5050506040518051905090507f0bdcb3b747cf033ae78b4b6e1576d2725709d03f68ad3d641b12cb72de614354816040518082815260200191505060405180910390a18095505b50505050509392505050565b6000600060006000600061100193508373ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260078152602001807f745f746573743100000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1515610b1257fe5b6102c65a03f11515610b2057fe5b5050506040518051905092508273ffffffffffffffffffffffffffffffffffffffff16637857d7c96000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515610b9857fe5b6102c65a03f11515610ba657fe5b5050506040518051905091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360008314610c81575b805182526020831115610c8157602082019150602081019050602083039250610c5d565b505050905090810190601f168015610cad5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1515610cc957fe5b6102c65a03f11515610cd757fe5b5050508173ffffffffffffffffffffffffffffffffffffffff1663e44594b9876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260078152602001807f6974656d5f69640000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b1515610d7e57fe5b6102c65a03f11515610d8c57fe5b5050508273ffffffffffffffffffffffffffffffffffffffff166328bb211788846000604051602001526040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360008314610e62575b805182526020831115610e6257602082019150602081019050602083039250610e3e565b505050905090810190601f168015610e8e5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1515610eaa57fe5b6102c65a03f11515610eb857fe5b5050506040518051905090507f896358cb98e9e8e891ae04efd1bc177efbe5cffd7eca2e784b16ed7468553e08816040518082815260200191505060405180910390a18094505b5050505092915050565b6000600060006000600061100193508373ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260078152602001807f745f746573743100000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1515610fbd57fe5b6102c65a03f11515610fcb57fe5b5050506040518051905092508273ffffffffffffffffffffffffffffffffffffffff166313db93466000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561104357fe5b6102c65a03f1151561105157fe5b5050506040518051905091508173ffffffffffffffffffffffffffffffffffffffff1663e942b516896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260058152602001807f6e616d653100000000000000000000000000000000000000000000000000000081525060200183810382528481815181526020019150805190602001908083836000831461112c575b80518252602083111561112c57602082019150602081019050602083039250611108565b505050905090810190601f1680156111585780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b151561117457fe5b6102c65a03f1151561118257fe5b5050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74886040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260088152602001807f6974656d5f69643200000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b151561122957fe5b6102c65a03f1151561123757fe5b5050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600a8152602001807f6974656d5f6e616d653200000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360008314611309575b805182526020831115611309576020820191506020810190506020830392506112e5565b505050905090810190601f1680156113355780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b151561135157fe5b6102c65a03f1151561135f57fe5b5050508273ffffffffffffffffffffffffffffffffffffffff166331afac3689846000604051602001526040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360008314611435575b80518252602083111561143557602082019150602081019050602083039250611411565b505050905090810190601f1680156114615780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b151561147d57fe5b6102c65a03f1151561148b57fe5b5050506040518051905090507f66f7705280112a4d1145399e0414adc43a2d6974b487710f417edcf7d4a39d71816040518082815260200191505060405180910390a18094505b505050509392505050565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a6000604051602001526040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845260078152602001807f745f746573743100000000000000000000000000000000000000000000000000815250602001848103835260058152602001807f6e616d6531000000000000000000000000000000000000000000000000000000815250602001848103825260138152602001807f6974656d5f6964312c6974656d5f6e616d6531000000000000000000000000008152506020019350505050602060405180830381600087803b15156115fb57fe5b6102c65a03f1151561160957fe5b50505060405180519050505b50565b611620611ea5565b611628611eb9565b611630611ea5565b6000600060006000611640611ea5565b611648611eb9565b611650611ea5565b6000600061100198508873ffffffffffffffffffffffffffffffffffffffff1663c184e0ff6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260078152602001807f745f746573743100000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15156116fe57fe5b6102c65a03f1151561170c57fe5b5050506040518051905097508773ffffffffffffffffffffffffffffffffffffffff16637857d7c96000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561178457fe5b6102c65a03f1151561179257fe5b5050506040518051905096508773ffffffffffffffffffffffffffffffffffffffff1663e8434e398e896000604051602001526040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360008314611871575b8051825260208311156118715760208201915060208101905060208303925061184d565b505050905090810190601f16801561189d5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15156118b957fe5b6102c65a03f115156118c757fe5b5050506040518051905095508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561193f57fe5b6102c65a03f1151561194d57fe5b505050604051805190506040518059106119645750595b908082528060200260200182016040525b5094508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156119e457fe5b6102c65a03f115156119f257fe5b50505060405180519050604051805910611a095750595b908082528060200260200182016040525b5093508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515611a8957fe5b6102c65a03f11515611a9757fe5b50505060405180519050604051805910611aae5750595b908082528060200260200182016040525b509250600091505b8573ffffffffffffffffffffffffffffffffffffffff1663949d225d6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515611b3357fe5b6102c65a03f11515611b4157fe5b50505060405180519050821215611e8b578573ffffffffffffffffffffffffffffffffffffffff1663846719e0836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515611bc657fe5b6102c65a03f11515611bd457fe5b5050506040518051905090508073ffffffffffffffffffffffffffffffffffffffff166327314f796000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260058152602001807f6e616d6531000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1515611c8557fe5b6102c65a03f11515611c9357fe5b505050604051805190508583815181101515611cab57fe5b9060200190602002019060001916908160001916815250508073ffffffffffffffffffffffffffffffffffffffff1663fda69fae6000604051602001526040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260088152602001807f6974656d5f696431000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1515611d6857fe5b6102c65a03f11515611d7657fe5b505050604051805190508483815181101515611d8e57fe5b90602001906020020181815250508073ffffffffffffffffffffffffffffffffffffffff166327314f796000604051602001526040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600a8152602001807f6974656d5f6e616d653100000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1515611e4157fe5b6102c65a03f11515611e4f57fe5b505050604051805190508383815181101515611e6757fe5b9060200190602002019060001916908160001916815250505b816001019150611ac7565b8484849b509b509b505b5050505050505050509193909250565b602060405190810160405280600081525090565b6020604051908101604052806000815250905600a165627a7a72305820a1b8c8cfad4ace3baa24aad113f65434d343bd3edbd5988fc64f49b269a2809c0029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"item_id\",\"type\":\"int256\"},{\"name\":\"item_name\",\"type\":\"string\"}],\"name\":\"update\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"item_id\",\"type\":\"int256\"}],\"name\":\"remove\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"item_id\",\"type\":\"int256\"},{\"name\":\"item_name\",\"type\":\"string\"}],\"name\":\"insert\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"create\",\"outputs\":[],\"payable\":false,\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32[]\"},{\"name\":\"\",\"type\":\"int256[]\"},{\"name\":\"\",\"type\":\"bytes32[]\"}],\"payable\":false,\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"name\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"item_id\",\"type\":\"int256\"},{\"indexed\":false,\"name\":\"item_name\",\"type\":\"bytes32\"}],\"name\":\"readResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"insertResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"updateResult\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"removeResult\",\"type\":\"event\"}]";

    private DBTest(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, isInitByName);
    }

    private DBTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Boolean isInitByName) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, isInitByName);
    }

    private DBTest(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    private DBTest(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static List<ReadResultEventResponse> getReadResultEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("readResult", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Int256>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ReadResultEventResponse> responses = new ArrayList<ReadResultEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ReadResultEventResponse typedResponse = new ReadResultEventResponse();
            typedResponse.name = (Bytes32) eventValues.getNonIndexedValues().get(0);
            typedResponse.item_id = (Int256) eventValues.getNonIndexedValues().get(1);
            typedResponse.item_name = (Bytes32) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReadResultEventResponse> readResultEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("readResult", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Int256>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReadResultEventResponse>() {
            @Override
            public ReadResultEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ReadResultEventResponse typedResponse = new ReadResultEventResponse();
                typedResponse.name = (Bytes32) eventValues.getNonIndexedValues().get(0);
                typedResponse.item_id = (Int256) eventValues.getNonIndexedValues().get(1);
                typedResponse.item_name = (Bytes32) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public static List<InsertResultEventResponse> getInsertResultEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("insertResult", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<InsertResultEventResponse> responses = new ArrayList<InsertResultEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            InsertResultEventResponse typedResponse = new InsertResultEventResponse();
            typedResponse.count = (Int256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<InsertResultEventResponse> insertResultEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("insertResult", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, InsertResultEventResponse>() {
            @Override
            public InsertResultEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                InsertResultEventResponse typedResponse = new InsertResultEventResponse();
                typedResponse.count = (Int256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public static List<UpdateResultEventResponse> getUpdateResultEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("updateResult", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<UpdateResultEventResponse> responses = new ArrayList<UpdateResultEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            UpdateResultEventResponse typedResponse = new UpdateResultEventResponse();
            typedResponse.count = (Int256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UpdateResultEventResponse> updateResultEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("updateResult", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, UpdateResultEventResponse>() {
            @Override
            public UpdateResultEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                UpdateResultEventResponse typedResponse = new UpdateResultEventResponse();
                typedResponse.count = (Int256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public static List<RemoveResultEventResponse> getRemoveResultEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("removeResult", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<RemoveResultEventResponse> responses = new ArrayList<RemoveResultEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            RemoveResultEventResponse typedResponse = new RemoveResultEventResponse();
            typedResponse.count = (Int256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RemoveResultEventResponse> removeResultEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("removeResult", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, RemoveResultEventResponse>() {
            @Override
            public RemoveResultEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                RemoveResultEventResponse typedResponse = new RemoveResultEventResponse();
                typedResponse.count = (Int256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> update(Utf8String name, Int256 item_id, Utf8String item_name) {
        Function function = new Function("update", Arrays.<Type>asList(name, item_id, item_name), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void update(Utf8String name, Int256 item_id, Utf8String item_name, TransactionSucCallback callback) {
        Function function = new Function("update", Arrays.<Type>asList(name, item_id, item_name), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> remove(Utf8String name, Int256 item_id) {
        Function function = new Function("remove", Arrays.<Type>asList(name, item_id), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void remove(Utf8String name, Int256 item_id, TransactionSucCallback callback) {
        Function function = new Function("remove", Arrays.<Type>asList(name, item_id), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> insert(Utf8String name, Int256 item_id, Utf8String item_name) {
        Function function = new Function("insert", Arrays.<Type>asList(name, item_id, item_name), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void insert(Utf8String name, Int256 item_id, Utf8String item_name, TransactionSucCallback callback) {
        Function function = new Function("insert", Arrays.<Type>asList(name, item_id, item_name), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<TransactionReceipt> create() {
        Function function = new Function("create", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public void create(TransactionSucCallback callback) {
        Function function = new Function("create", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        executeTransactionAsync(function, callback);
    }

    public Future<List<Type>> select(Utf8String name) {
        Function function = new Function("select", 
                Arrays.<Type>asList(name), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Int256>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public static Future<DBTest> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(DBTest.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<DBTest> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(DBTest.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static DBTest load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DBTest(contractAddress, web3j, credentials, gasPrice, gasLimit, false);
    }

    public static DBTest load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DBTest(contractAddress, web3j, transactionManager, gasPrice, gasLimit, false);
    }

    public static DBTest loadByName(String contractName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DBTest(contractName, web3j, credentials, gasPrice, gasLimit, true);
    }

    public static DBTest loadByName(String contractName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DBTest(contractName, web3j, transactionManager, gasPrice, gasLimit, true);
    }

    public static class ReadResultEventResponse {
        public Bytes32 name;

        public Int256 item_id;

        public Bytes32 item_name;
    }

    public static class InsertResultEventResponse {
        public Int256 count;
    }

    public static class UpdateResultEventResponse {
        public Int256 count;
    }

    public static class RemoveResultEventResponse {
        public Int256 count;
    }
}
