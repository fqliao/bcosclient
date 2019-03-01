package org.bcos.student.score.client;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bcos.student.score.contract.StudentScore;
import org.bcos.student.score.service.StudentScoreService;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class StudentScoreClient {
	
	static Logger logger = LoggerFactory.getLogger(StudentScoreClient.class);

	private StudentScoreService studentScoreService;
	
	public StudentScoreService getStudentScoreService() {
		return studentScoreService;
	}

	public void setStudentScoreService(StudentScoreService studentScoreService) {
		this.studentScoreService = studentScoreService;
	}

	public void initialize(String cmd) throws Exception {

		// init the Service
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);
		
		// init Credentials
		Credentials credentials = Credentials.create(Keys.createEcKeyPair());
		
		StudentScoreService studentScoreService = new StudentScoreService();
		studentScoreService.setCredentials(credentials);
		studentScoreService.setWeb3j(web3j);
		
		setStudentScoreService(studentScoreService);
		
		logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
		//
		if (!cmd.equals("deploy")) {
			
			// load StudentCore contact address
			Properties prop = new Properties();
			final Resource contractResource = new ClassPathResource("contract.properties");
			prop.load(contractResource.getInputStream());
			String contractAddress = prop.getProperty("address");
			
			if (contractAddress == null || contractAddress.trim().equals("")) {
				throw new Exception(" load student score contract address failed, deploy it first. ");
			}
			
			StudentScore studentScore = StudentScore.load(contractAddress, web3j, credentials, new StaticGasProvider(new BigInteger("300000000"), new BigInteger("300000000")));
			studentScoreService.setStudentScore(studentScore);
		}
	}

	public void deployStudentScoreAndRecordAddr() {
		
		try {
			String address = getStudentScoreService().deployStudentScoreContract();
			System.out.println(
					" deploy StudentScore contract success, contract address is " + address);

			Properties prop = new Properties();
			prop.setProperty("address", address);
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
	
			getStudentScoreService().insertStudentScore(name, subject, score);
			
			System.out.println(" insert student score success. ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" insert student score failed, error message is " + e.getMessage());
		}
	}

	public void updateStudentScore(String name, String subject, BigInteger score) {
		
		try {

			getStudentScoreService().updateStudentScore(name, subject, score);

			System.out.println(" update student score success. ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" update student score failed, error message is " + e.getMessage());
		}
	}

	public void removeStudentScore(String name) {
		try {
			
			getStudentScoreService().removeStudentScore(name);

			System.out.println(" remove student score success. ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" remove student score failed, error message is " + e.getMessage());
		}
	}

	public void selectStudentScore(String name) {
		try {
		
			Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>> result = getStudentScoreService().selectStudentScore(name);

			List<byte[]> value1 = result.getValue1();
			List<byte[]> value2 = result.getValue2();
			List<BigInteger> value3 = result.getValue3();

			System.out.println( " " + name + "'s score count = " + value1.size());

			for (int i = 0; i < value1.size(); i++) {
				System.out.printf(" subject => %s, score => %s\n", new String(value2.get(i)), value3.get(i).toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" select student score failed, error message is " + e.getMessage());
		}
	}

	public static void Usage() {
		System.out.println(" Usage:");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.bcos.student.score.client.StudentScoreClient deploy");
		System.out.println("\t java -cp conf/:lib/*:apps/* org.bcos.student.score.client.StudentScoreClient select name");
		System.out
				.println("\t java -cp conf/:lib/*:apps/* org.bcos.student.score.client.StudentScoreClient insert name subject score");
		System.out
				.println("\t java -cp conf/:lib/*:apps/* org.bcos.student.score.client.StudentScoreClient update name subject score");
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			Usage();
		}

		StudentScoreClient client = new StudentScoreClient();
		client.initialize(args[0]);
		
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
