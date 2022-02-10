package com.sparta.mydelivery01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RestaurantDto {
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
}
