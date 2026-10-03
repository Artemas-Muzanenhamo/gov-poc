FROM amazoncorretto:17.0.20-al2-native-jdk AS license-service
# copy JAR into image
COPY build/libs/license-service-*.jar /license-service.jar
# expose port
EXPOSE 8081
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/license-service.jar"]
