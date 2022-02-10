package com.sparta.mydelivery01.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class OrderFood {
// 개별 주문 내용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long orderPrice;

    public OrderFood(Food food, Long quantity){
        this.food = food;
        this.quantity = quantity;
        this.orderPrice = food.getPrice() * quantity;
    }

}
