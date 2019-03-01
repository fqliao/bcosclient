package org.bcos.student.score.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
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
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
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
public class StudentScore extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061002861002d640100000000026401000000009004565b610185565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001806020018481038452600f8152602001807f745f73747564656e745f73636f72650000000000000000000000000000000000815250602001848103835260048152602001807f6e616d65000000000000000000000000000000000000000000000000000000008152506020018481038252600d8152602001807f7375626a6563742c73636f7265000000000000000000000000000000000000008152506020019350505050602060405180830381600087803b15801561014657600080fd5b505af115801561015a573d6000803e3d6000fd5b505050506040513d602081101561017057600080fd5b81019080805190602001909291905050505050565b611ed380620001956000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806333575c7f14610067578063343453611461013457806380599e4b14610201578063fcd7e3c11461027e575b600080fd5b34801561007357600080fd5b5061011e600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291905050506103cc565b6040518082815260200191505060405180910390f35b34801561014057600080fd5b506101eb600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610910565b6040518082815260200191505060405180910390f35b34801561020d57600080fd5b50610268600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611176565b6040518082815260200191505060405180910390f35b34801561028a57600080fd5b506102e5600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506114d3565b60405180806020018060200180602001848103845287818151815260200191508051906020019060200280838360005b83811015610330578082015181840152602081019050610315565b50505050905001848103835286818151815260200191508051906020019060200280838360005b83811015610372578082015181840152602081019050610357565b50505050905001848103825285818151815260200191508051906020019060200280838360005b838110156103b4578082015181840152602081019050610399565b50505050905001965050505050505060405180910390f35b6000806000806103da611db8565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561044057600080fd5b505af1158015610454573d6000803e3d6000fd5b505050506040513d602081101561046a57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663e942b516886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561053d578082015181840152602081019050610522565b50505050905090810190601f16801561056a5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561058a57600080fd5b505af115801561059e573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516876040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f7375626a65637400000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610662578082015181840152602081019050610647565b50505050905090810190601f16801561068f5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156106af57600080fd5b505af11580156106c3573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74866040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260058152602001807f73636f726500000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561076f57600080fd5b505af1158015610783573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac3688846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610842578082015181840152602081019050610827565b50505050905090810190601f16801561086f5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561088f57600080fd5b505af11580156108a3573d6000803e3d6000fd5b505050506040513d60208110156108b957600080fd5b810190808051906020019092919050505090507f66f7705280112a4d1145399e0414adc43a2d6974b487710f417edcf7d4a39d71816040518082815260200191505060405180910390a18093505050509392505050565b6000806000806000610920611db8565b93508373ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561098657600080fd5b505af115801561099a573d6000803e3d6000fd5b505050506040513d60208110156109b057600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff1663e942b516896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610a83578082015181840152602081019050610a68565b50505050905090810190601f168015610ab05780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610ad057600080fd5b505af1158015610ae4573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663e942b516886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f7375626a65637400000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610ba8578082015181840152602081019050610b8d565b50505050905090810190601f168015610bd55780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610bf557600080fd5b505af1158015610c09573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff16632ef8ba74876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260058152602001807f73636f726500000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015610cb557600080fd5b505af1158015610cc9573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d3157600080fd5b505af1158015610d45573d6000803e3d6000fd5b505050506040513d6020811015610d5b57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610e2e578082015181840152602081019050610e13565b50505050905090810190601f168015610e5b5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610e7b57600080fd5b505af1158015610e8f573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260078152602001807f7375626a65637400000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610f53578082015181840152602081019050610f38565b50505050905090810190601f168015610f805780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610fa057600080fd5b505af1158015610fb4573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18985856040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b838110156110a657808201518184015260208101905061108b565b50505050905090810190601f1680156110d35780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b1580156110f457600080fd5b505af1158015611108573d6000803e3d6000fd5b505050506040513d602081101561111e57600080fd5b810190808051906020019092919050505090507f0bdcb3b747cf033ae78b4b6e1576d2725709d03f68ad3d641b12cb72de614354816040518082815260200191505060405180910390a1809450505050509392505050565b600080600080611184611db8565b92508273ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156111ea57600080fd5b505af11580156111fe573d6000803e3d6000fd5b505050506040513d602081101561121457600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1866040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156112e75780820151818401526020810190506112cc565b50505050905090810190601f1680156113145780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561133457600080fd5b505af1158015611348573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166328bb211786846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156114075780820151818401526020810190506113ec565b50505050905090810190601f1680156114345780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561145457600080fd5b505af1158015611468573d6000803e3d6000fd5b505050506040513d602081101561147e57600080fd5b810190808051906020019092919050505090507f896358cb98e9e8e891ae04efd1bc177efbe5cffd7eca2e784b16ed7468553e08816040518082815260200191505060405180910390a1809350505050919050565b6060806060600080600060608060606000806114ed611db8565b97508773ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561155357600080fd5b505af1158015611567573d6000803e3d6000fd5b505050506040513d602081101561157d57600080fd5b810190808051906020019092919050505096508773ffffffffffffffffffffffffffffffffffffffff1663e8434e398d896040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b8381101561164b578082015181840152602081019050611630565b50505050905090810190601f1680156116785780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561169857600080fd5b505af11580156116ac573d6000803e3d6000fd5b505050506040513d60208110156116c257600080fd5b810190808051906020019092919050505095508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561173957600080fd5b505af115801561174d573d6000803e3d6000fd5b505050506040513d602081101561176357600080fd5b81019080805190602001909291905050506040519080825280602002602001820160405280156117a25781602001602082028038833980820191505090505b5094508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561180957600080fd5b505af115801561181d573d6000803e3d6000fd5b505050506040513d602081101561183357600080fd5b81019080805190602001909291905050506040519080825280602002602001820160405280156118725781602001602082028038833980820191505090505b5093508573ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156118d957600080fd5b505af11580156118ed573d6000803e3d6000fd5b505050506040513d602081101561190357600080fd5b81019080805190602001909291905050506040519080825280602002602001820160405280156119425781602001602082028038833980820191505090505b509250600091505b8573ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156119ae57600080fd5b505af11580156119c2573d6000803e3d6000fd5b505050506040513d60208110156119d857600080fd5b8101908080519060200190929190505050821215611da0578573ffffffffffffffffffffffffffffffffffffffff1663846719e0836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015611a5f57600080fd5b505af1158015611a73573d6000803e3d6000fd5b505050506040513d6020811015611a8957600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff166327314f796040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015611b3c57600080fd5b505af1158015611b50573d6000803e3d6000fd5b505050506040513d6020811015611b6657600080fd5b81019080805190602001909291905050508583815181101515611b8557fe5b9060200190602002019060001916908160001916815250508073ffffffffffffffffffffffffffffffffffffffff166327314f796040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260078152602001807f7375626a65637400000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015611c3d57600080fd5b505af1158015611c51573d6000803e3d6000fd5b505050506040513d6020811015611c6757600080fd5b81019080805190602001909291905050508483815181101515611c8657fe5b9060200190602002019060001916908160001916815250508073ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260058152602001807f73636f7265000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b158015611d3e57600080fd5b505af1158015611d52573d6000803e3d6000fd5b505050506040513d6020811015611d6857600080fd5b81019080805190602001909291905050508383815181101515611d8757fe5b906020019060200201818152505081600101915061194a565b8484849a509a509a5050505050505050509193909250565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600f8152602001807f745f73747564656e745f73636f72650000000000000000000000000000000000815250602001915050602060405180830381600087803b158015611e6257600080fd5b505af1158015611e76573d6000803e3d6000fd5b505050506040513d6020811015611e8c57600080fd5b810190808051906020019092919050505090508092505050905600a165627a7a723058206a6e0be99e68baf787efc4fa25c0f35eae26644c951082ce95f08bee5ca7a2ac0029";

    public static final String FUNC_INSERT = "insert";

    public static final String FUNC_UPDATE = "update";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_SELECT = "select";

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
    protected StudentScore(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StudentScore(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StudentScore(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StudentScore(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> insert(String name, String subject, BigInteger score) {
        final Function function = new Function(
                FUNC_INSERT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(subject), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(score)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void insert(String name, String subject, BigInteger score, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_INSERT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(subject), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(score)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> update(String name, String subject, BigInteger score) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(subject), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(score)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void update(String name, String subject, BigInteger score, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(subject), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Int256(score)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> remove(String name) {
        final Function function = new Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void remove(String name, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>>> select(String name) {
        final Function function = new Function(FUNC_SELECT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Int256>>() {}));
        return new RemoteCall<Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>>>(
                new Callable<Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>>>() {
                    @Override
                    public Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>>(
                                convertToNative((List<Bytes32>) results.get(0).getValue()), 
                                convertToNative((List<Bytes32>) results.get(1).getValue()), 
                                convertToNative((List<Int256>) results.get(2).getValue()));
                    }
                });
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

    public Flowable<InsertResultEventResponse> insertResultEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, InsertResultEventResponse>() {
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
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
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

    public Flowable<UpdateResultEventResponse> updateResultEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, UpdateResultEventResponse>() {
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
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
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

    public Flowable<RemoveResultEventResponse> removeResultEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, RemoveResultEventResponse>() {
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
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REMOVERESULT_EVENT));
        return removeResultEventFlowable(filter);
    }

    @Deprecated
    public static StudentScore load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StudentScore(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StudentScore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StudentScore(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StudentScore load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new StudentScore(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StudentScore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StudentScore(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<StudentScore> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StudentScore.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<StudentScore> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(StudentScore.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StudentScore> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StudentScore.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<StudentScore> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StudentScore.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
