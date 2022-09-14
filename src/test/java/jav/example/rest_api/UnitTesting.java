package jav.example.rest_api;

import jav.example.rest_api.entity.Category;
import jav.example.rest_api.repository.CategoryRepository;
import jav.example.rest_api.service.CategoryServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes ={ UnitTesting.class})
public class UnitTesting
{
    @Mock
    CategoryRepository catego;

    @InjectMocks
    CategoryServiceImpl categoService;

    public List<Category> mycategory;
    @Test
    @Order(1)
    public void test_GetCategoryList()
    {
        List<Category> mycat =new ArrayList<Category>();
        mycat.add(new Category(1,"cat 1","cat1is",true,false,null,null,null));
        mycat.add(new Category(2,"cat 2","cat2is",true,false,null,null,null));
        when(catego.findAll()).thenReturn(mycat);
        categoService.GetCategoryList();

        assertEquals(2,categoService.GetCategoryList().size());
    }

    @Test
    public void test_addCategory()
    {
        Category category= new Category(5,"category5","category5is",true,false,null,null,null);
        when(catego.save(category)).thenReturn(category);
        categoService.addCategory(category);
        assertEquals(category,categoService.addCategory(category));
    }




}
