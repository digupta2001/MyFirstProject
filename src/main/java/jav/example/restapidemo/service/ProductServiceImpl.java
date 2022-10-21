package jav.example.restapidemo.service;
import jav.example.restapidemo.entity.Products;
import jav.example.restapidemo.exception.ResourceNotFoundException;
import jav.example.restapidemo.repository.CategoryRepository;
import jav.example.restapidemo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
public final class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    /**
     *
     * @return
     */
    @Override
    public List<Products> getProductAll()
    {
       log.trace("getting category list");
       List<Products> productsList = productRepository.findAll();
       if (productsList.size()==0)
       {
           log.error("no category list");
           throw new ResourceNotFoundException("empty data");
       }
       log.info("fetching all data for table");
       return productsList;
    }


    /**
     *
     * @param productId
     * @param products
     * @return
     */
    @Override
    public Products updateProduct(Integer productId,Products products)
    {

            log.trace("update product");
            Products updatedProduct = productRepository.findById(productId).orElseThrow(()->{
                log.error("Category"+productId+"do not exist");
                return new ResourceNotFoundException("Category"+productId+"do not exist");
                    }
                    );
            updatedProduct.setProductName(products.getProductName());
            updatedProduct.setProductDescription(products.getProductDescription());
            updatedProduct.setPrice(products.getPrice());
            productRepository.save(updatedProduct);
            return updatedProduct;

    }


    /**
     *
     * @param productId
     * @return
     */
    @Override
    public Products getProductById(Integer productId)
    {
        log.trace("fetching product id");
        return productRepository.findById(productId).orElseThrow(()->{
            log.error("Category"+productId+"dont exist");
            return new ResourceNotFoundException("Category"+productId+"not exist");
                }
                );
    }


    /**
     *
     * @param productId
     */
    @Override
    public void deleteproduct(Integer  productId)
    {
        log.trace("deleting method");
        Products products = productRepository.findById(productId).orElseThrow(()->{
            log.error("category not found");
            return new ResourceNotFoundException("category not found");
                }

        );
        products.setDeleted(true);
        products.setActive(false);
        productRepository.save(products);
    }

 }
