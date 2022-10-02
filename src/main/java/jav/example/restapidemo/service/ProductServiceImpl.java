package jav.example.restapidemo.service;


import jav.example.restapidemo.entity.Products;
import jav.example.restapidemo.repository.ProductRepository;
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
    public void deleteproduct(Integer id){
        productRepository.deleteProduct(id);
    }


}
