package org.bcosliteclient;

import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.bcosliteclient.DBTest.InsertResultEventResponse;
import org.bcosliteclient.DBTest.RemoveResultEventResponse;
import org.bcosliteclient.DBTest.UpdateResultEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.EthBlockNumber;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBClient {
  static Logger logger = LoggerFactory.getLogger(DBClient.class);
  public static Web3j web3j;
  // 初始化交易参数
  public static java.math.BigInteger gasPrice = new BigInteger("1");
  public static java.math.BigInteger gasLimit = new BigInteger("30000000");
  public static ECKeyPair keyPair;
  public static Credentials credentials;
  public static String contractAddress = "";

  /* deploy the contract,get address from blockchain */
  @SuppressWarnings("deprecation")
public static void deployDBTest() throws Exception{

	RemoteCall<DBTest> deploy  = DBTest.deploy(web3j, credentials, gasPrice, gasLimit);
    DBTest dbtest = deploy.send();
    contractAddress = dbtest.getContractAddress();
    
    System.out.println("Deploy contract address: " + contractAddress);
    logger.info("Deploy contract address: " + contractAddress);
    
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static void testDBTest(String[] args) throws Exception{

	RemoteCall<DBTest> deploy  = DBTest.deploy(web3j, credentials, gasPrice, gasLimit);
	DBTest dbtest = deploy.send();
    //创建表
    if("create".equals(args[0]))
    {
        try {
            dbtest.create().send();
            System.out.println("\nuser table insert _sys_tables_ completed!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nuser table insert _sys_tables_ failure!");
        }
    }
    //增
    else if("insert".equals(args[0]))
    {
        if(args.length == 4)
        {
            String name = args[1];
            int item_id = Integer.parseInt(args[2]);
            String item_name = args[3];
            
            RemoteCall<TransactionReceipt> insert = dbtest.insert(name,  BigInteger.valueOf(item_id), item_name);
            TransactionReceipt txReceipt = insert.send();
            List<InsertResultEventResponse> insertResultEvents = dbtest.getInsertResultEvents(txReceipt);
            for (int i = 0; i < insertResultEvents.size(); i++)
            {
                InsertResultEventResponse insertResultEventResponse = insertResultEvents.get(i);
                logger.info("insertCount = "+ insertResultEventResponse.count.intValue());
                System.out.println("insertCount = "+ insertResultEventResponse.count.intValue());
            }        
        }
        else
        {
            System.out.println("\nPlease enter as follow example:\n insert fruit 1 apple");
        }
    }
    //查
    else if("read".equals(args[0]))
    {
        if(args.length == 2)
        {
            String keyName = args[1];
            Tuple3<List<byte[]>, List<BigInteger>, List<byte[]>> lists = dbtest.read(keyName).send();
            try {
              List<byte[]> value1 = lists.getValue1();
              List<BigInteger> value2 = lists.getValue2();
              List<byte[]> value3 = lists.getValue3();
              logger.info("查询记录条数 = "+ value1.size());
              System.out.println("查询记录条数 = "+ value1.size());
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
              logger.info("查询无记录！");
              System.out.println("查询无记录！");
            }
        }
        else
        {
            System.out.println("\nPlease enter as follow example:\n select fruit");
        }
    }
    //改 
    else if("update".equals(args[0]))
    {
        if(args.length == 4)
        {   
            String name = args[1];
            int item_id = Integer.parseInt(args[2]);
            String item_name = args[3];
            RemoteCall<TransactionReceipt> update = dbtest.update(name, BigInteger.valueOf(item_id), item_name);
            TransactionReceipt transactionReceipt = update.send();
            List<UpdateResultEventResponse> updateResultEvents = dbtest.getUpdateResultEvents(transactionReceipt);
            for (int i = 0; i < updateResultEvents.size(); i++) {
              UpdateResultEventResponse updateResultEventResponse = updateResultEvents.get(i);
              System.out.println("updateCount = "+ updateResultEventResponse.count.intValue());
              logger.info("updateCount = "+ updateResultEventResponse.count.intValue());
            }
        }
        else
        {
            System.out.println("\nPlease enter as follow example:\n update fruit 1 orange");
        }
    }
    //删
    else if("remove".equals(args[0]))
    {
        if(args.length == 3)
        {
            String name = args[1];
            int item_id = Integer.parseInt(args[2]);
            RemoteCall<TransactionReceipt> remove = dbtest.remove(name, BigInteger.valueOf(item_id));
            TransactionReceipt transactionReceipt = remove.send();
            List<RemoveResultEventResponse> removeResultEvents = dbtest.getRemoveResultEvents(transactionReceipt);
            RemoveResultEventResponse reomveResultEventResponse = removeResultEvents.get(0);
            logger.info("removeCount = "+ reomveResultEventResponse.count.intValue());
            System.out.println("removeCount = "+ reomveResultEventResponse.count.intValue());
        }
        else
        {
            System.out.println("\nPlease enter as follow example:\n remove fruit 1");
        }
    }
    else
    {
        System.out.println("\nPlease choose follow commands:\n deploy, create, insert, select, update or remove");
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
    if(args.length > 0)
    {
        if("deploy".equals(args[0]))
        {
            deployDBTest();
        }
        else
        {
            logger.info("testDBTest");
            testDBTest(args); 
        }
    }
    else
    {
        System.out.println("\nPlease choose follow commands:\n deploy, create, insert, select, update or remove");
    }

    /* print block number after some transactions */
    ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
    int finishBlockNumber = ethBlockNumber.getBlockNumber().intValue();
    logger.info(
        "<--start blockNumber = " + startBlockNumber + ",finish blocknmber=" + finishBlockNumber);
    System.exit(0);

  }
}
