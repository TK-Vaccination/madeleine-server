package com.madeleine.madeleine.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Email
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String authority;
    private Boolean isEnabled;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<NewsLetter> subscribes;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    public User(@NotBlank String username, @NotBlank String password, String authority){
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.isEnabled = true;
    }
}
