package com.ryansusana.portfolio2;


import com.elepy.Configuration;
import com.elepy.Elepy;
import com.elepy.admin.AdminPanel;
import com.elepy.mongo.MongoConfiguration;
import com.google.cloud.opentelemetry.trace.TraceExporter;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;

import java.io.IOException;
import java.util.Optional;

public class Main {


    public static void main(String[] args) throws IOException {

        final Configuration mongoConfig;

        if ("TEST".equals(System.getenv("environment"))) {
            mongoConfig = MongoConfiguration.inMemory();
        } else {
            TraceExporter traceExporter = TraceExporter.createWithDefaultConfiguration();
            OpenTelemetrySdk.builder()
                    .setTracerProvider(
                            SdkTracerProvider.builder()
                                    .addSpanProcessor(BatchSpanProcessor.builder(traceExporter).build())
                                    .build())
                    .buildAndRegisterGlobal();
            String username = System.getenv("DATABASE_USERNAME");
            String password = System.getenv("DATABASE_PASSWORD");
            String server = System.getenv("DATABASE_SERVER");
            final var rawUri = String.format("mongodb+srv://%s:%s@%s", username, password, server);
            System.out.println(rawUri);
            MongoClientURI uri = new MongoClientURI(rawUri);


            MongoClient mongoClient = new MongoClient(uri);

            mongoConfig = MongoConfiguration.of(mongoClient, "portfolio", "files");

        }


        final Elepy elepy = new Elepy()
                .addConfiguration(AdminPanel.local())
                .addConfiguration(mongoConfig)
                .addModelPackage("com.ryansusana.portfolio2")
                .withPort(Integer.valueOf(Optional.ofNullable(System.getenv("PORT")).orElse("80")))
                .addExtension(new FrontendExtension());

        elepy.http().staticFiles("site");

        elepy.start();

    }
}
