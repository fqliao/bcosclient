package org.bcosliteclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.bcos.channel.client.Service;
import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Int256;
import org.bcos.web3j.abi.datatypes.generated.Uint256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.bcos.web3j.protocol.core.methods.response.EthBlockNumber;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.bcosliteclient.DBTest.InsertResultEventResponse;
import org.bcosliteclient.DBTest.RemoveResultEventResponse;
import org.bcosliteclient.DBTest.UpdateResultEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OkClient {
    static Logger logger = LoggerFactory.getLogger(DBClient.class);
    public static Web3j web3j;
    // 初始化交易参数
    public static java.math.BigInteger gasPrice = new BigInteger("1");
    public static java.math.BigInteger gasLimit = new BigInteger("30000000");
    public static java.math.BigInteger initialWeiValue = new BigInteger("0");
    public static ECKeyPair keyPair;
    public static Credentials credentials;
    public static String contractAddress = "";


    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void testOkAMDB(String[] args) throws InterruptedException, ExecutionException, IOException {

      Future<OkAMDB> futureDeploy = OkAMDB.deploy(web3j, credentials, gasPrice, gasLimit, initialWeiValue);
      OkAMDB ok = futureDeploy.get();
    
      int i = 1;
      while(true)
      { 
          String from_accout = "0x"+i;
          Future<TransactionReceipt> count =
                  ok.trans(new Utf8String(from_accout), new Int256(15));
          TransactionReceipt transactionReceipt = count.get();
          Future<Int256> future = ok.get();
          BigInteger num = future.get().getValue();
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
      web3j = Web3j.build(channelEthereumService);
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
