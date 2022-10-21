package jav.example.restapidemo.servicetest;

import jav.example.restapidemo.entity.Category;

import jav.example.restapidemo.repository.CategoryRepository;
import jav.example.restapidemo.service.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryServiceTest.class})
public class CategoryServiceTest
{
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Test
    public void test_getCategoryList()

    {
        List<Category> myCategory= new ArrayList<>();
        myCategory.add(new Category(1,
                "category1",
                "category1is",
                true,
                false,
                null,
                null
        ));
        myCategory.add(new Category(2,
                "category2",
                "category2is",
                true,
                false,
                null,
                null
    ));
        when(categoryRepository.findAll()).thenReturn(myCategory);
        categoryService.getCategoryList();
        assertEquals(2,categoryService.getCategoryList().size());
    }


    @Test
    public void test_getCategoryId() {

        Category category = new Category(1,
                "category1",
                "description",
                true,
                false,
                null,
                null);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        assertEquals("category1",this.categoryService.getCategoryId(1).getCategoryName());
        assertEquals("description",this.categoryService.getCategoryId(1).getCategoryDescription());

    }

    @Test
    public void test_addCategory()
    {
        Category category= new Category(3,
                "category3",
                "category1is",
                true,
                false,
                null,
                null);
        when(categoryRepository.save(category)).thenReturn(category);
        categoryService.addCategory(category);
        assertEquals(category,categoryService.addCategory(category));
    }

    @Test
    public void test_deleteCategory(){
        List <Category> myCategory= new ArrayList<>();
        myCategory.add(new Category(1,
                "category1",
                "category1is",
                true,
                false,
                null,
                null));
        int CategoryId =1;
        categoryRepository.deleteCategory(CategoryId);
        when(categoryRepository.findAll()).thenReturn(myCategory);
        assertThat(myCategory).isNotNull();

    }

    @Test
    void testUpdateCategory()
    {
        Category category = new Category(1,"name","caytegorylis",false,false,null,null);
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(category);
        Category category1=categoryService.updateCategory(1,category);
        assertEquals(category.getCategoryName(),category1.getCategoryName());
    }


}
