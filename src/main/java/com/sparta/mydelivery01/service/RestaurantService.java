package com.sparta.mydelivery01.service;


import com.sparta.mydelivery01.dto.RestaurantDto;
import com.sparta.mydelivery01.model.Restaurant;
import com.sparta.mydelivery01.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant register(RestaurantDto restaurantDto) {
        if (restaurantDto.getMinOrderPrice() < 1000 || restaurantDto.getMinOrderPrice() > 100_000){
            throw new IllegalArgumentException("허용값이 아닙니다. 1,000원 ~ 100,000원 사이로 설정해주세요.");
        }
        if (restaurantDto.getMinOrderPrice() % 100 != 0){
            throw new IllegalArgumentException("100원 단위로 설정해주세요.");
        }
        if (restaurantDto.getDeliveryFee() < 0 || restaurantDto.getDeliveryFee() > 10_000){
            throw new IllegalArgumentException("허용값이 아닙니다. 0원 ~ 10,000원 사이로 설정해주세요.");
        }
        if (restaurantDto.getDeliveryFee() % 500 != 0){
            throw new IllegalArgumentException("500원 단위로 설정해주세요.");
        }
        Restaurant restaurant = new Restaurant(restaurantDto);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
