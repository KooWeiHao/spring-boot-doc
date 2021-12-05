FROM openjdk:8-jdk-alpine
MAINTAINER erickoo
ARG JAR_FILE
COPY target/${JAR_FILE} spring-boot-doc-rest.jar
EXPOSE 31000
ENTRYPOINT ["java","-jar","/spring-boot-doc-rest.jar"]
