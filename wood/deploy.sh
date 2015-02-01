#! /bin/bash

cp target/wood-1.0-SNAPSHOT.war webapps/ROOT.war
sudo cp target/wood-1.0-SNAPSHOT.war /usr/share/apache-tomcat-7.0.57/webapps/wood.war
tail -f /usr/share/apache-tomcat-7.0.57/logs/catalina.out
