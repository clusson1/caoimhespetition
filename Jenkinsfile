pipeline {
    agent any

    stages {
        stage ('GetProject') {
            steps {
                git 'https://github.com/clusson1/caoimhespetition.git'
            }
        }
        stage ('build') {
            steps {
                sh 'mvn clean:clean'
                sh 'mvn dependency:copy-dependencies'
                sh 'mvn compiler:compile'
            }
        }
        stage ('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage ('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                artifacts: '**/caoimhespetition*.war'
            }
        }
        stage ('Deploy') {
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker run --name "myappcontainer" -p 8080:8081 --detach myapp:latest'
            }
        }
    }
}
