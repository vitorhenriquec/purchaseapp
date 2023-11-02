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
            sh "${gradle} clean build -x test"
        }

        stage("Test") {
            sh "${gradle} test"
        }
    } catch(Exception e) {
        echo "Deployment error. Cause: ${e}"
        throw e
    }
}
