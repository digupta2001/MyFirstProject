package jav.example.restapidemo.controllertest;



import com.fasterxml.jackson.databind.ObjectMapper;
import jav.example.restapidemo.controller.CategoryController;
import jav.example.restapidemo.entity.Category;
import jav.example.restapidemo.exception.ResourceNotFoundException;
import jav.example.restapidemo.service.CategoryService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
//import static java.lang.reflect.Array.get;
import static org.mockito.Mockito.when;
//import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void get_CategoryResourceNotFound() throws Exception
    {
        Integer categoryId=1;
        when(this.categoryService.getCategoryList()).thenThrow(ResourceNotFoundException.class);
        this.mockMvc.perform( get("/category",categoryId)).andExpect(status().isNotFound());
    }

    @Test
    void testgetCategoryId() throws Exception
    {
        Integer categoryId=1;
        Category category1=new Category(1,"name","description",false,false,null,null,null);
        when(this.categoryService.getCategoryId(categoryId)).thenReturn(category1);
                this.mockMvc.perform( get("/category/{id}",categoryId))
                        .andExpect(MockMvcResultMatchers.jsonPath(".categoryId").value(1))
                        .andExpect(MockMvcResultMatchers.jsonPath(".categoryName").value("name"))
                        .andExpect(MockMvcResultMatchers.jsonPath(".categoryDescription").value("description"))
                        .andExpect(status().isOk())
                        .andDo(print());

    }

    @Test
    void getCategoryIdResourceNotFound() throws Exception
    {
        Integer categoryId=1;
        when(this.categoryService.getCategoryId(categoryId)).thenThrow(ResourceNotFoundException.class);
        this.mockMvc.perform(get("/category/{id}",categoryId)).andExpect(status().isNotFound());
    }

    @Test
    public void test_addCategory() throws Exception
    {
        category = new Category(1,
                "name",
                "description",
                true,
                false,
                null,
                null,null);

        when(categoryService.addCategory(category)).thenReturn(category);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(category);
        this.mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void test_deleteCategory() throws Exception {
        int categoryId=1;
        categoryService.deleteCategory(categoryId);
        this.mockMvc
                .perform(delete("/category/{id}",1))
                .andExpect(status().isOk())
                .andDo(print());
    }








}
