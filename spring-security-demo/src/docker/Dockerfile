FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ${JAR_FILE} app.jar
RUN bash -c 'touch app.jar'
EXPOSE 9999
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
