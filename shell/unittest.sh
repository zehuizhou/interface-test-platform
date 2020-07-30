#!/bin/bash
source /etc/profile

DIR=$1

cd ${WORKSPACE}/$DIR
mvn org.jacoco:jacoco-maven-plugin:0.8.4:prepare-agent clean test -Dmaven.test.failure.ignore=false