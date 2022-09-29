package jav.example.restapidemo.repository;

import jav.example.restapidemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "update category set Deleted = true,Active = false where category_id = :categoryid",nativeQuery = true)

    @Transactional
    @Modifying

     void deleteCategory(@Param("categoryid") Integer categoryid);





}
