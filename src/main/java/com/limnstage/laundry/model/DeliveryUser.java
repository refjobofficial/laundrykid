package com.limnstage.laundry.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_user", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_user_seq")
    @SequenceGenerator(
            name = "delivery_user_seq",
            sequenceName = "delivery_user_id_seq",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "delivery_email", nullable = false)
    private String deliveryEmail;

    @Column(name = "phone_no", nullable = false)
    private Long phoneNo;

    @Column(name = "name", nullable = false)
    private String name;
}