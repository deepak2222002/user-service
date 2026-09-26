FROM tomcat:10.1-jdk21

WORKDIR /usr/local/tomcat

RUN rm -rf webapps/*

COPY target/*.war webapps/ROOT.war

COPY src/main/resources/user-service.p12 conf/user-service.p12

EXPOSE 8080
EXPOSE 8443

CMD ["catalina.sh", "run"]