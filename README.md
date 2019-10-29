# spring_demo_spe_2017

[![CircleCI](https://circleci.com/gh/dschien/spe_simple_demo.svg?style=svg)](https://circleci.com/gh/dschien/spe_simple_demo)

Simple web and REST controller + JPA

# Run
This application can be run in the host or in docker.

The most simple way is to run in 'dev' mode: 
`SPRING_PROFILES_ACTIVE=dev mvn spring-boot:run`
This uses an in-memory DB and so avoids a dependency on an external DB. 

Application config files for 'dev' and 'prod' more are defined in `src/main/resources/`.

The (default) 'prod' version requires a postgres db running. In production mode (profile "prod"), with pg server running on port 5432 on server `${dbhost}` (see `src/main/resources/default.properties`):

` db_password=XXX mvn spring-boot:run`

(XXX being the password to your postgres server with the db grottodb and username santa (see `src/main/resources/application-prod.yml`)).



# Build and run with docker
First install docker, of course.

`mvn clean package dockerfile:build`

Run in docker:
`docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8888:8080 dschien/spe-simple-demo:latest`

Or with postgres in 'pg' container
`docker run --link pg -e "SPRING_PROFILES_ACTIVE=prod" -e "db_password=XXX" -p 8888:8080 dschien/spe-simple-demo:latest`

see also [here](https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-running-your-application)

# Java Versions
In order to use Java >8 you need Spring Boot >2.0 in your pom file. This can trip you up.

Normally everything just works. If you do need to manually set Java versions, 
it is possible to define the Java version in the POM. E.g. to use Java 8, include the below.

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

This works if you compile with `mvn ...` on the command line.
IntelliJ also reads this configuration and adjust IDE settings.    

Additionally, IntelliJ keeps Java version settings in the
 
1. "Project Settings" in the menu go {File > Settings} 
(called "Preferences"  Java lang level can set OSXpe -).
There, under "Build, Execution, Deployment > Compiler > Java Compiler" Java lang level can set custom per module.
 
2. Module Settings (right click in File Navigator)
There, in the "Project Settings" under "Project" and "Modules"
  