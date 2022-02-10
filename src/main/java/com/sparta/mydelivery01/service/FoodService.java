package com.sparta.mydelivery01.service;


import com.sparta.mydelivery01.dto.FoodDto;
import com.sparta.mydelivery01.model.Food;
import com.sparta.mydelivery01.model.Restaurant;
import com.sparta.mydelivery01.repository.FoodRepository;
import com.sparta.mydelivery01.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void register(Long restaurantId, List<FoodDto> foods) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NullPointerException("음식점 없다."));

        for (FoodDto foodDto: foods) {
            if(foodRepository.existsByRestaurantAndName(restaurant, foodDto.getName())) {
                throw new IllegalArgumentException("중복된 음식 이름으로 등록할 수 없습니다.");
            }
            if(foodDto.getPrice() < 100 || foodDto.getPrice() > 1_000_000) {
                throw new IllegalArgumentException("허용값이 아닙니다. 1,000원 ~ 1_000,000원 사이로 설정해주세요.");
            }
            if (foodDto.getPrice() % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로 설정해주세요.");
            }
            Food food = new Food(restaurant, foodDto);
            foodRepository.save(food);
        }

    }

    public List<Food> getAllFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new IllegalArgumentException("식당이 없어"));
        return foodRepository.findAllByRestaurant(restaurant);
    }
}
