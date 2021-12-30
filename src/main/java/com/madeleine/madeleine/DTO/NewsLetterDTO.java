package com.madeleine.madeleine.DTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.madeleine.madeleine.model.NewsLetter;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;

public class NewsLetterDTO {
    @Getter
    public static class Request{
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

    @Getter
    public static class Response extends ResponseDTO {
        private NewsLetter body;
        public Response(String code, String message, NewsLetter body){
            super(code, message);
            this.body = body;
        }
    }

    @Getter
    public static class ListResponse extends ResponseDTO {
        private List<NewsLetter> body;
        public ListResponse(String code, String message, List<NewsLetter> body){
            super(code, message);
            this.body = body;
        }
    }
}