package jav.example.restapidemo.controllertest;
import jav.example.restapidemo.controller.ProductController;
import jav.example.restapidemo.entity.Products;
import jav.example.restapidemo.exception.ResourceNotFoundException;
import jav.example.restapidemo.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProductControllerTest.class)
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "jav.example.restapidemo")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;
    List<Products> products;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }


    @Test
    @Order(1)
    void getProductResourceNotFound() throws Exception {
        Integer productId = 1;

        when(this.productService.getProductAll()).thenThrow(ResourceNotFoundException.class);

        this.mockMvc.perform(get("/product", productId))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(2)
    public void testGetProductList() throws Exception {
        products = new ArrayList<>();
        products.add(new Products(1, "name", "description", 1200, false, false, null, null, null));
        when(productService.getProductAll()).thenReturn(products);
        this.mockMvc.perform(get("/getProdAll"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(3)
    public void testProductById() throws Exception {
        Integer productId = 1;
        Products products1 = new Products(1, "name", "description", 1200, false, false, null, null, null);
        when(this.productService.getProductById(productId)).thenReturn(products1);
        this.mockMvc.perform(get("/product/{id}", productId))
                .andExpect(MockMvcResultMatchers.jsonPath(".productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".productName").value("name"))
                .andExpect(MockMvcResultMatchers.jsonPath(".productDescription").value("description"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @Order(4)
    void getProductByIdResourceNotFound() throws Exception
    {
        Integer productId=1;
        when(this.productService.getProductById(productId)).thenThrow(ResourceNotFoundException.class);
        this.mockMvc.perform(get("/product/{id}",productId))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    public void testDeleteProduct() throws Exception
    {
        Integer productId=1;
        productService.deleteproduct(productId);
        this.mockMvc.perform(delete("/deleteById/{id}",productId))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
