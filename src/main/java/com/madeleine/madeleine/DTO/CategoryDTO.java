package com.madeleine.madeleine.DTO;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.madeleine.madeleine.model.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CategoryDTO{
    @Setter
    @Getter
    @NoArgsConstructor
    public static class CategoryRequest {
        @NotBlank
        private String category;
    }

    @Getter
    public static class ListCategoryResponse extends ResponseDTO {
        private List<Category> body;
        
        public ListCategoryResponse(String code, String message, List<Category> body){
            super(code, message);
            this.body = body;
        }
    }

    @Getter
    public static class CategoryResponse extends ResponseDTO {
        private Category body;
        public CategoryResponse(String code ,String message, Category body){
            super(code, message);
            this.body = body;
        }
    }
}  
