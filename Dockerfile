FROM java:8
COPY target/rideforce-maps-service.jar /tmp/rideforce-maps-service.jar
CMD ["java", "-jar", "/tmp/rideforce-maps-service-0.0.1-SNAPSHOT.jar","--server.servlet.context-path=/maps","&"]
