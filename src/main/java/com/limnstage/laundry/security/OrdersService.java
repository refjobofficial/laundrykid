package com.limnstage.laundry.security;

import com.limnstage.laundry.Repository.OrdersRepository;
import com.limnstage.laundry.model.enumfiles.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public void updateStatus(Long id, String status) {

        DeliveryStatus deliveryStatus;

        try {
            deliveryStatus = DeliveryStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status! Allowed: DELIVERED, NOT_DELIVERED, PICKED");
        }

        int updated = ordersRepository.updateDeliveryStatusById(id, deliveryStatus.name());

        if (updated == 0) {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }
}