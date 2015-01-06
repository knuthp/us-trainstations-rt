# Microservices Trainstations Real-Time [![Build Status](https://travis-ci.org/knuthp/us-trainstations-rt.js.svg?branch=master)](https://travis-ci.org/knuthp/us-trainstations-rt.js)
This is part of the microservices architecture experiment.

This microservice polls data from http://reisapi.ruter.no/ for some stations. When new info is seen it publishes it to AMPQ exchange train.stations.rt see https://bigwig.lshift.net/management/42808/#/.


# Technology
1. Language: Java 8 Spring 4.x
2. Build: Maven
3. Deployment: Heroku
4. Tools:
  1. RabbitMQ
  2. Jackson
  

# Resources
1. Deployed Heroku version [trainstations-rt](https://trainstations-rt.herokuapp.com/)
2. Publishes to RabbitMQ Bigwig on exchange "train.stations.rt"

# Notes
## Need to keep web app in running state
In order for this to work with a free account on Heroku, the webapp need to be continously running. To accomplish this I use my own Quartz-worker on Heroku to request the homepage of trainstations-rt each minute. 