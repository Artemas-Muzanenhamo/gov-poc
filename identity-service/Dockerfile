FROM openjdk:8-jre-alpine AS identity-service
# copy JAR into image
COPY build/libs/identity-service-*-SNAPSHOT.jar /identity-service.jar
# expose port
EXPOSE 8080
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/identity-service.jar"]
