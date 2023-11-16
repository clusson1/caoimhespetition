FROM tomcat:latest
ADD target/*.war /usr/local/tomcat/webapps/
ADD src/main/resources/static /usr/local/tomcat/webapps/ROOT/static
EXPOSE 9090
CMD ["catalina.sh", "run"]
