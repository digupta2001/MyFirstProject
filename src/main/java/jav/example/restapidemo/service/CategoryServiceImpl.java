package jav.example.restapidemo.service;


import jav.example.restapidemo.entity.Category;
import jav.example.restapidemo.repository.CategoryRepository;
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
        updateCategory.setCategoryId(category.getCategoryId());
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
