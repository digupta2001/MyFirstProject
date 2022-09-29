package jav.example.restapidemo.servicetest;

import jav.example.restapidemo.entity.Category;
import jav.example.restapidemo.exception.ResourceNotFoundException;
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
                null,null
        ));
        myCategory.add(new Category(2,
                "category2",
                "category2is",
                true,
                false,
                null,
                null,null
    ));
        when(categoryRepository.findAll()).thenReturn(myCategory);
        categoryService.getCategoryList();
        assertEquals(2,categoryService.getCategoryList().size());
    }

    @Test
    void test_getCategoryThrowsResourceNotFoundException() {

        List <Category> myCategory= new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(myCategory);

        assertThatThrownBy(() -> categoryService.getCategoryList())
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void test_getCategoryId() {

        Category category = new Category(1,
                "category1",
                "description",
                true,
                false,
                null,
                null,null);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        assertEquals("category1",this.categoryService.getCategoryId(1).getCategoryName());
        assertEquals("description",this.categoryService.getCategoryId(1).getCategoryDescription());

    }

    @Test
    void test_getCategoryIdThrowsResourceNotFoundException() {
        Integer id = 1;

        when(categoryRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.getCategoryId(id))
                .isInstanceOf(ResourceNotFoundException.class);

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
                null,null);
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
                null,null));
        int CategoryId =1;
        categoryRepository.deleteCategory(CategoryId);
        when(categoryRepository.findAll()).thenReturn(myCategory);
        assertThat(myCategory).isNotNull();

    }


}