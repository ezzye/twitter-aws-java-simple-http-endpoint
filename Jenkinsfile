pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo "Hello This is Your World"'
                sh '''
                    echo "Multiline shell steps works too - well sort of"
                    ls -lah
                '''
            }
        }
    }
}