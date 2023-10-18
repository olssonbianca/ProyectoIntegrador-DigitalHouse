package com.finalprojectc7t3.backend.entity;
import javax.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private Integer imageId;


    @OneToOne
    @JoinColumn(name = "imageId", insertable = false, updatable = false)
    private Image image;
}
