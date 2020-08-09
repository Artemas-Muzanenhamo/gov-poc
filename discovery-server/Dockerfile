FROM adoptopenjdk/openjdk11 AS discovery-server
# copy JAR into image
COPY build/libs/discovery-server-*-SNAPSHOT.jar /discovery-server.jar
# expose port
EXPOSE 8761
# run application with this command line
CMD ["java", "-jar", "/discovery-server.jar"]
