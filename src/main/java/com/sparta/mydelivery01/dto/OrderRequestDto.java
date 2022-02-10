package com.sparta.mydelivery01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderRequestDto {
    private Long restaurantId;
    List<FoodRequestInOrderDto> foods;
}
