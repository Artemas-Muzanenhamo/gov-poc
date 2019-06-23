FROM openjdk:8-jre-alpine AS discovery-server
# copy JAR into image
COPY build/libs/discovery-server-*-SNAPSHOT.jar /discovery-server.jar
# expose port
EXPOSE 8761
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/discovery-server.jar"]
