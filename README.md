# RideForce maps service

This service handles the following endpoints (see the API documentation in
the gateway service repo for all endpoints and their explanations):

- `/location`
- `/route`

## Environment variables

Environment variables are used for sensitive data that should not be exposed
in the public Git repository. The following is a comprehensive list of all
environment variables that are necessary for proper program execution:

- `JDBC_URL`: the database url
- `JDBC_USERNAME`: the database username
- `JDBC_PASSWORD`: the database password
- `MAPS_API_KEY`: the Google Maps API key
