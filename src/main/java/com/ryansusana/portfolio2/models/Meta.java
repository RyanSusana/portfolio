package com.ryansusana.portfolio2.models;

import com.elepy.annotations.FileReference;
import com.elepy.annotations.Label;
import com.elepy.annotations.TextArea;
import com.elepy.models.TextType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    @Label("Site Title")
    private String title;

    @Label("Site Subtitle")
    private String subtitle;

    @FileReference
    private String image;

    @Label("Site Author")
    private String author;

    @TextArea
    @Label("Site Description")
    private String description;

    @Label("Site Keywords")
    private String keywords;
}
