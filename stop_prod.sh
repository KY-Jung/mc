#!/bin/sh

echo ""

JAVA_PID=$(ps -ef | grep java | grep spring.profiles.active | awk '{print $2}')

if [ -z "$JAVA_PID" ];
then
    echo "Java service is not running."
else
    kill -9 $JAVA_PID
    echo "Java service stopped."
fi

echo ""

