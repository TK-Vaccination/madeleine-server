package com.madeleine.madeleine.DTO;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;

@Getter
public class NewsLetterDTO implements Serializable {
    private final long serialVersionUID = 48939457732L;        
    @NotBlank
    private String name;
    private String description;
    @URL
    @NotBlank
    private String subsribeUrl;
    private String imageUrl;
    private Integer count;
    @Email
    private String email;
    @URL
    private String archiveUrl;
    private List<Long> categories;
}