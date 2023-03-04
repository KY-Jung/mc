@echo off

echo.

set MODE_RUN=dev
set PATH_JAR=.\build\libs
set FILE_JAR=mc-0.0.1.jar

echo Using JAVA_HOME : %JAVA_HOME%
echo Using sprint.profile.active : %MODE_RUN%
echo jar path : %PATH_JAR%
echo jar file : %FILE_JAR%

cd %PATH_JAR%
%JAVA_HOME%\bin\java -jar -Dspring.profiles.active=%MODE_RUN% -Duser.timezone="Asia/Seoul" %FILE_JAR%
cd ..\..

echo.

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: -Dspring.config.location=classpath:/application.yml,/home/mc/config/application-prod.yml
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


pause

