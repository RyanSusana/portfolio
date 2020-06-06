package com.ryansusana.portfolio2.models;

import com.elepy.annotations.*;
import com.elepy.id.SlugIdentityProvider;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Model(path = "/posts", name = "Posts", defaultSortField = "week")
@IdProvider(SlugIdentityProvider.class)
@Action(handler = ZipPostAttachments.class, requiredPermissions = "", name = "Download Attachments", path = "/download-attachments")
public class Post {
    private String id;

    @FileReference
    private String image;
    private String title;

    private Integer week = 0;

    @TrueFalse(trueValue = "Show on site", falseValue = "Don't show on site")
    private Boolean show = true;

    @PrettyName("Categories")
    private List<@Reference(to = Category.class) String> categoryIds;

    private List<@FileReference String> attachments;

    @HTML
    @Importance(-1)
    private String content;


} 
