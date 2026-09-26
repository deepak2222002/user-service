pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t user-service .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker stop user-service || true
                    docker rm user-service || true

                    docker run -d \
                    --name user-service \
                    --network backend_default \
                    -p 8092:8092 \
                    -e DB_URL="jdbc:sqlserver://sqlserver:1433;databaseName=jobportal;trustServerCertificate=true" \
                    -e DB_USERNAME="remoteuser" \
                    -e DB_PASSWORD="remote@321" \
                    -e KAFKA_BOOTSTRAP_SERVERS="kafka:9092" \
                    user-service
                '''
            }
        }

    }
}