#!groovy

node {
    def gradle = "./gradlew"

    try {
        stage("Clone the project") {
            git branch: 'main', url: 'https://github.com/vitorhenriquec/purchaseapp.git'
        }

        stage("Checkout") {
            checkout scm
        }

        stage("Build Application") {
            bat "${gradle} clean build -x test"
        }

        stage("Test") {
            bat "${gradle} test"
        }

        stage("Docker build") {
            bat "docker build -t purchaseappapi ."
        }
    } catch(Exception e) {
        echo "Deployment error. Cause: ${e}"
        throw e
    }
}
