FROM openjdk:8-jre-alpine AS gateway
# copy JAR into image
COPY build/libs/gateway-*-SNAPSHOT.jar /gateway.jar
# expose port
EXPOSE 9999
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/gateway.jar"]
