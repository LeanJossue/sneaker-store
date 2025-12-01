package co.edu.poli.sneaker_store_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sneaker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String color;
    private Float price;
    private Integer stock;
    private Float size;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}