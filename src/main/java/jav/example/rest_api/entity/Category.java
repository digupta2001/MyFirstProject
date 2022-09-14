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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryId;
    private String CategoryName;
    private String CategoryDescription;
    private boolean Active;
    private boolean Deleted;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate CreateDate= LocalDate.now();
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate UpdateDate= LocalDate.now();

    @OneToMany(targetEntity = Products.class,cascade = CascadeType.ALL)
    @JoinColumn(name="c_fk",referencedColumnName = "CategoryId")
    private List<Products> products;

}
