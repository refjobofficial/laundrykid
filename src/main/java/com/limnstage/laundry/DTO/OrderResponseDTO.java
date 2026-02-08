package com.limnstage.laundry.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class OrderResponseDTO {

    private Long orderId;
    private Integer shirts;
    private Integer pants;
    private String deliveryStatus;
    private OffsetDateTime orderDate;
}