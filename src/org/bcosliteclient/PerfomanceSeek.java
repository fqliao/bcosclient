package org.bcosliteclient;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.util.concurrent.RateLimiter;

public class PerfomanceSeek {
    static Logger logger = LoggerFactory.getLogger(PerfomanceSeek.class);
    private static AtomicInteger sended = new AtomicInteger(0);
    
	
    public static void main(String[] args) throws Exception {
        
        //解析参数
        if(args.length != 3)
        {
            System.out.println("运行参数有误，请输入./run.sh groupID count qps,例如：./run.sh 1 1000 100");
            System.exit(0);
        }
        String groupIDstr = args[0].trim();
    	if(!groupIDstr.matches("^[0-9]*$") || "".equals(groupIDstr))
    	{
    		System.out.println("groupID需为整数！");
            System.exit(0);
    	}
    	String countstr = args[1].trim();
    	if(!countstr.matches("^[0-9]*$") || "".equals(countstr))
    	{
    		System.out.println("count需为整数！");
    		System.exit(0);
    	}
    	String qpsstr = args[2].trim();
    	if(!qpsstr.matches("^[0-9]*$") || "".equals(qpsstr))
    	{
    		System.out.println("qps需为整数！");
    		System.exit(0);
    	}
    	//初始化Service
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Service service = context.getBean(Service.class);
        service.run();

        System.out.println("开始测试...");
        System.out.println("===================================================================");
        
        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        
        Web3j web3 = Web3j.build(channelEthereumService, Integer.parseInt(groupIDstr));

        //初始化交易签名私钥
        ECKeyPair keyPair = Keys.createEcKeyPair();
        Credentials credentials = Credentials.create(keyPair);

        //初始化交易参数
        java.math.BigInteger gasPrice = new BigInteger("30000000");
        java.math.BigInteger gasLimit = new BigInteger("30000000");
        java.math.BigInteger initialWeiValue = new BigInteger("0");
        
        System.out.println("部署合约");
        DBTest dbTest = DBTest.deploy(web3,credentials,gasPrice,gasLimit).send();
        dbTest.create().send();
        
        Integer count = Integer.parseInt(countstr);
        Integer qps = Integer.parseInt(qpsstr);
        
        System.out.println("开始压测，总交易量：" + count);
        
        RateLimiter limiter = RateLimiter.create(qps);
        
        Integer area = count / 10;
        
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(50);
        threadPool.setMaxPoolSize(200);
        threadPool.setQueueCapacity(1000000);
        
        threadPool.initialize();
        
        PerfomanceSeekCallback callback = new PerfomanceSeekCallback();
        callback.setTotal(count);

        for (Integer i = 0; i < count; ++i) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    limiter.acquire();
                    Long currentTime = System.currentTimeMillis();
                    UUID uuid = UUID.randomUUID(); 
                    RemoteCall<TransactionReceipt> insert = dbTest.insert(uuid.toString(), BigInteger.valueOf(1), "1");
                    System.out.println("key="+uuid.toString());
                    TransactionReceipt transactionReceipt;
					try {
						 CompletableFuture<TransactionReceipt> sendAsync = insert.sendAsync();
						 transactionReceipt = sendAsync.get();
						 // 检查执行结果
						 callback.onResponse(System.currentTimeMillis() - currentTime, dbTest, transactionReceipt);
						 
					} catch (Exception e) {
						e.printStackTrace();
					}
                    
                    int current = sended.incrementAndGet();
                
                    if (current >= area && ((current % area) == 0)) {
                        System.out.println("已发送: " + current + "/" + count +  " 交易");
                    }
                }
                
            });
            
        }

        System.out.println("全部交易已发送: " + count);
    }
}
