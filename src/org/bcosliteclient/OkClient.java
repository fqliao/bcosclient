package org.bcosliteclient;

import java.math.BigInteger;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.EthBlockNumber;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OkClient {
    static Logger logger = LoggerFactory.getLogger(OkClient.class);
    public static Web3j web3j;
    // 初始化交易参数
    public static java.math.BigInteger gasPrice = new BigInteger("1");
    public static java.math.BigInteger gasLimit = new BigInteger("30000000");
    public static java.math.BigInteger initialWeiValue = new BigInteger("0");
    public static ECKeyPair keyPair;
    public static Credentials credentials;
    public static String contractAddress = "";


    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void testOkAMDB(String[] args) throws Exception {

     @SuppressWarnings("deprecation")
	 RemoteCall<OkD> deploy = OkD.deploy(web3j, credentials, gasPrice, gasLimit);
     OkD ok = deploy.send();
    
      int i = 2;
      while(true)
      { 
          String from_accout = "0x"+i;
          RemoteCall<TransactionReceipt> trans = ok.trans(from_accout, new BigInteger("5"));
          trans.send();
          RemoteCall<BigInteger> remoteCall = ok.get();
          BigInteger num = remoteCall.send();
          System.out.println("num = "+num);
          i++;
      }
        
    }

    public static void main(String[] args) throws Exception {

      // init the Service
      ApplicationContext context =
          new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
      Service service = context.getBean(Service.class);
      service.run(); // run the daemon service
      // init the client keys
      keyPair = Keys.createEcKeyPair();
      credentials = Credentials.create(keyPair);

      logger.info("-----> start test !");
      logger.info("init AOMP ChannelEthereumService");
      ChannelEthereumService channelEthereumService = new ChannelEthereumService();
      channelEthereumService.setChannelService(service);

      // init webj client base on channelEthereumService
      web3j = Web3j.build(channelEthereumService, 1);
      /*------------------init done start test--------------------------------*/


      // test get blocknumber,just optional steps

      EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
      int startBlockNumber = ethBlockNumber.getBlockNumber().intValue();
      logger.info("-->Got ethBlockNumber:{}", startBlockNumber);
      logger.info("testOkAMDB");
      
      testOkAMDB(args); 

      /* print block number after some transactions */
      ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
      int finishBlockNumber = ethBlockNumber.getBlockNumber().intValue();
      logger.info(
          "<--start blockNumber = " + startBlockNumber + ",finish blocknmber=" + finishBlockNumber);
      System.exit(0);

    }
}
