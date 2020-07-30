#!/bin/bash
source /etc/profile

DIR=$1
PARAM=$2

cd ${WORKSPACE}/$DIR
mvn test $PARAM