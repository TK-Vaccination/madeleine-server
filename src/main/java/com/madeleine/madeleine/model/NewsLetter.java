package com.madeleine.madeleine.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class NewsLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private String description;
    private Boolean isFree = true;
    @NotNull
    private String subscribeUrl;
    private String imageUrl;
    private Integer count;
    private String email;
    private String archiveUrl;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

    public NewsLetter(@NotBlank String name, String description, Boolean isFree, @NotBlank String subscribeUrl, String imageUrl, String email, String archiveUrl, List<Category> categoires){
        this.name = name;
        this.description = description;
        if(isFree == null)  this.isFree = true;
        else this.isFree = isFree;
        this.subscribeUrl = subscribeUrl;
        this.imageUrl = imageUrl;
        this.count = 0;
        this.email = email;
        this.archiveUrl = archiveUrl;
        this.categories = categoires;
    }
}
