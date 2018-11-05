API test exercise using BDD Framework
=====================================
#resources -> todos.feature
This module contains the cucumber feature file. This makes use of the api_test_exercise to execute the scenarios.

#todos.exercise.cucumber.steps -> TodosSteps.java
This class contains the steps associated with the cucumber feature. Each step in the cucumber feature file is embedded
to a method in this class.

#todos.exercise.cucumber -> CucumberRunner.java
In addition to running a cucumber feature file, the test runner class also acts as an interlink between feature file 
and step definition class. It is in test runner class, which has the path for both feature file and step defs class.

#todos.exercise.cucumber -> TodosConfiguration.java
The todos API URI endpoints are configured in this class

#todos.exercise.cucumber -> TodosDefinition.java
This class contains method definitions used to perform the CRUD operation for todos endpoint

#spotlessApply
This is a general-purpose formatting plugin. Execute the below command when ever the code is modified
$ gradlew spotlessApply

#How to run this project
Prerequisites
- Install Cucumber plugin in intellij
- JDK 1.8 or above

Navigate to the cucumber feature file todos.feature, right click and select Run 'Feature:todos'

#Reports
After execution of the scenarios, gradle test results are saved under the below path
api_test_exercise -> build -> reports -> tests -> test -> index.html
