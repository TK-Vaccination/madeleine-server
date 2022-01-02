package com.madeleine.madeleine.DTO;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.madeleine.madeleine.model.CustomUserDetails;
import com.madeleine.madeleine.model.NewsLetter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDTO {
    @Setter
    @Getter
    @NoArgsConstructor
    public static class UserRequest {
        @Email
        private String username;
        @NotBlank
        private String password;
    }    

    @Getter
    @Setter
    @AllArgsConstructor
    private static class UserInfo {
        private Long id;
        private String username;
        private String authority;
        private Boolean isEnabled;
        private Set<NewsLetter> subscribes;
    }

    @Getter
    public static class UserResponse extends ResponseDTO{
        private UserInfo body;
        public UserResponse(String code, String message, CustomUserDetails user){
            super(code, message);
            this.body = new UserInfo(user.getId(), user.getUsername(), user.getAuthorities().toString(), user.isEnabled(), user.getSubscribes());
        }
    }
}
