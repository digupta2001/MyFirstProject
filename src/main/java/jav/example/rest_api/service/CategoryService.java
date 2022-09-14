package jav.example.rest_api.service;


import jav.example.rest_api.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> GetCategoryList();

    public void updateCategory(Integer id,Category category);

    Category GetCategoryId(Integer id);

    public void DeleteCategory(Integer id);

    Category addCategory(Category category);


}
