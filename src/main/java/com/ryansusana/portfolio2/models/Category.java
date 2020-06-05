package com.ryansusana.portfolio2.models;

import com.elepy.annotations.*;
import com.elepy.id.SlugIdentityProvider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Model(name = "Categories", path = "/categories")
@IdProvider(SlugIdentityProvider.class)
public class Category {
    private String id;

    private Boolean main = true;

    @Featured
    @Searchable
    private String name;

    @TextArea
    private String description;
} 
