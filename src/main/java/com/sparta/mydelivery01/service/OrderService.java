package com.sparta.mydelivery01.service;


import com.sparta.mydelivery01.dto.FoodRequestInOrderDto;
import com.sparta.mydelivery01.dto.FoodResponseInOrderDto;
import com.sparta.mydelivery01.dto.OrderRequestDto;
import com.sparta.mydelivery01.dto.OrderResponseDto;
import com.sparta.mydelivery01.model.Food;
import com.sparta.mydelivery01.model.OrderEntity;
import com.sparta.mydelivery01.model.OrderFood;
import com.sparta.mydelivery01.model.Restaurant;
import com.sparta.mydelivery01.repository.FoodRepository;
import com.sparta.mydelivery01.repository.OrderFoodRepository;
import com.sparta.mydelivery01.repository.OrderRepository;
import com.sparta.mydelivery01.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final OrderFoodRepository orderFoodRepository;

    @Transactional
    public OrderResponseDto makeOrder(OrderRequestDto requestDto) {
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("식당없다."));

        // 주문 저장하기
        List<OrderFood> orderFoodList = new ArrayList<>();
        List<FoodResponseInOrderDto> orderedFoodResponseDto = new ArrayList<>();

        String restaurantName = restaurant.getName();
        Long deliveryFee = restaurant.getDeliveryFee();
        Long totalPrice = deliveryFee;

        for (FoodRequestInOrderDto requestFood: requestDto.getFoods()){
            Long quantity = requestFood.getQuantity();
            Food food = foodRepository.findById(requestFood.getId())
                    .orElseThrow(() -> new IllegalArgumentException("음식없다."));
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("수량 확인");
            }
            OrderFood orderFood = new OrderFood(food, quantity);
            FoodResponseInOrderDto foodResponseDto = new FoodResponseInOrderDto(
                    food.getName(),
                    quantity,
                    orderFood.getOrderPrice()
                    );
            orderedFoodResponseDto.add(foodResponseDto);
            orderFoodList.add(orderFood);
            orderFoodRepository.save(orderFood);
            totalPrice += orderFood.getOrderPrice();
        }
        if (totalPrice-deliveryFee < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("더 주문해");
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto(restaurantName, orderedFoodResponseDto, deliveryFee, totalPrice);
        OrderEntity order = new OrderEntity(orderFoodList, restaurant, totalPrice);
        orderRepository.save(order);
        return orderResponseDto;
    }

    public List<OrderResponseDto> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderResponseDto> resultDto = new ArrayList<>();

        for (OrderEntity order : orders) {
            String restaurantName = order.getRestaurant().getName();
            List<FoodResponseInOrderDto> foodResponseInOrderDtoList = new ArrayList<>();
            for (OrderFood orderedFood: order.getFoodList()) {
                String name = orderedFood.getFood().getName();
                Long quantity = orderedFood.getQuantity();
                Long foodPrice = orderedFood.getOrderPrice();
                FoodResponseInOrderDto foodResponseInOrderDto = new FoodResponseInOrderDto(
                    name, quantity, foodPrice
                );
                foodResponseInOrderDtoList.add(foodResponseInOrderDto);
            }
            Long deliveryFee = order.getRestaurant().getDeliveryFee();
            Long price = order.getTotalPrice();

            OrderResponseDto orderResponseDto = new OrderResponseDto(
                    restaurantName, foodResponseInOrderDtoList, deliveryFee, price
            );
            resultDto.add(orderResponseDto);
        }
        return resultDto;
    }
}
