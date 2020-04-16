# Unity

Unity is a web app, where people can help each other.


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)

## Technology
Backend: Java, Spring Framework(Spring Boot, Spring MVC), REST, Java Utility Libraries, Amazon S3, H2 Database.

## How to launch?

Install Intellij Idea. Go to JetBrains site for that.

Install JDK 8: https://www.oracle.com/java/technologies/javase-jdk8-downloads.html

Install Lombok plugin: on the control panel choose File -> Settings -> Plugins. Then choose Lombok Plugin.

To launch the program run the main method in UnityApplication class.

Program starts on localhost, port: 8080. Swagger API support added. Link to switch to Swagger: localhost:8080/swagger-ui.html.

Application uses in-memory database H2. It is accessible with the next link: localhost:8080/h2db.

To build *.jar you can use the Maven command line:

`mvn package`

To build the image you can use the Docker command line:

`docker build -t springio/gs-spring-boot-docker .`

To run container you can use the Docker command line:

`docker run springio/gs-spring-boot-docker`
