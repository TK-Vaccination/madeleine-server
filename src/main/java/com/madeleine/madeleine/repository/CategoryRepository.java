package com.madeleine.madeleine.repository;

import java.util.List;

import com.madeleine.madeleine.model.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    List<Category> findAll();
}
