package jav.example.restapidemo.controller;


import jav.example.restapidemo.entity.Category;
import jav.example.restapidemo.service.CategoryService;
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


    /**
     * fetching category list
     * @return
     */
    @GetMapping("getAll")
    public ResponseEntity<List<Category>> getCategoryList(){
        log.info("category list fetching begins");
        List<Category> categoryList=categoryService.getCategoryList();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }


    /**
     * fetching category by id
     * @param id
     * @return
     */
    @GetMapping("categoryById/{id}")
    public ResponseEntity<Category>getCategoryById(final @PathVariable Integer id){
        log.info("category by id begins");
        try {
            Category category=this.categoryService.getCategoryId(id);
            return new ResponseEntity<Category>(category,HttpStatus.OK);
        }
        catch (NoSuchElementException ef){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * adding new category
     * @param category
     * @return
     */
    @PostMapping("create")
    public ResponseEntity<Category> addCategory(final @RequestBody Category category){
        log.info("adding new info ");
        Category category1=categoryService.addCategory(category);
        return new ResponseEntity<>(category1,HttpStatus.CREATED);
    }


    /**
     * Deleting category by id
     * @param id
     * @return
     */
    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<Category> deleteCategory(final @PathVariable("id")Integer id){
        try {
            log.info("category deleted");
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException ef){
            log.error("no category found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    /**
     * updating category by id
     * @param categoryId
     * @param category
     * @return
     */
    @PutMapping("update/{categoryId}")
    public ResponseEntity<Category>updateCategory(@PathVariable("categoryId")Integer categoryId,@RequestBody Category category){
        categoryService.updateCategory(categoryId,category);
        return new ResponseEntity<>(categoryService.getCategoryId(categoryId),HttpStatus.OK);
    }


}
