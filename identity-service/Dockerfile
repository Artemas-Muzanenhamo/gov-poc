FROM amazoncorretto:8u504-al2023-jre AS identity-service
# copy JAR into image
COPY build/libs/identity-service-*.jar /identity-service.jar
# expose port
EXPOSE 8080
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/identity-service.jar"]
