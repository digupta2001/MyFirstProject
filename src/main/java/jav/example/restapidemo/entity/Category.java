package jav.example.restapidemo.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Getter
@Setter
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

    @NotBlank(message = "category name cannot be empty.")
    private String categoryName;

    private String categoryDescription;

    private boolean active=Boolean.TRUE;

    private boolean deleted=Boolean.FALSE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createDate;

    @UpdateTimestamp
    private LocalDate updateDate;



}
