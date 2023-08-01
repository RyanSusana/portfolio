package com.ryansusana.portfolio2;


import com.elepy.Configuration;
import com.elepy.Elepy;
import com.elepy.admin.AdminPanel;
import com.elepy.mongo.MongoConfiguration;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;

import java.io.IOException;
import java.util.Optional;


public class Main {


    public static void main(String[] args) throws IOException {

        final Configuration mongoConfig;

        var autoConfiguredOtel = AutoConfiguredOpenTelemetrySdk.initialize();
        var otel = autoConfiguredOtel.getOpenTelemetrySdk();


        if ("TEST".equals(System.getenv("environment"))) {
            mongoConfig = MongoConfiguration.inMemory();
            Tracer tracer =
                    otel.getTracer("instrumentation-library-name", "1.0.0");
            var childSpan = tracer.spanBuilder("child")
//                    .setParent(Context.current().with(parentSpan))
                    .startSpan();
            try {
                // do stuff
            } finally {
                childSpan.end();
            }
        } else {
            final var rawUri = System.getenv("DATABASE_CONNECTION_STRING");
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
