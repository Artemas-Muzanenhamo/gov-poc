FROM amazoncorretto:17.0.20-al2-native-jdk AS configuration-server
# copy JAR into image
COPY build/libs/configuration-server-*.jar /configuration-server.jar
# expose port
EXPOSE 8888
# run application with this command line
CMD ["java", "-jar", "/configuration-server.jar"]
