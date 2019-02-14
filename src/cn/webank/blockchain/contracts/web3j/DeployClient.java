package cn.webank.blockchain.contracts.web3j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.fisco.bcos.web3j.utils.Numeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class DeployClient {
	static Logger logger = LoggerFactory.getLogger(DeployClient.class);
	public static Web3j web3j;
	// 初始化交易参数
	public static java.math.BigInteger gasPrice = new BigInteger("1");
	public static java.math.BigInteger gasLimit = new BigInteger("30000000");
	public static ECKeyPair keyPair;
	public static Credentials credentials;
	public static String contractAddress = "";

	/* deploy the contract,get address from blockchain */
	@SuppressWarnings("deprecation")
	public static void deploy() {

		try {
			ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
			String abi = "";
			String check_info_abi = "";
			RemoteCall<CheckInfoManager> checkInfoManagerCall = CheckInfoManager.deploy(web3j, credentials,
					contractGasProvider, "CheckInfoManager", abi, "CheckInfo", check_info_abi);

			CheckInfoManager checkInfoManager = checkInfoManagerCall.send();
			contractAddress = checkInfoManager.getContractAddress();
			System.out.println("deploy contract address: " + contractAddress);
			logger.info("deploy contract address: " + contractAddress);
			Properties prop = new Properties();
			prop.setProperty("address", contractAddress);
			final Resource contractResource = new ClassPathResource("contract.properties");
			FileOutputStream fos = new FileOutputStream(contractResource.getFile());
			prop.store(fos, "contract address");

			System.out.println("deploy contract successful!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {

		// init the Service
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run(); // run the daemon service

		// init the client keys
		keyPair = Keys.createEcKeyPair();
		String address = Numeric.prependHexPrefix(Keys.getAddress(keyPair));
		String priviteKey = "bcec428d5205abe0f0cc8a734083908d9eb8563e31f943d760786edf42ad67dd";
		String origin = "0x00571e6f3538bfb77dde21537769243f81e13570";
		credentials = Credentials.create(priviteKey);
		System.out.println("tx.origin = " + origin);
		System.out.println();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		web3j = Web3j.build(channelEthereumService);

		deploy();

		System.exit(0);

	}
}
