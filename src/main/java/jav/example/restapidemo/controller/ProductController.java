package jav.example.restapidemo.controller;


import jav.example.restapidemo.entity.Products;
import jav.example.restapidemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public final  class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * fetching all product list
     * @return
     */
    @GetMapping("getProdAll")
    public ResponseEntity<List<Products>>getProductAll()
    {

        List<Products> products=productService.getProductAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    /**
     * fetching product by id
     * @param id
     * @return
     */
    @GetMapping("product/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Integer id)
    {
        Products products = this.productService.getProductById(id);

        return new ResponseEntity<>(products,HttpStatus.OK);
    }


    /**
     * updating produt by id
     * @param id
     * @param products
     * @return
     */
    @PutMapping("Uproduct/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("Id") Integer id,@RequestBody Products products)
    {
        productService.updateProduct(id,products);

        return new ResponseEntity<>("product successfully updated",HttpStatus.OK);
    }


    /**
     * deleting product by id
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<Products> deleteProduct(@PathVariable("id")Integer id){
        productService.deleteproduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
