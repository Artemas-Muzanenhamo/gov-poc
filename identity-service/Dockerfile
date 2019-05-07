FROM openjdk:8-jre-alpine AS identity-service
# copy WAR into image
COPY target/identity-service-*-SNAPSHOT.jar /identity-service.jar
# expose port
EXPOSE 8080
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/identity-service.jar"]
