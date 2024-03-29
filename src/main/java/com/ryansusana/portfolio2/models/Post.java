package com.ryansusana.portfolio2.models;

import com.elepy.annotations.*;
import com.elepy.id.SlugIdentityProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Model(path = "/posts", name = "Posts", defaultSortField = "week")
@IdProvider(SlugIdentityProvider.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private String id;

    @FileReference
    private String image;
    private String title;

    private Integer week = 0;

    @TrueFalse(trueValue = "Show on site", falseValue = "Don't show on site")
    private Boolean show = true;

    @Label("Categories")
    private List<@Reference(to = Category.class) String> categoryIds;

    private List<Attachment> downloads;

    @HTML
    @Importance(-1)
    private String content;


} 
