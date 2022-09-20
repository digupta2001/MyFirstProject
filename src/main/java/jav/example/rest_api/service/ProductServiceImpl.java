package jav.example.rest_api.service;


import jav.example.rest_api.entity.Products;
import jav.example.rest_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Products> getProductAll(){
        return productRepository.findAll();
    }

    @Override
    public void Del(Integer id){
        productRepository.DeleteProduct(id);
    }


}
