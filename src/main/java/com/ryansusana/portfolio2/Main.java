package com.ryansusana.portfolio2;


import com.elepy.Configuration;
import com.elepy.Elepy;
import com.elepy.admin.AdminPanel;
import com.elepy.mongo.MongoConfiguration;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.Optional;

public class Main {


    public static void main(String[] args) {

        final Configuration mongoConfig;

        if ("TEST".equals(System.getenv("environment"))) {
            mongoConfig = MongoConfiguration.inMemory();
        } else {
            String username = System.getenv("DATABASE_USERNAME");
            String password = System.getenv("DATABASE_PASSWORD");
            String server = System.getenv("DATABASE_SERVER");
            MongoClientURI uri = new MongoClientURI(String.format("mongodb+srv://%s:%s@%s", username, password, server));

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
