package com.madeleine.madeleine.service;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import com.madeleine.madeleine.model.NewsLetter;
import com.madeleine.madeleine.repository.NewsLetterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NewsLetterService {
    private NewsLetterRepository newsLetterRepository;
    
    @Autowired
    private UploadService uploadService;
    
    public List<NewsLetter> listNewsletter(){
        return newsLetterRepository.findAll();       
    }

    // public NewsLetter createNewsLetter(NewsLetterDTO )
    
    public NewsLetter getNewsLetterById(Long newsletterId) throws Exception {
        Optional<NewsLetter> opNewsletter = newsLetterRepository.findById(newsletterId);
        if(!opNewsletter.isPresent()) throw new NameNotFoundException();
        return opNewsletter.get();
    }

    public void deleteNewsLetterById(Long newsletterId) throws Exception {
        newsLetterRepository.deleteById(newsletterId);
    }

}