pipeline {
        agent none
        stages {
         
          stage("build & sonarqube") {
            agent any
            steps {
              withSonarQubeEnv('sonarqube_server') {
                sh 'mvn clean package sonar:sonar'
              }
            }
          }
        }
      }