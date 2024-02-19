#!/bin/bash
echo "publish dragon-api-service----------"

process_id=`ps -ef | grep dragon-api.jar | grep -v grep |awk '{print $2}'`
if [ $process_id ] ; then
sudo kill -9 $process_id
fi

source /etc/profile
nohup java -jar -Dspring.profiles.active=prod /www/wwwroot/rabbit-java/dragon-api.jar > /dev/null 2>&1 &

echo "end publish"
