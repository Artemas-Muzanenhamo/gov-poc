FROM amazoncorretto:17.0.20-al2-native-jdk AS health-service
# copy JAR into image
COPY build/libs/health-service-*.jar /health-service.jar
# expose port
EXPOSE 8082
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/health-service.jar"]
