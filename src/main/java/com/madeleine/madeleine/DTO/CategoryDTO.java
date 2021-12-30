package com.madeleine.madeleine.DTO;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class CategoryDTO{
    @NotBlank
    private String category;
}  
