package jav.example.rest_api.controller;


import jav.example.rest_api.entity.Category;
import jav.example.rest_api.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public final class  CategoryController {

    @Autowired
    private CategoryService categoryService;


    //trying to work for get task
    @GetMapping("home")
    public String home()
    {
        return" hi POSTMAN working";
    }


    //fetching all category info
    @GetMapping("getall")
    public ResponseEntity<List<Category>> getCategoryList(){
        List<Category> categoryList=categoryService.getCategoryList();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }


    //fetching category by id
    @GetMapping("categoryby{id}")
    public ResponseEntity<Category>getCategoryById(@PathVariable Integer id){
        try {
            Category category=this.categoryService.getCategoryId(id);
            return new ResponseEntity<Category>(category,HttpStatus.OK);
        }
        catch (NoSuchElementException ef){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }


    //posting data in category table
    @PostMapping("create")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category category1=categoryService.addCategory(category);
        return new ResponseEntity<>(category1,HttpStatus.CREATED);
    }

    //deleting operation by category id

    @DeleteMapping("deleteby{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id")Integer id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //updating new info in table
    @PutMapping("update{categoryId}")
    public ResponseEntity<Category>updateCategory(@PathVariable("categoryId")Integer categoryId,@RequestBody Category category){
        categoryService.updateCategory(categoryId,category);
        return new ResponseEntity<>(categoryService.getCategoryId(categoryId),HttpStatus.OK);
    }


}
