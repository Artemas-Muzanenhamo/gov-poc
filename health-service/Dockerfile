FROM openjdk:8-jre-alpine AS health-service
# copy WAR into image
COPY target/health-service-*-SNAPSHOT.jar /health-service.jar
# expose port
EXPOSE 8082
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/health-service.jar"]
