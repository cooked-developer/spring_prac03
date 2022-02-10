package com.sparta.mydelivery01.model;


import com.sparta.mydelivery01.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    public Food(Restaurant restaurant, FoodDto foodDto) {
        this.restaurant = restaurant;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }
}