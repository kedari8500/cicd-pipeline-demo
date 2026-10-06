pipeline {
    agent any

    tools {
        jdk 'JDK27'
        maven 'Maven3'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Build & Test') {
            steps {
                bat 'mvn -B clean verify'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
    }
}
