package com.madeleine.madeleine.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.madeleine.madeleine.DTO.NewsLetterDTO;
import com.madeleine.madeleine.model.NewsLetter;
import com.madeleine.madeleine.repository.NewsLetterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NewsLetterService {
    @Autowired
    private NewsLetterRepository newsLetterRepository;
    
    @Autowired
    private UploadService uploadService;
    
    public List<NewsLetter> listNewsletter(){
        return newsLetterRepository.findAll();       
    }
    
    public NewsLetter getNewsLetterById(Long newsletterId) throws Exception {
        Optional<NewsLetter> opNewsletter = newsLetterRepository.findById(newsletterId);
        if(!opNewsletter.isPresent()) throw new NameNotFoundException();
        return opNewsletter.get();
    }

    public void deleteNewsLetterById(Long newsletterId) throws Exception {
        newsLetterRepository.deleteById(newsletterId);
    }

    public NewsLetter createNewsLetter(NewsLetterDTO.Request newsletterDTO) throws Exception {
        String imageUrl = uploadImage(newsletterDTO.getImage(), newsletterDTO.getName());
        log.info(imageUrl);
        return newsLetterRepository.save(new NewsLetter(
            newsletterDTO.getName(), 
            newsletterDTO.getDescription(), 
            newsletterDTO.getIsFree(), 
            newsletterDTO.getSubscribeUrl(), 
            imageUrl, 
            newsletterDTO.getEmail(), 
            newsletterDTO.getArchiveUrl()));
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