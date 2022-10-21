package jav.example.restapidemo.servicetest;


import jav.example.restapidemo.entity.Category;
import jav.example.restapidemo.entity.Products;
import jav.example.restapidemo.exception.ResourceNotFoundException;
import jav.example.restapidemo.repository.CategoryRepository;
import jav.example.restapidemo.repository.ProductRepository;
import jav.example.restapidemo.service.ProductServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ProductServiceTest.class})
public class ProductServiceTest
{
    @Mock
    ProductRepository productRepository;
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    @Order(1)
    public void testGetProductList() {
        List<Products> myProduct = new ArrayList<>();
        myProduct.add(new Products(1, "prod1", "prod1lis", 100, false, false, null, null, null));
        myProduct.add(new Products(2, "prod2", "prodlis2", 100, false, false, null, null, null));
        when(productRepository.findAll()).thenReturn(myProduct);
        productService.getProductAll();
        assertEquals(2, productService.getProductAll().size());
    }

    @Test
    @Order(2)
    void testGetProductListResourceNotFoundException() {
        List<Category> myCategory = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(myCategory);
        assertThatThrownBy(() -> productService.getProductAll())
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @Order(4)
    void testProductByIdResourceNotFoundException() {
        Integer id = 1;
        when(productRepository.findById(id))
                .thenReturn(Optional.empty());
        assertThatThrownBy(() -> productService.getProductById(id))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @Order(5)
    void testUpdateProduct() {
        Products products = new Products(1, "name", "prod1", 4500, false, false, null, null, null);
        when(productRepository.findById(any())).thenReturn(Optional.of(products));
        when(productRepository.save(any())).thenReturn(products);
        Products products1 = productService.updateProduct(1, products);
        assertEquals(products.getProductName(), products1.getProductName());
    }

    @Test
    @Order(6)
    public void testDeleteProduct() {
        Integer productId = 1;
        Products products = new Products(1, "prod1", "prodlis1", 80, false, false, null, null, null);
        when(productRepository.findById(productId)).thenReturn(Optional.of(products));
        productService.deleteproduct(productId);
        assertTrue(products.isDeleted());
        assertFalse(products.isActive());
    }
}
