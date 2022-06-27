FROM tomcat
COPY target/news-portal-project.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.bat", "run"]