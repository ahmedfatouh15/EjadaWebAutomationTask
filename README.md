# EjadaWebAutomationTask


## Automation Testing for "Swag Labs" Web Application
The project is basically a step for applying to a QA Engineer for Ejada Co., The  application only test the login scenarios and the purchase process focusing on only the basic test cases.

## Framework
The framwork is designed in java using selenium and cucumber (gherkin), the framework supports two browsers (chrome 114 and firefox 114.0) according to the config file of the project.

## Design Pattern
The project is using the page object model.

## Concepts Included

* Parallel test runs
* Page Object pattern
* Common web page interaction methods

## Tools

* Maven
* Cucumber-JVM
* JUnit
* Selenium Webdriver
* Jackson

## Test Run
In order to run the project there are three options, 
1. Test Runners
    * There are test runner file `TestRunner.java` that can be executed as a JUnit test

2. .bat file
    * `Parallel.bat` file in the root directory is for executing the app in parallel by executing the file
3. pom file
    * `pom.xml` file in the root directory is designed to execute the app using `maven failsafe pluing` in parallel mode by running the file as `Maven Verify` or `Maven Install`
## Reporting
Reporting in the project is exported automatically to provide the tests results at the `/target` directory using mainly two reporting plugins:

    1- `Cucumber reports` that generates the reports in the `/target/cucumber` directory.
    2- `Fail safe reports` that generates the reports in the `/target/failsafe-reports` directory.
