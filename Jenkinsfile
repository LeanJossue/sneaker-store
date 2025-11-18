pipeline {

    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    environment {
        BACK_IMAGE = "sneaker-back"
        FRONT_IMAGE = "sneaker-front"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Clonando repositorio completo..."
                git branch: 'master', url: 'https://github.com/LeanJossue/sneaker-store.git'
            }
        }

        stage('Build Backend with Maven') {
            steps {
                echo "Compilando backend..."
                dir('sneaker-store-back') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                echo "Construyendo imagen Docker del backend..."
                dir('sneaker-store-back') {
                    sh 'docker build -t ${BACK_IMAGE}:latest .'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                echo "Construyendo frontend..."
                dir('sneaker-store-front') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Build Frontend Docker Image') {
            steps {
                echo "Construyendo imagen Docker del frontend..."
                dir('sneaker-store-front') {
                    sh 'docker build -t ${FRONT_IMAGE}:latest .'
                }
            }
        }

        stage('Run Containers') {
            steps {
                echo "Iniciando contenedores del backend y frontend..."

                sh 'docker stop sneaker-back-test || true'
                sh 'docker rm sneaker-back-test || true'
                sh 'docker stop sneaker-front-test || true'
                sh 'docker rm sneaker-front-test || true'

                sh 'docker run -d --name sneaker-back-test -p 8085:8080 ${BACK_IMAGE}:latest'
                sh 'docker run -d --name sneaker-front-test -p 8086:80 ${FRONT_IMAGE}:latest'
            }
        }

        stage('Verify Containers') {
            steps {
                echo "Verificando contenedores..."
                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo "Pipeline ejecutado correctamente BACK + FRONT OK"
        }
        failure {
            echo "Pipeline falló"
        }
    }
}
