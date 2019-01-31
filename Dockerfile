FROM java:8
COPY target/rideforce-maps-service-0.0.1-SNAPSHOT.jar /opt/lib/rideforce-maps-service-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/opt/lib/rideforce-maps-service-0.0.1-SNAPSHOT.jar","--server.servlet.context-path=/maps","&"]
