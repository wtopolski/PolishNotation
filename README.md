PolishNotation
====================

Polish Notation for Android. Sample application with JNI and C++ code.

Application presents conversion arithmetic statements to polish notation and reverse polish notation. As a sample of
usage, application contains simple calculator. Example polish notation is conversion from "7 / (2 3)" to "/ 7 + 2 3",
reverse polish notation is from "(2 +3) * 5" to "2 3 + 5 *".

![Screen](https://raw.github.com/wtopolski/PolishNotation/master/docs/device-2012-12-31-094307.png)

Build
====================

1) Add libGoogleAnalyticsV2.jar file to your maven repository from main directory of project: 
mvn install:install-file -Dfile=lib/libGoogleAnalyticsV2.jar -DgroupId=google -DartifactId=analytics -Dversion=2.0.0 
-Dpackaging=jar

2) Execute: mvn clean install
