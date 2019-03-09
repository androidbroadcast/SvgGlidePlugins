#!/usr/bin/env bash
./gradlew sonarqube \
  -Dsonar.projectKey=$SONAR_PROJECT_KEY \
  -Dsonar.organization=$SONAR_ORGANIZATION \
  -Dsonar.host.url=$SONAR_HOST_URL \
  -Dsonar.login=$SONAR_LOGIN \
  -Dsonar.branch.name=$TRAVIS_BRANCH