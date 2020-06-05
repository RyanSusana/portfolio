package com.ryansusana.portfolio2;

import com.elepy.ElepyExtension;
import com.elepy.ElepyPostConfiguration;
import com.elepy.annotations.Inject;
import com.elepy.dao.Crud;
import com.elepy.http.HttpContext;
import com.elepy.http.HttpService;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.ryansusana.portfolio2.models.Home;
import com.ryansusana.portfolio2.models.Page;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FrontendExtension implements ElepyExtension {

    @Inject
    private Crud<Page> pages;

    @Inject
    private Crud<Home> home;


    private PebbleEngine engine = new PebbleEngine.Builder().build();

    @Override
    public void setup(HttpService http, ElepyPostConfiguration elepy) {
        http.before(ctx -> ctx.attribute("pages", pages.getAll()));

        http.get("/", ctx -> {
            ctx.attribute("home", home.getAll().stream().findFirst().orElse(new Home()));

            setFrontend(ctx, "site/index.peb");
        });
    }

    private void setFrontend(HttpContext ctx, String template) {
        final Map<String, Object> model = new HashMap<>();
        ctx.attributes().forEach(attr -> model.put(attr, ctx.attribute(attr)));
        Writer writer = new StringWriter();
        try {
            engine.getTemplate(template).evaluate(writer, model);
        } catch (IOException e) {
            try {
                writer.write(e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        ctx.result(writer.toString());

    }
}
