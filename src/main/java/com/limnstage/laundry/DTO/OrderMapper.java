package com.limnstage.laundry.DTO;

import com.limnstage.laundry.model.Orders;

public class OrderMapper {

    public static OrderResponseDTO toDto(Orders order) {
        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .shirts(order.getShirts())
                .pants(order.getPants())
                .deliveryStatus(order.getDeliveryStatus())
                .orderDate(order.getOrderDate())
                .build();
    }
}