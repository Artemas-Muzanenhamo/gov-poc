FROM adoptopenjdk/openjdk11 AS configuration-server
# copy JAR into image
COPY build/libs/configuration-server-*-SNAPSHOT.jar /configuration-server.jar
# expose port
EXPOSE 8888
# run application with this command line
CMD ["java", "-jar", "/configuration-server.jar"]
