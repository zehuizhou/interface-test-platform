#!/bin/bash
source /etc/profile

DIR=$1

cd ${WORKSPACE}/DIR
mvn clean package -e -U -Dmaven.test.skip=true