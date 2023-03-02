#!/bin/sh

echo ""

MODE_RUN=prod
PATH_JAR=./build/libs
FILE_JAR=mc-0.0.1.jar
LOG_NOHUP=nohup.out

echo "Using JAVA_HOME : $JAVA_HOME"
echo "Using sprint.profile.active : $MODE_RUN"
echo "jar path : $PATH_JAR"
echo "jar file : $FILE_JAR"

cd $PATH_JAR
$JAVA_HOME/bin/java -jar -Dspring.profiles.active=$MODE_RUN $FILE_JAR
###nohup $JAVA_HOME/bin/java -jar -Dspring.profiles.active=$MODE_RUN $FILE_JAR > $LOG_NOHUP 2>&1 &
cd -

echo ""

################################################################################
# -Dspring.config.location=classpath:/application.yml,/home/mc/config/application-proddv.yml
################################################################################
