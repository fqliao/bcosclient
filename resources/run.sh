#!/bin/bash
echo $@
java -cp './:lib/*:./' org.bcosliteclient.DBClient $@
