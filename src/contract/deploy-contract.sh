#!/bin/bash
#cd ./output
cp ./output -r `date +%Y%m%d`
mv `date +%Y%m%d` ./output-bak
babel-node deploy_WePOP_Contract.js

