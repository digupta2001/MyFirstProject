package jav.example.rest_api.repository;

import jav.example.rest_api.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ProductRepository extends JpaRepository<Products,Integer> {


    @Query(value = "update products set Deleted = true,Active = false where product_id = :productid",nativeQuery = true)

    @Modifying
    @Transactional
     void deleteProduct(@Param("productid") Integer productid);
}
