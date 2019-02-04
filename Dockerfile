FROM java:8
COPY target/rideforce-maps-service.jar /tmp/rideforce-maps-service.jar
CMD ["java", "-jar", "/tmp/rideforce-maps-service.jar","--server.servlet.context-path=/maps","&"]

#finalDockerfile
