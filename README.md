# RideForce maps service

This service handles the following endpoints (see the API documentation in
the gateway service repo for all endpoints and their explanations):

- `/location`
- `/route`
- `/favoritelocations`

## Environment variables

Environment variables are used for sensitive data that should not be exposed
in the public Git repository. The following is a comprehensive list of all
environment variables that are necessary for proper program execution:

- `JDBC_URL`: the database url
- `JDBC_USERNAME`: the database username
- `JDBC_PASSWORD`: the database password
- `MAPS_API_KEY`: the Google Maps API key

### Setting up environment variables in STS:

- In the run dropdown menu, select run configurations

	![Image of run bttn](https://i.imgur.com/PwGMq8B.png)
	
- Select the proper configuration context to specify the scope of your application variables
	![Image of conf context](https://i.imgur.com/PQGfgXW.png)
- Select the environment variables tab
	![Image of env variables tab](https://i.imgur.com/rnjpaVB.png)
- On the right, select "new..." to start adding your variables
- In the name box, put the descriptor of your variable (ex. JDBC_USERNAME)
- In the variable box, type the value of your variable
- Hit "apply"

Note: Used ECL Emma, a free Java code coverage tool to ensure adequate coverage
