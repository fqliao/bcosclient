package org.bcos.student.score.service;

import org.bcos.student.score.contract.StudentScore.InsertResultEventResponse;
import org.bcos.student.score.contract.StudentScore.RemoveResultEventResponse;
import org.bcos.student.score.contract.StudentScore.UpdateResultEventResponse;
import org.bcos.student.score.contract.StudentScore;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentScoreService {

	private Web3j web3j;

	private Credentials credentials;
	
	private StudentScore studentScore;
	
	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public StudentScore getStudentScore() {
		return studentScore;
	}

	public void setStudentScore(StudentScore studentScore) {
		this.studentScore = studentScore;
	}

	private static BigInteger gasPrice = new BigInteger("300000000");
	private static BigInteger gasLimit = new BigInteger("300000000");

	private static Logger logger = LoggerFactory.getLogger(StudentScoreService.class);

	public String deployStudentScoreContract() throws Exception {

		StudentScore studentScore = StudentScore.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();

		logger.info(" deploy  StudentScore contract success, address is {}", studentScore.getContractAddress());

		return studentScore.getContractAddress();
	}

	public void insertStudentScore(String name, String subject, BigInteger score) throws Exception {

		TransactionReceipt receipt = studentScore.insert(name, subject, score).send();
		List<InsertResultEventResponse> response = studentScore.getInsertResultEvents(receipt);
		
		if (response.isEmpty()) {
			throw new Exception(" insert failed, event log not found, may be transaction not exec.");
		}
		
		if ((response.get(0).count.compareTo(new BigInteger("1")) != 0)) {
			throw new Exception(" insert failed, ret code = " + response.get(0).count.toString());
		}
		logger.info(" insert  StudentScore contract success, ,name is {}, subject is {}, score is {} ", name, subject,
				score);
	}

	public void updateStudentScore(String name, String subject, BigInteger score) throws Exception {

		TransactionReceipt receipt = studentScore.update(name, subject, score).send();
		List<UpdateResultEventResponse> response = studentScore.getUpdateResultEvents(receipt);
		
		if (response.isEmpty()) {
			throw new Exception(" update failed, event log not found, may be transaction not exec.");
		}
		
		if (response.get(0).count.compareTo(new BigInteger("1")) != 0) {
			throw new Exception(" update failed, maybe score of this subject not exist, ret code = " + response.get(0).count.toString());
		}
		
		logger.info(" update  StudentScore contract success, ,name is {}, subject is {}, score is {} ", name, subject,
				score);
	}

	public void removeStudentScore(String name) throws Exception {

		TransactionReceipt receipt = studentScore.remove(name).send();
		List<RemoveResultEventResponse> response = studentScore.getRemoveResultEvents(receipt);
		
		if (response.isEmpty()) {
			throw new Exception(" remove failed, event log not found, may be transaction not exec.");
		}
		
		logger.info(" remove StudentScore contract success, name is {} ", name);

	}

	public Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>> selectStudentScore(String name)
			throws Exception {

		Tuple3<List<byte[]>, List<byte[]>, List<BigInteger>> result = studentScore.select(name).send();

		logger.info(" select StudentScore contract success, name is {}, result is {} ", name, result);

		return result;
	}
}
