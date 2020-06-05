package com.ryansusana.portfolio2.models;

import com.elepy.annotations.FileReference;
import com.elepy.annotations.PrettyName;
import com.elepy.annotations.TextArea;
import com.elepy.models.TextType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {
    @PrettyName("Site Title")
    private String title;

    @PrettyName("Site Subtitle")
    private String subtitle;

    @FileReference
    private String image;

    @PrettyName("Site Author")
    private String author;

    @TextArea
    @PrettyName("Site Description")
    private String description;

    @PrettyName("Site Keywords")
    private String keywords;
}
