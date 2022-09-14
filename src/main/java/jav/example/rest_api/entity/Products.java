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

    @Id
    private int ProductId;
    private int CategoryId;
    private String ProductName;
    private String ProductDescription;
    private int price;
    private boolean Active;
    private boolean Deleted;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate CreateDate= LocalDate.now();

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate UpdateDate= LocalDate.now();


}
