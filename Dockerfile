FROM azul/zulu-openjdk-alpine:11-jre
WORKDIR /
COPY target/executable.jar app.jar
CMD java -jar app.jar
