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

Kubernetes Notes  
I was not successful in my attempts to run the system in Kubernetes. I believe it is running, but I can't seem at access the UI with the external IP. I installed minikube, and ran  
minikube start  
From the docker folder, I ran the following commands:  
kompose convert  
kubectl apply -f converter-ui-service.yaml,db-service.yaml,springboot-api-service.yaml,converter-ui-deployment.yaml,db-deployment.yaml,springboot-api-deployment.yaml  
The Kubernetes dashboard then lists the three services as running, but without external IPs. I add an externalIP and messed around with the port settings, as per various websites, but didn't have much luck. The dashboard lists the external IP, but I can't reach it.
