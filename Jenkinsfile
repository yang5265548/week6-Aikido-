pipeline{
    agent any
    stages{
      stage('checking'){
        steps{
          git branch:'main',url:'https://github.com/yang5265548/week6-Aikido-.git'
        }
      }
      stage('build'){
        steps{
          bat 'mvn clean install'
        }
      }
        stage('Test & coverage'){
            steps{
                bat 'mvn clean install'
            }
            post{
                always{
                    junit 'target/surefire-reports/*.xml'
                    jacoco execPattern:'**/target/jacoco.exec',
                        classPattern:'**/target/classes',
                        sourcePattern:'**/src/main/java',
                        exclusionPattern:'**/test/**'
                }
            }
        }
    }
}
