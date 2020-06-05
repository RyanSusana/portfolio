package com.ryansusana.portfolio2.models;

import com.elepy.annotations.FileReference;
import com.elepy.annotations.PrettyName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {


    @PrettyName("Email Address")
    private String email;

    @FileReference(allowedMimeType = "image/*")
    private String image;

}
