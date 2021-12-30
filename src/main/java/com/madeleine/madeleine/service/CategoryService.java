package com.madeleine.madeleine.service;

import java.util.List;
import java.util.Optional;

import com.madeleine.madeleine.model.Category;
import com.madeleine.madeleine.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }

    public void deleteCategoryById(Long categoryId) throws Exception{
        Optional<Category> opCategory = categoryRepository.findById(categoryId);
        if(!opCategory.isPresent()) throw new Exception();
        categoryRepository.delete(opCategory.get());
    }
}
