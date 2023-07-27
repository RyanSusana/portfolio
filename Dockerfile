FROM openjdk:17-alpine
WORKDIR /
COPY target/executable.jar app.jar
CMD java -jar app.jar
