package com.finalprojectc7t3.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.CategoryCreateDTO;
import com.finalprojectc7t3.backend.dto.CategoryDTO;
import com.finalprojectc7t3.backend.service.impl.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    private ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(CategoryService.class);

    // Endpoint to create a new category
    @PostMapping
    public ResponseEntity<CategoryCreateDTO> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        CategoryCreateDTO newCategoryDTO = mapper.convertValue(
            categoryService.createCategory(categoryCreateDTO), CategoryCreateDTO.class);
        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    // Endpoint to list a category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        LOGGER.info("... ... From CategoryController class, getCategoryById method is running ... ...");
        CategoryDTO categoryDTO = mapper.convertValue(categoryService.getCategoryById(id), CategoryDTO.class);
        LOGGER.info("Controller: Category id: " + id + " has been found.");
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    // Endpoint to list categories
    @GetMapping("/list")
    public Set<CategoryDTO> getAllCategories() {
        LOGGER.info("### From CategoryController class, getAllCategories method is running ###");
        return categoryService.getAllCategories();
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateACategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        LOGGER.info("<<<<<<<<<< From CategoryController class, updateCategory method is running >>>>>>>>>>");
        CategoryDTO categoryDTO = mapper.convertValue(categoryService.updateCategory(categoryCreateDTO), CategoryDTO.class);
        LOGGER.info("Controller: Category id: " + categoryCreateDTO.getCategoryId() + " has been successfully updated.");
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteACategory(@PathVariable Long id) {
        LOGGER.info("********** From CategoryController class, deleteACategory method is running **********");
        categoryService.deleteCategoryById(id);
        LOGGER.info("Controller: Category id: " + id + " has been successfully deleted");
        return ResponseEntity.status(HttpStatus.OK).body("Category id: " + id + " has been successfully deleted.");
    }
}
