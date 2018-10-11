SD-4_RodkoAlexander
Before start working check that following tools are present on your environment:

Java version "1.8" http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Maven https://maven.apache.org/download.cgi
Git https://git-scm.com/downloads or https://git-for-windows.github.io/
Clone the repository:
git clone https://github.com/ncdevschool2018-2/{YOUR_REPOSITORY_NAME}.git

For example: {YOUR_REPOSITORY_NAME} - SD-1_IvanIvanov

Change directory to {YOUR_REPOSITORY_NAME} directory:
cd {YOUR_REPOSITORY_NAME}

To start your/new maven module use the following command: mvn archetype:generate -DgroupId=by.training.nc.sd1 -DartifactId=YourPrefix-Module-Name -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
You should used correct naming for java packages: by.training.nc.{YOUR_GROUP_NAME} For example by.training.nc.sd1

Also please read Java Package Naming: http://www.oracle.com/technetwork/java/codeconventions-135099.html

To avoid name collision use your initial prefix for instance the prefix for Ivan Petrov is IP

To add you module for version tracking use the command:
git add YourPrefix-Module-Name

Don't forget that only source code should be put into repository versioning

To commit your changes in local repository use:
git commit -m "initial Commit of YourPrefix-Module-Name"

To share your changes to remote repository:
git push origin master

Set up database.

Install MySql server https://dev.mysql.com/downloads/installer/
After the setup run MySQL Workbench
Helper

To install a JAR in the local repository use the following command: mvn install:install-file -Dfile= -DgroupId= 
-DartifactId= -Dversion= -Dpackaging=
Set up Tomcat 9

Download corresponding version of tomcat zip archive from https://tomcat.apache.org/download-90.cgi (e.g apache-tomcat-9.0.0.M18-windows-x64.zip)

Unzip it to /apache-tomcat-9.0.0.M18 (e.g D:\learning-program\apache-tomcat-9.0.0.M18)

Download and update content of start-tomcat.bat according to your variables In the example it is:

set JAVA_HOME=d:\jdk18

set CATALINA_HOME="D:\learning-program\apache-tomcat-9.0.0.M18"

set CATALINA_BASE=%CATALINA_HOME%

%CATALINA_HOME%\bin\catalina.bat run

Copy updated file to /apache-tomcat-9.0.0.M18

Open new cmd and execute the scenario: /apache-tomcat-9.0.0.M18>start-tomcat.bat

Tomcat should be run. To check if evething is ok go to browser and open the url: http://localhost:8080/index.jsp

Set up web application module

Go to {YOUR_REPOSITORY_NAME} directory
Open new cmd
Execute the following mvn command with your parameters: mvn archetype:generate -DgroupId=by.training.nc.sd1 -DartifactId=YOUR-PREFIX-WebApp-Module -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
