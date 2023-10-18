package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.CategoryCreateDTO;
import com.finalprojectc7t3.backend.dto.CategoryDTO;

import java.util.Set;

public interface ICategoryService {
    CategoryCreateDTO createCategory (CategoryCreateDTO categoryCreateDTO);
    CategoryDTO getCategoryById (Long id);
    Set<CategoryDTO> getAllCategories();
    CategoryDTO updateCategory (CategoryCreateDTO categoryCreateDTO);
    void deleteCategoryById (Long id);
}
