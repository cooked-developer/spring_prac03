package com.sparta.mydelivery01.repository;


import com.sparta.mydelivery01.model.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
}
