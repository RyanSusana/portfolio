package com.ryansusana.portfolio2.models;

import com.elepy.annotations.*;
import com.elepy.id.SlugIdentityProvider;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@IdProvider(SlugIdentityProvider.class)
@Getter
@Setter
@Model(name = "Pages", path = "/pages")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {

    @JsonAlias("id")
    @Identifier
    private String id;

    @FileReference(allowedMimeType = "image/*")
    private String image;

    private String title;
    private String subtitle;

    @HTML
    private String content;

} 
