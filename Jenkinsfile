pipeline{
    
    agent any
       
    stages {
		stage('BUILD'){
            steps{
				echo "start:构建============================="
				dir('') {
				//    sh "sh ifiles/build.sh ${params.ServerDir}"
                }
                echo "end:构建============================="
            }
        }
		
		stage('PUBLISH'){
            steps{
				echo "start:发布============================="
				echo "end:发布============================="
            }
        }
		
		stage('WAIT'){
			when{
				expression{
					return currentBuild.currentResult=='SUCCESS'
				}
			}
			steps{
				sleep time: 1, unit: 'MINUTES'
			}
		}

    }
	
	post{
		failure{
			dingTalk accessToken:'f6b7946f0a6d6dc6901ea98e1df62a3f7a7dbdea7c5fe2211e33adc0f07c3b34',
			jenkinsUrl:'http://10.10.201.221:8080/',
			message:'测试环境发布失败'
		}
		always{
			allure includeProperties: false, jdk: '', results: [[path: '**/target/allure-results']]
		}
	}
		
}
