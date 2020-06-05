package com.ryansusana.portfolio2.models;

import com.elepy.annotations.Featured;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Link {
    @Featured
    private String linkName;
    private String location;
    private String fontAwesomeIcon;
} 
