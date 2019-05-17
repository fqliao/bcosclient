package org.bcosliteclient;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.bcosliteclient.TableTest.CreateResultEventResponse;
import org.bcosliteclient.TableTest.InsertResultEventResponse;
import org.bcosliteclient.TableTest.RemoveResultEventResponse;
import org.bcosliteclient.TableTest.UpdateResultEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.BlockNumber;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.protocol.exceptions.TransactionException;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TableTestClient {
	static Logger logger = LoggerFactory.getLogger(TableTestClient.class);
	public static Web3j web3j;
	// 初始化交易参数
	public static java.math.BigInteger gasPrice = new BigInteger("1");
	public static java.math.BigInteger gasLimit = new BigInteger("30000000");
	public static ECKeyPair keyPair;
	public static Credentials credentials;
	public static String contractAddress = "";

	/* deploy the contract,get address from blockchain */
	@SuppressWarnings("deprecation")
	public static void deployDBTest() {

		RemoteCall<TableTest> deploy = TableTest.deploy(web3j, credentials, gasPrice, gasLimit);
		TableTest tableTest;
		try {
			tableTest = deploy.send();
			contractAddress = tableTest.getContractAddress();
			System.out.println("deploy contract address: " + contractAddress);
			logger.info("deploy contract address: " + contractAddress);

			Properties prop = new Properties();
			prop.setProperty("address", contractAddress);
			final Resource contractResource = new ClassPathResource("contract.properties");
			FileOutputStream fos = new FileOutputStream(contractResource.getFile());
			prop.store(fos, "contract address");

			System.out.println("deploy contract successful!");

		} catch (TransactionException e) {
			if ("0x19".equals(e.getStatus())) {
				System.out.println("non-authorized to deploy contracts!");
			} else {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("deploy failed! " + e.getMessage());
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void testDBTest(String[] args) throws Exception {

		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		InputStream fis = contractResource.getInputStream();
		prop.load(fis);
		String contractAddress = prop.getProperty("address");

		ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
		;
		TableTest tableTest = TableTest.load(contractAddress, web3j, credentials, contractGasProvider);
		// 创建表
		if ("create".equals(args[0])) {
			TransactionReceipt receipt = tableTest.create().send();
			List<CreateResultEventResponse> createResultEvents = tableTest.getCreateResultEvents(receipt);
			if (createResultEvents.size() == 0) {
				System.out.println("create t_test table failed.");
				return;
			}
			CreateResultEventResponse createResultEventResponse = createResultEvents.get(0);
			int createCount = createResultEventResponse.count.intValue();
			switch (createCount) {
			case 80:
				System.out.println("non-authorized to create t_test table.");
				break;
			case 0:
				System.out.println("t_test table already exist.");
				break;
			case 1:
				System.out.println("create t_test table completed.");
				break;
			}

		}
		// 增
		else if ("insert".equals(args[0])) {
			if (args.length == 4) {
				String name = args[1];
				int item_id = Integer.parseInt(args[2]);
				String item_name = args[3];

				RemoteCall<TransactionReceipt> insert = tableTest.insert(name, BigInteger.valueOf(item_id), item_name);
				TransactionReceipt txReceipt = insert.send();
				List<InsertResultEventResponse> insertResultEvents = tableTest.getInsertResultEvents(txReceipt);
				if (insertResultEvents.size() > 0) {
					for (int i = 0; i < insertResultEvents.size(); i++) {
						InsertResultEventResponse insertResultEventResponse = insertResultEvents.get(i);
						logger.info("insertCount = " + insertResultEventResponse.count.intValue());
						System.out.println("insertCount = " + insertResultEventResponse.count.intValue());
					}
				} else {
					System.out.println("t_test table does not exist.");
				}
			} else {
				System.out.println("\nPlease enter as follow example:\n 1 1 insert fruit 1 apple");
			}
		}
		// 查
		else if ("select".equals(args[0])) {
			if (args.length == 2) {
				try {
					String keyName = args[1];
					Tuple3<List<byte[]>, List<BigInteger>, List<byte[]>> lists = tableTest.select(keyName).send();
					List<byte[]> value1 = lists.getValue1();
					List<BigInteger> value2 = lists.getValue2();
					List<byte[]> value3 = lists.getValue3();
					logger.info("record numbers = " + value1.size());
					System.out.println("record numbers = " + value1.size());
					for (int i = 0; i < value1.size(); i++) {
						String name = new String(value1.get(i));
						logger.info("name = " + name);
						System.out.println("name = " + name);
						int item_id = value2.get(i).intValue();
						logger.info("item_id = " + item_id);
						System.out.println("item_id = " + item_id);
						String item_name = new String(value3.get(i));
						logger.info("item_name = " + item_name);
						System.out.println("item_name = " + item_name);
					}
				} catch (Exception e) {
					logger.info("record numbers = 0");
					System.out.println("record numbers = 0");
				}
			} else {
				System.out.println("\nPlease enter as follow example:\n 1 1 select fruit");
			}
		}
		// 改
		else if ("update".equals(args[0])) {
			if (args.length == 4) {
				String name = args[1];
				int item_id = Integer.parseInt(args[2]);
				String item_name = args[3];
				RemoteCall<TransactionReceipt> update = tableTest.update(name, BigInteger.valueOf(item_id), item_name);
				TransactionReceipt transactionReceipt = update.send();
				List<UpdateResultEventResponse> updateResultEvents = tableTest.getUpdateResultEvents(transactionReceipt);

				if (updateResultEvents.size() > 0) {
					for (int i = 0; i < updateResultEvents.size(); i++) {
						UpdateResultEventResponse updateResultEventResponse = updateResultEvents.get(i);
						System.out.println("updateCount = " + updateResultEventResponse.count.intValue());
						logger.info("updateCount = " + updateResultEventResponse.count.intValue());
					}
				} else {
					System.out.println("t_test table does not exist.");
				}
			} else {
				System.out.println("\nPlease enter as follow example:\n 1 1 update fruit 1 orange");
			}
		}
		// 删
		else if ("remove".equals(args[0])) {
			if (args.length == 3) {
				String name = args[1];
				int item_id = Integer.parseInt(args[2]);
				RemoteCall<TransactionReceipt> remove = tableTest.remove(name, BigInteger.valueOf(item_id));
				TransactionReceipt transactionReceipt = remove.send();
				List<RemoveResultEventResponse> removeResultEvents = tableTest.getRemoveResultEvents(transactionReceipt);

				if (removeResultEvents.size() > 0) {
					RemoveResultEventResponse reomveResultEventResponse = removeResultEvents.get(0);
					logger.info("removeCount = " + reomveResultEventResponse.count.intValue());
					System.out.println("removeCount = " + reomveResultEventResponse.count.intValue());
				} else {
					System.out.println("t_test table does not exist.");
				}
			} else {
				System.out.println("\nPlease enter as follow example:\n 1 1 remove fruit 1");
			}
		} else {
			System.out.println("\nPlease choose follow commands:\n deploy, create, insert, select, update or remove");
		}

	}

	public static void main(String[] args) throws Exception {

		// init the Service
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run(); // run the daemon service
		// init the client keys
		keyPair = Keys.createEcKeyPair();
//    credentials = Credentials.create(keyPair);
//		String address = Numeric.prependHexPrefix(Keys.getAddress(keyPair));
		String priviteKey1 = "3bed914595c159cbce70ec5fb6aff3d6797e0c5ee5a7a9224a21cae8932d84a4";
		String origin1 = "0xf1585b8d0e08a0a00fff662e24d67ba95a438256";
		String priviteKey2 = "ab40568a2f77b4cb70706b4c6119916a143eb75c0d618e5f69909af1f9f9695e";
		String origin2 = "0xc0d0e6ccc0b44c12196266548bec4a3616160e7d";
		String priviteKey3 = "d0fee0a4e3c545a9394965042f8f891b6e5482c212a7428ec175d6aed121353a";
		String origin3 = "0x1600e34312edea101d8b41a3465f2e381b66baed";
		System.out.println();
		try {
			String priviteKey = priviteKey1;
			String origin = origin1;
			switch (args[0]) {
			case "1":
				priviteKey = priviteKey1;
				origin = origin1;
				break;
			case "2":
				priviteKey = priviteKey2;
				origin = origin2;
				break;
			case "3":
				priviteKey = priviteKey3;
				origin = origin3;
				break;
			default:
				System.out.println("Please provide 1 or 2 or 3 for specifying priviteKey.");
				System.exit(0);
				;
			}
			credentials = Credentials.create(priviteKey);
			System.out.println("tx.origin = " + origin);
			System.out.println();
		} catch (Exception e) {
			System.out.println("\nPlease provide priviteKeyNumber in the first paramters");
			System.exit(0);
		}

		logger.info("-----> start test !");
		logger.info("init AOMP ChannelEthereumService");
		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		try {
			web3j = Web3j.build(channelEthereumService, Integer.parseInt(args[1]));
		} catch (Exception e) {
			System.out.println("\nPlease provide groupID in the second paramters");
			System.exit(0);
		}

		// test get blocknumber,just optional steps

		BlockNumber ethBlockNumber = web3j.getBlockNumber().sendAsync().get();
		int startBlockNumber = ethBlockNumber.getBlockNumber().intValue();
		logger.info("-->Got ethBlockNumber:{}", startBlockNumber);

		if (args.length > 2) {
			if ("deploy".equals(args[2])) {
				deployDBTest();
			} else {
				String[] params = new String[args.length - 2];
				for (int i = 0; i < params.length; i++)
					params[i] = args[i + 2];
				testDBTest(params);
			}
		} else {
			System.out.println("\nPlease choose follow commands:\n deploy, create, insert, select, update or remove");
		}
		/* print block number after some transactions */
		ethBlockNumber = web3j.getBlockNumber().sendAsync().get();
		int finishBlockNumber = ethBlockNumber.getBlockNumber().intValue();
		logger.info("<--start blockNumber = " + startBlockNumber + ",finish blocknmber=" + finishBlockNumber);
		System.exit(0);

	}
}
