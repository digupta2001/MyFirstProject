package jav.example.restapidemo.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "Products")


public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NotBlank(message = "product name cannot be blank.")
    private String productName;

    private String productDescription;

    private int price;

    private boolean active=Boolean.TRUE;

    private boolean deleted=Boolean.FALSE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    @UpdateTimestamp
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



}
