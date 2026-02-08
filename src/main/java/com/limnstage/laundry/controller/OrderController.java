package com.limnstage.laundry.controller;

import com.limnstage.laundry.DTO.OrderMapper;
import com.limnstage.laundry.DTO.OrderResponseDTO;
import com.limnstage.laundry.Repository.OrdersRepository;
import com.limnstage.laundry.model.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")

public class OrderController {

    @Autowired
    private  OrdersRepository ordersRepository;

    @PostMapping("/v1/new-order")
    public ResponseEntity<?> createOrder(@RequestBody Orders order) {
        try {
            System.out.println("ORDER RECEIVED = " + order);

            Orders savedOrder = ordersRepository.save(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 THIS WILL TELL THE TRUTH
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Map.of(
                                    "status", 400,
                                    "message", e.getMessage()
                            )
                    );
        }


    }

    @GetMapping("/v1/all-orders/{uid}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable String uid) {

        List<Orders> orders = ordersRepository.findByUid(Integer.parseInt(uid));
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

    @DeleteMapping("/v1/delete/{orderId}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long orderId) {

        if (!ordersRepository.existsById(orderId)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND) // 404
                    .body(
                            Map.of(
                                    "status", 404,
                                    "message", "Order not found"
                            )
                    );
        }

        ordersRepository.deleteById(orderId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // 204
                .build();
    }


}
