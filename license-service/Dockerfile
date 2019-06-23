FROM openjdk:8-jre-alpine AS license-service
# copy JAR into image
COPY build/libs/license-service-*-SNAPSHOT.jar /license-service.jar
# expose port
EXPOSE 8081
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/license-service.jar"]
