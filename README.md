# converter
The converter system to test out Spring Boot, Angular, Postgres, Docker and Kubernetes.
Currently builds the into the following Docker images:
jsincl4ir/converter-ui
jsincl4ir/converter-api

The project can be run with ./runner
The docker-compose.yml uses the postgres docker image, and the api sets up the DB when it starts up. Once it's running, the UI should be accessible on http://localhost:4200
For testing, here are some user accounts:
Username  Password
james     password
ruben     password
philip    password
