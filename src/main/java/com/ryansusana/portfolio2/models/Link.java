package com.ryansusana.portfolio2.models;

import com.elepy.annotations.Featured;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {
    @Featured
    private String linkName;
    private String location;
    private String fontAwesomeIcon;
} 
