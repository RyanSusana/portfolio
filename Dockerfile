FROM azul/zulu-openjdk-alpine:11-jre
WORKDIR /
COPY target/executable.jar app.jar
RUN wget  https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.28.0/opentelemetry-javaagent.jar -O otel.jar
CMD java -javaagent:otel.jar -jar app.jar
