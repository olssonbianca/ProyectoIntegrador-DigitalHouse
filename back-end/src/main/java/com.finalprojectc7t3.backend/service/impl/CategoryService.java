package com.finalprojectc7t3.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.CategoryCreateDTO;
import com.finalprojectc7t3.backend.dto.CategoryDTO;
import com.finalprojectc7t3.backend.entity.Category;
import com.finalprojectc7t3.backend.repository.ICategoryRepository;
import com.finalprojectc7t3.backend.service.ICategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService implements ICategoryService {

    private static final Logger LOGGER = Logger.getLogger(CategoryService.class);

    private ICategoryRepository iCategoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private Validator validator;

    public CategoryCreateDTO createCategory(CategoryCreateDTO categoryCreateDTO){
        LOGGER.info("... ... From CategoryService class, createCategory method is running ... ...");
        Category newCategoryToSave = mapper.convertValue(categoryCreateDTO, Category.class);
        CategoryCreateDTO categoryDTOsaved = mapper.convertValue(
                iCategoryRepository.save(newCategoryToSave), CategoryCreateDTO.class);
        LOGGER.info("Creating a new category: " + categoryDTOsaved.getCategoryName());
        return categoryDTOsaved;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        LOGGER.info("... ... From CategoryService class, getCategoryById method is running ... ...");
        Optional<Category> categoryEntityFound = iCategoryRepository.findById(id);
        CategoryDTO categoryDTOFound = null;

        if(!categoryEntityFound.isPresent()){
            LOGGER.info("Service: ResourceNotFoundException was thrown trying to get a category which is null, category id: " + id + ".");
        }else{
            categoryDTOFound = mapper.convertValue(categoryEntityFound, CategoryDTO.class);
            LOGGER.info("Getting category id: " + id);
        }
        return categoryDTOFound;
    }

    @Override
    public Set<CategoryDTO> getAllCategories() {
        LOGGER.info("### From CategoryService class, getAllCategories method is running ###");
        List<Category> categoryEntityList = iCategoryRepository.findAll();
        Set<CategoryDTO> categoryDtoSet = new HashSet<>();
        for (Category category: categoryEntityList){
            categoryDtoSet.add(mapper.convertValue(category, CategoryDTO.class));
        }
        LOGGER.info("Getting all dentists");
        return categoryDtoSet;
    }

    @Override
    public CategoryDTO updateCategory(CategoryCreateDTO categoryCreateDTO) {
        LOGGER.info("<<<<<<<<<< From CategoryService class, updateCategory method is running >>>>>>>>>>");
        if (getCategoryById(categoryCreateDTO.getCategoryId()) == null) {
            LOGGER.info("Service: ResourceNotFoundException was thrown trying to update a category which is null, category id: " + categoryCreateDTO.getCategoryId() + ".");
        }
        Category categoryEntityToUpdate = mapper.convertValue(categoryCreateDTO, Category.class);
        CategoryDTO categoryDtoUpdated = mapper.convertValue(
                iCategoryRepository.save(categoryEntityToUpdate), CategoryDTO.class);
        LOGGER.info("Updating a category: " + categoryCreateDTO.getCategoryName());
        return categoryDtoUpdated;
    }

    @Override
    public void deleteCategoryById(Long id) {
        LOGGER.info("********** From CategoryService class, deleteCategoryById method is running **********");
        if (getCategoryById(id) == null) {
            LOGGER.info("Service: ResourceNotFoundException was thrown trying to delete a category which is null, category id: " + id + ".");
        }
        LOGGER.info("Service: Deleting category id: " + id);
        iCategoryRepository.deleteById(id);
    }


}
