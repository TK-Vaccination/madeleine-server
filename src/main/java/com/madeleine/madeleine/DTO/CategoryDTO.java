package com.madeleine.madeleine.DTO;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.madeleine.madeleine.model.Category;

import lombok.Getter;

public class CategoryDTO{
    @Getter
    public static class Request {
        @NotBlank
        private String category;
    }

    @Getter
    public static class ListResponse extends ResponseDTO {
        private List<Category> body;
        
        public ListResponse(String code, String message, List<Category> body){
            super(code, message);
            this.body = body;
        }
    }

    @Getter
    public static class Response extends ResponseDTO {
        private Category body;
        public Response(String code ,String message, Category body){
            super(code, message);
            this.body = body;
        }
    }
}  
