package jav.example.restapidemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "Products")


public class Products {

    //Declaring product varaiables

    @Id
    public int productId;
    //private int categoryId;
    private String productName;
    private String productDescription;
    private int price;
    private boolean active=Boolean.TRUE;
    private boolean deleted=Boolean.FALSE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate= LocalDate.now();

    @UpdateTimestamp
    private LocalDate updateDate= LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
