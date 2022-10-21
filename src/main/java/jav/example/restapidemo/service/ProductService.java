package jav.example.restapidemo.service;

import jav.example.restapidemo.entity.Products;

import java.util.List;

public interface ProductService {

    List<Products> getProductAll();

    Products updateProduct(Integer id,Products products);

    Products getProductById(Integer id);

    void deleteproduct(Integer productId);




}
