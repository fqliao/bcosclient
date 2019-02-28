package org.bcosliteclient;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.bcosliteclient.StudentScore.InsertResultEventResponse;
import org.bcosliteclient.StudentScore.RemoveResultEventResponse;
import org.bcosliteclient.StudentScore.UpdateResultEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BcosClient {
	static Logger logger = LoggerFactory.getLogger(BcosClient.class);
	public static Web3j web3j;
	// 初始化交易参数
	public static java.math.BigInteger gasPrice = new BigInteger("30000000");
	public static java.math.BigInteger gasLimit = new BigInteger("30000000");
	public static ECKeyPair keyPair;
	public static Credentials credentials;
	public static String contractAddress = "";

	/* deploy the contract,get address from blockchain */
	@SuppressWarnings("deprecation")
	public static void deployStudentScore() {

		RemoteCall<StudentScore> deploy = StudentScore.deploy(web3j, credentials, gasPrice, gasLimit);
		StudentScore studentScore;
		try {
			studentScore = deploy.send();
			contractAddress = studentScore.getContractAddress();
			System.out.println("deploy contract address: " + contractAddress);
			logger.info("deploy contract address: " + contractAddress);

	        Properties prop = new Properties();
	        prop.setProperty("address", contractAddress);
	        final Resource contractResource = new ClassPathResource("contract.properties");
	        FileOutputStream fos = new FileOutputStream(contractResource.getFile());
	        prop.store(fos, "contract address");
	        
	        System.out.println("deploy contract successful!");

		} 
		catch (Exception e) {
			System.out.println("deploy failed! " + e.getMessage());
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void testStudentScore(String[] args) throws Exception {

		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		InputStream fis = contractResource.getInputStream();
        prop.load(fis);
        String contractAddress = prop.getProperty("address");
        
		ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);;
		StudentScore studentScore = StudentScore.load(contractAddress, web3j, credentials, contractGasProvider);
		// 增
		if ("insert".equals(args[0])) {
			if (args.length == 4) {
				String name = args[1];
				String subject = args[2];
				BigInteger score = new BigInteger(args[3]);
				
				RemoteCall<TransactionReceipt> insert = studentScore.insert(name, subject, score);
				TransactionReceipt txReceipt = insert.send();
				List<InsertResultEventResponse> insertResultEvents = studentScore.getInsertResultEvents(txReceipt);
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
					Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>> lists = studentScore.select(keyName).send();
					List<byte[]> value1 = lists.getValue1();
					List<byte[]> value2 = lists.getValue2();
					List<BigInteger> value3 = lists.getValue3();
					logger.info("record numbers = " + value1.size());
					System.out.println("record numbers = " + value1.size());
					for (int i = 0; i < value1.size(); i++) {
						String name = new String(value1.get(i));
						logger.info("name = " + name);
						System.out.println("name = " + name);
						String subject = new String(value2.get(i));
						logger.info("subject = " + subject);
						System.out.println("subject = " + subject);
						BigInteger score = value3.get(i);
						logger.info("score = " + score);
						System.out.println("score = " + score);
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
				String subject = args[2];
				BigInteger score = new BigInteger(args[3]);
				RemoteCall<TransactionReceipt> update = studentScore.update(name, subject, score);
				TransactionReceipt transactionReceipt = update.send();
				List<UpdateResultEventResponse> updateResultEvents = studentScore.getUpdateResultEvents(transactionReceipt);

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
				RemoteCall<TransactionReceipt> remove = studentScore.remove(name);
				TransactionReceipt transactionReceipt = remove.send();
				List<RemoveResultEventResponse> removeResultEvents = studentScore.getRemoveResultEvents(transactionReceipt);

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
		credentials = Credentials.create(keyPair);
//		String address = Numeric.prependHexPrefix(Keys.getAddress(keyPair));
		System.out.println();

		logger.info("-----> start!");
		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		web3j = Web3j.build(channelEthereumService, 1);

		if (args.length > 0) {
			if ("deploy".equals(args[0])) {
				deployStudentScore();
			} else {
				testStudentScore(args);
			}
		} else {
			System.out.println("\nPlease choose follow commands:\n deploy, insert, select, update or remove");
		}
		System.exit(0);

	}
}
