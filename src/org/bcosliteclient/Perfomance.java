package org.bcosliteclient;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.util.concurrent.RateLimiter;

import org.bcos.channel.client.Service;
import org.bcos.web3j.abi.datatypes.Utf8String;
import org.bcos.web3j.abi.datatypes.generated.Int256;
import org.bcos.web3j.crypto.Credentials;
import org.bcos.web3j.crypto.ECKeyPair;
import org.bcos.web3j.crypto.Keys;
import org.bcos.web3j.protocol.Web3j;
import org.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public class Perfomance {
	static Logger logger = LoggerFactory.getLogger(Perfomance.class);
	
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
		Integer count = Integer.parseInt(args[0]);
		Integer qps = Integer.parseInt(args[1]);
		
		System.out.println("开始压测，总交易量：" + count);
		
		Set<Future<TransactionReceipt> > futureSet = new HashSet<Future<TransactionReceipt> >();
		
		Thread sender = new Thread() {
			RateLimiter limiter = RateLimiter.create(qps);
			
			public void run() {
				for(Integer i=0; i<count; ++i) {
					limiter.acquire();
					Future<TransactionReceipt> receipt = dbTest.insert(new Utf8String("user" + String.valueOf(i)), new Int256(i), new Utf8String("car" + i));
					
					synchronized (futureSet) {
						futureSet.add(receipt);
					}
					
					Integer area = count / 10;
					if(i >= area && ((i % area) == 0)) {
						System.out.println("已发送: " + i + " 交易");
					}
				}
				
				System.out.println("全部交易已发送: " + count);
			}
		};
		
		sender.start();
		
		Thread checker = new Thread() {
			Integer finished = 0;
			
			public void run(){
				Long startTime = System.currentTimeMillis();
				
				while (finished < count) {
					synchronized (futureSet) {
						Iterator<Future<TransactionReceipt>> it = futureSet.iterator();
						while (it.hasNext()) {
							Future<TransactionReceipt> receipt = it.next();
							if (receipt.isDone()) {
								it.remove();

								++finished;
							}
						}
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				Long cost = System.currentTimeMillis() - startTime;

				System.out.println("全部 " + count + " 交易已执行完成，共耗时" + cost.toString() + "ms");
				System.out.println("TPS: " + (double)count / ((double)cost / 1000));

				System.out.println("测试完成");

				System.exit(0);
			}
		};
		
		checker.start();
	}
}
