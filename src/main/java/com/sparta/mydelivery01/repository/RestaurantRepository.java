package com.sparta.mydelivery01.repository;

import com.sparta.mydelivery01.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
