package jav.example.rest_api.service;

import jav.example.rest_api.entity.Products;

import java.util.List;

public interface ProductService {

    List<Products> getProductAll();

    public void Del(Integer id);


}
