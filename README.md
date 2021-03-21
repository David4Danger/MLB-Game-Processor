# MLB-Game-Processor

The MLB-Game-Processor acts as an intermediary API between the MLB Stats API handler and services requesting MLB data.

### Running the project
Create an `application-local.properties` file under `src/main/resources` and populate the below variables. Do NOT commit this file and push it to GitHub. Of course right now only my access and secret key work for calling the MLB-Game-Lambda, this'll probably change in the future?
```
aws.accessKey = KEY HERE
aws.secretKey = KEY HERE
```

### Sample request
```
curl --location --request GET 'http://localhost:8080/mlb-game-processor/get-player-stats?playerName=Max%20Scherzer&year=career&statGroup=pitching' \
--header 'Content-Type: json'
```



### TODO
* Create Swagger documentation for endpoints
* Actually implement some of the endpoints I want to create documentation for
* Manually deploy to EC2
* Create GitHub Actions or Jenkins pipeline for CICD.
* A million other things (I'm working on it)
