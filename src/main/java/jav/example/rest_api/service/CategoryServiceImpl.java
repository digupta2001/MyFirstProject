package jav.example.rest_api.service;


import jav.example.rest_api.entity.Category;
import jav.example.rest_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoryList(){
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(Integer id,Category category){
        Category updateCategory=categoryRepository.findById(id).get();
        updateCategory.setCategoryName(category.getCategoryName());
        updateCategory.setCategoryDescription(category.getCategoryDescription());
        updateCategory.setProducts(category.getProducts());
    }

    @Override
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryId(Integer categoryid){
        return categoryRepository.findById(categoryid).get();
    }

    @Override
    public void deleteCategory(Integer categoryid){
        categoryRepository.deleteCategory(categoryid);
    }
}
