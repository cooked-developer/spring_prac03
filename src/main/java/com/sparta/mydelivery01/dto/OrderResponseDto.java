package com.sparta.mydelivery01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderResponseDto {
    private String restaurantName;
    private List<FoodResponseInOrderDto> foods;
    private Long deliveryFee;
    private Long totalPrice;
}
