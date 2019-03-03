FROM openjdk:8-jdk-alpine
VOLUME /tmp

#ARG DEPENDENCY=target/deps
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY target/gs-spring-boot-docker-0.1.0.jar app.jar

EXPOSE 8080

#ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar" ]
