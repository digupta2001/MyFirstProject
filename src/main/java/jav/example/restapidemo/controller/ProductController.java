package jav.example.restapidemo.controller;


import jav.example.restapidemo.entity.Products;
import jav.example.restapidemo.service.ProductService;
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
public final  class ProductController {

    @Autowired
    private ProductService productService;





    @GetMapping("getProdAll")
    public ResponseEntity<List<Products>>getProductAll()
    {
        log.info("product list ");
        List<Products> products=productService.getProductAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Products> delproduct(@PathVariable("id")Integer id){
        try {
            log.info("product deleted");
            productService.delproduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException ef){
        log.error("error in product");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
