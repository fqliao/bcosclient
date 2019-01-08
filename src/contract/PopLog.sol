pragma solidity ^0.4.4;


contract PopLog {

    event transRetLog(string oper,int256 status,int check_code);
    // event setCheckCodeTagRetLog(string oper,int status,int check_code);

    event orderLog(bytes32[] bytesArgs/*8*/,int[] intArgs/*12*/,int txStatus);

    event debugRetLog(bytes32 msg1,int256 msg2,int msg3);

    event newAccount(bytes32 org_id,bytes32 account,int currency,string curr_date,bytes32 firstName,bytes32 lastName,bytes32 idType,bytes32 userID,bytes32 UserCard);
}
