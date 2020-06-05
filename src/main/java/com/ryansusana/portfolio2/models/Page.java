package com.ryansusana.portfolio2.models;

import com.elepy.annotations.*;
import com.elepy.id.SlugIdentityProvider;
import lombok.Getter;
import lombok.Setter;


@IdProvider(SlugIdentityProvider.class)
@Getter
@Setter
@Model(name = "Pages", path = "/pages")
public class Page {
    private String id;

    @FileReference(allowedMimeType = "image/*")
    private String image;

    private String title;
    private String subtitle;

    @HTML
    private String content;

} 
