FROM amazoncorretto:17.0.20-al2-native-jdk AS identity-service
# copy JAR into image
COPY build/libs/identity-service-*.jar /identity-service.jar
# expose port
EXPOSE 8080
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/identity-service.jar"]
