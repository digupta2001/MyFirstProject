package jav.example.restapidemo.service;


import jav.example.restapidemo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();

     Category updateCategory(Integer id,Category category);

    Category getCategoryId(Integer id);

     void deleteCategory(Integer id);

    Category addCategory(Category category);


}
