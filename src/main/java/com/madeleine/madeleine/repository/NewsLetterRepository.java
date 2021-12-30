package com.madeleine.madeleine.repository;

import java.util.List;

import com.madeleine.madeleine.model.NewsLetter;

import org.springframework.data.repository.CrudRepository;

public interface NewsLetterRepository extends CrudRepository<NewsLetter, Long> {
    public List<NewsLetter> findAll();    
}
