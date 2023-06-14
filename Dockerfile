FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
WORKDIR /XYZ-App
COPY . .
EXPOSE 3010
ENTRYPOINT exec java $JAVA_OPTS -jar xyzbank.jar
CMD [ "mvn" , "clean", "test" ]
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar xyzbank.jar
