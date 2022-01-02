package com.madeleine.madeleine.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.madeleine.madeleine.DTO.NewsLetterDTO.NewsLetterRequest;
import com.madeleine.madeleine.model.Category;
import com.madeleine.madeleine.model.NewsLetter;
import com.madeleine.madeleine.repository.CategoryRepository;
import com.madeleine.madeleine.repository.NewsLetterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NewsLetterService {
    @Autowired
    private NewsLetterRepository newsLetterRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UploadService uploadService;
    
    public List<NewsLetter> listNewsletter(){
        return newsLetterRepository.findAll();       
    }
    
    public NewsLetter getNewsLetterById(Long newsletterId) throws Exception {
        NewsLetter newsletter = newsLetterRepository.findById(newsletterId).orElseThrow(() -> new NotFoundException("잘못된 뉴스레터 아이디"));
        return newsletter;
    }

    public void deleteNewsLetterById(Long newsletterId) throws Exception {
        newsLetterRepository.deleteById(newsletterId);
    }

    public NewsLetter createNewsLetter(NewsLetterRequest newsletterDTO) throws Exception {
        String imageUrl = uploadImage(newsletterDTO.getImage(), newsletterDTO.getName());
        log.info(imageUrl);
        List<Category> categories = new ArrayList<>();
        for(Long c : newsletterDTO.getCategories()){
            Category category = categoryRepository.findById(c).orElseThrow(() -> new NotFoundException("잘못된 카테고리 아이디"));
            categories.add(category);
        }
        return newsLetterRepository.save(new NewsLetter(
            newsletterDTO.getName(), 
            newsletterDTO.getDescription(), 
            newsletterDTO.getIsFree(), 
            newsletterDTO.getSubscribeUrl(), 
            imageUrl, 
            newsletterDTO.getEmail(), 
            newsletterDTO.getArchiveUrl(),
            categories));
    }

    public NewsLetter updateNewsLetter(Long newsLetterId, NewsLetterRequest newsLetterDTO) throws NotFoundException, IOException{
        NewsLetter newsLetter = newsLetterRepository.findById(newsLetterId).orElseThrow(() -> new NotFoundException("잘못된 뉴스레터 아이디"));
        newsLetter.setName(newsLetterDTO.getName());
        newsLetter.setDescription(newsLetterDTO.getDescription());
        newsLetter.setIsFree(newsLetterDTO.getIsFree());
        newsLetter.setSubscribeUrl(newsLetterDTO.getSubscribeUrl());
        if(newsLetterDTO.getImage().isEmpty()){
            uploadService.deleteFile(newsLetter.getImageUrl());
            newsLetter.setImageUrl(null);
        }else {
            String imageUrl = uploadImage(newsLetterDTO.getImage(), newsLetter.getName());
            newsLetter.setImageUrl(imageUrl);
        }
        newsLetter.setEmail(newsLetterDTO.getEmail());
        newsLetter.setArchiveUrl(newsLetterDTO.getArchiveUrl());
        List<Category> categories = new ArrayList<>();
        for(Long c : newsLetterDTO.getCategories()){
            Category category = categoryRepository.findById(c).orElseThrow(() -> new NotFoundException("잘못된 카테고리 이름"));
            categories.add(category);
        }
        newsLetter.setCategories(categories);
        return newsLetterRepository.save(newsLetter);
    }

    public List<NewsLetter> findByCategory(Long categoryId){
        return newsLetterRepository.findByCategories_Id(categoryId);
    }

    public NewsLetter findById(Long newsletterId){
        return newsLetterRepository.findById(newsletterId).orElseThrow(()->new NotFoundException("잘못된 뉴스레터 아이디"));
    }

    private String uploadImage(MultipartFile multipartFile, String name) throws IOException {
        String filename = name + "/" + createFilename(multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        try (InputStream inputStream = multipartFile.getInputStream()){
            uploadService.uploadFile(inputStream, objectMetadata, filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러 발생  %s", multipartFile.getOriginalFilename()));
        }
    
        return uploadService.getFileUrl(filename);
    }

    private String createFilename(String originalFilename){
        return UUID.randomUUID().toString().concat(getFileExtension(originalFilename));
    }
    
    private String getFileExtension(String filename){
        try {
            return filename.substring(filename.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 %s 입니다.", filename));
        }
    }
}