pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn compile'
            }
        }
         stage('Static Analysis') {
                    steps {
                        bat 'mvn checkstyle:check'
                    }
                }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Validate') {
            steps {
                bat 'mvn validate'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }
}