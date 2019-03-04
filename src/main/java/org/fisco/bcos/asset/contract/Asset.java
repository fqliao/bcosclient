package org.fisco.bcos.asset.contract;

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
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
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
public class Asset extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b506200002b62000031640100000000026401000000009004565b6200018c565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845260128152602001807f745f61737365745f6d616e6167656d656e7400000000000000000000000000008152506020018481038352600d8152602001807f61737365745f6163636f756e74000000000000000000000000000000000000008152506020018481038252600c8152602001807f61737365745f616d6f756e7400000000000000000000000000000000000000008152506020019350505050602060405180830381600087803b1580156200014b57600080fd5b505af115801562000160573d6000803e3d6000fd5b505050506040513d60208110156200017757600080fd5b81019080805190602001909291905050505050565b612221806200019c6000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680639b80b0501461005c578063ea87152b14610129578063fcd7e3c1146101b0575b600080fd5b34801561006857600080fd5b50610113600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610234565b6040518082815260200191505060405180910390f35b34801561013557600080fd5b5061019a600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291905050506115bf565b6040518082815260200191505060405180910390f35b3480156101bc57600080fd5b50610217600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611ad3565b604051808381526020018281526020019250505060405180910390f35b600080600080600080600080600080600080995060009850600097506000965061025d8e611ad3565b809950819a5050506000891415156103b3577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff99507f8f6b9fa4d4bf04c7c1c3242d4a5c59ba22525b6761cf89e44becb27c606154bd8a8f8f8f604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b838110156103075780820151818401526020810190506102ec565b50505050905090810190601f1680156103345780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b8381101561036d578082015181840152602081019050610352565b50505050905090810190601f16801561039a5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1899a506115ae565b6103bc8d611ad3565b809850819a505050600089141515610512577ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe99507f8f6b9fa4d4bf04c7c1c3242d4a5c59ba22525b6761cf89e44becb27c606154bd8a8f8f8f604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b8381101561046657808201518184015260208101905061044b565b50505050905090810190601f1680156104935780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156104cc5780820151818401526020810190506104b1565b50505050905090810190601f1680156104f95780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1899a506115ae565b8b88101561065e577ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffd99507f8f6b9fa4d4bf04c7c1c3242d4a5c59ba22525b6761cf89e44becb27c606154bd8a8f8f8f604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b838110156105b2578082015181840152602081019050610597565b50505050905090810190601f1680156105df5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156106185780820151818401526020810190506105fd565b50505050905090810190601f1680156106455780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1899a506115ae565b868c880110156107ac577ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffc99507f8f6b9fa4d4bf04c7c1c3242d4a5c59ba22525b6761cf89e44becb27c606154bd8a8f8f8f604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b838110156107005780820151818401526020810190506106e5565b50505050905090810190601f16801561072d5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b8381101561076657808201518184015260208101905061074b565b50505050905090810190601f1680156107935780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1899a506115ae565b6107b4612106565b95508573ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561081a57600080fd5b505af115801561082e573d6000803e3d6000fd5b505050506040513d602081101561084457600080fd5b810190808051906020019092919050505094508473ffffffffffffffffffffffffffffffffffffffff1663cd30a1d18f6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600d8152602001807f61737365745f6163636f756e7400000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156109175780820151818401526020810190506108fc565b50505050905090810190601f1680156109445780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561096457600080fd5b505af1158015610978573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156109e057600080fd5b505af11580156109f4573d6000803e3d6000fd5b505050506040513d6020811015610a0a57600080fd5b810190808051906020019092919050505093508373ffffffffffffffffffffffffffffffffffffffff1663e942b5168f6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600d8152602001807f61737365745f6163636f756e7400000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610add578082015181840152602081019050610ac2565b50505050905090810190601f168015610b0a5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610b2a57600080fd5b505af1158015610b3e573d6000803e3d6000fd5b505050508373ffffffffffffffffffffffffffffffffffffffff16632ef8ba748d8a036040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600c8152602001807f61737365745f616d6f756e74000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015610bec57600080fd5b505af1158015610c00573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18f86886040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b83811015610cf2578082015181840152602081019050610cd7565b50505050905090810190601f168015610d1f5780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b158015610d4057600080fd5b505af1158015610d54573d6000803e3d6000fd5b505050506040513d6020811015610d6a57600080fd5b81019080805190602001909291905050509250600183141515610ecb577ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffb99507f8f6b9fa4d4bf04c7c1c3242d4a5c59ba22525b6761cf89e44becb27c606154bd8a8f8f8f604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b83811015610e1f578082015181840152602081019050610e04565b50505050905090810190601f168015610e4c5780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b83811015610e85578082015181840152602081019050610e6a565b50505050905090810190601f168015610eb25780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1899a506115ae565b8573ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610f2f57600080fd5b505af1158015610f43573d6000803e3d6000fd5b505050506040513d6020811015610f5957600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663cd30a1d18e6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600d8152602001807f61737365745f6163636f756e7400000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561102c578082015181840152602081019050611011565b50505050905090810190601f1680156110595780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561107957600080fd5b505af115801561108d573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156110f557600080fd5b505af1158015611109573d6000803e3d6000fd5b505050506040513d602081101561111f57600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff1663e942b5168e6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600d8152602001807f61737365745f6163636f756e7400000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b838110156111f25780820151818401526020810190506111d7565b50505050905090810190601f16801561121f5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561123f57600080fd5b505af1158015611253573d6000803e3d6000fd5b505050508073ffffffffffffffffffffffffffffffffffffffff16632ef8ba748d89016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600c8152602001807f61737365745f616d6f756e74000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561130157600080fd5b505af1158015611315573d6000803e3d6000fd5b505050508573ffffffffffffffffffffffffffffffffffffffff1663bf2b70a18e83856040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b838110156114075780820151818401526020810190506113ec565b50505050905090810190601f1680156114345780820380516001836020036101000a031916815260200191505b50945050505050602060405180830381600087803b15801561145557600080fd5b505af1158015611469573d6000803e3d6000fd5b505050506040513d602081101561147f57600080fd5b8101908080519060200190929190505050507f8f6b9fa4d4bf04c7c1c3242d4a5c59ba22525b6761cf89e44becb27c606154bd8a8f8f8f604051808581526020018060200180602001848152602001838103835286818151815260200191508051906020019080838360005b838110156115065780820151818401526020810190506114eb565b50505050905090810190601f1680156115335780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b8381101561156c578082015181840152602081019050611551565b50505050905090810190601f1680156115995780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a1899a505b505050505050505050509392505050565b60008060008060008060008095506115d689611ad3565b945094506000851415156119f4576115ec612106565b92508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561165257600080fd5b505af1158015611666573d6000803e3d6000fd5b505050506040513d602081101561167c57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168a6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600d8152602001807f61737365745f6163636f756e7400000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b8381101561174f578082015181840152602081019050611734565b50505050905090810190601f16801561177c5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561179c57600080fd5b505af11580156117b0573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74896040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018381526020018281038252600c8152602001807f61737365745f616d6f756e74000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561185c57600080fd5b505af1158015611870573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac368a846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b8381101561192f578082015181840152602081019050611914565b50505050905090810190601f16801561195c5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561197c57600080fd5b505af1158015611990573d6000803e3d6000fd5b505050506040513d60208110156119a657600080fd5b8101908080519060200190929190505050905060018114156119cb57600095506119ef565b7ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe95505b611a18565b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff95505b7f91c95f04198617c60eaf2180fbca88fc192db379657df0e412a9f7dd4ebbe95d868a8a6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b83811015611a88578082015181840152602081019050611a6d565b50505050905090810190601f168015611ab55780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a185965050505050505092915050565b6000806000806000806000611ae6612106565b94508473ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611b4c57600080fd5b505af1158015611b60573d6000803e3d6000fd5b505050506040513d6020811015611b7657600080fd5b810190808051906020019092919050505093508373ffffffffffffffffffffffffffffffffffffffff1663cd30a1d1896040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018381038352600d8152602001807f61737365745f6163636f756e7400000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015611c49578082015181840152602081019050611c2e565b50505050905090810190601f168015611c765780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015611c9657600080fd5b505af1158015611caa573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663e8434e39898773ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611d2f57600080fd5b505af1158015611d43573d6000803e3d6000fd5b505050506040513d6020811015611d5957600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015611e07578082015181840152602081019050611dec565b50505050905090810190601f168015611e345780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015611e5457600080fd5b505af1158015611e68573d6000803e3d6000fd5b505050506040513d6020811015611e7e57600080fd5b81019080805190602001909291905050509250600091508273ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611ef957600080fd5b505af1158015611f0d573d6000803e3d6000fd5b505050506040513d6020811015611f2357600080fd5b810190808051906020019092919050505060001415611f6a577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82819150965096506120fc565b8273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015611fda57600080fd5b505af1158015611fee573d6000803e3d6000fd5b505050506040513d602081101561200457600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252600c8152602001807f61737365745f616d6f756e740000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1580156120b957600080fd5b505af11580156120cd573d6000803e3d6000fd5b505050506040513d60208110156120e357600080fd5b8101908080519060200190929190505050819150965096505b5050505050915091565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260128152602001807f745f61737365745f6d616e6167656d656e740000000000000000000000000000815250602001915050602060405180830381600087803b1580156121b057600080fd5b505af11580156121c4573d6000803e3d6000fd5b505050506040513d60208110156121da57600080fd5b810190808051906020019092919050505090508092505050905600a165627a7a7230582042f609694ed259999a8c7ca8189c6bfe0dadffb4dd122d728040c6f4eb78fff00029";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_SELECT = "select";

    public static final Event REGISTEREVENT_EVENT = new Event("RegisterEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFEREVENT_EVENT = new Event("TransferEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> transfer(String from_asset_account, String to_asset_account, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_asset_account), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_asset_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transfer(String from_asset_account, String to_asset_account, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_asset_account), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_asset_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> register(String asset_account, BigInteger amount) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(asset_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void register(String asset_account, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(asset_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> select(String asset_account) {
        final Function function = new Function(FUNC_SELECT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(asset_account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Uint256>() {}));
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

    public List<RegisterEventEventResponse> getRegisterEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTEREVENT_EVENT, transactionReceipt);
        ArrayList<RegisterEventEventResponse> responses = new ArrayList<RegisterEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.asset_account = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RegisterEventEventResponse> registerEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, RegisterEventEventResponse>() {
            @Override
            public RegisterEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REGISTEREVENT_EVENT, log);
                RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
                typedResponse.log = log;
                typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.asset_account = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RegisterEventEventResponse> registerEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REGISTEREVENT_EVENT));
        return registerEventEventFlowable(filter);
    }

    public List<TransferEventEventResponse> getTransferEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEREVENT_EVENT, transactionReceipt);
        ArrayList<TransferEventEventResponse> responses = new ArrayList<TransferEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventEventResponse typedResponse = new TransferEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.from_asset_account = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.to_asset_account = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventEventResponse> transferEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventEventResponse>() {
            @Override
            public TransferEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFEREVENT_EVENT, log);
                TransferEventEventResponse typedResponse = new TransferEventEventResponse();
                typedResponse.log = log;
                typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.from_asset_account = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.to_asset_account = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventEventResponse> transferEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFEREVENT_EVENT));
        return transferEventEventFlowable(filter);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RegisterEventEventResponse {
        public Log log;

        public BigInteger ret;

        public String asset_account;

        public BigInteger amount;
    }

    public static class TransferEventEventResponse {
        public Log log;

        public BigInteger ret;

        public String from_asset_account;

        public String to_asset_account;

        public BigInteger amount;
    }
}
