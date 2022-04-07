# Little Red
Microservice for attendance booking for small tabletop game conventions. The service uses Spring Boot as a platform, and SwaggerUI (served as /swagger-ui.html) to document itself. You will need to install java 1.8 and maven to build and start the service.
## Building the project
### Requirements
In order to build, you will need to have Maven 3.3.1 (or better) and Java 1.8 installed. Once you install them, test them with the following command:

```
mvn --version
```

This will output something like this:


```
Apache Maven 3.3.1 ([redacted hash]; 2015-03-13T13:10:27-07:00)
Maven home: /opt/apache/maven
Java version: 1.8.0_40, vendor: Oracle Corporation
Java home: /opt/java/jdk1.8.0_40/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "2.6.32-504.12.2.el6.x86_64", arch: "amd64", family: "unix"
```

you need java JDK, not just JRE. on Debian based systems the package is openjdk-8-jdk.

### Building
Once you have maven and java working properly, check out the project. Then, go to the top level directory of the project and build with the following command:

```
mvn clean package -DskipTests
```

The `-DskipTests` flag at the end is important, because if you don't have access to the database, and your properties file isn't properly set up, the tests will fail, failing the entire build.
If you do have access to the database, just omit the `-DskipTests` flag.

The build command will create an artifact in the `/target` directory of your checkout of the repo. The artifact to use is named `dataservices-[version number]-SNAPSHOT.jar`. Copy this file to wherever you're going to keep the jar while it runs. It doesn't really matter where it is, as long as there's a valid config file for it.  See the config section below.

## Runtime
### Configuration file
Once you've copied the jar file built by maven, create a new file in that directory named `application.properties`.  In that file, place the following content:

```
server.port=8091
# Database
db.driver=com.mysql.jdbc.Driver
db.url=jdbc:mysql://[url of db host]:[mysql port]/[database name]?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&tinyInt1isBit=false
db.username=[redacted]
db.password=[redacted]

# Hibernate
hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
hibernate.show_sql=true
hibernate.hbm2ddl.auto=validate
entitymanager.packagesToScan=org.littlered.dataservices.entity
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect

# Logging
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate=ERROR

# runtime properties
# For testing, override current year filtering (for when there are no games for the current year yet)
display.year.filter=2017

display.timezone=America/Los_Angeles
bookings.quota=2

# email configurations
spring.mail.host=[whatever]
spring.mail.username=[redacted]
spring.mail.password=[redacted]
spring.mail.port=25

```

### Executing the jar file
Once you have the jar file and the properties file in the same place, you can execute it with the following command:

```
java -jar [path to directory with jar file]/dataservices-[version]-SNAPSHOT.jar
```

This should start up the service with a colorful display. :)

Once it's up and running, it will be listening on whatever port you set in the properties file.  The default in this example is 8091.

### Troubleshooting
```
Exception in thread "main" java.lang.UnsupportedClassVersionError: org/littlered/dataservices/DataservicesApplication : Unsupported major.minor version 52.0
```
If you get an error that resembles this when you try to start the service, you are using the wrong version of java.  It's possible to install multiple versions on a machine, so make sure you're using the 1.8 java executable.

## Documentation
The data service uses Swagger UI to self-document the code. This generates a set of HTML files describing the data service that it makes available via the service itself. To access the documentation, navigate your browser to:

```
http://[url]:[port]/swagger-ui.html
```

Port is the port of the service, 8091 in this example.
