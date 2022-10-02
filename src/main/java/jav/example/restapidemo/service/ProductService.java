package jav.example.restapidemo.service;

import jav.example.restapidemo.entity.Products;

import java.util.List;

public interface ProductService {

    List<Products> getProductAll();

     void deleteproduct(Integer id);


}
