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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("home")
    public String home()
    {
        return" hi POSTMAN working";
    }

    @GetMapping("getall")
    public ResponseEntity<List<Category>> GetCategoryList(){
        List<Category> categoryList=categoryService.GetCategoryList();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("categoryby{id}")
    public ResponseEntity<Category>GetCategoryById(@PathVariable Integer id){
        try {
            Category category=this.categoryService.GetCategoryId(id);
            return new ResponseEntity<Category>(category,HttpStatus.OK);
        }
        catch (NoSuchElementException ef){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("create")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category category1=categoryService.addCategory(category);
        return new ResponseEntity<>(category1,HttpStatus.CREATED);
    }

    @DeleteMapping("deleteby{id}")
    public ResponseEntity<Category> DeleteCategory(@PathVariable("id")Integer id){
        categoryService.DeleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update{CategoryId}")
    public ResponseEntity<Category>updateCategory(@PathVariable("CategoryId")Integer CategoryId,@RequestBody Category category){
        categoryService.updateCategory(CategoryId,category);
        return new ResponseEntity<>(categoryService.GetCategoryId(CategoryId),HttpStatus.OK);
    }


}
