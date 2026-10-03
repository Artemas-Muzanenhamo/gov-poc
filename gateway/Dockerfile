FROM amazoncorretto:8u504-al2023-jre AS gateway
# copy JAR into image
COPY build/libs/gateway-*.jar /gateway.jar
# expose port
EXPOSE 9999
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/gateway.jar"]
