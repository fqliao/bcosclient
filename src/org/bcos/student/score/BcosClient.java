package org.bcos.student.score;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bcos.student.score.StudentScore.InsertResultEventResponse;
import org.bcos.student.score.StudentScore.RemoveResultEventResponse;
import org.bcos.student.score.StudentScore.UpdateResultEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BcosClient {
	static Logger logger = LoggerFactory.getLogger(BcosClient.class);

	public static java.math.BigInteger gasPrice = new BigInteger("30000000");
	public static java.math.BigInteger gasLimit = new BigInteger("30000000");

	public Web3j web3j;
	public Credentials credentials;

	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void initialize() throws Exception {

		// init the Service
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);

		Credentials credentials = Credentials.create(Keys.createEcKeyPair());

		setWeb3j(web3j);
		setCredentials(credentials);
	}

	public void deployStudentScoreAndRecordAddr() {
		try {
			initialize();
			StudentScore studentScore = StudentScore.deploy(web3j, credentials, gasPrice, gasLimit).send();
			System.out.println(
					" deploy StudentScore contract success, contract address is " + studentScore.getContractAddress());

			Properties prop = new Properties();
			prop.setProperty("address", studentScore.getContractAddress());
			final Resource contractResource = new ClassPathResource("contract.properties");
			FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
			prop.store(fileOutputStream, "contract address");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy StudentScore contract failed, error message is  " + e.getMessage());
		}
	}

	public void insertStudentScore(String name, String subject, BigInteger score) {
		try {
			initialize();

			Properties prop = new Properties();
			final Resource contractResource = new ClassPathResource("contract.properties");
			InputStream fis = contractResource.getInputStream();
			prop.load(fis);
			String contractAddress = prop.getProperty("address");

			StudentScore studentScore = StudentScore.load(contractAddress, web3j, credentials, gasPrice, gasLimit);
			TransactionReceipt receipt = studentScore.insert(name, subject, score).send();

			List<InsertResultEventResponse> response = studentScore.getInsertResultEvents(receipt);
			if (response.isEmpty() || (response.get(0).count.compareTo(new BigInteger("1")) != 0)) {
				throw new Exception(" insert event log not found. ");
			}

			System.out.printf(" insert student score success. ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.printf(" insert student score failed, error message is " + e.getMessage());
		}
	}

	public void updateStudentScore(String name, String subject, BigInteger score) {
		try {
			initialize();

			Properties prop = new Properties();
			final Resource contractResource = new ClassPathResource("contract.properties");
			InputStream fis = contractResource.getInputStream();
			prop.load(fis);
			String contractAddress = prop.getProperty("address");

			StudentScore studentScore = StudentScore.load(contractAddress, web3j, credentials, gasPrice, gasLimit);
			TransactionReceipt receipt = studentScore.insert(name, subject, score).send();

			List<UpdateResultEventResponse> response = studentScore.getUpdateResultEvents(receipt);
			if (response.isEmpty() || (response.get(0).count.compareTo(new BigInteger("1")) != 0)) {
				throw new Exception(" update event log not found. ");
			}

			System.out.printf(" update student score success. ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.printf(" update student score failed, error message is " + e.getMessage());
		}
	}

	public void removeStudentScore(String name) {
		try {
			initialize();

			Properties prop = new Properties();
			final Resource contractResource = new ClassPathResource("contract.properties");
			InputStream fis = contractResource.getInputStream();
			prop.load(fis);
			String contractAddress = prop.getProperty("address");

			StudentScore studentScore = StudentScore.load(contractAddress, web3j, credentials, gasPrice, gasLimit);

			TransactionReceipt receipt = studentScore.remove(name).send();

			List<RemoveResultEventResponse> response = studentScore.getRemoveResultEvents(receipt);
			if (response.isEmpty() || (response.get(0).count.compareTo(new BigInteger("1")) != 0)) {
				throw new Exception(" update event log not found, may be score not exist. ");
			}

			System.out.printf(" remove student score success. ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.printf(" remove student score failed, error message is " + e.getMessage());
		}
	}

	public void selectStudentScore(String name) {
		try {
			initialize();

			Properties prop = new Properties();
			final Resource contractResource = new ClassPathResource("contract.properties");
			InputStream fis = contractResource.getInputStream();
			prop.load(fis);
			String contractAddress = prop.getProperty("address");

			StudentScore studentScore = StudentScore.load(contractAddress, web3j, credentials, gasPrice, gasLimit);

			Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>> result = studentScore.select(name).send();

			List<byte[]> value1 = result.getValue1();
			List<byte[]> value2 = result.getValue2();
			List<BigInteger> value3 = result.getValue3();

			System.out.println(" student score record numbers = " + value1.size());

			for (int i = 0; i < value1.size(); i++) {
				System.out.printf(" subject => %s, score => %s", value2.get(i), value3.get(i).toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.printf(" select student score failed, error message is " + e.getMessage());
		}
	}

	public static void Usage() {
		System.out.println(" Usage:");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.bcos.student.score.BcosClient deploy");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.bcos.student.score.BcosClient select name");
		System.out
				.println("\t java -cp conf/:lib/*:apps/* org.bcos.student.score.BcosClient insert name subject score");
		System.out
				.println("\t java -cp conf/:lib/*:apps/* org.bcos.student.score.BcosClient update name subject score");
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			Usage();
		}

		BcosClient client = new BcosClient();
		switch (args[0]) {
		case "deploy":
			client.deployStudentScoreAndRecordAddr();
			break;
		case "select":
			if (args.length < 2) {
				Usage();
			}
			client.selectStudentScore(args[1]);
			break;
		case "update":
			if (args.length < 4) {
				Usage();
			}
			client.updateStudentScore(args[1], args[2], new BigInteger(args[3]));
			break;
		case "remove":
			if (args.length < 2) {
				Usage();
			}
			client.removeStudentScore(args[1]);
			break;
		case "insert":
			if (args.length < 4) {
				Usage();
			}
			client.insertStudentScore(args[1], args[2], new BigInteger(args[3]));
			break;

		default: {
			Usage();
		}
		}

		System.exit(0);
	}
}
