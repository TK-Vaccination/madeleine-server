package com.madeleine.madeleine.DTO;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.madeleine.madeleine.model.NewsLetter;

import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

public class NewsLetterDTO {
    @Setter
    @Getter
    public static class NewsLetterRequest{
        @NotBlank
        private String name;
        private String description;
        @URL
        @NotBlank
        private String subscribeUrl;
        private MultipartFile image;
        private Boolean isFree;
        private Integer count;
        @Email
        private String email;
        @URL
        private String archiveUrl;
        private List<Long> categories;
    }

    @Getter
    public static class NewsLetterResponse extends ResponseDTO {
        private NewsLetter body;
        public NewsLetterResponse(String code, String message, NewsLetter body){
            super(code, message);
            this.body = body;
        }
    }

    @Getter
    public static class ListNewsLetterResponse extends ResponseDTO {
        private List<NewsLetter> body;
        public ListNewsLetterResponse(String code, String message, List<NewsLetter> body){
            super(code, message);
            this.body = body;
        }
    }
}