PolishNotation
====================

Polish Notation for Android. Sample application with JNI and C++ code.

Application presents conversion arithmetic statements to polish notation and reverse polish notation. As a sample of
usage, application contains simple calculator. Example polish notation is conversion from "7 / (2 3)" to "/ 7 + 2 3",
reverse polish notation is from "(2 +3) * 5" to "2 3 + 5 *".

![Screen](https://raw.github.com/wtopolski/PolishNotation/master/docs/screen_mini.png)
Build
====================

1) Add libGoogleAnalyticsV2.jar file to your maven repository from main directory of project: 
mvn install:install-file -Dfile=lib/libGoogleAnalyticsV2.jar -DgroupId=google -DartifactId=analytics -Dversion=2.0.0 
-Dpackaging=jar

2) Update property with path to keystore in main pom.xml file. 

3) Execute: mvn clean install

Features
====================

- Android 4.0
- Maven (divided into modules, Android Maven Plugin, other common plugins)
- Native Development Kit
- Screen Size Support (different layout for different size)
- Google analytics
- View animations (alpha on buttons)
- Clipboard tool (ability to copy results)
