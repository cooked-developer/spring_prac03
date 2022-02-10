package com.sparta.mydelivery01.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class OrderEntity {
// 주문서

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderFood> foodList;

    @OneToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private Long totalPrice;

    public OrderEntity(List<OrderFood> foodList, Restaurant restaurant, Long totalPrice) {
        this.foodList = foodList;
        this.restaurant = restaurant;
        this.totalPrice = totalPrice;
    }
}