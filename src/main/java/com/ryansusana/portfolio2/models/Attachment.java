package com.ryansusana.portfolio2.models;

import com.elepy.annotations.Featured;
import com.elepy.annotations.FileReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment {
    @Featured
    private String name;

    @FileReference
    private String file;
} 
