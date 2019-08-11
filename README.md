
# church-people
**Build Status:**
[![Build Status](https://travis-ci.com/rowanpi/church-people.svg?branch=master)](https://travis-ci.com/rowanpi/church-people)
**Code Coverage Status:**
[![codecov](https://codecov.io/gh/rowanpi/church-people/branch/master/graph/badge.svg)](https://codecov.io/gh/rowanpi/church-people)

A church people API

### Local Dev Environment
To build: `gradlew build`

To run: `gradlew bootRun`

### Docker Environment
1. Change the host name part of the database url in application.properties from 'localhost' to 'mysql' (the name of the docker container).
2. Build the church-people image: `sudo docker build . -t church-people`
3. Bring up the containers with docker-compose: `sudo docker-compose up -d`
4. Access API in port 8081 (as configured in the docker-compose.yml)