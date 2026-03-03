package com.limnstage.laundry.Repository;

import com.limnstage.laundry.model.Orders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByEmail(String email);

    List<Orders> findByDeliveryStatus(String deliveryStatus);

    // ✅ get all orders by user uid
    List<Orders> findByUidOrderByIdDesc(Integer uid);
    // ✅ Update only delivery status by ID
    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.deliveryStatus = :status WHERE o.id = :id")
    int updateDeliveryStatusById(@Param("id") Long id,
                                 @Param("status") String status);
}