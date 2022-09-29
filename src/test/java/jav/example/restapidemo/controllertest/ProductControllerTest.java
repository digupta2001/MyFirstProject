package jav.example.restapidemo.controllertest;


import jav.example.restapidemo.controller.ProductController;
import jav.example.restapidemo.entity.Products;
import jav.example.restapidemo.exception.ResourceNotFoundException;
import jav.example.restapidemo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProductControllerTest.class)
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "jav.example.restapidemo")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;
    List<Products> products;
    @BeforeEach
    public void setUp()
    {
        mockMvc= MockMvcBuilders.standaloneSetup(productController).build();
    }


    @Test
    void getProductResourceNotFound() throws Exception
    {
        Integer productId=1;

        when(this.productService.getProductAll()).thenThrow( ResourceNotFoundException.class);

        this.mockMvc.perform(get("/product",productId))
                .andExpect(status().isNotFound());


    }

    @Test
    public void test_deleteProduct() throws Exception {
        int productId=1;
        productService.delproduct(productId);
        this.mockMvc
                .perform(delete("/product/{id}",1))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
