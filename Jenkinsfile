pipeline {
    agent any

    tools {
        maven 'Maven3'
        nodejs 'Node18'
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Clonando repositorio..."
                git branch: 'master', url: 'https://github.com/LeanJossue/sneaker-store.git'
            }
        }

        stage('Build Backend') {
            steps {
                echo "Compilando backend con Maven..."
                dir('sneaker-store-back') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                echo "Compilando frontend con npm..."
                dir('sneaker-store-front') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

    }

    post {
        success {
            echo "Pipeline ejecutado correctamente ✔"
        }
        failure {
            echo "Pipeline falló "
        }
    }
}
