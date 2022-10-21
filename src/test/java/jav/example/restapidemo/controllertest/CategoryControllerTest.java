package jav.example.restapidemo.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import jav.example.restapidemo.controller.CategoryController;
import jav.example.restapidemo.entity.Category;
import jav.example.restapidemo.exception.ResourceNotFoundException;
import jav.example.restapidemo.service.CategoryService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(classes = CategoryControllerTest.class)
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "jav.example.restapidemo")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CategoryControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;
    List<Category> categories;
    Category category;

    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }


    @Test
    @Order(1)
    void get_CategoryResourceNotFound() throws Exception
    {
        Integer categoryId=1;
        when(this.categoryService.getCategoryList()).thenThrow(ResourceNotFoundException.class);
        this.mockMvc.perform( get("/category",categoryId)).andExpect(status().isNotFound());
    }

    @Test
    @Order(2)
    public void testGetCategoryList() throws Exception
    {
        categories = new ArrayList<>();
        categories.add(new Category(1,"name","description",false,false,null,null));
        when(categoryService.getCategoryList()).thenReturn(categories);
        this.mockMvc.perform(get("/getAll"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Order(3)
    public void testgetCategoryId() throws Exception
    {
        Integer categoryId=1;
        Category category1=new Category(1,"name","description",false,false,null,null);
        when(this.categoryService.getCategoryId(categoryId)).thenReturn(category1);
                this.mockMvc.perform( get("/categoryById/{id}",categoryId))
                        .andExpect(MockMvcResultMatchers.jsonPath(".categoryId").value(1))
                        .andExpect(MockMvcResultMatchers.jsonPath(".categoryName").value("name"))
                        .andExpect(MockMvcResultMatchers.jsonPath(".categoryDescription").value("description"))
                        .andExpect(status().isOk())
                        .andDo(print());

    }

    @Test
    @Order(4)
    void getCategoryIdResourceNotFound() throws Exception
    {
        Integer categoryId=1;
        when(this.categoryService.getCategoryId(categoryId)).thenThrow(ResourceNotFoundException.class);
        this.mockMvc.perform(get("/category/{id}",categoryId)).andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    public void test_addCategory() throws Exception
    {
        category = new Category(1,
                "name",
                "description",
                true,
                false,
                null,
                null);

        when(categoryService.addCategory(category)).thenReturn(category);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(category);
        this.mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void testUpdateResourceNotFound() throws Exception
    {
        Integer categoryId = 1;
        Category testCategory = new Category();
        testCategory.setCategoryId(1);
        testCategory.getCategoryName();
        testCategory.getCategoryDescription();

        when(categoryService.updateCategory(anyInt(),any(Category.class))).thenThrow(ResourceNotFoundException.class);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(category);
        this.mockMvc.perform(put("/update/{categoryId}",categoryId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    public void testDeleteCategory() throws Exception
    {
        Integer categoryId=1;
        categoryService.deleteCategory(categoryId);

        this.mockMvc.perform(delete("/deletebyid/{id}",categoryId))
                .andExpect(status().isOk());

    }
}
