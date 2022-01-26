package com.ryansusana.portfolio2.models;

import com.elepy.annotations.FileReference;
import com.elepy.annotations.Label;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {


    @Label("Email Address")
    private String email;

    @FileReference(allowedMimeType = "image/*")
    private String image;

}
