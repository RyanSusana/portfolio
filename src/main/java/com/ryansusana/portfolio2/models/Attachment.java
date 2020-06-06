package com.ryansusana.portfolio2.models;

import com.elepy.annotations.Featured;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {
    @Featured
    private String name;
    private String file;
} 
