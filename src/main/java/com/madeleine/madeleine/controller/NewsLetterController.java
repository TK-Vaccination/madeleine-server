package com.madeleine.madeleine.controller;

import java.util.List;

import javax.validation.Valid;

import com.madeleine.madeleine.DTO.NewsLetterDTO;
import com.madeleine.madeleine.model.NewsLetter;
import com.madeleine.madeleine.service.NewsLetterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/newsletter")
public class NewsLetterController {
    @Autowired
    private NewsLetterService newsLetterService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<NewsLetterDTO.ListResponse> listNewstletter(){
        List<NewsLetter> newsletters = newsLetterService.listNewsletter();

        return ResponseEntity.ok().body(new NewsLetterDTO.ListResponse("OK", "뉴스레터 리스트", newsletters));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<NewsLetterDTO.Response> createNewsLetter(@RequestBody @Valid NewsLetterDTO.Request newsLetterDTO){
        return ResponseEntity.ok().body(new NewsLetterDTO.Response("OK", "뉴스레터 생성 완료", null));
    }
}
