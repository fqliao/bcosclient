#!/bin/bash 
echo $@
java -cp 'apps/*:conf/:lib/*' org.bcosliteclient.DBClient $@

