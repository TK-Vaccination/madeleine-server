package com.madeleine.madeleine.controller;

import java.util.List;

import javax.validation.Valid;

import com.madeleine.madeleine.DTO.NewsLetterDTO;
import com.madeleine.madeleine.DTO.ResponseDTO;
import com.madeleine.madeleine.model.NewsLetter;
import com.madeleine.madeleine.service.NewsLetterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<NewsLetterDTO.Response> createNewsLetter(@Valid @ModelAttribute NewsLetterDTO.Request newsLetterDTO){
        try {
            NewsLetter newsLetter = newsLetterService.createNewsLetter(newsLetterDTO);    
            return ResponseEntity.ok().body(new NewsLetterDTO.Response("OK", "뉴스레터 생성 완료", newsLetter));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new NewsLetterDTO.Response("ERROR", "뉴스레서 생성 실패", null));
        }
    }

    @RequestMapping(value = "/{newsletterId}/", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteNewsletter(@PathVariable Long newsletterId){
        try{
            newsLetterService.deleteNewsLetterById(newsletterId);
            return ResponseEntity.ok().body(new ResponseDTO("OK","뉴스레터 삭제 성공"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseDTO("ERROR", "잘못된 뉴스레터 아이디"));
        }
    }
}
