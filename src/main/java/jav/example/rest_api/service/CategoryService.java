package jav.example.rest_api.service;


import jav.example.rest_api.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();

     void updateCategory(Integer id,Category category);

    Category getCategoryId(Integer id);

     void deleteCategory(Integer id);

    Category addCategory(Category category);


}
