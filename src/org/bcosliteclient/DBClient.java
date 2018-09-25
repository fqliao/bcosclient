package org.bcosliteclient;

import org.bcos.web3j.abi.datatypes.Type;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.bcos.web3j.abi.datatypes.generated.Int256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.protocol.core.methods.response.EthBlockNumber;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.bcosliteclient.DBTest.InsertResultEventResponse;
import org.bcosliteclient.DBTest.RemoveResultEventResponse;
import org.bcosliteclient.DBTest.UpdateResultEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.bcos.channel.client.Service;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DBClient {
  static Logger logger = LoggerFactory.getLogger(DBClient.class);
  public static Web3j web3j;
  // 初始化交易参数
  public static java.math.BigInteger gasPrice = new BigInteger("1");
  public static java.math.BigInteger gasLimit = new BigInteger("30000000");
  public static java.math.BigInteger initialWeiValue = new BigInteger("0");
  public static ECKeyPair keyPair;
  public static Credentials credentials;
  public static String contractAddress = "";

  /* deploy the contract,get address from blockchain */
  public static void deployDBTest() throws InterruptedException, ExecutionException {

    Future<DBTest> futureDeploy =
        DBTest.deploy(web3j, credentials, gasPrice, gasLimit, initialWeiValue);
    DBTest dbtest = futureDeploy.get();
    contractAddress = dbtest.getContractAddress();
    dbtest.getContractName();
    logger.info("Deploy contract :" + dbtest.getContractName() + ",address :" + contractAddress);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static void testDBTest() throws InterruptedException, ExecutionException {

   
    DBTest dbtest = DBTest.load(contractAddress, web3j, credentials, gasPrice, gasLimit);
    
    //创建表
    dbtest.create().get();
    //增
    Future<TransactionReceipt> count =
        dbtest.insert(new Utf8String("fruit"), new Int256(1), new Utf8String("apple"));
    TransactionReceipt transactionReceipt = count.get();
    List<InsertResultEventResponse> InsertResultEvents = dbtest.getInsertResultEvents(transactionReceipt);
    for (int i = 0; i < InsertResultEvents.size(); i++) {
    InsertResultEventResponse insertResultEventResponse = InsertResultEvents.get(i);
    logger.info("insertCount = "+ insertResultEventResponse.count.getValue().intValue());
    }
    
    
    //查
    List<Type> lists = dbtest.read(new Utf8String("fruit")).get();
    try {
      List<Bytes32> list0 = (ArrayList<Bytes32>) lists.get(0).getValue();
      List<Int256> list1 = (ArrayList<Int256>) lists.get(1).getValue();
      List<Bytes32> list2 = (ArrayList<Bytes32>) lists.get(2).getValue();
      logger.info("查询记录条数 = "+ list0.size());
      for (int i = 0; i < list0.size(); i++) {
        String name = new String(list0.get(i).getValue());
        logger.info("name = " + name);
        int item_id = list1.get(i).getValue().intValue();
        logger.info("item_id = " + item_id);
        String item_name = new String(list2.get(i).getValue());
        logger.info("item_name = " + item_name);
      }
    } catch (Exception e) {
      logger.info("查询无记录！");
    }

      
    //改  
//    Future<TransactionReceipt> updateCount = dbtest.update(new Utf8String("fruit"), new Int256(1), new Utf8String("apple2"));
//    TransactionReceipt transactionReceiptU = updateCount.get();
//    List<UpdateResultEventResponse> updateResultEvents = dbtest.getUpdateResultEvents(transactionReceiptU);
//    for (int i = 0; i < updateResultEvents.size(); i++) {
//      UpdateResultEventResponse updateResultEventResponse = updateResultEvents.get(i);
//      logger.info("updateCount = "+ updateResultEventResponse.count.getValue().intValue());
//    }
    
    
    //删
//  Future<TransactionReceipt> removeCount = dbtest.remove(new Utf8String("fruit"), new Int256(1));
//  TransactionReceipt transactionReceiptR = removeCount.get();
//  List<RemoveResultEventResponse> removeResultEvents = dbtest.getRemoveResultEvents(transactionReceiptR);
//  RemoveResultEventResponse reomveResultEventResponse = removeResultEvents.get(0);
//  logger.info("removeCount = "+ reomveResultEventResponse.count.getValue().intValue());
      
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


    deployDBTest();
    
    logger.info("testDBTest");
    testDBTest(); 
    /* print block number after some transactions */
    ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
    int finishBlockNumber = ethBlockNumber.getBlockNumber().intValue();
    logger.info(
        "<--start blockNumber = " + startBlockNumber + ",finish blocknmber=" + finishBlockNumber);
    System.exit(0);

  }
}
