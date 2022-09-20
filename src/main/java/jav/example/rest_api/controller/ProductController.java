package jav.example.rest_api.controller;


import jav.example.rest_api.entity.Products;
import jav.example.rest_api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public final class ProductController {

    @Autowired
    private ProductService productService;


    //basic get function trial
    @GetMapping("/homeProd")
    public String homeProd()
    {

        return "hello products";
    }


    //fetching all details of products
    @GetMapping("getProdAll")
    public ResponseEntity<List<Products>>getProductAll()
    {
        log.info("product list ");
        List<Products> products=productService.getProductAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //deleting product info by id

    @DeleteMapping("deleteById{id}")
    public ResponseEntity<Products> del(@PathVariable("id")Integer id){
        try {
            log.info("product deleted");
            productService.Del(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException ef){
        log.error("error in product");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
