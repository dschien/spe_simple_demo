# spring_demo_spe_2017

[![CircleCI](https://circleci.com/gh/dschien/spe_simple_demo.svg?style=svg)](https://circleci.com/gh/dschien/spe_simple_demo)

Simple web and REST controller + JPA

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
  