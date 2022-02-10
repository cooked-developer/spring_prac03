package com.sparta.mydelivery01.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 400 Bad Request
    DUPLICATE_FOOD(HttpStatus.BAD_REQUEST, "400_1", "중복된 음식 이름으로 등록할 수 없습니다."),
    NOT_ALLOWED_FOOD_PRICE(HttpStatus.BAD_REQUEST, "400_2", "허용값이 아닙니다. 1,000원 ~ 1_000,000원 사이로 설정해주세요."),
    PRICE_UNIT_100_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "400_3", "100원 단위로 설정해주세요."),
    PRICE_UNIT_500_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "400_4", "500원 단위로 설정해주세요."),
    NOT_ALLOWED_MIN_ORDER_PRICE_RESTAURANT(HttpStatus.BAD_REQUEST, "400_5", "허용값이 아닙니다. 1,000원 ~ 100,000원 사이로 설정해주세요."),
    NOT_ALLOWED_DELIVERY_FEE(HttpStatus.BAD_REQUEST, "400_6", "허용값이 아닙니다. 0원 ~ 10,000원 사이로 설정해주세요."),
    NOT_ALLOWED_FOOD_QUANTITY(HttpStatus.BAD_REQUEST, "400_7", "주문 가능한 수량 : 1~100"),
    NOT_ALLOWED_MIN_ORDER_PRICE_CUSTOMER(HttpStatus.BAD_REQUEST, "400_8", "최소 주문금액을 확인해주세요."),

    // 404 Not Found
    NOT_FOUND_RESTAURANT(HttpStatus.BAD_REQUEST, "404_1", "해당 식당이 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
