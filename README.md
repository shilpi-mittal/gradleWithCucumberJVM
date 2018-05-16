# gradleWithCucumberJVM #

This is a demo framework for test suite using Selenium Webdriver , TestNG and Cucumber JVM, using Gradle.

#### Page object model 
is used to segregate page dependent information from test logic.
For each pages there should be one pageObject, listing all elements locators and the methods on those elements.

There are two modules under testModule:
*	Under functional module:
	*	Page objects are grouped at /src/main/java/pageObjects
	*	The functional tests on these pages are grouped at /src/test/java/functional
	*	Step definitions for Cucumber steps are at /src/test/java/stepDefinitions
	*	And Cucumber  feature file is at /src/test/resources/features 
*	Under TestCore module, we have utils, or say helpers, for our test cases, at /src/main/java/utils. All external methods are wrapped under utils method to save rework due to deprecated methods in upgraded versions. It also contains the driver to execute the test cases on  browser at src/test/resources

* Test cases are written in Java using Selenium Webdriver.
* To run test code, TestNG suite is written.
* Above test code a BDD layer is provided with the help of Cucumber JVM. To run the tests feature files can be executed.

### To run java test using IDE ###
*	only selenium jars are reqd.

### To compile a cucumber feature file ##
*	only cucumber-java-1.1.2.jar is reqd. 
*	make sure you have ‘cucumber for java’ plugin

### To run Cucumber scenario using IDE ###
*	cucumber-core-1.1.6.jar is reqd. and other jars too
*	go to run - edit config and add a configuration for cucumber java, set its main class as cucumber.api.cli.Main and feature/folder path as the path of feature file

# Gradle #
It's a build tool that gives you conventions but still gives you power to override them easily. Each project/sub-project has a build file, which is named as 'build.gradle'.  It is written in Groovy and specifies the dependencies required and tasks performed by the sub-project.

### to resolve dependencies ###
*	on console cd into the project and run ‘gradle build’
*	now dependencies should be resolved and you should be able to write tests

### Managing multiple build files ###
This project contains multiple sub-projects dependent on each other. Each sub-project has its own build file. To manage this,
*	mention subProjects in main build file
*	mention module in settings file, as follows
    include "testModule:functionalTests", "testModule:testCore"
*	mention dependencies on other build files
  compile the project from the dependent project’s build file, for eg in functionalTests build file add the following dependency
  compileproject(':test-modules:test-core')
  testCompileproject(':test-modules:test-core')


In build file for functionalTests module, we will mention the tasks for our test cases.
### to run testNG task ###
To run TestNG tests, we can create a task in two ways
*	Using suite.xml file
	*	Suite.xml files help to group different TestNG test cases under different suites, it is located at
	*	testNGTests task executes this suite
	*	To execute the task,
		* cd into the module, and say
		* gradle clean testNGTests
*	Using group tags
	*	One can dynamically pass the group name via command line when executing the task. Only those tests will run which have the mentioned group tagged to them. 
	*	(You can refer an example in my repo mavenWithCucumber)

### to run cucumber task ###
*	To run cucumber feature file, create a task mentioning either a feature file, or regex
*	Please refer task runCucumberScenario for the same .
*	To execute the task,
	  * cd into the module, and say
	  * gradle clean runCucumberScenario
	  
Please feel free to use it..

Suggestions, comments and improvements are welcome. Also, feel free to contact me.

Stayed tuned for Rest test repo and advanced Selenium implementations.
