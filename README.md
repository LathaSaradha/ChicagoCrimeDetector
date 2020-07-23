# ChicagoCrimeDetector

Instructions to run the Project

Project Description: 
Crime Detector is a systematic approach for identifying the patterns and trends of crimes happened in Chicago since 2001.

## Installation
Install your choice of IDE, which supports Java 11. Install the Java 11 package according to the system specifications. The following guidelines are based on IDE IntelliJ and Java 11.

Link to download Intellij (one of the IDE) -https://www.jetbrains.com/idea/
Link to download Java11- https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
Change the JAVA_HOME environment variable to that of JAVA 11 JDK.

## Usage
Download all the java files in this repository as a zipped file. Unzip the folder in your local.

Open IntelliJ. Choose ”Import project” and select the unzipped project ?le folder (the one that contains all of the ?les). 
Choose ”Import project from external model”, select Gradle and click Next. 
The Gradle project name/location should be ?lled in of your choice.

The checkbox ”Create separate module per source set” should be checked and the radio button for ”Use default Gradle wrapper (recommended)” should also be selected. Make sure that the Gradle JVM isusingJava11. 

If not, you will either need to select that version or navigate to the JDK and ?nd it (see possible locations below): 

Mac users: /Library/Java/JavaVirtualMachines/jdk-11.0.4.jdk/Contents/Home Windows users (probably): C:\Program Files\Java\jdk-11\bin” 
Click Finish.

Following message will be displayed in the terminal in IntelliJ. Click “Import Gradle changes”. 

All of the above changes will be imported.

To enable ‘Gradle’ as the TestRunner, In Mac go to Intellij Idea -> Preferences. (next to File on the left), in windows go to File-> Settings. Then go to Build, Execution, and Deployment. Click on Gradle. Look for Delegate settings (beneath Gradle JVM). Choose Build and run using Gradle (not IntelliJ Idea) and choose Run tests using Gradle (not IntelliJ Idea). Click ok. 

To view your project directory structure, go to View?Tool Windows?Project. You will see your project directory structure on the left-hand side of the screen.

 All the Java code/?les must be created inside the src?main?java folder.

“Main.java” file is the java file that contains the ‘main’ method for the application.

In the build.gradle file, following dependencies, need to be added:
? testCompile group: 'junit', name: 'junit', version: '4.12'
? compile 'com.squareup.okhttp3:okhttp:3.4.1'
? compile group: 'com.google.code.gson', name: 'gson', version: '2.7'
Also in the plugins pls add the following
? id 'java'
? id 'application'
? id 'org.openjfx.javafxplugin' version '0.0.8'

Gradle-wrapper.properties must be added the following line
1. distributionUrl=https\://services.gradle.org/distributions/gradle-5.6.2-all.zip

To run the application Click on the Gradle button available on the left side of Intellij. A new menu will be displayed. Expand the drop-down menu.

Select
Tasks ->application -> run.

The application will be run successfully.

## Running the tests

In our application, the testing is done by the user.
Run the application as specified above instructions.
The below dialogue box will be displayed.


To predict the total number of crimes in 2020 select the first button.
The results are shown in bar graph form as below.


To view, the pie chart of arrests for the top 5 crime types in Chicago click the second button.

The results will be as below.


To view the crime rate in a particular district and particular year select third button.


The result will be shown as below.

For invalid inputs, the warning message will be as follows.


## Authors

Anju Shrestha
Latha Saradha
Sai Krishna

## Acknowledgements/Reference
? Chicago Data Portal,
       https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-present/ijzp-q8t2/data
? SODA Developers, Crimes - 2001 to Present, https://dev.socrata.com/foundry/data.cityofchicago.org/ijzp-q8t2
? SODA Developers, API Endpoints,https://dev.socrata.com/docs/endpoints.html 
? 



