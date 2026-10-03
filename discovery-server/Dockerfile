FROM amazoncorretto:17.0.20-al2-native-jdk AS discovery-server
# copy JAR into image
COPY build/libs/discovery-server-*.jar /discovery-server.jar
# expose port
EXPOSE 8761
# run application with this command line
CMD ["java", "-jar", "/discovery-server.jar"]
