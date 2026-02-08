package com.limnstage.laundry.Repository;

import com.limnstage.laundry.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByEmail(String email);

    List<Orders> findByDeliveryStatus(String deliveryStatus);

    // ✅ get all orders by user uid
    List<Orders> findByUid(Integer uid);
}