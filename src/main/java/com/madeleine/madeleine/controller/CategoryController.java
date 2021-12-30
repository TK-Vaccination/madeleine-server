package com.madeleine.madeleine.controller;

import javax.validation.Valid;

import com.madeleine.madeleine.DTO.CategoryDTO;
import com.madeleine.madeleine.DTO.ResponseDTO;
import com.madeleine.madeleine.model.Category;
import com.madeleine.madeleine.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO.Response> createCategory(@RequestBody @Valid CategoryDTO.Request categoryDTO){
        Category category = categoryService.createCategory(new Category(categoryDTO.getCategory()));
        return ResponseEntity.ok().body(new CategoryDTO.Response("OK", "카테고리 생성 성공", category));
    }

    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity<CategoryDTO.ListResponse> listCategories() {
        log.info("list category");
        return ResponseEntity.ok().body(new CategoryDTO.ListResponse("OK", "카테고리 리스트", categoryService.listCategories()));
    }

    @RequestMapping(value="/{categoryId}/", method=RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> requestMethodName(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO("ERROR", "잘못된 카테고리 ID"));
        }
        return ResponseEntity.ok().body(new ResponseDTO("OK", "카테고리 삭제 성공"));
    }
    
}
