# converter
The converter system to test out Spring Boot, Angular, Postgres, Docker and Kubernetes.
Currently builds the into the following Docker images:
jsincl4ir/converter-ui
jsincl4ir/converter-api

The project can be run with ./runner from the checked out repository folder.

The docker-compose.yml uses the postgres docker image, and the API sets up the DB when it starts up. Once it's running, the UI should be accessible on http://localhost:4200  
For testing, here are some user accounts:  
Username  Password  
james     password  
ruben     password  
philip    password  

The user accounts, sessions, and most user actions are logged to the DB, which is accessible on localhost:5432, postgres database, username and password postgres.  
The API runs on port 8080. It has some mundane unit test, but the conversion commands can be tested in the browser with the type of conversion, value and units. E.G  
http://localhost:8080/volume?from=l&to=ml&value=10  
The above will convert volume of 10 litres to millilitres.
