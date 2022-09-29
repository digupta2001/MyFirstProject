package jav.example.restapidemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate= LocalDate.now();
   @UpdateTimestamp
    private LocalDate updateDate= LocalDate.now();

   @OneToMany(targetEntity = Products.class,cascade = CascadeType.ALL)
   @JoinColumn(name="foreignkey",referencedColumnName = "CategoryId")
   private List<Products> products;

}
