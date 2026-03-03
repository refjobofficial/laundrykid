package com.limnstage.laundry.Repository;

import com.limnstage.laundry.model.DeliveryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DeliveryUserRepository extends JpaRepository<DeliveryUser, Long> {

    Optional<DeliveryUser> findByDeliveryEmail(String deliveryEmail);

}