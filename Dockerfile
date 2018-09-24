FROM openjdk:8-jdk-alpine
COPY target/rideforce-map-service.jar /opt/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/rideforce-map-service.jar"]
#EXPOSE 9090 #this isn't used yet
