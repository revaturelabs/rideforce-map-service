FROM openjdk:8-jdk-alpine
ARG URL
ENV JDBC_URL=$URL
ARG USER
ENV JDBC_USERNAME=$USER
ARG PASS
ENG JDBC_PASSWORD=$PASS
ARG KEY
ENV MAPS_API_KEY=$KEY
COPY target/rideforce-maps-service.jar /opt/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/rideforce-maps-service.jar"]
#EXPOSE 9090 #this isn't used yet
