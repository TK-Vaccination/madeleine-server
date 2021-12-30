package com.madeleine.madeleine.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class CategoryDTO implements Serializable{
    private final long serialVersionUID = 40428328550L;
    @NotBlank
    private String category;
}  
