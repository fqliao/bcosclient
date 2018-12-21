#!/bin/bash 
echo $@
if [ $# != 4 ]; then
   echo "运行参数有误，请输入./run.sh [ok/db] groupID count qps,例如：./run.sh 1 1000 100"
   exit 1
fi
if [ $1 == "ok" ]; then
   java -cp 'apps/*:conf/:lib/*' org.bcosliteclient.PerfomanceOk $2 $3 $4
else
   java -cp 'apps/*:conf/:lib/*' org.bcosliteclient.PerfomanceSeek $2 $3 $4
fi

