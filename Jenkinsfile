pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/myapp.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                bat 'deploy.bat'
            }
        }

        stage('Verify') {
            steps {
                sleep 5
                bat 'curl --fail http://localhost:9090/health'
            }
        }
    }

    post {
        success { echo 'Pipeline finished: app is live at http://localhost:9090' }
        failure { echo 'Pipeline failed: check the stage that turned red.' }
    }
}
