FROM openjdk:8-jdk-alpine
COPY target/rideforce-maps-service.jar /opt/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/rideforce-maps-service.jar"]
#EXPOSE 9090 #this isn't used yet
