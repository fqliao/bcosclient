package org.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.fisco.bcos.web3j.protocol.core.methods.response.EthBlockNumber;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.protocol.exceptions.TransactionException;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.fisco.bcos.web3j.utils.Numeric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cn.webank.blockchain.contracts.web3j.*;

public class DeployClient {
	static Logger logger = LoggerFactory.getLogger(DeployClient.class);
	public static Web3j web3j;
	// 初始化交易参数
	public static java.math.BigInteger gasPrice = new BigInteger("1");
	public static java.math.BigInteger gasLimit = new BigInteger("30000000");
	public static ECKeyPair keyPair;
	public static Credentials credentials;
	public static String contractAddress = "";
	public static ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);

	/* deploy the contract,get address from blockchain */
	@SuppressWarnings("deprecation")
	public static void deploy() {

		try {
			// 1. 部署场切合约
			System.out.println("准备部署场切合约....");
			CheckInfoManager checkInfoManager = CheckInfoManager.deploy(web3j, credentials, contractGasProvider,
					"CheckInfoManager", getAbiStr("CheckInfoManager"), "CheckInfo", getAbiStr("CheckInfo")).send();
			System.out.println("场切合约部署结束：" + checkInfoManager.getContractAddress());

			ClearCenterData clearCenterData = ClearCenterData
					.deploy(web3j, credentials, contractGasProvider, "ClearCenterData", getAbiStr("ClearCenterData"))
					.send();
			ClearCenterInfo clearCenterInfo = ClearCenterInfo
					.deploy(web3j, credentials, contractGasProvider, "ClearCenterInfo", getAbiStr("ClearCenterInfo"),
							"AAAAAAAAAAAAAAAAAAAAAAAAAAAAABOC".getBytes(), "BOC", new BigInteger("1"))
					.send();

			// 2. 部署清算合约
			System.out.println("准备部署清算合约....");
			ClearCenter clearCenter = ClearCenter.deploy(web3j, credentials, contractGasProvider, "ClearCenter",
					getAbiStr("ClearCenter"), clearCenterInfo.getContractAddress(),
					clearCenterData.getContractAddress(), checkInfoManager.getContractAddress()).send();

			// 初始化IssueBank
			clearCenter.setCheckInfoManager(checkInfoManager.getContractAddress());
			// 初始化ClearCenter
			clearCenter.setInfo(clearCenterInfo.getContractAddress());
			clearCenter.setData(clearCenterData.getContractAddress());
			System.out.println("清算合约部署结束：" + clearCenter.getContractAddress());

			// 3. 部署发卡行合约
			System.out.println("准备部署发卡行合约....");
			IssueBankData issueBankData = IssueBankData
					.deploy(web3j, credentials, contractGasProvider, "IssueBankData", getAbiStr("IssueBankData"))
					.send();
			IssueBankInfo issueBankInfo = IssueBankInfo
					.deploy(web3j, credentials, contractGasProvider, "IssueBankInfo", getAbiStr("IssueBankInfo"))
					.send();
			IssueBank issueBank = IssueBank
					.deploy(web3j, credentials, contractGasProvider, "IssueBank", getAbiStr("IssueBank")).send();

			PopLimit popLimit = PopLimit
					.deploy(web3j, credentials, contractGasProvider, "PopLimit", getAbiStr("PopLimit"),
							new BigInteger("2000000"), new BigInteger("30000000"), new BigInteger("300000"))
					.send();
			DateTime dateTime = DateTime.deploy(web3j, credentials, contractGasProvider).send();
			UserLimit userLimit = UserLimit.deploy(web3j, credentials, contractGasProvider, "UserLimit",
					getAbiStr("UserLimit"), popLimit.getContractAddress(), dateTime.getContractAddress()).send();
			OrderFactory orderFactory = OrderFactory.deploy(web3j, credentials, contractGasProvider, "OrderFactory",
					getAbiStr("OrderFactory"), "Order", getAbiStr("Order")).send();
			System.out.println("当前日期：" + userLimit.getYearAndDate().send());

			// 初始化IssueBank
			issueBank.setInfo(issueBankInfo.getContractAddress());
			issueBank.setData(issueBankData.getContractAddress());
			issueBank.setCheckInfoManager(checkInfoManager.getContractAddress());
			issueBank.setPopLimit(popLimit.getContractAddress());
			issueBank.setDateTime(dateTime.getContractAddress());
			issueBank.setOrderFactory(orderFactory.getContractAddress());

			issueBankInfo.setOrgID("AAAAAAAAAAAAAAAAAAAAAAAAAAABOCHK".getBytes());
			issueBankInfo.setOrgName("BOCHK");
			issueBankInfo.setOrgID("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAWB".getBytes());
			issueBankInfo.setOrgName("WB");

			clearCenter.registerOrg(new BigInteger("1"), "AAAAAAAAAAAAAAAAAAAAAAAAAAABOCHK".getBytes(),
					issueBank.getContractAddress(), new BigInteger("1000000000"), new BigInteger("1000000000"),
					new BigInteger("13"));
			System.out.println("发卡行合约部署结束：" + issueBank.getContractAddress());

//			BigInteger currentBalance = issueBank.currentBalance().send();
//			BigInteger credit = issueBank.credit().send();
//			System.out.println("issue : "+ currentBalance + " - " + credit);
			
			// 4. 部署收单行合约
			System.out.println("准备部署收单行合约....");
			MerchantInfoFactory merchantInfoFactory = MerchantInfoFactory
					.deploy(web3j, credentials, contractGasProvider, "MerchantInfoFactory",
							getAbiStr("MerchantInfoFactory"), "MerchantInfo", getAbiStr("MerchantInfo"))
					.send();
			MerchantDataFactory merchantDataFactory = MerchantDataFactory
					.deploy(web3j, credentials, contractGasProvider, "MerchantDataFactory",
							getAbiStr("MerchantDataFactory"), "MerchantData", getAbiStr("MerchantData"))
					.send();
			MerchantFactory merchantFactory = MerchantFactory.deploy(web3j, credentials, contractGasProvider,
					"MerchantFactory", getAbiStr("MerchantFactory"), "Merchant", getAbiStr("Merchant")).send();
			AcquirerBankData acquirerBankData = AcquirerBankData
					.deploy(web3j, credentials, contractGasProvider, "AcquirerBankData", getAbiStr("AcquirerBankData"))
					.send();
			AcquirerBankInfo acquirerBankInfo = AcquirerBankInfo.deploy(web3j, credentials, contractGasProvider,
					"AcquirerBankInfo", getAbiStr("AcquirerBankInfo"), "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAWB".getBytes(),
					"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAWB".getBytes()).send();
			AcquirerBank acquirerBank = AcquirerBank
					.deploy(web3j, credentials, contractGasProvider, "AcquirerBank", getAbiStr("AcquirerBank"),
							acquirerBankInfo.getContractAddress(), acquirerBankData.getContractAddress(),
							orderFactory.getContractAddress(), checkInfoManager.getContractAddress(),
							clearCenter.getContractAddress(), merchantInfoFactory.getContractAddress(),
							merchantDataFactory.getContractAddress(), merchantFactory.getContractAddress())
					.send();
			System.out.println("收单行合约部署结束：" + acquirerBank.getContractAddress());
			
			BigInteger acquirer_balance = acquirerBank.currentBalance().send();
			BigInteger acquirer_credit = acquirerBank.credit().send();
			System.out.println("acquirer: " + acquirer_balance + " - " + acquirer_credit);
			System.out.println("==============================End==============================");
			System.out.println("业务合约部署完成！");
			
			System.out.println("清算行合约(clearingBank)地址：" + clearCenter.getContractAddress());
			System.out.println("发卡行合约(issuingBank)地址：" + issueBank.getContractAddress());
			System.out.println("收单行合约(acquirerBank)地址：" + acquirerBank.getContractAddress());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getAbiStr(String contractName) throws IOException {
		final Resource resource = new ClassPathResource(contractName + ".sol_" + contractName + ".abi");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		StringBuilder strBuilder = new StringBuilder();
		String data = null;
		while ((data = br.readLine()) != null) {
			strBuilder.append(data);
		}
		return strBuilder.toString();
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
