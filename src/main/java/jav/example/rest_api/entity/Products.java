package jav.example.rest_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "Products")


public class Products {

    //Declaring product varaiables

    @Id
    private int productId;
    private int categoryId;
    private String productName;
    private String productDescription;
    private int price;
    private boolean active;
    private boolean deleted;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate= LocalDate.now();

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate= LocalDate.now();


}
