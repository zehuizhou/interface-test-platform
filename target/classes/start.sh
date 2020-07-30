#!/usr/bin/env bash
PID=$(ps -ef | grep rabbit-platform-client-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    #java  -Dfile.encoding=UTF8  -jar rabbit-platform-client-0.0.1-SNAPSHOT.jar
	#nohup java -jar rabbit-platform-client-0.0.1-SNAPSHOT.jar > catalina.out  2>&1 --server.port=8888&
	nohup java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC rabbit-platform-server-0.0.1-SNAPSHOT.jar > catalina.out  2>&1&
else
    echo Application is already started
fi 
