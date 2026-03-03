package com.limnstage.laundry.controller;

import com.limnstage.laundry.DTO.OrderMapper;
import com.limnstage.laundry.DTO.OrderResponseDTO;
import com.limnstage.laundry.Repository.OrdersRepository;
import com.limnstage.laundry.model.Orders;
import com.limnstage.laundry.security.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/v1/orders/{Status}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable String Status) {

        List<Orders> orders = ordersRepository.findByDeliveryStatus(Status);
        List<OrderResponseDTO> response =
                orders.stream()
                        .map(OrderMapper::toDto)
                        .toList();
        if (orders.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT) // 204
                    .build();
        }
        return ResponseEntity.ok(response); // 200
    }

    @PatchMapping("/v1/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String status = body.get("status");

        if (status == null || status.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Status is required"));
        }

        ordersService.updateStatus(id, status);

        return ResponseEntity.ok(
                Map.of("message", "Order status updated successfully")
        );
    }
}
