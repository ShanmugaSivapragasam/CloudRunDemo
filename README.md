# CloudRunDemo

##About

Quick `Cloud Run` sample with  `Cloud Storage ` and optional `Cloud Scheduler`

## Steps 
* Write a simple Spring Boot App (use this repo and tweak the gcs locations)
* Containerize the app
* Build the app (sample cloud build)
    - `gcloud builds submit --tag gcr.io/$GCP_PROJ/cloudrundemo`
* deploy
    - `gcloud beta run deploy --image gcr.io/$GCP_PROJ/cloudrundemo --platform managed`
* optionally configure cloud scheduler to invoke the endpoint in certain interval (sample config for every 15 minutes)
    - `*/15 * * * *`

## TODO
 * currently do not support local validation or testing. Needs to be deployed in GCP to validate