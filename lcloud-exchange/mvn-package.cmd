@ECHO OFF&PUSHD %~DP0

call mvn clean package -Dmaven.test.skip=true

@pause