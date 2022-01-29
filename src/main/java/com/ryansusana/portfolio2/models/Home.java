package com.ryansusana.portfolio2.models;

import com.elepy.annotations.FileReference;
import com.elepy.annotations.Model;
import com.elepy.annotations.View;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@View(View.Defaults.SINGLE)
@Getter
@Setter
@Model(name = "Home", path = "/settings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Home {
    private String id;


    private String title;
    private String subtitle;

    @FileReference
    private String image;

    @FileReference(allowedMimeType = "application/pdf")
    private String cv;

    private Meta meta;
    private Contact contact;

    private List<Link> links;

    private String copyright;
} 
