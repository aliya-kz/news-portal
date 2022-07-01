FROM maven as maven_builder

ENV HOME=/app

WORKDIR $HOME

ADD src $HOME

ADD pom.xml $HOME

RUN ["mvn","clean","install"]

FROM tomcat

COPY --from=maven_builder $HOME/news-portal/target/news-portal-project.war /usr/local/tomcat/webapps

CMD ["catalina.sh", "run"]

