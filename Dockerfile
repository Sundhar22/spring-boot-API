From openjdk:21
Maintainer mohanasundharam.com
COPY ./target/*.jar app.jar
EntryPoint ["java","-jar","app.jar"]