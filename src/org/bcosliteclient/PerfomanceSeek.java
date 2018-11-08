package org.bcosliteclient;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.util.concurrent.RateLimiter;

import org.bcos.channel.client.Service;
import org.bcos.channel.client.TransactionSucCallback;
import org.bcos.channel.dto.EthereumResponse;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Int256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;

public class PerfomanceSeek {
    static Logger logger = LoggerFactory.getLogger(PerfomanceSeek.class);
    private static AtomicInteger sended = new AtomicInteger(0);
    
    public static void main(String[] args) throws Exception {
        //初始化Service
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Service service = context.getBean(Service.class);
        service.run();

        System.out.println("开始测试...");
        System.out.println("===================================================================");
        
        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        channelEthereumService.setChannelService(service);
        
        Web3j web3 = Web3j.build(channelEthereumService);

        //初始化交易签名私钥
        ECKeyPair keyPair = Keys.createEcKeyPair();
        Credentials credentials = Credentials.create(keyPair);

        //初始化交易参数
        java.math.BigInteger gasPrice = new BigInteger("30000000");
        java.math.BigInteger gasLimit = new BigInteger("30000000");
        java.math.BigInteger initialWeiValue = new BigInteger("0");
        
        System.out.println("部署合约");
        DBTest dbTest = DBTest.deploy(web3,credentials,gasPrice,gasLimit,initialWeiValue).get();
        dbTest.create().get();
        
        //解析参数
        if(args.length != 2)
        {
            System.out.println("运行参数有误，请输入./run.sh count qps,例如：./run.sh 1000 100");
            System.exit(0);
        }
        Integer count = Integer.parseInt(args[0]);
        Integer qps = Integer.parseInt(args[1]);
        
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
                    dbTest.insert(new Utf8String(uuid.toString()), new Int256(1), new Utf8String("1"), new TransactionSucCallback() {
                        @Override
                        public void onResponse(EthereumResponse response) {
                            callback.onResponse(System.currentTimeMillis() - currentTime, response);
                        }
                    });
                    
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
