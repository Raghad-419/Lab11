package com.example.blog_system.Service;

import com.example.blog_system.ApiResponse.ApiException;
import com.example.blog_system.Model.Category;
import com.example.blog_system.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
        private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategory(){
            return categoryRepository.findAll();
        }

        public void addCategory(Category category){
            categoryRepository.save(category);

        }

        public void updateCategory(Integer id,Category category){
        Category oldCategory =categoryRepository.findCategoriesById(id);
        if(oldCategory==null){
            throw new ApiException("Category not found");
        }
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
        }


        public void deleteCategory(Integer id){
        Category category= categoryRepository.findCategoriesById(id);
        if(category==null){
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(category);
        }

    }
