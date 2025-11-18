pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo "Clonando repositorio..."
                git branch: 'master', url: 'https://github.com/LeanJossue/sneaker-store.git'
            }
        }

        stage('Build Backend') {
            steps {
                echo "Compilando backend con Maven Wrapper..."
                dir('sneaker-store-back') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean package -DskipTests'
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline ejecutado correctamente ✔"
        }
        failure {
            echo "Pipeline falló ❌"
        }
    }
}
