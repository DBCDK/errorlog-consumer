#!groovy

def workerNode = "devel10"

pipeline {
	agent {label workerNode}
	tools {
		// refers to the name set in manage jenkins -> global tool configuration
		maven "Maven 3"
	}
	triggers {
		pollSCM("H/03 * * * *")
	}
	options {
		timestamps()
	}
	stages {
		stage("clear workspace") {
			steps {
				deleteDir()
				checkout scm
			}
		}
		stage("verify") {
			steps {
				sh "mvn -D sourcepath=src/main/java verify pmd:pmd javadoc:aggregate"
				junit "target/surefire-reports/TEST-*.xml"
			}
		}
		stage("warnings") {
			agent {label workerNode}
			steps {
				warnings consoleParsers: [
					[parserName: "Java Compiler (javac)"],
					[parserName: "JavaDoc Tool"]
				],
					unstableTotalAll: "0",
					failedTotalAll: "0"
			}
		}
		stage("pmd") {
			agent {label workerNode}
			steps {
				step([$class: 'hudson.plugins.pmd.PmdPublisher',
					  pattern: '**/target/pmd.xml',
					  unstableTotalAll: "0",
					  failedTotalAll: "0"])
			}
		}
		stage("docker build") {
			steps {
				script {
					def image = docker.build("docker-metascrum.artifacts.dbccloud.dk/errorlog-consumer:${env.BRANCH_NAME}-${env.BUILD_NUMBER}",
						"-f src/main/docker/Dockerfile --pull --no-cache .")
					image.push()
				}
			}
		}
	}
}
