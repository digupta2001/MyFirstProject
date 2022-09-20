package jav.example.rest_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Category")

public class Category {

    //Declaring variables of Category

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private boolean active;
    private boolean deleted;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate= LocalDate.now();
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate= LocalDate.now();

    @OneToMany(targetEntity = Products.class,cascade = CascadeType.ALL)
    @JoinColumn(name="c_fk",referencedColumnName = "CategoryId")
    private List<Products> products;

}
