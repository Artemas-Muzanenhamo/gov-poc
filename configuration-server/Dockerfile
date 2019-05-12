FROM openjdk:8-jre-alpine AS configuration-server
# copy JAR into image
COPY target/config-*-SNAPSHOT.jar /configuration-server.jar
# expose port
EXPOSE 8888
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/configuration-server.jar"]
