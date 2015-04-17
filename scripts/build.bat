cd ..
mvn clean install > logs\build.log
cd target
rm SecureChatClient-0.0.1-SNAPSHOT.jar
cp SecureChatClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar SecureChatClient-0.0.1-SNAPSHOT.jar
rm SecureChatClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar
pause